package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/create/*")
@MultipartConfig(maxFileSize = 128_000_000)
public class PostCreateServlet extends HttpServlet {

  private final PostService postService = PostServiceImpl.getInstance();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    req.setAttribute("username", username);

    getServletContext().getRequestDispatcher(SelectPage.POST_CREATE.get()).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    postService.createNewPost(username, req.getParameter("description"), req.getParts());

    req.setAttribute("username", username);
    resp.sendRedirect("/home/" + username);
  }
}
