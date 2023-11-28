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
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/account/edit/*")
public class AccountServlet extends HttpServlet {

  private final AccountService accountService = AccountServiceImpl.getInstance();


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String usernameFromPath = ServletUtil.variableOfQueryPath(req.getPathInfo());
    String phone = ServletUtil.getValueAndStrip(req, "phone");
    String email = ServletUtil.getValueAndStrip(req, "email");
    String username = ServletUtil.getValueAndStrip(req, "username");

    Account accountUpdated = accountService.update(usernameFromPath, phone, email, username);

    resp.sendRedirect("/edit/" + accountUpdated.getUsername());
  }

}
