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
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.IconBase64Dto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.service.IconService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.impl.IconServiceImpl;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

  private static final int POSTS_PER_PAGE = 12;

  private final ProfileService profileService;
  private final PostService postService;
  private final ImageService imageService;
  private final IconService iconService;


  public HomeServlet() {
    profileService = ProfileServiceImpl.getInstance();
    postService = PostServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
    iconService = IconServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    ProfileDto profileDto = profileService.loadProfile(accountDto.id());

    List<PostDto> postDtos = postService.findTopPostIdByAccountIdAndLimit(
        accountDto.id(), false, POSTS_PER_PAGE);
    int postCount = postService.countPosts(accountDto.id());
    Map<UUID, UUID> postToImageMap = imageService.findPreviewImageIdByPostIds(postDtos);
    IconBase64Dto iconBase64Dto = iconService.findById(accountDto.id());

    req.setAttribute("profile", profileDto);
    req.setAttribute("posts", postDtos);
    req.setAttribute("postCount", postCount);
    req.setAttribute("postToImageMap", postToImageMap);
    req.setAttribute("icon", iconBase64Dto);

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.HOME))
        .forward(req, resp);
  }
}
