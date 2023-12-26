package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.ComplaintActionDto;
import su.itpro.photogram.model.enums.ComplainStatus;
import su.itpro.photogram.service.ComplaintService;
import su.itpro.photogram.service.impl.ComplaintServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/moderator/action")
public class ModeratorActionServlet extends HttpServlet {

  private final ComplaintService complaintService;


  public ModeratorActionServlet() {
    complaintService = ComplaintServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    ComplaintActionDto actionDto = new ComplaintActionDto(
        UUID.fromString(ServletUtil.getValue(req, "accountId")),
        UUID.fromString(ServletUtil.getValue(req, "postId")),
        ComplainStatus.valueOf(ServletUtil.getValue(req, "complaintResult"))
    );

    complaintService.action(actionDto);


    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.MODERATOR)).forward(req, resp);
  }
}
