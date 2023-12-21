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
import su.itpro.photogram.service.CommentService;
import su.itpro.photogram.service.impl.CommentServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {

  private final CommentService commentService;


  public CommentDeleteServlet() {
    commentService = CommentServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String postId = ServletUtil.getValue(req, "postId");
    String commentId = ServletUtil.getValue(req, "commentId");
    String view = ServletUtil.getValue(req, "view");

    commentService.deleteComment(UUID.fromString(commentId));

    Map<String, String> queryPath = new HashMap<>();
    if (Objects.nonNull(view)) {
      queryPath.put("view", view);
    }
    resp.sendRedirect(ServletUtil.getServletPath(
        req.getContextPath(), PathSelector.POST, postId, queryPath));
  }

}
