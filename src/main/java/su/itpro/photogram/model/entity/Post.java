package su.itpro.photogram.model.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import su.itpro.photogram.model.enums.PostStatus;

public class Post {

  private UUID id;
  private UUID accountId;
  private PostStatus status;
  private String description;
  private Instant createDate;


  public Post() {
  }

  public Post(UUID id) {
    this.id = id;
  }

  public Post(UUID accountId, PostStatus status, String description) {
    this(accountId, status, description, Instant.now());
  }

  public Post(UUID accountId, PostStatus status, String description, Instant createDate) {
    this(UUID.randomUUID(), accountId, status, description, createDate);
  }

  public Post(UUID id, UUID accountId, PostStatus status, String description, Instant createDate) {
    this.id = id;
    this.accountId = accountId;
    this.status = status;
    this.description = description;
    this.createDate = createDate;
  }

  public UUID getId() {
    return id;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public PostStatus getStatus() {
    return status;
  }

  public String getDescription() {
    return description;
  }

  public Instant getCreateDate() {
    return createDate;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public void setStatus(PostStatus status) {
    this.status = status;
  }

  public void setDescription(String description) {
    this.description = description;
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
    Post post = (Post) o;
    return Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Post{" +
           "id=" + id +
           ", accountId=" + accountId +
           ", description='" + description + '\'' +
           ", createDate=" + createDate +
           '}';
  }
}
