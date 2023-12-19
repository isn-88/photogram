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
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.entity.Post;

public class PostDaoImpl implements PostDao {

  private static final String ID = "id";
  private static final String ACCOUNT_ID = "account_id";
  private static final String IS_ACTIVE = "is_active";
  private static final String CREATE_DATE = "create_date";
  private static final String DESCRIPTION = "description";

  private static final PostDao INSTANCE = new PostDaoImpl();

  private static final String FIND_BY_ID_SQL = """
      SELECT id, account_id, is_active, create_date, description
      FROM post
      WHERE id = ?
      ;
      """;

  private static final String FIND_BY_TOP_SQL = """
      SELECT id, account_id, is_active, create_date, description
      FROM post
      WHERE account_id = ? AND is_active IN (true, ?)
      ORDER BY create_date DESC
      LIMIT ?
      ;
      """;

  private static final String COUNT_POSTS_SQL = """
      SELECT count(id)
      FROM post
      WHERE account_id = ? AND is_active = true
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO post (account_id, is_active, description)
      VALUES (?, ?, ?)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE post
      SET is_active = ?,
          description = ?
      WHERE id = ?
      ;
      """;

  private static final String DELETE_SQL = """
      DELETE FROM post
      WHERE id = ?
      ;
      """;

  private PostDaoImpl() {
  }

  public static PostDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Post> findById(UUID id) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_ID_SQL)) {
      prepared.setObject(1, id);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      Post post = null;
      if (resultSet.next()) {
        post = parsePost(resultSet);
      }
      return Optional.ofNullable(post);
    } catch (SQLException e) {
      throw new DaoException("Error findById Post", e.getMessage());
    }
  }

  @Override
  public List<Post> findTopByAccountIdAndLimit(UUID accountId, boolean onlyIsActive, int limit) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_TOP_SQL)) {
      prepared.setObject(1, accountId);
      prepared.setBoolean(2, onlyIsActive);
      prepared.setInt(3, limit);

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
  public int countPosts(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(COUNT_POSTS_SQL)) {
      prepared.setObject(1, accountId);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
      return 0;
    } catch (SQLException e) {
      throw new DaoException("Error findByTop Post", e.getMessage());
    }
  }

  @Override
  public Post save(Post post) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      prepared.setObject(1, post.getAccountId());
      prepared.setBoolean(2, post.getActive());
      prepared.setString(3, post.getDescription());
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();
      if (generatedKeys.next()) {
        post.setId(generatedKeys.getObject(1, UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Post", e.getMessage());
    }
    return post;
  }

  @Override
  public void update(Post post) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setBoolean(1, post.getActive());
      prepared.setString(2, post.getDescription());
      prepared.setObject(3, post.getId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Post", e.getMessage());
    }
  }

  @Override
  public void delete(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(DELETE_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error delete Post", e.getMessage());
    }
  }

  private Post parsePost(ResultSet resultSet) throws SQLException {
    Post post = new Post(resultSet.getObject(ID, UUID.class));
    post.setAccountId(resultSet.getObject(ACCOUNT_ID, UUID.class));
    post.setActive(resultSet.getBoolean(IS_ACTIVE));
    fromTimestamp(resultSet.getTimestamp(CREATE_DATE)).ifPresent(post::setCreateDate);
    post.setDescription(resultSet.getString(DESCRIPTION));
    return post;
  }
}
