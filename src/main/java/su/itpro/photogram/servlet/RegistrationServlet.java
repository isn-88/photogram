package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.RegistrationDto;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.service.impl.RegistrationServiceImpl;
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

    getServletContext().getRequestDispatcher(SelectPage.REG.get()).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    RegistrationDto registrationDto = new RegistrationDto(
        ServletUtil.getValue(req, "phone"),
        ServletUtil.getValue(req, "email"),
        ServletUtil.getValue(req, "username"),
        ServletUtil.getValue(req, "password"),
        ServletUtil.getValue(req, "full_name")
    );

    registrationService.registerNewAccount(registrationDto);

    getServletContext().getRequestDispatcher(SelectPage.LOGIN.get()).forward(req, resp);
  }
}
