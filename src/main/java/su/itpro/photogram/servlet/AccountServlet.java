package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/account/edit")
public class AccountServlet extends HttpServlet {

  private final AccountService accountService;


  public AccountServlet() {
    accountService = AccountServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    var accountUpdateDto = new AccountUpdateDto(
        ServletUtil.getValueAndStrip(req, "phone"),
        ServletUtil.getValueAndStrip(req, "email"),
        ServletUtil.getValueAndStrip(req, "username")
    );

    AccountDto updatedAccountDto = accountService.update(accountDto.username(), accountUpdateDto);
    req.getSession().setAttribute("account", updatedAccountDto);

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.EDIT));
  }

}
