package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

  private final AccountService accountService = AccountServiceImpl.getInstance();
  private final ProfileServiceImpl profileService = ProfileServiceImpl.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    getServletContext().getRequestDispatcher(SelectPage.REG.get()).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String phone = ServletUtil.getValueAndStrip(req, "phone");
    String email = ServletUtil.getValueAndStrip(req, "email");
    String username = ServletUtil.getValueAndStrip(req, "username");
    String password = ServletUtil.getValue(req, "password");
    String fullName = ServletUtil.getValueAndStrip(req, "full_name");

    Account newAccount = accountService.registerNewAccount(phone, email, username, password);
    newAccount.setProfile(profileService.registerNewProfile(newAccount.getId(), fullName));

    req.setAttribute("account", newAccount);

    getServletContext().getRequestDispatcher(SelectPage.EDIT.get()).forward(req, resp);
  }
}
