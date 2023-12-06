package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.DateConverter.fromTimestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.dao.exception.DaoException;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.model.entity.Post;

public class PostDaoImpl implements PostDao {

  private static final String ID = "id";
  private static final String ACCOUNT_ID = "account_id";
  private static final String CREATE_DATE = "create_date";
  private static final String DESCRIPTION = "description";

  private static final PostDao INSTANCE = new PostDaoImpl();

  private static final String SAVE_SQL = """
      INSERT INTO post (account_id, description)
      VALUES (?, ?)
      ;
      """;

  private static final String FIND_BY_TOP_SQL = """
      SELECT id, account_id, create_date, description
      FROM post
      WHERE account_id = ? AND is_active = true
      ORDER BY create_date DESC
      LIMIT ?
      ;
      """;

  private PostDaoImpl() {
  }

  public static PostDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Post> findById(UUID id) {
    // TODO add implementation
    return Optional.empty();
  }

  @Override
  public List<Post> findTopByAccountIdAndLimit(UUID accountId, int limit) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_TOP_SQL)) {
      prepared.setObject(1, accountId);
      prepared.setInt(2, limit);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      List<Post> posts = new ArrayList<>();
      while (resultSet.next()) {
        posts.add(parsePost(resultSet));
      }
      return posts;
    } catch (SQLException e) {
      throw new DaoException("Error findByTop Post", e.getMessage());
    }
  }

  @Override
  public List<Post> findAll() {
    // TODO add implementation
    return new ArrayList<>();
  }

  @Override
  public void save(Post post) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      prepared.setObject(1, post.getAccountId());
      prepared.setString(2, post.getDescription());
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();
      if (generatedKeys.next()) {
        post.setId(generatedKeys.getObject(1, UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Post", e.getMessage());
    }
  }

  @Override
  public void update(Post post) {
    // TODO add implementation
  }

  @Override
  public boolean delete(UUID id) {
    // TODO add implementation
    return false;
  }

  private Post parsePost(ResultSet resultSet) throws SQLException {
    Post post = new Post(resultSet.getObject(ID, UUID.class));
    post.setAccountId(resultSet.getObject(ACCOUNT_ID, UUID.class));
    fromTimestamp(resultSet.getTimestamp(CREATE_DATE)).ifPresent(post::setCreateDate);
    post.setDescription(resultSet.getString(DESCRIPTION));
    return post;
  }
}
