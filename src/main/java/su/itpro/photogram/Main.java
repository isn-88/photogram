package su.itpro.photogram;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import su.itpro.photogram.dao.ContactDao;
import su.itpro.photogram.entity.Contact;
import su.itpro.photogram.util.DataSource;

public class Main {

  private Main() {
  }

  public static void main(String[] args) {

    try (Connection connection = DataSource.getConnection()) {
      //connection.setAutoCommit(false);

      List<Contact> contactList = generate();
      save(contactList);

      UUID id = contactList.get(0).getId();
      findById(id, connection);

      Contact contactUpdate = contactList.get(0);
      update(contactUpdate, connection);

      UUID idToDelete = contactList.get(4).getId();
      delete(idToDelete, connection);

      findAll();

      //connection.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static void save(List<Contact> contactList) {
    contactList.forEach(c -> ContactDao.getInstance().save(c));
    System.out.println("Contacts saved successfully!\n");
  }

  private static void findById(UUID id, Connection connection) {
    Contact contact = ContactDao.getInstance()
        .findById(id, connection)
        .orElse(null);
    System.out.println("FindById [" + id + "]\n" + contact + "\n");
  }

  private static void update(Contact contactUpdate, Connection connection) {
    contactUpdate.setEmail("updated-" + new Random().nextInt(100) + "@email.com");
    ContactDao.getInstance().update(contactUpdate, connection);
    System.out.println("Contacts [" + contactUpdate.getId() + "] updated successfully!\n");
  }

  private static void delete(UUID idToDelete, Connection connection) {
    var deleted = ContactDao.getInstance().delete(idToDelete, connection);
    if (deleted) {
      System.out.println("Contacts [" + idToDelete + "] deleted successfully!\n");
    }
  }

  private static void findAll() {
    List<Contact> allContacts = ContactDao.getInstance().findAll();
    System.out.println("All contacts:");
    allContacts.forEach(System.out::println);
  }

  private static List<Contact> generate() {
    int pref = new Random().nextInt(90) + 10;
    List<Contact> contactList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Map<String, String> contacts = new HashMap<>();
      contacts.put("WhatsApp", "+1 (234) 567-" + pref + "-0" + i);
      contacts.put("Telegram", "@name-" + pref + "-" + i);
      contactList.add(new Contact("user-" + i + "@email" + pref + ".net",
                                    "8 (800) 10-" + pref + "-3" + i,
                                    contacts));
    }
    return contactList;
  }
}