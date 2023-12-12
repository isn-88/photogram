package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/account/edit/*")
public class AccountServlet extends HttpServlet {

  private final AccountService accountService;


  public AccountServlet() {
    accountService = AccountServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String usernameFromPath = ServletUtil.variableOfQueryPath(req.getPathInfo());
    var accountUpdateDto = new AccountUpdateDto(
        ServletUtil.getValueAndStrip(req, "phone"),
        ServletUtil.getValueAndStrip(req, "email"),
        ServletUtil.getValueAndStrip(req, "username")
    );

    accountService.update(usernameFromPath, accountUpdateDto);

    resp.sendRedirect("/edit/" + usernameFromPath);
  }

}
