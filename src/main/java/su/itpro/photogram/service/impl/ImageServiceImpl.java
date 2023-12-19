package su.itpro.photogram.service.impl;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.dao.ImageInfoDao;
import su.itpro.photogram.exception.dao.ImageOperationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.thread.SaveImageCallable;
import su.itpro.photogram.thread.SaveImageThreadPool;
import su.itpro.photogram.util.PropertiesUtil;

public class ImageServiceImpl implements ImageService {

  private static final int DEFAULT_IMAGE_WIDTH = 1080;
  private static final int DEFAULT_IMAGE_HEIGHT = 1920;
  private static final String CONTENT_TYPES = "image/jpg,image/jpeg,image/png,image/gif";
  private static final ImageService INSTANCE = new ImageServiceImpl();


  private final Logger log;
  private final ImageDataDao imageDataDao;
  private final ImageInfoDao imageInfoDao;
  private final SaveImageThreadPool threadPool;
  private final int imageWidth;
  private final int imageHeight;


  private ImageServiceImpl() {
    log = LoggerFactory.getLogger(PostServiceImpl.class);
    imageDataDao = DaoFactory.INSTANCE.getImageDataDao();
    imageInfoDao = DaoFactory.INSTANCE.getImageInfoDao();
    threadPool = SaveImageThreadPool.getInstance();
    imageWidth = PropertiesUtil.getInt("app.post.image.width", DEFAULT_IMAGE_WIDTH);
    imageHeight = PropertiesUtil.getInt("app.post.image.height", DEFAULT_IMAGE_HEIGHT);
  }

  public static ImageService getInstance() {
    return INSTANCE;
  }

  @Override
  public List<UUID> findAllImageIdsByPostId(UUID postId) {
    List<Image> images = imageInfoDao.findAllByPostId(postId);
    return images.stream()
        .map(Image::getId)
        .toList();
  }

  @Override
  public Map<UUID, UUID> findPreviewImageIdByPostIds(List<PostDto> posts) {
    return posts.stream()
        .collect(toMap(PostDto::id, post -> getImageIdByPostId(post.id())));
  }

  public void saveImages(UUID accountId, UUID postId, Collection<Part> files) {
    Instant start = Instant.now();
    List<Future<Image>> saveImageFutureList = new ArrayList<>();
    for (Part file : files) {
      if (checkContentType(file.getContentType())) {
        if (file.getName() != null && file.getName().startsWith("image-")) {
          saveImageFutureList.add(saveImage(accountId, postId, file, getOrdinal(file.getName())));
        }
      }
    }
    for (Future<Image> future : saveImageFutureList) {
      try {
        future.get();
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
    }
    log.info("Images was saved in {}ms", Duration.between(start, Instant.now()).toMillis());
  }

  @Override
  public Optional<InputStream> loadImage(UUID imageId) {
    Image image = imageInfoDao.findById(imageId).orElseThrow(
        () -> new ImageOperationException("Image " + imageId + " not found")
    );
    try {
      return Optional.of(imageDataDao.loadImage(image));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void deleteImagesByPostId(UUID postId) {
    List<Image> images = imageInfoDao.findAllByPostId(postId);
    images.forEach(image -> deleteImage(image.getFullPath()));
    imageInfoDao.deleteImagesByPostId(postId);
  }

  private UUID getImageIdByPostId(UUID postId) {
    return imageInfoDao.findPreviewImageId(postId).orElse(null);
  }

  private Future<Image> saveImage(UUID accountId, UUID postId, Part file, int ordinal) {
    return threadPool.getPool().submit(
        new SaveImageCallable(imageDataDao, imageInfoDao, accountId, postId,
                              file, ordinal, imageWidth, imageHeight)
    );
  }

  private void deleteImage(Path path) {
    if (Files.exists(path)) {
      try {
        Files.delete(path);
      } catch (IOException e) {
        log.error("Error delete Image from path {}", path);
      }
    }
  }

  private boolean checkContentType(String type) {
    if (type == null) {
      return false;
    }
    String[] types = CONTENT_TYPES.split(",");
    return Arrays.asList(types).contains(type);
  }

  private int getOrdinal(String filename) {
    return Integer.parseInt(filename.substring("image-".length()));
  }

}
