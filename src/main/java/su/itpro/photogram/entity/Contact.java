package su.itpro.photogram.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Contact {

  private UUID id;
  private String email;
  private String phone;
  private Map<String, String> contacts;

  public Contact() {
  }

  public Contact(UUID id) {
    this.id = id;
  }

  public Contact(String email, String phone) {
    this(email, phone, new HashMap<>());
  }

  public Contact(String email, String phone, Map<String, String> contacts) {
    this(null, email, phone, contacts);
  }

  public Contact(UUID id, String email, String phone, Map<String, String> contacts) {
    this.id = id;
    this.email = email;
    this.phone = phone;
    this.contacts = contacts;
  }

  public void addContact(String key, String value) {
    contacts.put(key, value);
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public Map<String, String> getContacts() {
    return contacts;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setContacts(Map<String, String> contacts) {
    this.contacts = contacts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(id, contact.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Contact{" +
           "id=" + id +
           ", email='" + email + '\'' +
           ", phone='" + phone + '\'' +
           ", contacts=" + contacts +
           '}';
  }
}
