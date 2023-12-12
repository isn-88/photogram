package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.service.LoginService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.LoginServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private final LoginService loginService;
  private final ProfileService profileService;


  public LoginServlet() {
    loginService = LoginServiceImpl.getInstance();
    profileService = ProfileServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    getServletContext().getRequestDispatcher(SelectPage.LOGIN.get()).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String login = ServletUtil.getValueAndStrip(req, "login");
    String password = ServletUtil.getValue(req, "password");
    AccountDto accountDto = loginService.login(login, password);
    ProfileDto profileDto = profileService.loadProfile(accountDto.id());

    req.setAttribute("account", accountDto);
    req.setAttribute("profile", profileDto);

    resp.sendRedirect("/home/" + accountDto.username());
  }
}
