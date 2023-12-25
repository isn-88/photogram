package su.itpro.photogram.dao.impl;

import java.sql.SQLException;
import java.util.UUID;
import su.itpro.photogram.dao.LikeDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.Like;

public class LikeDaoImpl implements LikeDao {

  private static final String EXISTS_SQL = """
      SELECT account_id, post_id
      FROM likes
      WHERE account_id = ? AND post_id = ?
      ;
      """;

  private static final String SUM_SCORE_SQL = """
      SELECT sum(score)
      FROM likes
      WHERE post_id = ?
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO likes (account_id, post_id, score)
      VALUES (?, ?, ?)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE likes
      SET score = ?
      WHERE account_id = ? AND post_id = ?
      ;
      """;

  private static final String DELETE_SQL = """
      DELETE FROM likes
      WHERE post_id = ?
      ;
      """;

  private static final LikeDao INSTANCE = new LikeDaoImpl();


  private LikeDaoImpl() {
  }

  public static LikeDao getInstance() {
    return INSTANCE;
  }

  @Override
  public int sumScore(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SUM_SCORE_SQL)) {
      prepared.setObject(1, postId);
      var resultSet = prepared.executeQuery();

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
      return 0;
    } catch (SQLException e) {
      throw new DaoException("Error sumScore Like", e.getMessage());
    }
  }

  @Override
  public boolean exists(Like like) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(EXISTS_SQL)) {
      prepared.setObject(1, like.getAccountId());
      prepared.setObject(2, like.getPostId());
      var resultSet = prepared.executeQuery();

      return resultSet.next();
    } catch (SQLException e) {
      throw new DaoException("Error exists Like", e.getMessage());
    }
  }

  @Override
  public void save(Like like) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL)) {
      prepared.setObject(1, like.getAccountId());
      prepared.setObject(2, like.getPostId());
      prepared.setShort(3, like.getScore());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error save Like", e.getMessage());
    }
  }

  @Override
  public void update(Like like) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setShort(1, like.getScore());
      prepared.setObject(2, like.getAccountId());
      prepared.setObject(3, like.getPostId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Like", e.getMessage());
    }
  }

  @Override
  public void deleteBy(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(DELETE_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error delete Like", e.getMessage());
    }
  }
}
