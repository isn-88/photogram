package su.itpro.photogram.model.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import su.itpro.photogram.model.enums.Gender;

public class Profile {

  private UUID accountId;
  private String fullName;
  private Gender gender;
  private LocalDate birthdate;
  private Map<String, String> contacts;
  private String aboutMe;


  public Profile() {
  }

  public Profile(UUID accountId) {
    this.accountId = accountId;
    contacts = new HashMap<>();
  }

  public Profile(UUID accountId, String fullName) {
    this.accountId = accountId;
    this.fullName = fullName;
    contacts = new HashMap<>();
  }

  public Profile(UUID accountId,
                 String fullName,
                 Gender gender,
                 LocalDate birthdate,
                 Map<String, String> contacts,
                 String aboutMe) {
    this.accountId = accountId;
    this.fullName = fullName;
    this.gender = gender;
    this.birthdate = birthdate;
    this.contacts = (contacts != null) ? contacts : new HashMap<>();
    this.aboutMe = aboutMe;
  }

  public void addContact(String key, String value) {
    contacts.put(key, value);
  }

  public UUID getAccountId() {
    return accountId;
  }

  public String getFullName() {
    return fullName;
  }

  public Gender getGender() {
    return gender;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public Map<String, String> getContacts() {
    return contacts;
  }

  public String getAboutMe() {
    return aboutMe;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public void setContacts(Map<String, String> contacts) {
    this.contacts = (contacts != null) ? contacts : new HashMap<>();
  }

  public void setAboutMe(String aboutMe) {
    this.aboutMe = aboutMe;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(accountId, profile.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }

  @Override
  public String toString() {
    return "Person{" +
           "fullName='" + fullName + '\'' +
           '}';
  }
}
