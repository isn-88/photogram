package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.ComplaintDto;
import su.itpro.photogram.model.dto.ComplaintSearchDto;
import su.itpro.photogram.service.ComplaintService;
import su.itpro.photogram.service.impl.ComplaintServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/moderator/find")
public class ModeratorFindServlet extends HttpServlet {

  private final ComplaintService complaintService;


  public ModeratorFindServlet() {
    complaintService = ComplaintServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    ComplaintSearchDto complainSearchDto = new ComplaintSearchDto(
        ServletUtil.getComplainStatusOrDefault(req, "isOpened", null),
        ServletUtil.getComplainStatusOrDefault(req, "isClosed", null),
        ServletUtil.getComplainStatusOrDefault(req, "isApproved", null),
        ServletUtil.getComplainStatusOrDefault(req, "isRejected", null)
    );

    List<ComplaintDto> complaintDtoList = complaintService.search(complainSearchDto);

    req.setAttribute("complaintDtoList", complaintDtoList);

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.MODERATOR)).forward(req, resp);
  }
}
