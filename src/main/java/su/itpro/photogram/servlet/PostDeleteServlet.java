package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/delete/*")
public class PostDeleteServlet extends HttpServlet {

  private final PostService postService;

  public PostDeleteServlet() {
    this.postService = PostServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String postId = ServletUtil.variableOfQueryPath(req.getPathInfo());

    postService.delete(UUID.fromString(postId));

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.HOME));
  }
}
