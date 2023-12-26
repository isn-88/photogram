package su.itpro.photogram.servlet.enums;

public enum PathSelector {

  ADMIN("/admin"),
  EDIT("/edit"),
  HOME("/home"),
  LOGIN("/login"),
  LOGOUT("/logout"),
  MODERATOR("/moderator"),
  POST("/post"),
  REGISTRATION("/registration");

  private final String path;

  PathSelector(String path) {
    this.path = path;
  }

  public String get() {
    return this.path;
  }

}
