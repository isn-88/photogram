package su.itpro.photogram.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.PersonDao;
import su.itpro.photogram.entity.Contact;
import su.itpro.photogram.entity.Icon;
import su.itpro.photogram.entity.Person;
import su.itpro.photogram.exception.DaoException;

public class PersonDaoImpl implements PersonDao {

  private static final PersonDao INSTANCE = new PersonDaoImpl();

  private static final String FIND_BY_ID_SQL = """
      SELECT id, first_name, last_name, middle_name, birth_date, contact_id, icon_id
      FROM person
      WHERE id = ?
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO person (first_name, last_name, middle_name, contact_id, birth_date, icon_id)
      VALUES (?, ?, ?, ?, ?, ?)
      ;
      """;

  private PersonDaoImpl() {
  }

  public static PersonDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Person> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public Optional<Person> findById(UUID id, Connection connection) {
    try (var prepared = connection.prepareStatement(FIND_BY_ID_SQL)) {
      prepared.setObject(1, id);

      prepared.executeQuery();

      Person person = null;
      var resultSet = prepared.getResultSet();
      if (resultSet.next()) {
        person = parsePerson(resultSet);
      }
      return Optional.ofNullable(person);
    } catch (SQLException e) {
      throw new DaoException("Error findById Person", e.getMessage());
    }
  }

  @Override
  public List<Person> findAll() {
    return null;
  }

  @Override
  public List<Person> findAll(Connection connection) {
    return null;
  }

  @Override
  public void save(Person entity) {

  }

  @Override
  public void save(Person person, Connection connection) {
    try (var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      UUID contactId = (person.getContact() != null) ? person.getContact().getId() : null;
      UUID iconId = (person.getIcon() != null) ? person.getIcon().getId() : null;
      prepared.setString(1, person.getFirstName());
      prepared.setString(2, person.getLastName());
      prepared.setString(3, person.getMiddleName());
      prepared.setObject(4, contactId);
      prepared.setDate(5, Date.valueOf(person.getBirthdate()));
      prepared.setObject(6, iconId);
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();
      if (generatedKeys.next()) {
        person.setId(generatedKeys.getObject("id", UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Person", e.getMessage());
    }
  }

  @Override
  public void update(Person entity) {

  }

  @Override
  public void update(Person entity, Connection connection) {

  }

  @Override
  public boolean delete(UUID id) {
    return false;
  }

  @Override
  public boolean delete(UUID id, Connection connection) {
    return false;
  }

  private Person parsePerson(ResultSet resultSet) throws SQLException {
    Person person = new Person();
    person.setId(resultSet.getObject("id", UUID.class));
    person.setFirstName(resultSet.getString("first_name"));
    person.setLastName(resultSet.getString("last_name"));
    person.setMiddleName(resultSet.getString("middle_name"));
    person.setBirthdate(resultSet.getDate("birth_date").toLocalDate());
    person.setContact(new Contact(resultSet.getObject("contact_id", UUID.class)));
    person.setIcon(new Icon(resultSet.getObject("icon_id", UUID.class)));
    return person;
  }
}
