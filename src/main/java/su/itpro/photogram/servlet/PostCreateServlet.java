package su.itpro.photogram.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.PostCreateDto;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.thread.SaveImageThreadPool;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/create")
@MultipartConfig(maxFileSize = 128_000_000)
public class PostCreateServlet extends HttpServlet {

  private final PostService postService;
  private final SaveImageThreadPool threadPool;

  public PostCreateServlet() {
    postService = PostServiceImpl.getInstance();
    threadPool = SaveImageThreadPool.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.POST_CREATE))
        .forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    var postCreateDto = new PostCreateDto(
        ServletUtil.getBoolean(req, "isActive"),
        ServletUtil.getValue(req, "description"),
        req.getParts()
    );

    postService.createNewPost(accountDto.username(), postCreateDto);

    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.HOME));
  }

  @Override
  public void destroy() {
    threadPool.shutdown(10);
  }
}
