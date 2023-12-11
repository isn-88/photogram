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
import su.itpro.photogram.model.dto.PostUpdateDto;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/edit/*")
public class PostEditServlet extends HttpServlet {

  private final ImageService imageService;
  private final PostService postService;


  public PostEditServlet() {
    imageService = ImageServiceImpl.getInstance();
    postService = PostServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    PostUpdateDto postUpdateDto = new PostUpdateDto(
        UUID.fromString(req.getParameter("id")),
        ServletUtil.getBoolean(req, "isActive"),
        ServletUtil.getValue(req, "description")
    );

    postService.update(postUpdateDto);

    PostDto postDto = postService.findById(postUpdateDto.id());
    List<ImageAndBase64Dto> images = imageService.findImagesBy(postUpdateDto.id());

    req.setAttribute("username", username);
    req.setAttribute("post", postDto);
    req.setAttribute("images", images);

    getServletContext().getRequestDispatcher(SelectPage.POST_VIEW.get()).forward(req, resp);
  }
}
