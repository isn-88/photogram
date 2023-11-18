package su.itpro.photogram.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Post {

  private UUID id;
  private Account account;
  private String title;
  private LocalDateTime createDate;

  public Post(Account account, String title) {
    this(account, title, LocalDateTime.now());
    this.account = account;
    this.title = title;
  }

  public Post(Account account, String title, LocalDateTime createDate) {
    this(UUID.randomUUID(), account, title, createDate);
  }

  public Post(UUID id, Account account, String title, LocalDateTime createDate) {
    this.id = id;
    this.account = account;
    this.title = title;
    this.createDate = createDate;
  }

  public UUID getId() {
    return id;
  }

  public Account getAccount() {
    return account;
  }

  public String getTitle() {
    return title;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setCreateDate(LocalDateTime createDate) {
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
    Post post = (Post) o;
    return Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    String login = (account != null)
                   ? Objects.toString(account.getLogin(), "null")
                   : "null";
    return "Post{" +
           "id=" + id +
           ", account=" + login +
           ", title='" + title + '\'' +
           ", createDate=" + createDate +
           '}';
  }
}
