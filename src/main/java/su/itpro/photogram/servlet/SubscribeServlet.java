package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.SubscribeDto;
import su.itpro.photogram.service.SubscribeService;
import su.itpro.photogram.service.impl.SubscribeServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/subscribe/*")
public class SubscribeServlet extends HttpServlet {

  private final SubscribeService subscribeService;


  public SubscribeServlet() {
    subscribeService = SubscribeServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    UUID subscribeId = UUID.fromString(ServletUtil.variableOfQueryPath(req.getPathInfo()));
    AccountDto accountDto = ServletUtil.getAccountFromSession(req);

    subscribeService.subscribe(new SubscribeDto(accountDto.id(), subscribeId));

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.HOME));
  }
}
