package su.itpro.photogram.entity;

import java.util.Arrays;
import java.util.Objects;

public class Icon {

  private Long id;
  private String name;
  private String type;
  private byte[] data;

  public Icon(String name, String type, byte[] data) {
    this(null, name, type, data);
  }

  public Icon(Long id, String name, String type, byte[] data) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.data = data != null ? Arrays.copyOf(data, data.length) : new byte[0];
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public byte[] getData() {
    return Arrays.copyOf(data, data.length);
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
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
    return Objects.equals(id, icon.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Icon{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", type='" + type + '\'' +
           '}';
  }
}
