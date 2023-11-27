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

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    Account accountUpdated = accountService.update(
        username,
        req.getParameter("phone"),
        req.getParameter("email"),
        req.getParameter("username")
    );

    resp.sendRedirect("/edit/" + accountUpdated.getUsername());
  }

}
