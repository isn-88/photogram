package su.itpro.photogram.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.IconDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.entity.Icon;

public class IconDaoImpl implements IconDao {

  private static final String ACCOUNT_ID = "account_id";
  private static final String DATA = "data";


  private static final String FIND_BY_ID_SQL = """
      SELECT account_id, data
      FROM icon
      WHERE account_id = ?
      ;
      """;
  private static final String EXISTS_SQL = """
      SELECT account_id
      FROM icon
      WHERE account_id = ?
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO icon (account_id, data)
      VALUES (?, ?)
      """;

  private static final String UPDATE_SQL = """
      UPDATE icon
      SET data = ?
      WHERE account_id = ?
      ;
      """;

  private static final String DELETE_SQL = """
      DELETE FROM icon
      WHERE account_id = ?
      ;
      """;


  private static final IconDao INSTANCE = new IconDaoImpl();

  private IconDaoImpl() {
  }

  public static IconDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Icon> findById(UUID id) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_ID_SQL)) {
      prepared.setObject(1, id);

      prepared.executeQuery();

      Icon icon = null;
      var resultSet = prepared.getResultSet();
      if (resultSet.next()) {
        icon = parseIcon(resultSet);
      }
      return Optional.ofNullable(icon);
    } catch (SQLException e) {
      throw new DaoException("Error findById Person", e.getMessage());
    }
  }

  @Override
  public List<Icon> findAll() {
    return null;
  }

  public boolean exists(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(EXISTS_SQL)) {
      prepared.setObject(1, accountId);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      return resultSet.next();
    } catch (SQLException e) {
      throw new DaoException("Error exists Icon", e.getMessage());
    }
  }

  @Override
  public Icon save(Icon icon) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL)) {
      prepared.setObject(1, icon.getAccountId());
      prepared.setBytes(2, icon.getData());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error save Icon", e.getMessage());
    }
    return icon;
  }

  @Override
  public void update(Icon icon) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setBytes(1, icon.getData());
      prepared.setObject(2, icon.getAccountId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Icon", e.getMessage());
    }
  }

  @Override
  public void delete(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(DELETE_SQL)) {
      prepared.setObject(1, accountId);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error delete Icon", e.getMessage());
    }
  }

  private Icon parseIcon(ResultSet resultSet) throws SQLException {
    Icon icon = new Icon(resultSet.getObject(ACCOUNT_ID, UUID.class));
    icon.setData(resultSet.getBytes(DATA));
    return icon;
  }

}
