package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/edit/*")
public class EditServlet extends HttpServlet {

  private final AccountService accountService = AccountServiceImpl.getInstance();
  private final ProfileService profileService = ProfileServiceImpl.getInstance();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    Account account = accountService.findByUsername(username);
    account.setProfile(profileService.loadProfile(account.getId()));
    req.setAttribute("username", username);
    req.setAttribute("account", account);

    getServletContext().getRequestDispatcher(SelectPage.EDIT.get()).forward(req, resp);
  }

}
