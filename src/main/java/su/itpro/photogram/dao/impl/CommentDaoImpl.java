package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.DateConverter.fromTimestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.CommentDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.entity.Comment;

public class CommentDaoImpl implements CommentDao {

  private static final String ID = "id";
  private static final String ACCOUNT_ID = "account_id";
  private static final String POST_ID = "post_id";
  private static final String CREATE_TIME = "create_time";
  private static final String CHANGE_TIME = "change_time";
  private static final String IS_DELETED = "is_deleted";
  private static final String USERNAME = "username";
  private static final String MESSAGE = "message";


  private static final String FIND_ALL_BY_POST_SQL = """
      SELECT c.id, account_id, post_id, create_time, change_time, is_deleted, a.username, message
      FROM comment c
          JOIN account a ON a.id = c.account_id
      WHERE post_id = ?
      ORDER BY create_time
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO comment (account_id, post_id, message)
      VALUES (?, ?, ?)
      ;
      """;

  private static final String DELETE_SQL = """
      UPDATE comment
      SET is_deleted = true, change_time = now()
      WHERE id = ?
      ;
      """;

  private static final String DELETE_ALL_BY_POST_ID_SQL = """
      DELETE FROM comment
      WHERE post_id = ?
      ;
      """;

  private static final CommentDao INSTANCE = new CommentDaoImpl();


  private CommentDaoImpl() {
  }

  public static CommentDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Comment> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<Comment> findAll() {
    return null;
  }

  @Override
  public List<Comment> findAllByPostId(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_ALL_BY_POST_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      List<Comment> comments = new ArrayList<>();
      while (resultSet.next()) {
        comments.add(parseMessage(resultSet));
      }
      return comments;
    } catch (SQLException e) {
      throw new DaoException("Error findAllByPostId Comment", e.getMessage());
    }
  }

  @Override
  public Comment save(Comment comment) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      prepared.setObject(1, comment.getAccountId());
      prepared.setObject(2, comment.getPostId());
      prepared.setString(3, comment.getMessage());
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();
      if (generatedKeys.next()) {
        comment.setId(generatedKeys.getObject(1, UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Comment", e.getMessage());
    }
    return comment;
  }

  @Override
  public void update(Comment comment) {
    // не используется
  }

  @Override
  public void deleteByPostId(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(DELETE_ALL_BY_POST_ID_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error deleteByPostId Comment", e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(DELETE_SQL)) {
      prepared.setObject(1, id);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error delete Comment", e.getMessage());
    }
  }

  private Comment parseMessage(ResultSet resultSet) throws SQLException {
    return new Comment(
        resultSet.getObject(ID, UUID.class),
        resultSet.getObject(ACCOUNT_ID, UUID.class),
        resultSet.getObject(POST_ID, UUID.class),
        fromTimestamp(resultSet.getTimestamp(CREATE_TIME)).orElse(null),
        fromTimestamp(resultSet.getTimestamp(CHANGE_TIME)).orElse(null),
        resultSet.getBoolean(IS_DELETED),
        resultSet.getString(USERNAME),
        resultSet.getString(MESSAGE)
    );
  }
}
