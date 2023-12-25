package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.mapper.UserAccountFindMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.UserAccountFindDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {

  private final AccountService accountService;
  private final UserAccountFindMapper mapper;


  public SearchServlet() {
    accountService = AccountServiceImpl.getInstance();
    mapper = UserAccountFindMapper.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    UserAccountFindDto accountFindDto =
        new UserAccountFindDto(ServletUtil.getValue(req, "search"));

    List<AccountDto> accountDtoList = accountService.findAllBy(mapper.mapFrom(accountFindDto));

    req.setAttribute("accountFindList", accountDtoList);

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.SEARCH))
        .forward(req, resp);
  }
}
