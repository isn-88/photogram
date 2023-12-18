package su.itpro.photogram.thread;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.Callable;
import javax.servlet.http.Part;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.dao.ImageInfoDao;
import su.itpro.photogram.exception.dao.ImageOperationException;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.util.image.ImageUtil;

public class SaveImageCallable implements Callable<Image> {

  private final ImageDataDao imageDataDao;
  private final ImageInfoDao imageInfoDao;
  private final UUID accountId;
  private final UUID postId;
  private final Part file;
  private final int ordinal;
  private final int imageWidth;
  private final int imageHeight;


  public SaveImageCallable(ImageDataDao imageDataDao,
                           ImageInfoDao imageInfoDao,
                           UUID accountId,
                           UUID postId,
                           Part file,
                           int ordinal,
                           int imageWidth,
                           int imageHeight) {
    this.imageDataDao = imageDataDao;
    this.imageInfoDao = imageInfoDao;
    this.accountId = accountId;
    this.postId = postId;
    this.file = file;
    this.ordinal = ordinal;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
  }

  @Override
  public Image call() {
    String fileName = file.getSubmittedFileName();
    Image image = new Image(accountId, postId, fileName, null, ordinal);
    try (InputStream inputStream = file.getInputStream()) {
      Path path = imageDataDao.saveImage(
          image, ImageUtil.checkAndResize(inputStream.readAllBytes(), imageWidth, imageHeight));
      image.setFullPath(path);
      return imageInfoDao.save(image);
    } catch (IOException e) {
      LoggerFactory.getLogger(SaveImageCallable.class)
          .error("Error save Image with file name {}", fileName);
      throw new ImageOperationException(e.getMessage());
    }
  }
}
