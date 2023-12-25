package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.SubscribeService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.service.impl.PostServiceImpl;
import su.itpro.photogram.service.impl.ProfileServiceImpl;
import su.itpro.photogram.service.impl.SubscribeServiceImpl;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet({"/home", "/home/*"})
public class HomeServlet extends HttpServlet {

  private static final int POSTS_PER_PAGE = 12;
  private static final int ADVICE_PER_PAGE = 24;

  private final AccountService accountService;
  private final ProfileService profileService;
  private final PostService postService;
  private final ImageService imageService;
  private final SubscribeService subscribeService;


  public HomeServlet() {
    accountService = AccountServiceImpl.getInstance();
    profileService = ProfileServiceImpl.getInstance();
    postService = PostServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
    subscribeService = SubscribeServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = ServletUtil.variableOfQueryPath(req.getPathInfo());
    final boolean isOwner = Objects.isNull(username);
    AccountDto loginAccountDto = ServletUtil.getAccountFromSession(req);
    AccountDto accountDto = (Objects.isNull(username))
                            ? loginAccountDto
                            : accountService.findByUsername(username);
    ProfileDto profileDto = profileService.loadProfile(accountDto.id());

    // Публикации пользователя
    List<PostDto> postDtoList = postService.findTopPostIdByAccountIdAndLimit(
        accountDto.id(), !isOwner, POSTS_PER_PAGE);
    final Map<UUID, UUID> postToImageMap = imageService.findPreviewImageIdByPostIds(postDtoList);
    final int postCount = postService.countPosts(accountDto.id());

    // Публикации по подписке
    List<UUID> subscribeList = subscribeService.findAllSubscribe(accountDto.id());
    List<PostDto> subscribePostDtoList =
        subscribeService.getSubscribePost(subscribeList, POSTS_PER_PAGE);
    final Map<UUID, UUID> postSubscribeToImageMap = imageService
        .findPreviewImageIdByPostIds(subscribePostDtoList);


    if (!loginAccountDto.id().equals(accountDto.id())) {
      req.setAttribute("readyToSubscribe",
                       subscribeService.readyToSubscribe(loginAccountDto.id(), accountDto.id()));
    }

    req.setAttribute("account", accountDto);
    req.setAttribute("profile", profileDto);
    req.setAttribute("posts", postDtoList);
    req.setAttribute("postCount", postCount);
    req.setAttribute("subscribePosts", subscribePostDtoList);
    req.setAttribute("postToImageMap", postToImageMap);
    req.setAttribute("postSubscribeToImageMap", postSubscribeToImageMap);
    req.setAttribute("adviceDtoList",
                     subscribeService.getAdvice(accountDto.id(), ADVICE_PER_PAGE));
    req.setAttribute("subscribeDtoList",
                     subscribeService.findSubscribe(accountDto.id(), ADVICE_PER_PAGE));
    req.setAttribute("subscribersDtoList",
                     subscribeService.findSubscribers(accountDto.id(), ADVICE_PER_PAGE));
    req.setAttribute("subscribeCount", subscribeService.subscribeCount(accountDto.id()));
    req.setAttribute("subscribersCount", subscribeService.subscribersCount(accountDto.id()));

    req.getRequestDispatcher(ServletUtil.getJspPage(PageSelector.HOME))
        .forward(req, resp);
  }
}
