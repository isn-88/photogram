package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/edit")
public class EditServlet extends HttpServlet {

  private final ProfileService profileService;


  public EditServlet() {
    profileService = ProfileServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    ProfileDto profileDto = profileService.loadProfile(accountDto.id());

    req.setAttribute("profile", profileDto);

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.EDIT)).forward(req, resp);
  }

}
