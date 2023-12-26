package su.itpro.photogram.servlet;

import java.util.UUID;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.service.IconService;
import su.itpro.photogram.service.impl.IconServiceImpl;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/icon/download/*")
public class IconDownloadServlet extends HttpServlet {

  private static final int DEFAULT_BUFFER_SIZE = 128 * 1024; // 128Kb
  private final IconService iconService;


  public IconDownloadServlet() {
    iconService = IconServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    String iconId = ServletUtil.variableOfQueryPath(req.getPathInfo());
    iconService.loadIcon(UUID.fromString(iconId)).ifPresentOrElse(
        inputStream -> {
          resp.setContentType("application/octet-stream");
          ServletUtil.writeDate(inputStream, resp, DEFAULT_BUFFER_SIZE);
        },
        () -> resp.setStatus(404)
    );

  }
}
