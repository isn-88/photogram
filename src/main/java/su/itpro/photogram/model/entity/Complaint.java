package su.itpro.photogram.model.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import su.itpro.photogram.model.enums.ComplainStatus;

public class Complaint {

  private UUID accountId;
  private String username;
  private UUID postId;
  private String message;
  private ComplainStatus status;
  private Instant createTime;
  private Instant closeTime;


  public Complaint() {
  }

  public Complaint(UUID accountId,
                   String username,
                   UUID postId,
                   String message,
                   ComplainStatus status,
                   Instant createTime,
                   Instant closeTime) {
    this.accountId = accountId;
    this.username = username;
    this.postId = postId;
    this.message = message;
    this.status = status;
    this.createTime = createTime;
    this.closeTime = closeTime;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public String getUsername() {
    return username;
  }

  public UUID getPostId() {
    return postId;
  }

  public String getMessage() {
    return message;
  }

  public ComplainStatus getStatus() {
    return status;
  }

  public Instant getCreateTime() {
    return createTime;
  }

  public Instant getCloseTime() {
    return closeTime;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPostId(UUID postId) {
    this.postId = postId;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setStatus(ComplainStatus status) {
    this.status = status;
  }

  public void setCreateTime(Instant createTime) {
    this.createTime = createTime;
  }

  public void setCloseTime(Instant closeTime) {
    this.closeTime = closeTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Complaint complaint = (Complaint) o;
    return Objects.equals(accountId, complaint.accountId) &&
           Objects.equals(postId, complaint.postId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, postId);
  }

  @Override
  public String toString() {
    return "Complaint{" +
           "username" + username +
           ", postId=" + postId +
           ", message='" + message + '\'' +
           ", status=" + status +
           '}';
  }
}
