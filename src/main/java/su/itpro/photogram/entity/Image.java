package su.itpro.photogram.entity;

import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

public class Image {

  private UUID id;
  private String fileName;
  private Path filePath;
  private Post post;

  public Image(String fileName, Path filePath, Post post) {
    this(UUID.randomUUID(), fileName, filePath, post);
  }

  public Image(UUID id, String fileName, Path filePath, Post post) {
    this.id = id;
    this.fileName = fileName;
    this.filePath = filePath;
    this.post = post;
  }

  public UUID getId() {
    return id;
  }

  public String getFileName() {
    return fileName;
  }

  public Path getFilePath() {
    return filePath;
  }

  public Post getPost() {
    return post;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setFilePath(Path filePath) {
    this.filePath = filePath;
  }

  public void setPost(Post post) {
    this.post = post;
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
    String postId = (post != null)
                    ? Objects.toString(post.getId(), "null")
                    : "null";
    return "Image{" +
           "id=" + id +
           ", fileName='" + fileName + '\'' +
           ", postId=" + postId +
           '}';
  }
}
