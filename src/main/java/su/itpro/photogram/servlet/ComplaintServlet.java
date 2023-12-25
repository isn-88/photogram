package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.ComplaintCreateDto;
import su.itpro.photogram.service.ComplaintService;
import su.itpro.photogram.service.impl.ComplaintServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/complaint")
public class ComplaintServlet extends HttpServlet {

  private final ComplaintService complaintService;


  public ComplaintServlet() {
    complaintService = ComplaintServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    String postId = ServletUtil.getValue(req, "postId");
    String view = ServletUtil.getValue(req, "view");
    ComplaintCreateDto complaintCreateDto = new ComplaintCreateDto(
        accountDto.id(),
        UUID.fromString(postId),
        ServletUtil.getValueAndStrip(req, "message")
    );

    complaintService.create(complaintCreateDto);

    Map<String, String> queryPath = new HashMap<>();
    if (Objects.nonNull(view)) {
      queryPath.put("view", view);
    }
    resp.sendRedirect(ServletUtil.getServletPath(
        req.getContextPath(), PathSelector.POST, postId, queryPath));
  }
}
