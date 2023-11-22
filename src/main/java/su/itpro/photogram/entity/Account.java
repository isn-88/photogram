package su.itpro.photogram.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Account {

  private UUID id;
  private Person person;
  private String login;
  private String password;
  private Role role;
  private Boolean isActive;
  private String description;
  private Instant createDate;
  private Instant lastActivity;

  public Account() {
  }

  public Account(Person person,
                 String login,
                 String password,
                 Role role,
                 Boolean isActive,
                 String description) {
    this(null, person, login, password, role, isActive, description, Instant.now(), Instant.now());
  }

  public Account(UUID id,
                 Person person,
                 String login,
                 String password,
                 Role role,
                 Boolean isActive,
                 String description,
                 Instant createDate,
                 Instant lastActivity) {
    this.id = id;
    this.person = person;
    this.login = login;
    this.password = password;
    this.role = role;
    this.isActive = isActive;
    this.description = description;
    this.createDate = createDate;
    this.lastActivity = lastActivity;
  }

  public UUID getId() {
    return id;
  }

  public Person getPerson() {
    return person;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return role;
  }

  public Boolean getActive() {
    return isActive;
  }

  public String getDescription() {
    return description;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public Instant getLastActivity() {
    return lastActivity;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
  }

  public void setLastActivity(Instant lastActivity) {
    this.lastActivity = lastActivity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    String personId = (person != null) ? Objects.toString(person.getId()) : "null";
    return "Account{" +
           ", login='" + login + '\'' +
           ", personId='" + personId + '\'' +
           ", description='" + description + '\'' +
           '}';
  }
}
