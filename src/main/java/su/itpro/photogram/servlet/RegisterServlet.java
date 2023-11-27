package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

  private final AccountService accountService = AccountServiceImpl.getInstance();
  private final ProfileServiceImpl profileService = ProfileServiceImpl.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    Account newAccount = accountService.registerNewAccount(
        req.getParameter("phone"),
        req.getParameter("email"),
        req.getParameter("username"),
        req.getParameter("password")
    );
    Profile newProfile = profileService.registerNewProfile(
        newAccount.getId(),
        req.getParameter("full_name")
    );

    req.setAttribute("account", newAccount);
    req.setAttribute("profile", newProfile);


    getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
  }
}
