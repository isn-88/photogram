package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.LikeDto;
import su.itpro.photogram.service.LikeService;
import su.itpro.photogram.service.impl.LikeServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/like/*")
public class LikeServlet extends HttpServlet {

  private final LikeService likeService;


  public LikeServlet() {
    likeService = LikeServiceImpl.getInstance();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    AccountDto accountDto = ServletUtil.getAccountFromSession(req);
    String postId = ServletUtil.variableOfQueryPath(req.getPathInfo());
    boolean like = ServletUtil.getBoolean(req, "like");
    boolean dislike = ServletUtil.getBoolean(req, "dislike");
    int score = like ? 1 : (dislike ? -1 : 0);
    likeService.update(new LikeDto(accountDto.id(), UUID.fromString(postId), (short) score));

    String view = ServletUtil.getValue(req, "view");
    Map<String, String> queryPath = new HashMap<>();
    if (Objects.nonNull(view)) {
      queryPath.put("view", view);
    }
    resp.sendRedirect(ServletUtil.getServletPath(
        req.getContextPath(), PathSelector.POST, postId, queryPath));
  }
}
