package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.enums.Status;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/admin/status")
public class AdminStatusServlet extends HttpServlet {

  private final AccountService accountService;

  public AdminStatusServlet() {
    accountService = AccountServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String accountId = ServletUtil.getValue(req, "updated_account");
    String status = ServletUtil.getValue(req, "updated_status");

    accountService.updateStatus(UUID.fromString(accountId), Status.valueOf(status));

    req.setAttribute("tabFind", "view");

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.ADMIN)).forward(req, resp);
  }
}
