package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.mapper.AdminAccountFindMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AdminAccountFindDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/admin/find")
public class AdminFindServlet extends HttpServlet {

  private final AccountService accountService;
  private final AdminAccountFindMapper mapper;

  public AdminFindServlet() {
    accountService = AccountServiceImpl.getInstance();
    mapper = AdminAccountFindMapper.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AdminAccountFindDto accountFindDto = new AdminAccountFindDto(
        ServletUtil.getValue(req, "find_id"),
        ServletUtil.getValue(req, "find_username"),
        ServletUtil.getValue(req, "find_phone"),
        ServletUtil.getValue(req, "find_email"),
        ServletUtil.getBoolean(req, "only_blocked")
    );

    List<AccountDto> accountDtoList = accountService.findAllBy(mapper.mapFrom(accountFindDto));

    req.setAttribute("accountFindList", accountDtoList);
    req.setAttribute("findUsername", accountFindDto.username());
    req.setAttribute("findPhone", accountFindDto.phone());
    req.setAttribute("findEmail", accountFindDto.email());
    req.setAttribute("onlyBlocked", accountFindDto.onlyBlocked());
    req.setAttribute("tabFind", "view");

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.ADMIN)).forward(req, resp);
  }
}
