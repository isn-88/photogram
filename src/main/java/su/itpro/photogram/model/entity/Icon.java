package su.itpro.photogram.model.entity;


import java.util.Objects;
import java.util.UUID;

public class Icon {

  private UUID accountId;
  private byte[] data;


  public Icon() {
  }

  public Icon(UUID accountId) {
    this.accountId = accountId;
  }

  public Icon(UUID accountId, byte[] data) {
    this.accountId = accountId;
    this.data = data;
  }

  public UUID getAccountId() {
    return accountId;
  }

  public byte[] getData() {
    return data;
  }

  public void setAccountId(UUID accountId) {
    this.accountId = accountId;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Icon icon = (Icon) o;
    return Objects.equals(accountId, icon.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }

  @Override
  public String toString() {
    return "Icon{" +
           "accountId=" + accountId +
           '}';
  }
}
