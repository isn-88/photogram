package su.itpro.photogram.model.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Account {

  private UUID id;
  private Profile profile;
  private String phone;
  private String email;
  private String username;
  private String password;
  private Role role;
  private Boolean isActive;
  private Boolean isVerifiedPhone;
  private Boolean isVerifiedEmail;
  private Instant createDate;

  public Account() {
  }

  public Account(UUID id) {
    this.id = id;
  }

  public Account(String phone, String email, String username, String password) {
    this.phone = phone;
    this.email = email;
    this.username = username;
    this.password = password;
  }

  public Account(UUID id,
                 Profile profile,
                 String phone,
                 String email,
                 String username,
                 String password,
                 Role role,
                 Boolean isActive,
                 Boolean isVerifiedPhone,
                 Boolean isVerifiedEmail,
                 Instant createDate) {
    this.id = id;
    this.profile = profile;
    this.phone = phone;
    this.email = email;
    this.username = username;
    this.password = password;
    this.role = role;
    this.isActive = isActive;
    this.isVerifiedPhone = isVerifiedPhone;
    this.isVerifiedEmail = isVerifiedEmail;
    this.createDate = createDate;
  }

  public UUID getId() {
    return id;
  }

  public Profile getProfile() {
    return profile;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
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

  public Boolean getVerifiedPhone() {
    return isVerifiedPhone;
  }

  public Boolean getVerifiedEmail() {
    return isVerifiedEmail;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public void setVerifiedPhone(Boolean isVerifiedPhone) {
    this.isVerifiedPhone = isVerifiedPhone;
  }

  public void setVerifiedEmail(Boolean isVerifiedEmail) {
    this.isVerifiedEmail = isVerifiedEmail;
  }

  public void setCreateDate(Instant createDate) {
    this.createDate = createDate;
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
    return "Account{" +
           "phone='" + phone + '\'' +
           ", email='" + email + '\'' +
           ", username='" + username + '\'' +
           '}';
  }
}
