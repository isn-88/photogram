package su.itpro.photogram.servlet.enums;

public enum PageSelector {

  ADMIN("admin"),
  EDIT("edit"),
  HOME("home"),
  LOGIN("login"),
  MODERATOR("moderator"),
  POST_CREATE("post-create"),
  POST_VIEW("post-view"),
  REG("registration"),
  SEARCH("search");



  private final String jsp;

  PageSelector(String jsp) {
    this.jsp = jsp;
  }

  public String get() {
    return this.jsp;
  }
}
