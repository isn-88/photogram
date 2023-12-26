package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.DateConverter.fromTimestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import su.itpro.photogram.dao.SubscribeDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.dto.AdviceDto;
import su.itpro.photogram.model.dto.SubscribeUserDto;
import su.itpro.photogram.model.entity.Subscribe;

public class SubscribeDaoImpl implements SubscribeDao {

  private static final String ACCOUNT_ID = "account_id";
  private static final String SUBSCRIBE_ID = "subscribe_id";
  private static final String SUBSCRIBE_TIME = "subscribe_time";
  private static final String USERNAME = "username";
  private static final String COUNT = "count";


  private static final String FIND_TOP_100_BY_COUNT_POST_SQL = """
      SELECT account_id, a.username, count(p.id) AS count
      FROM post p
          JOIN post_status ps ON ps.id = p.status_id
          JOIN account a ON a.id = p.account_id
          JOIN status ast ON ast.id = a.status_id
      WHERE ast.status = 'ACTIVE' AND ps.status = 'PUBLIC'
      GROUP BY account_id, a.username
      ORDER BY count DESC
      LIMIT 100
      ;
      """;

  private static final String FIND_TOP_100_BY_COUNT_SUBSCRIBES_SQL = """
      SELECT account_id, username, count(subscribe_id) AS count
      FROM subscribe
          JOIN account a ON a.id = subscribe.account_id
          JOIN status s ON s.id = a.status_id
      WHERE status = 'ACTIVE'
      GROUP BY account_id, username
      ORDER BY count DESC
      LIMIT 100;
      ;
      """;

  private static final String FIND_ALL_SUBSCRIBE_SQL = """
      SELECT account_id, subscribe_id, subscribe_time, username
      FROM subscribe
          JOIN account a ON a.id = subscribe_id
      WHERE account_id = ?
      ;
      """;

  private static final String FIND_ALL_SUBSCRIBERS_SQL = """
      SELECT account_id, subscribe_id, subscribe_time, username
       FROM subscribe
          JOIN account a ON a.id = account_id
          JOIN status s ON s.id = a.status_id
       WHERE status = 'ACTIVE' AND subscribe_id = ?
       ;
      """;

  private static final String COUNT_SUBSCRIBE_SQL = """
      SELECT count(subscribe_id) AS count
      FROM subscribe
          JOIN account a ON a.id = subscribe_id
          JOIN status s ON s.id = a.status_id
      WHERE account_id = ? AND status = 'ACTIVE'
      ;
      """;

  private static final String COUNT_SUBSCRIBERS_SQL = """
      SELECT count(account_id) AS count
       FROM subscribe
          JOIN account a ON a.id = account_id
          JOIN status s ON s.id = a.status_id
       WHERE status = 'ACTIVE' AND subscribe_id = ?
       ;
      """;

  private static final String READY_TO_SUBSCRIBE_SQL = """
      SELECT account_id, subscribe_id
      FROM subscribe
      WHERE account_id = ? AND subscribe_id = ?
      ;
      """;

  private static final String SUBSCRIBE_SQL = """
      INSERT INTO subscribe (account_id, subscribe_id)
      VALUES (?, ?)
      ;
      """;

  private static final String UNSUBSCRIBE_SQL = """
      DELETE FROM subscribe
      WHERE account_id = ? AND subscribe_id = ?
      ;
      """;


  private static final SubscribeDao INSTANCE = new SubscribeDaoImpl();


  private SubscribeDaoImpl() {
  }

  public static SubscribeDao getInstance() {
    return INSTANCE;
  }

  public List<AdviceDto> findTop100AdviceByPost() {
    try (var connection = DataSource.getConnection();
        var statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(FIND_TOP_100_BY_COUNT_POST_SQL);

      List<AdviceDto> adviceDtoList = new ArrayList<>();
      while (resultSet.next()) {
        adviceDtoList.add(parseAdvice(resultSet));
      }

      return adviceDtoList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<AdviceDto> findTop100AdviceBySubscribes() {
    try (var connection = DataSource.getConnection();
        var statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(FIND_TOP_100_BY_COUNT_SUBSCRIBES_SQL);

      List<AdviceDto> adviceDtoList = new ArrayList<>();
      while (resultSet.next()) {
        adviceDtoList.add(parseAdvice(resultSet));
      }

      return adviceDtoList;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<SubscribeUserDto> findAllSubscribe(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_ALL_SUBSCRIBE_SQL)) {
      prepared.setObject(1, accountId);
      var resultSet = prepared.executeQuery();

      List<SubscribeUserDto> subscribes = new ArrayList<>();
      while (resultSet.next()) {
        subscribes.add(parseSubscribeDto(resultSet));
      }
      return subscribes;
    } catch (SQLException e) {
      throw new DaoException("Error findById Subscribe", e.getMessage());
    }
  }

  @Override
  public List<SubscribeUserDto> findAllSubscribers(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_ALL_SUBSCRIBERS_SQL)) {
      prepared.setObject(1, accountId);

      var resultSet = prepared.executeQuery();

      List<SubscribeUserDto> subscribes = new ArrayList<>();
      while (resultSet.next()) {
        subscribes.add(parseSubscribeDto(resultSet));
      }
      return subscribes;
    } catch (SQLException e) {
      throw new DaoException("Error findById Subscribe", e.getMessage());
    }
  }

  @Override
  public int subscribeCount(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(COUNT_SUBSCRIBE_SQL)) {
      prepared.setObject(1, accountId);

      var resultSet = prepared.executeQuery();

      if (resultSet.next()) {
        return resultSet.getInt(COUNT);
      }
      return 0;
    } catch (SQLException e) {
      throw new DaoException("Error subscribeCount Subscribe", e.getMessage());
    }
  }

  @Override
  public int subscribersCount(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(COUNT_SUBSCRIBERS_SQL)) {
      prepared.setObject(1, accountId);

      var resultSet = prepared.executeQuery();

      if (resultSet.next()) {
        return resultSet.getInt(COUNT);
      }
      return 0;
    } catch (SQLException e) {
      throw new DaoException("Error subscribersCount Subscribe", e.getMessage());
    }
  }

  @Override
  public boolean readyToSubscribe(UUID accountId, UUID subscribeId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(READY_TO_SUBSCRIBE_SQL)) {
      prepared.setObject(1, accountId);
      prepared.setObject(2, subscribeId);

      var resultSet = prepared.executeQuery();
      return !resultSet.next();
    } catch (SQLException e) {
      throw new DaoException("Error readyToSubscribe Subscribe", e.getMessage());
    }
  }

  @Override
  public void subscribe(Subscribe subscribe) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SUBSCRIBE_SQL)) {
      prepared.setObject(1, subscribe.getAccountId());
      prepared.setObject(2, subscribe.getSubscribeId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error subscribe", e.getMessage());
    }
  }

  @Override
  public void unsubscribe(Subscribe subscribe) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UNSUBSCRIBE_SQL)) {
      prepared.setObject(1, subscribe.getAccountId());
      prepared.setObject(2, subscribe.getSubscribeId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error unsubscribe", e.getMessage());
    }
  }

  private SubscribeUserDto parseSubscribeDto(ResultSet resultSet) throws SQLException {
    return new SubscribeUserDto(
        resultSet.getObject(ACCOUNT_ID, UUID.class),
        resultSet.getObject(SUBSCRIBE_ID, UUID.class),
        resultSet.getString(USERNAME),
        fromTimestamp(resultSet.getTimestamp(SUBSCRIBE_TIME)).orElse(null)
    );
  }

  private AdviceDto parseAdvice(ResultSet resultSet) throws SQLException {
    return new AdviceDto(resultSet.getObject(ACCOUNT_ID, UUID.class),
                         resultSet.getString(USERNAME));
  }
}
