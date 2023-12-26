package su.itpro.photogram.model.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Comment {

  private UUID id;
  private UUID accountId;
  private UUID postId;
  private Instant createTime;
  private Instant changeTime;
  private Boolean isDeleted;
  private String username;
  private String message;


  public Comment() {
  }

  public Comment(UUID accountId, UUID postId, String message) {
    this.accountId = accountId;
    this.postId = postId;
    this.message = message;
  }

  public Comment(UUID id, UUID accountId, UUID postId, Instant createTime, Instant changeTime,
                 Boolean isDeleted, String username, String message) {
    this.id = id;
    this.accountId = accountId;
    this.postId = postId;
    this.createTime = createTime;
    this.changeTime = changeTime;
    this.isDeleted = isDeleted;
    this.username = username;
    this.message = message;
  }

  public UUID getId() {
    return id;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public UUID getPostId() {
    return postId;
  }

  public Instant getCreateTime() {
    return createTime;
  }

  public Instant getChangeTime() {
    return changeTime;
  }

  public Boolean getDeleted() {
    return isDeleted;
  }

  public String getUsername() {
    return username;
  }

  public String getMessage() {
    return message;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public void setPostId(UUID postId) {
    this.postId = postId;
  }

  public void setCreateTime(Instant createTime) {
    this.createTime = createTime;
  }

  public void setChangeTime(Instant changeTime) {
    this.changeTime = changeTime;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Message{" +
           "id=" + id +
           ", accountId=" + accountId +
           ", postId=" + postId +
           ", username='" + username + '\'' +
           ", message='" + message + '\'' +
           '}';
  }
}
