package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.service.IconService;
import su.itpro.photogram.service.impl.IconServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/icon/edit")
@MultipartConfig(maxFileSize = 32_000_000)
public class IconEditServlet extends HttpServlet {

  private final IconService iconService;

  public IconEditServlet() {
    iconService = IconServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);

    Part iconPart = req.getPart("icon");
    iconService.saveOrUpdate(accountDto.id(), iconPart.getInputStream().readAllBytes());

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.EDIT));
  }
}
