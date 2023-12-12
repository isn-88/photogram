package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/edit/*")
public class EditServlet extends HttpServlet {

  private final AccountService accountService;
  private final ProfileService profileService;


  public EditServlet() {
    accountService = AccountServiceImpl.getInstance();
    profileService = ProfileServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    AccountDto accountDto = accountService.findByUsername(username);
    ProfileDto profileDto = profileService.loadProfile(accountDto.id());

    req.setAttribute("username", username);
    req.setAttribute("account", accountDto);
    req.setAttribute("profile", profileDto);

    getServletContext().getRequestDispatcher(SelectPage.EDIT.get()).forward(req, resp);
  }

}
