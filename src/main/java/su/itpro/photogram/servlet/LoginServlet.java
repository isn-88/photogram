package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.LoginService;
import su.itpro.photogram.service.impl.LoginServiceImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private final LoginService loginService = LoginServiceImpl.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String login = req.getParameter("login");
    String password = req.getParameter("password");
    Account account = loginService.login(login, password);

    req.setAttribute("account", account);
    getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
  }
}
