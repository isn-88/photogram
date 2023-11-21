package su.itpro.photogram.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ContactDao;
import su.itpro.photogram.entity.Contact;
import su.itpro.photogram.exception.DaoException;
import su.itpro.photogram.util.ContactConverter;
import su.itpro.photogram.util.DataSource;

public class ContactDaoImpl implements ContactDao {

  private static final ContactDaoImpl INSTANCE = new ContactDaoImpl();

  private static final String FIND_BY_ID_SQL = """
      SELECT id, email, phone, contacts
      FROM contact
      WHERE id = ?
      ;
      """;
  private static final String FIND_ALL_SQL = """
      SELECT id, email, phone, contacts
      FROM contact
      ;
      """;
  private static final String SAVE_SQL = """
      INSERT INTO contact (email, phone, contacts)
      VALUES (?, ?, ?::JSON)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE contact
      SET email = ?,
          phone = ?,
          contacts = ?::JSON
      WHERE id = ?
      ;
      """;

  private static final String DELETE_SQL = """
      DELETE
      FROM contact
      WHERE id = ?
      ;
      """;


  private ContactDaoImpl() {
  }

  public static ContactDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Contact> findById(UUID id) {
    try (var connection = DataSource.getConnection()) {
      return findById(id, connection);
    } catch (SQLException e) {
      throw new DaoException("Error findById Contact", e.getMessage());
    }
  }

  @Override
  public Optional<Contact> findById(UUID id, Connection connection) {
    try (var prepared = connection.prepareStatement(FIND_BY_ID_SQL)) {
      prepared.setObject(1, id);
      prepared.executeQuery();

      var resultSet = prepared.getResultSet();

      Contact contact = null;
      if (resultSet.next()) {
        contact = parseContact(resultSet);
      }
      return Optional.ofNullable(contact);
    } catch (SQLException e) {
      throw new DaoException("Error findById Contact", e.getMessage());
    }
  }

  @Override
  public List<Contact> findAll() {
    try (var connection = DataSource.getConnection()) {
      return findAll(connection);
    } catch (SQLException e) {
      throw new DaoException("Error findAll Contact", e.getMessage());
    }
  }

  @Override
  public List<Contact> findAll(Connection connection) {
    try (var prepared = connection.prepareStatement(FIND_ALL_SQL)) {
      prepared.executeQuery();

      var resultSet = prepared.getResultSet();
      List<Contact> contacts = new ArrayList<>();
      while (resultSet.next()) {
        contacts.add(parseContact(resultSet));
      }

      return contacts;
    } catch (SQLException e) {
      throw new DaoException("Error findAll Contact", e.getMessage());
    }
  }

  @Override
  public void save(Contact contact) {
    try (var connection = DataSource.getConnection()) {
      save(contact, connection);
    } catch (SQLException e) {
      throw new DaoException("Error save Contact", e.getMessage());
    }
  }

  @Override
  public void save(Contact contact, Connection connection) {
    try (var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      prepared.setString(1, contact.getEmail());
      prepared.setString(2, contact.getPhone());
      prepared.setObject(3, ContactConverter.toJson(contact.getContacts()));
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();

      if (generatedKeys.next()) {
        contact.setId(generatedKeys.getObject(1, UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Contact", e.getMessage());
    }
  }


  @Override
  public void update(Contact contact) {
    try (var connection = DataSource.getConnection()) {
      update(contact, connection);
    } catch (SQLException e) {
      throw new DaoException("Error update Contact", e.getMessage());
    }
  }

  @Override
  public void update(Contact contact, Connection connection) {
    try (var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, contact.getEmail());
      prepared.setString(2, contact.getPhone());
      prepared.setString(3, ContactConverter.toJson(contact.getContacts()));
      prepared.setObject(4, contact.getId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Contact", e.getMessage());
    }
  }

  @Override
  public boolean delete(UUID id) {
    try (var connection = DataSource.getConnection()) {
      return delete(id, connection);
    } catch (SQLException e) {
      throw new DaoException("Error delete Contact", e.getMessage());
    }
  }

  @Override
  public boolean delete(UUID id, Connection connection) {
    try (var prepared = connection.prepareStatement(DELETE_SQL)) {
      prepared.setObject(1, id);

      var deleted = prepared.executeUpdate();
      return deleted > 0;
    } catch (SQLException e) {
      throw new DaoException("Error delete Contact", e.getMessage());
    }
  }

  private static Contact parseContact(ResultSet resultSet) throws SQLException {
    Contact contact = new Contact();
    contact.setId(resultSet.getObject("id", UUID.class));
    contact.setEmail(resultSet.getString("email"));
    contact.setPhone(resultSet.getString("phone"));
    contact.setContacts(ContactConverter.toMap(resultSet.getString("contacts")));
    return contact;
  }
}
