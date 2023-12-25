package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.DateConverter.fromTimestamp;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import su.itpro.photogram.dao.ComplaintDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.dto.ComplainFindDto;
import su.itpro.photogram.model.entity.Complaint;
import su.itpro.photogram.model.enums.ComplainStatus;

public class ComplaintDaoImpl implements ComplaintDao {

  private static final String ACCOUNT_ID = "account_id";
  private static final String USERNAME = "username";
  private static final String POST_ID = "post_id";
  private static final String STATUS = "status";
  private static final String CREATE_TIME = "create_time";
  private static final String CLOSE_TIME = "close_time";
  private static final String MESSAGE = "message";



  private static final String FIND_ALL_BY_SQL = """
      SELECT account_id, username, post_id, status, create_time, close_time, message
      FROM complaint c
          JOIN complain_status cs ON c.status_id = cs.id
          JOIN account a ON c.account_id = a.id
      WHERE cs.status = ANY (?)
      LIMIT 100
      ;
      """;

  private static final String EXIST_COMPLAINT_SQL = """
      SELECT account_id, post_id, status
      FROM complaint
          JOIN complain_status s on s.id = complaint.status_id
      WHERE account_id = ? AND post_id = ? AND status = 'OPEN'
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO complaint (account_id, post_id, message, status_id)
      VALUES (?, ?, ?, (SELECT id FROM complain_status WHERE status = 'OPEN'))
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE complaint
      SET status_id = (SELECT id FROM complain_status cs WHERE cs.status = ?),
          close_time = now()
      WHERE account_id = ? AND post_id = ?
      ;
      """;

  private static final String DELETE_SQL = """
      DELETE FROM complaint
      WHERE post_id = ?
      ;
      """;

  private static final ComplaintDao INSTANCE = new ComplaintDaoImpl();


  private ComplaintDaoImpl() {
  }

  public static ComplaintDao getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean exist(UUID accountId, UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(EXIST_COMPLAINT_SQL)) {
      prepared.setObject(1, accountId);
      prepared.setObject(2, postId);

      var resultSet = prepared.executeQuery();
      return resultSet.next();
    } catch (SQLException e) {
      throw new DaoException("Error exist Complaint", e.getMessage());
    }
  }

  @Override
  public List<Complaint> findAllBy(ComplainFindDto findDto) {

    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_ALL_BY_SQL)) {
      List<String> listStatuses = findDto.statuses().stream().map(ComplainStatus::name).toList();
      String[] arrayStatuses = listStatuses.toArray(new String[0]);
      Array statuses = connection.createArrayOf("varchar", arrayStatuses);
      prepared.setArray(1, statuses);

      ResultSet resultSet = prepared.executeQuery();
      List<Complaint> complaints = new ArrayList<>();
      while (resultSet.next()) {
        complaints.add(parseComplaint(resultSet));
      }
      return complaints;
    } catch (SQLException e) {
      throw new DaoException("Error findAllBy Complaint", e.getMessage());
    }
  }

  @Override
  public void save(Complaint complaint) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL)) {
      prepared.setObject(1, complaint.getAccountId());
      prepared.setObject(2, complaint.getPostId());
      prepared.setString(3, complaint.getMessage());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error save Complaint", e.getMessage());
    }
  }

  @Override
  public void update(Complaint complaint) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, complaint.getStatus().name());
      prepared.setObject(2, complaint.getAccountId());
      prepared.setObject(3, complaint.getPostId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Complaint", e.getMessage());
    }
  }

  @Override
  public void deleteByPost(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(DELETE_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error deleteByPost Complaint", e.getMessage());
    }
  }

  private Complaint parseComplaint(ResultSet resultSet) throws SQLException {
    return new Complaint(
        resultSet.getObject(ACCOUNT_ID, UUID.class),
        resultSet.getString(USERNAME),
        resultSet.getObject(POST_ID, UUID.class),
        resultSet.getString(MESSAGE),
        ComplainStatus.valueOf(resultSet.getString(STATUS)),
        fromTimestamp(resultSet.getTimestamp(CREATE_TIME)).orElse(null),
        fromTimestamp(resultSet.getTimestamp(CLOSE_TIME)).orElse(null)
    );
  }

}
