package su.itpro.photogram.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.entity.Person;

public interface PersonDao extends BaseDao<UUID, Person> {

  Optional<Person> findById(UUID id, Connection connection);

  List<Person> findAll(Connection connection);

  void save(Person person, Connection connection);

  void update(Person person, Connection connection);

  boolean delete(UUID id, Connection connection);

}
