package su.itpro.photogram.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import su.itpro.photogram.dao.ContactDao;
import su.itpro.photogram.dao.PersonDao;
import su.itpro.photogram.dao.impl.ContactDaoImpl;
import su.itpro.photogram.dao.impl.PersonDaoImpl;
import su.itpro.photogram.entity.Person;
import su.itpro.photogram.exception.DaoException;
import su.itpro.photogram.exception.PersonServiceException;
import su.itpro.photogram.util.DataSource;

public class PersonService {

  private static final PersonService INSTANCE = new PersonService();

  private final PersonDao personDao = PersonDaoImpl.getInstance();
  private final ContactDao contactDao = ContactDaoImpl.getInstance();


  private PersonService() {
  }

  public static PersonService getInstance() {
    return INSTANCE;
  }

  public Person findById(UUID id) {
    Person person = new Person(id);
    try (var connection = DataSource.getConnection()) {
      person = personDao.findById(person.getId(), connection)
          .orElseThrow(() -> new PersonServiceException("Person not found"));
      person.setContact(
          contactDao.findById(person.getContact().getId())
              .orElse(person.getContact())
      );
      return person;
    } catch (DaoException | SQLException e) {
      throw new PersonServiceException("Error findById Person");
    }
  }

  public void save(Person person) {
    try (Connection connection = DataSource.getConnection()) {
      connection.setAutoCommit(false);

      if (person.getContact() != null) {
        contactDao.save(person.getContact(), connection);
      }
      personDao.save(person, connection);

      connection.commit();
    } catch (Exception ex) {
      throw new PersonServiceException("Error save Person");
    }
  }

}
