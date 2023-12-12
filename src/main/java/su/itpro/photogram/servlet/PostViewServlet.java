package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.ImageAndBase64Dto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/*")
public class PostViewServlet extends HttpServlet {

  private final PostService postService;
  private final ImageService imageService;


  public PostViewServlet() {
    postService = PostServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    UUID postId = UUID.fromString(req.getParameter("id"));

    PostDto postDto = postService.findById(postId);
    List<ImageAndBase64Dto> images = imageService.findImagesBy(postId);

    req.setAttribute("username", username);
    req.setAttribute("post", postDto);
    req.setAttribute("images", images);

    getServletContext().getRequestDispatcher(SelectPage.POST_VIEW.get()).forward(req, resp);
  }
}
