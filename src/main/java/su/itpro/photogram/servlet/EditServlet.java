package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.model.enums.Gender;
import su.itpro.photogram.service.IconService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.IconServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {

  private final ProfileService profileService;
  private final IconService iconService;


  public EditServlet() {
    profileService = ProfileServiceImpl.getInstance();
    iconService = IconServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    ProfileDto profileDto = profileService.loadProfile(accountDto.id());
    boolean iconIsExists = iconService.exists(accountDto.id());

    req.setAttribute("profile", profileDto);
    req.setAttribute("progress", getProgress(accountDto, profileDto, iconIsExists));
    req.setAttribute("iconIsExists", iconIsExists);

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.EDIT)).forward(req, resp);
  }


  private int getProgress(AccountDto accountDto, ProfileDto profileDto, boolean iconIsExists) {
    int progress = 100;
    if (accountDto.phone() == null) {
      progress -= 15;
    }
    if (accountDto.email() == null) {
      progress -= 15;
    }
    if (profileDto.fullName() == null) {
      progress -= 15;
    }
    if (profileDto.birthdate() == null) {
      progress -= 15;
    }
    if (profileDto.gender().equals(Gender.UNDEFINE)) {
      progress -= 15;
    }
    if (profileDto.aboutMe() == null) {
      progress -= 15;
    }
    if (!iconIsExists) {
      progress -= 15;
    }
    return progress;
  }

}
