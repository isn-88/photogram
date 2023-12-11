package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.ProfileEditDto;
import su.itpro.photogram.model.enums.Gender;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/profile/edit/*")
public class ProfileServlet extends HttpServlet {

  AccountService accountService;
  ProfileService profileService;


  public ProfileServlet() {
    accountService = AccountServiceImpl.getInstance();
    profileService = ProfileServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    AccountDto accountDto = accountService.findByUsername(username);

    ProfileEditDto profileEditDto = new ProfileEditDto(
        accountDto.id(),
        ServletUtil.getValue(req, "full_name"),
        ServletUtil.parseDate(req, "birthdate"),
        Gender.valueOf(ServletUtil.getValue(req, "gender")),
        ServletUtil.getValue(req, "about_me")
    );

    profileService.update(profileEditDto);

    resp.sendRedirect("/edit/" + username);
  }
}
