package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.CommentDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.PostUpdateDto;
import su.itpro.photogram.model.enums.PostStatus;
import su.itpro.photogram.service.CommentService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.LikeService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.CommentServiceImpl;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.LikeServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/edit/*")
public class PostEditServlet extends HttpServlet {

  private final ImageService imageService;
  private final PostService postService;
  private final CommentService commentService;
  private final LikeService likeService;


  public PostEditServlet() {
    imageService = ImageServiceImpl.getInstance();
    postService = PostServiceImpl.getInstance();
    commentService = CommentServiceImpl.getInstance();
    likeService = LikeServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String postId = ServletUtil.variableOfQueryPath(req.getPathInfo());
    PostUpdateDto postUpdateDto = new PostUpdateDto(
        UUID.fromString(postId),
        ServletUtil.getPostStatusOrDefault(req, "postStatus", PostStatus.DRAFT),
        ServletUtil.getValue(req, "description")
    );

    postService.update(postUpdateDto);

    PostDto postDto = postService.findById(postUpdateDto.id());
    List<UUID> imageIds = imageService.findAllImageIdsByPostId(postDto.id());
    List<CommentDto> comments = commentService.findAllBy(postDto.id());

    req.setAttribute("post", postDto);
    req.setAttribute("imageIds", imageIds);
    req.setAttribute("comments", comments);
    req.setAttribute("likeScore", likeService.getScore(postUpdateDto.id()));

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.POST_VIEW)).forward(req, resp);
  }
}
