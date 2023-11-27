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
    service.changePassword(username,
                           req.getParameter("old_password"),
                           req.getParameter("new_password"),
                           req.getParameter("check_password")
    );

    //TODO LogOut
    resp.sendRedirect("/edit/" + username);
  }

}
