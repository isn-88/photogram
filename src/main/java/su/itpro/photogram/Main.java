package su.itpro.photogram;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import su.itpro.photogram.dao.impl.ContactDaoImpl;
import su.itpro.photogram.entity.Contact;
import su.itpro.photogram.entity.Person;
import su.itpro.photogram.service.ContactService;
import su.itpro.photogram.service.PersonService;

public class Main {

  private Main() {
  }

  public static void main(String[] args) {

    Map<String, String> contacts = new HashMap<>();
    String uniqueEmail = "user-" + new Random().nextInt(100) + "@email.com";
    contacts.put("WhatsApp", "+7 (900) 900 80 70");
    Contact contact1 = new Contact(uniqueEmail, null, contacts);
    Person person1 = new Person("First", "Last", "Middle",
                                LocalDate.of(2000, 12, 31),
                                contact1, null);

    var personService = PersonService.getInstance();
    personService.save(person1);
    System.out.printf("Saved person with contact:\n%s\n%s\n\n", person1, contact1);


    Person personFind = personService.findById(person1.getId());
    System.out.printf("Find person by id [%s]\n%s\n%s\n", personFind.getId(),
                      personFind, personFind.getContact());


    var contactService = ContactService.getInstance();
    Contact contactUpd = personFind.getContact();
    contactUpd.addContact("Telegram", "@writeMe");
    contactService.update(contactUpd);

    Contact contactFind = contactService.findById(contactUpd.getId());
    System.out.printf("Contact after update:\n%s\n", contactFind);


  }

  private static void demo(Connection connection) {
    List<Contact> contactList = generate();
    save(contactList, connection);

    UUID id = contactList.get(0).getId();
    findById(id, connection);

    Contact contactUpdate = contactList.get(0);
    update(contactUpdate, connection);

    UUID idToDelete = contactList.get(4).getId();
    delete(idToDelete, connection);

    findAll(connection);
  }

  private static void save(List<Contact> contactList, Connection connection) {
    contactList.forEach(c -> ContactDaoImpl.getInstance().save(c, connection));
    System.out.println("Contacts saved successfully!\n");
  }

  private static void findById(UUID id, Connection connection) {
    Contact contact = ContactDaoImpl.getInstance()
        .findById(id, connection)
        .orElse(null);
    System.out.println("FindById [" + id + "]\n" + contact + "\n");
  }

  private static void update(Contact contactUpdate, Connection connection) {
    contactUpdate.setEmail("updated-" + new Random().nextInt(100) + "@email.com");
    ContactDaoImpl.getInstance().update(contactUpdate, connection);
    System.out.println("Contacts [" + contactUpdate.getId() + "] updated successfully!\n");
  }

  private static void delete(UUID idToDelete, Connection connection) {
    var deleted = ContactDaoImpl.getInstance().delete(idToDelete, connection);
    if (deleted) {
      System.out.println("Contacts [" + idToDelete + "] deleted successfully!\n");
    }
  }

  private static void findAll(Connection connection) {
    List<Contact> allContacts = ContactDaoImpl.getInstance().findAll(connection);
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