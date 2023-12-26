package su.itpro.photogram.model;

import java.util.Objects;
import java.util.UUID;

public class Like {

  private UUID accountId;
  private UUID postId;
  private short score;


  public Like() {
  }

  public Like(UUID accountId, UUID postId, short score) {
    this.accountId = accountId;
    this.postId = postId;
    this.score = score;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public UUID getPostId() {
    return postId;
  }

  public void setPostId(UUID postId) {
    this.postId = postId;
  }

  public short getScore() {
    return score;
  }

  public void setScore(short score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Like like = (Like) o;
    return Objects.equals(accountId, like.accountId) &&
           Objects.equals(postId, like.postId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, postId);
  }

  @Override
  public String toString() {
    return "Like{" +
           "accountId=" + accountId +
           ", postId=" + postId +
           ", score=" + score +
           '}';
  }
}
