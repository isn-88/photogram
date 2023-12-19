package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.service.impl.RegistrationServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

  private final RegistrationService registrationService;


  public RegistrationServlet() {
    registrationService = RegistrationServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.REG))
        .forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    AccountChangeDto accountChangeDto = new AccountChangeDto(
        ServletUtil.getPreparedPhone(req, "phone"),
        ServletUtil.getValue(req, "email"),
        ServletUtil.getValue(req, "username"),
        ServletUtil.getValue(req, "password"),
        ServletUtil.getValue(req, "full_name")
    );

    try {
      registrationService.registerNewAccount(accountChangeDto);
      req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.LOGIN))
          .forward(req, resp);
    } catch (ValidationException e) {
      req.setAttribute("errors", e.getErrors());
      req.setAttribute("phone", accountChangeDto.phone());
      req.setAttribute("email", accountChangeDto.email());
      req.setAttribute("username", accountChangeDto.username());
      req.setAttribute("full_name", accountChangeDto.fullName());
      doGet(req, resp);
    }
  }
}
