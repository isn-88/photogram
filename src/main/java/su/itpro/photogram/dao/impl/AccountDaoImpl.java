package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.DateConverter.fromTimestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.exception.DaoException;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.model.enums.Status;

public class AccountDaoImpl implements AccountDao {

  private static final String ID = "id";
  private static final String PHONE = "phone";
  private static final String EMAIL = "email";
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";
  private static final String ROLE = "role";
  private static final String STATUS = "status";
  private static final String IS_VERIFIED_PHONE = "is_verified_phone";
  private static final String IS_VERIFIED_EMAIL = "is_verified_email";
  private static final String CREATE_DATE = "create_date";

  private static final AccountDao INSTANCE = new AccountDaoImpl();

  private static final String FIND_BY_USERNAME_SQL = """
      SELECT  a.id, phone, email, username, password, role, status,
              is_verified_phone, is_verified_email, create_date
      FROM account a
        JOIN role r ON r.id = a.role_id
        JOIN status s ON s.id = a.status_id
      WHERE username = ?
      ;
      """;

  private static final String FIND_BY_EMAIL_SQL = """
      SELECT  a.id, phone, email, username, password, role, status,
              is_verified_phone, is_verified_email, create_date
      FROM account a
        JOIN role r ON r.id = a.role_id
        JOIN status s ON s.id = a.status_id
      WHERE email = ?
      ;
      """;

  private static final String FIND_BY_PHONE_SQL = """
      SELECT  a.id, phone, email, username, password, role, status,
              is_verified_phone, is_verified_email, create_date
      FROM account a
        JOIN role r ON r.id = a.role_id
        JOIN status s ON s.id = a.status_id
      WHERE phone = ?
      ;
      """;

  private static final String CHANGE_PASSWORD_SQL = """
      UPDATE account
      SET password = ?
      WHERE id = ? AND password = ?
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO account (
          phone,
          email,
          username,
          password,
          role_id,
          status_id
      )
      VALUES (?, ?, ?, ?,
          (SELECT id FROM role WHERE role = ?),
          (SELECT id FROM status WHERE status = ?))
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE account
      SET phone = ?,
          email = ?,
          username = ?
      WHERE id = ?
      ;
      """;

  private static final String DELETE_SQL = """
      UPDATE account
      SET status_id = (SELECT id FROM status WHERE status = ?)
      WHERE id = ?
      ;
      """;

  private AccountDaoImpl() {
  }

  public static AccountDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Account> findByUsername(String username) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_USERNAME_SQL)) {
      prepared.setString(1, username);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      Account account = null;
      if (resultSet.next()) {
        account = parseAccount(resultSet);
      }
      return Optional.ofNullable(account);
    } catch (SQLException e) {
      throw new DaoException("Error findByUsername Account", e.getMessage());
    }
  }

  @Override
  public Optional<Account> findByEmail(String email) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
      prepared.setString(1, email);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      Account account = null;
      if (resultSet.next()) {
        account = parseAccount(resultSet);
      }
      return Optional.ofNullable(account);
    } catch (SQLException e) {
      throw new DaoException("Error findByEmail Account", e.getMessage());
    }
  }

  @Override
  public Optional<Account> findByPhone(String phone) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_PHONE_SQL)) {
      prepared.setString(1, phone);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      Account account = null;
      if (resultSet.next()) {
        account = parseAccount(resultSet);
      }
      return Optional.ofNullable(account);
    } catch (SQLException e) {
      throw new DaoException("Error findByPhone Account", e.getMessage());
    }
  }

  @Override
  public void changePassword(Account account, String password) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(CHANGE_PASSWORD_SQL)) {
      prepared.setString(1, password);
      prepared.setObject(2, account.getId());
      prepared.setString(3, account.getPassword());

      int updated = prepared.executeUpdate();
      if (updated == 0) {
        throw new DaoException(
            "Error update password",
            "Account not found or password has already been changed"
        );
      }
    } catch (SQLException e) {
      throw new DaoException("Error change Password", e.getMessage());
    }
  }

  @Override
  public Optional<Account> findById(UUID id) {
    // TODO add implementation
    return Optional.empty();
  }

  @Override
  public List<Account> findAll() {
    // TODO add implementation
    return new ArrayList<>();
  }

  @Override
  public Account save(Account account) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      prepared.setString(1, account.getPhone());
      prepared.setString(2, account.getEmail());
      prepared.setString(3, account.getUsername());
      prepared.setString(4, account.getPassword());
      prepared.setString(5, account.getRole().name());
      prepared.setString(6, account.getStatus().name());
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();

      if (generatedKeys.next()) {
        account.setId(generatedKeys.getObject(1, UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Account", e.getMessage());
    }
    return account;
  }

  @Override
  public void update(Account account) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, account.getPhone());
      prepared.setString(2, account.getEmail());
      prepared.setString(3, account.getUsername());
      prepared.setObject(4, account.getId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Account", e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, Status.DELETED.name());
      prepared.setObject(2, id);

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Account", e.getMessage());
    }
  }

  private Account parseAccount(ResultSet resultSet) throws SQLException {
    Account account = new Account(resultSet.getObject(ID, UUID.class));
    account.setPhone(resultSet.getString(PHONE));
    account.setEmail(resultSet.getString(EMAIL));
    account.setUsername(resultSet.getString(USERNAME));
    account.setPassword(resultSet.getString(PASSWORD));
    account.setRole(Role.valueOf(resultSet.getString(ROLE)));
    account.setStatus(Status.valueOf(resultSet.getString(STATUS)));
    account.setVerifiedPhone(resultSet.getBoolean(IS_VERIFIED_PHONE));
    account.setVerifiedEmail(resultSet.getBoolean(IS_VERIFIED_EMAIL));
    fromTimestamp(resultSet.getTimestamp(CREATE_DATE)).ifPresent(account::setCreateDate);
    return account;
  }

}
