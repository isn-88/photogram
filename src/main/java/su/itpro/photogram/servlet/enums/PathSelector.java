package su.itpro.photogram.servlet.enums;

public enum PathSelector {

  EDIT("edit"),
  HOME("home"),
  LOGIN("login"),
  LOGOUT("logout"),
  POST("post");

  private final String path;

  PathSelector(String path) {
    this.path = path;
  }

  public String get() {
    return this.path;
  }

}
