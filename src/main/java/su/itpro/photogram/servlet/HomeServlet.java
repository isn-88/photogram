package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.ImageBase64Dto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/home/*")
public class HomeServlet extends HttpServlet {

  private static final int POST_PER_PAGE = 12;


  private final AccountService accountService;

  private final ProfileService profileService;

  private final PostService postService;

  private final ImageService imageService;


  public HomeServlet() {
    accountService = AccountServiceImpl.getInstance();
    profileService = ProfileServiceImpl.getInstance();
    postService = PostServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    Account account = accountService.findByUsername(username);
    account.setProfile(profileService.loadProfile(account.getId()));
    List<Post> posts = postService.findTopByAccountIdAndLimit(account.getId(), POST_PER_PAGE);
    Map<UUID, ImageBase64Dto> images = imageService.loadPreviewImageFilesBy(posts);

    req.setAttribute("username", username);
    req.setAttribute("account", account);
    req.setAttribute("posts", posts);
    req.setAttribute("images", images);

    getServletContext().getRequestDispatcher(SelectPage.HOME.get()).forward(req, resp);
  }
}
