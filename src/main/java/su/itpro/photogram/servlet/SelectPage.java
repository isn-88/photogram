package su.itpro.photogram.servlet;

public enum SelectPage {

  EDIT("/edit.jsp"),
  HOME("/home.jsp"),
  LOGIN("/login.jsp"),
  POST_CREATE("/postCreate.jsp"),
  POST_VIEW("/postView.jsp"),
  REG("/registration.jsp");


  private final String jsp;

  SelectPage(String jsp) {
    this.jsp = jsp;
  }

  public String get() {
    return this.jsp;
  }
}
