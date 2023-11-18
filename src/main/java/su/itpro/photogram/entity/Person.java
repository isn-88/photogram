package su.itpro.photogram.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Person {

  private Long id;
  private String firstName;
  private String lastName;
  private String middleName;
  private Contact contact;
  private LocalDate birthdate;
  private Icon icon;


  public Person(String firstName,
                String lastName,
                String middleName,
                Contact contact,
                LocalDate birthdate,
                Icon icon) {
    this(null, firstName, lastName, middleName, contact, birthdate, icon);
  }

  public Person(Long id,
                String firstName,
                String lastName,
                String middleName,
                Contact contact,
                LocalDate birthdate,
                Icon icon) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.contact = contact;
    this.birthdate = birthdate;
    this.icon = icon;
  }

  public Long getId() {
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

  public Contact getContact() {
    return contact;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public Icon getIcon() {
    return icon;
  }

  public void setId(Long id) {
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

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
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
