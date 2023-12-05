package su.itpro.photogram.model.entity;

import java.util.Objects;
import java.util.UUID;

public class Image {

  private UUID id;
  private UUID accountId;
  private UUID postId;
  private String fileName;
  private int ordinal;


  public Image() {
  }

  public Image(UUID accountId, UUID postId, String fileName, int ordinal) {
    this(UUID.randomUUID(), accountId, postId, fileName, ordinal);
  }

  public Image(UUID id, UUID accountId, UUID postId, String fileName, int ordinal) {
    this.id = id;
    this.accountId = accountId;
    this.postId = postId;
    this.fileName = fileName;
    this.ordinal = ordinal;
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

  public String getFileName() {
    return fileName;
  }

  public int getOrdinal() {
    return ordinal;
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

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public void setOrdinal(int ordinal) {
    this.ordinal = ordinal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(id, image.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Image{" +
           "id=" + id +
           ", accountId=" + accountId +
           ", postId=" + postId +
           ", fileName='" + fileName + '\'' +
           ", ordinal=" + ordinal +
           '}';
  }
}
