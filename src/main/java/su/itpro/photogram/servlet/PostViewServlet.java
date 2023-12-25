package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.CommentDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.service.CommentService;
import su.itpro.photogram.service.ComplaintService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.LikeService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.impl.CommentServiceImpl;
import su.itpro.photogram.service.impl.ComplaintServiceImpl;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.LikeServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/post/*")
public class PostViewServlet extends HttpServlet {

  private final PostService postService;
  private final ImageService imageService;
  private final CommentService commentService;
  private final ComplaintService complaintService;
  private final LikeService likeService;


  public PostViewServlet() {
    postService = PostServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
    commentService = CommentServiceImpl.getInstance();
    complaintService = ComplaintServiceImpl.getInstance();
    likeService = LikeServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    UUID postId = UUID.fromString(ServletUtil.variableOfQueryPath(req.getPathInfo()));
    String view = ServletUtil.getValue(req, "view");

    PostDto postDto = postService.findById(postId);
    List<UUID> imageIds = imageService.findAllImageIdsByPostId(postId);
    List<CommentDto> comments = commentService.findAllBy(postDto.id());

    req.setAttribute("view", view);
    req.setAttribute("post", postDto);
    req.setAttribute("imageIds", imageIds);
    req.setAttribute("comments", comments);
    req.setAttribute("likeScore", likeService.getScore(postId));
    //TODO accountId who view
    req.setAttribute("isComplaint", complaintService.exists(accountDto.id(), postId));

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.POST_VIEW)).forward(req, resp);
  }
}
