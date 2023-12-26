package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.model.dto.ProfileEditDto;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.service.impl.RegistrationServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/admin/create")
public class AdminCreateServlet extends HttpServlet {

  private final RegistrationService registrationService;
  private final ProfileService profileService;


  public AdminCreateServlet() {
    registrationService = RegistrationServiceImpl.getInstance();
    profileService = ProfileServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.ADMIN))
        .forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountChangeDto accountChangeDto = new AccountChangeDto(
        ServletUtil.getPreparedPhone(req, "phone"),
        ServletUtil.getValue(req, "email"),
        ServletUtil.getValue(req, "username"),
        ServletUtil.getValue(req, "password"),
        null
    );
    Role role = ServletUtil.getRole(req, "account_type");

    try {
      UUID accountId = registrationService.createNewAccount(accountChangeDto, role);
      profileService.update(new ProfileEditDto(accountId, accountChangeDto.fullName()));

      req.setAttribute("result", true);
      req.setAttribute("tabCreate", "view");

      req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.ADMIN))
          .forward(req, resp);

    } catch (ValidationException e) {
      req.setAttribute("errors", e.getErrors());
      req.setAttribute("phone", accountChangeDto.phone());
      req.setAttribute("email", accountChangeDto.email());
      req.setAttribute("username", accountChangeDto.username());
      req.setAttribute("role", role);
      req.setAttribute("tabCreate", "view");
      doGet(req, resp);
    }
  }
}
