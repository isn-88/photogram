package su.itpro.photogram.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.entity.Contact;

public interface ContactDao extends BaseDao<UUID, Contact> {

  Optional<Contact> findById(UUID id, Connection connection);

  List<Contact> findAll(Connection connection);

  void save(Contact contact, Connection connection);

  void update(Contact contact, Connection connection);

  boolean delete(UUID id, Connection connection);


}
