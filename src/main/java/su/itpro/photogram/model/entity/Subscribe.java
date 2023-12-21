package su.itpro.photogram.model.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Subscribe {

  private UUID accountId;
  private UUID subscribeId;
  private Instant subscribeTime;


  public Subscribe(UUID accountId, UUID subscribeId) {
    this(accountId, subscribeId, Instant.now());
  }

  public Subscribe(UUID accountId, UUID subscribeId, Instant subscribeTime) {
    this.accountId = accountId;
    this.subscribeId = subscribeId;
    this.subscribeTime = subscribeTime;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public UUID getSubscribeId() {
    return subscribeId;
  }

  public Instant getSubscribeTime() {
    return subscribeTime;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public void setSubscribeId(UUID subscribeId) {
    this.subscribeId = subscribeId;
  }

  public void setSubscribeTime(Instant subscribeTime) {
    this.subscribeTime = subscribeTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscribe subscribe = (Subscribe) o;
    return Objects.equals(accountId, subscribe.accountId) &&
           Objects.equals(subscribeId, subscribe.subscribeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, subscribeId);
  }

  @Override
  public String toString() {
    return "Subscribe{" +
           "accountId=" + accountId +
           ", subscribeId=" + subscribeId +
           ", subscribeTime=" + subscribeTime +
           '}';
  }
}
