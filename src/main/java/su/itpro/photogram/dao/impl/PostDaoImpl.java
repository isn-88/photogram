package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.DateConverter.fromTimestamp;

import java.sql.Array;
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
import su.itpro.photogram.model.enums.PostStatus;

public class PostDaoImpl implements PostDao {

  private static final String ID = "id";
  private static final String ACCOUNT_ID = "account_id";
  private static final String STATUS = "status";
  private static final String CREATE_DATE = "create_date";
  private static final String DESCRIPTION = "description";

  private static final PostDao INSTANCE = new PostDaoImpl();

  private static final String FIND_BY_ID_SQL = """
      SELECT p.id, account_id, status, create_date, description
      FROM post p
          JOIN post_status ps ON ps.id = p.status_id
      WHERE p.id = ?
      ;
      """;

  private static final String FIND_BY_TOP_SQL = """
      SELECT p.id, account_id, status, create_date, description
      FROM post p
          JOIN post_status ps ON ps.id = p.status_id
      WHERE account_id = ? AND status = ANY (?)
      ORDER BY create_date DESC
      LIMIT ?
      ;
      """;

  private static final String COUNT_POSTS_SQL = """
      SELECT count(id)
      FROM post
      WHERE account_id = ? AND
            status_id = (SELECT id FROM post_status WHERE status = 'PUBLIC')
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO post (account_id, status_id, description)
      VALUES (?, (SELECT id FROM post_status WHERE status = ?), ?)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE post
      SET status_id = (SELECT id FROM post_status WHERE status = ?),
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
  public List<Post> findTopByAccountIdAndLimit(UUID accountId,
                                               List<PostStatus> findStatuses, int limit) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_TOP_SQL)) {
      prepared.setObject(1, accountId);
      List<String> listStatuses = findStatuses.stream().map(PostStatus::name).toList();
      String[] arrayStatuses = listStatuses.toArray(new String[0]);
      Array statuses = connection.createArrayOf("varchar", arrayStatuses);
      prepared.setArray(2, statuses);
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
      prepared.setString(2, post.getStatus().name());
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
  public boolean update(Post post) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, post.getStatus().name());
      prepared.setString(2, post.getDescription());
      prepared.setObject(3, post.getId());

      return prepared.executeUpdate() > 0;
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
    post.setStatus(PostStatus.valueOf(resultSet.getString(STATUS)));
    fromTimestamp(resultSet.getTimestamp(CREATE_DATE)).ifPresent(post::setCreateDate);
    post.setDescription(resultSet.getString(DESCRIPTION));
    return post;
  }
}
