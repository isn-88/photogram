package su.itpro.photogram.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Account {

  private Long id;
  private Person person;
  private String login;
  private String password;
  private Role role;
  private Boolean isActive;
  private String description;
  private LocalDateTime createDate;
  private LocalDateTime lastActivity;

  public Account(Long id,
                 String login,
                 Person person,
                 String password,
                 Role role,
                 Boolean isActive,
                 String description,
                 LocalDateTime createDate,
                 LocalDateTime lastActivity) {
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

  public Long getId() {
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

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public LocalDateTime getLastActivity() {
    return lastActivity;
  }

  public void setId(Long id) {
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

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public void setLastActivity(LocalDateTime lastActivity) {
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
           ", personId'" + personId +'\'' +
           ", description='" + description + '\'' +
           '}';
  }
}
