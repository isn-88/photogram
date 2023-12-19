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
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/profile/edit")
public class ProfileServlet extends HttpServlet {

  ProfileService profileService;


  public ProfileServlet() {
    profileService = ProfileServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);

    ProfileEditDto profileEditDto = new ProfileEditDto(
        accountDto.id(),
        ServletUtil.getValue(req, "full_name"),
        ServletUtil.parseDate(req, "birthdate"),
        Gender.valueOf(ServletUtil.getValue(req, "gender")),
        ServletUtil.getValue(req, "about_me")
    );

    profileService.update(profileEditDto);

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.EDIT));
  }
}
