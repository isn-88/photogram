package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.LoginDto;
import su.itpro.photogram.service.LoginService;
import su.itpro.photogram.service.impl.LoginServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private static final int INACTIVE_INTERVAL = 60 * 60; //1 hour
  private final LoginService loginService;


  public LoginServlet() {
    loginService = LoginServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.LOGIN))
        .forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    LoginDto loginDto = new LoginDto(
        ServletUtil.getValueAndStrip(req, "login"),
        ServletUtil.getValue(req, "password")
    );

    try {
      AccountDto accountDto = loginService.login(loginDto);
      req.getSession().setAttribute("account", accountDto);
      req.getSession().setMaxInactiveInterval(INACTIVE_INTERVAL);

      switch (accountDto.role()) {
        case USER -> resp.sendRedirect(
            ServletUtil.getServletPath(req.getContextPath(), PathSelector.HOME));
        case ADMIN -> resp.sendRedirect(
            ServletUtil.getServletPath(req.getContextPath(), PathSelector.ADMIN));
        case MODERATOR -> resp.sendRedirect(
            ServletUtil.getServletPath(req.getContextPath(), PathSelector.MODERATOR));
        default -> throw new IllegalStateException();
      }

    } catch (ValidationException e) {
      req.setAttribute("errors", e.getErrors());
      req.setAttribute("login", loginDto.login());
      doGet(req, resp);
    }
  }

}
