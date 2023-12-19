package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/account/edit")
public class AccountUpdateServlet extends HttpServlet {

  private final AccountService accountService;


  public AccountUpdateServlet() {
    accountService = AccountServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    var accountUpdateDto = new AccountUpdateDto(
        ServletUtil.getPreparedPhone(req, "phone"),
        ServletUtil.getValueAndStrip(req, "email"),
        ServletUtil.getValueAndStrip(req, "username")
    );

    try {
      AccountDto updatedAccountDto = accountService.update(accountDto.id(), accountUpdateDto);
      req.getSession().setAttribute("account", updatedAccountDto);
    } catch (ValidationException e) {
      req.setAttribute("errors", e.getErrors());
      throw e;
    }

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.EDIT));
  }

}