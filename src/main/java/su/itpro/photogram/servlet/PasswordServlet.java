package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.service.PasswordService;
import su.itpro.photogram.service.impl.PasswordServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/password/*")
public class PasswordServlet extends HttpServlet {

  private final PasswordService service = PasswordServiceImpl.getInstance();


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    String oldPass = ServletUtil.getValue(req, "old_password");
    String newPass = ServletUtil.getValue(req, "new_password");
    String checkPass = ServletUtil.getValue(req, "check_password");

    service.changePassword(username, oldPass, newPass, checkPass);

    //TODO LogOut
    resp.sendRedirect("/edit/" + username);
  }

}
