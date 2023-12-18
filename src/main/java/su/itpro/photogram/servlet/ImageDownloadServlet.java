package su.itpro.photogram.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

  private static final int DEFAULT_BUFFER_SIZE = 1024 * 1024; // 1Mb

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
              writeImage(inputStream, resp);
            },
            () -> resp.setStatus(404)
        );
  }

  private void writeImage(InputStream inputStream, HttpServletResponse resp) {
    try (inputStream; OutputStream outputStream = resp.getOutputStream()) {
      byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
      int read;
      while ((read = inputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, read);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
