package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.impl.ImageServiceImpl;
import su.itpro.photogram.util.ServletUtil;

@WebServlet("/image/download/*")
public class ImageDownloadServlet extends HttpServlet {

  private static final int DEFAULT_BUFFER_SIZE = 512 * 1024; // 512Kb

  private final ImageService imageService;

  public ImageDownloadServlet() {
    imageService = ImageServiceImpl.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String imageId = ServletUtil.variableOfQueryPath(req.getPathInfo());
    imageService.loadImage(UUID.fromString(imageId))
        .ifPresentOrElse(
            inputStream -> {
              resp.setContentType("application/octet-stream");
              ServletUtil.writeDate(inputStream, resp, DEFAULT_BUFFER_SIZE);
            },
            () -> resp.setStatus(404)
        );
  }

}
