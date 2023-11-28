package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/profile/edit/*")
public class ProfileServlet extends HttpServlet {

  ProfileService profileService = ProfileServiceImpl.getInstance();


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    String fullName = ServletUtil.getValueAndStrip(req, "full_name");
    profileService.update(username, fullName);

    resp.sendRedirect("/edit/" + username);
  }
}
