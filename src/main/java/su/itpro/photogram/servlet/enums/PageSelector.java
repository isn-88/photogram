package su.itpro.photogram.servlet.enums;

public enum PageSelector {

  EDIT("edit"),
  HOME("home"),
  LOGIN("login"),
  POST_CREATE("post-create"),
  POST_VIEW("post-view"),
  REG("registration");


  private final String jsp;

  PageSelector(String jsp) {
    this.jsp = jsp;
  }

  public String get() {
    return this.jsp;
  }
}
