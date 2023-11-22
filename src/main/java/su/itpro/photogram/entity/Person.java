package su.itpro.photogram.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Person {

  private UUID id;
  private String firstName;
  private String lastName;
  private String middleName;
  private LocalDate birthdate;
  private Contact contact;
  private Icon icon;


  public Person() {
  }

  public Person(UUID id) {
    this.id = id;
  }

  public Person(String firstName,
                String lastName,
                String middleName,
                LocalDate birthdate,
                Contact contact,
                Icon icon) {
    this(null, firstName, lastName, middleName, birthdate, contact, icon);
  }

  public Person(UUID id,
                String firstName,
                String lastName,
                String middleName,
                LocalDate birthdate,
                Contact contact,
                Icon icon) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.birthdate = birthdate;
    this.contact = contact;
    this.icon = icon;
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public Contact getContact() {
    return contact;
  }

  public Icon getIcon() {
    return icon;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    String fio = String.join(" ", firstName, lastName, middleName);
    String email = (contact != null)
                   ? Objects.toString(contact.getEmail(), "null")
                   : "null";
    return "Person{" +
           "id=" + id +
           ", fio='" + fio + '\'' +
           ", email='" + email + '\'' +
           '}';
  }
}
