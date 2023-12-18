package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.CommentCreateDto;
import su.itpro.photogram.service.CommentService;
import su.itpro.photogram.service.impl.CommentServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/comment/send/*")
public class CommentServlet extends HttpServlet {

  private final CommentService commentService;


  public CommentServlet() {
    commentService = CommentServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    String postId = ServletUtil.variableOfQueryPath(req.getPathInfo());

    var createMessageDto = new CommentCreateDto(
        accountDto.id(),
        UUID.fromString(postId),
        ServletUtil.getValueAndStrip(req, "message")
    );

    commentService.saveComment(createMessageDto);

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.POST, postId));
  }
}
