package su.itpro.photogram.service.impl;

import static java.util.stream.Collectors.toMap;
import static su.itpro.photogram.util.image.ImageUtil.checkAndResize;
import static su.itpro.photogram.util.image.ImageUtil.convertToBase64;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.dao.ImageInfoDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.ImageBase64Dto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.util.PropertiesUtil;

public class ImageServiceImpl implements ImageService {

  private static final int DEFAULT_IMAGE_WIDTH = 1080;
  private static final int DEFAULT_IMAGE_HEIGHT = 1920;
  private static final String CONTENT_TYPES = "image/jpg,image/jpeg,image/png,image/gif";
  private static final ImageService INSTANCE = new ImageServiceImpl();


  private final Logger log;
  private final ImageDataDao imageDataDao;
  private final ImageInfoDao imageInfoDao;
  private final int imageWidth;
  private final int imageHeight;


  private ImageServiceImpl() {
    log = LoggerFactory.getLogger(PostServiceImpl.class);
    imageDataDao = DaoFactory.INSTANCE.getImageDataDao();
    imageInfoDao = DaoFactory.INSTANCE.getImageDao();
    imageWidth = PropertiesUtil.getInt("app.post.image.width", DEFAULT_IMAGE_WIDTH);
    imageHeight = PropertiesUtil.getInt("app.post.image.height", DEFAULT_IMAGE_HEIGHT);
  }

  public static ImageService getInstance() {
    return INSTANCE;
  }

  public List<Image> saveImages(Account account, Post post, Collection<Part> files) {
    List<Image> images = new ArrayList<>();
    int ordinal = 0;
    for (Part file : files) {
      if (checkContentType(file.getContentType())) {
        images.add(saveImage(account, post, file, ++ordinal));
      }
    }
    return images;
  }

  @Override
  public Map<UUID, ImageBase64Dto> loadPreviewImageFilesBy(List<Post> posts) {
    return posts.stream()
        .collect(toMap(Post::getId, post -> getBase64ImageByPostId(post.getId())));
  }

  private Image saveImage(Account account, Post post, Part file, int ordinal) {
    String fileName = file.getSubmittedFileName();
    Image image = new Image(account.getId(), post.getId(), fileName, ordinal);
    try {
      imageDataDao.saveImage(image.getId(), checkAndResize(
          file.getInputStream().readAllBytes(), imageWidth, imageHeight
      ));
    } catch (IOException e) {
      log.error("Error save Image with file name {}", fileName);
    }
    imageInfoDao.save(image);
    return image;
  }

  private ImageBase64Dto getBase64ImageByPostId(UUID postId) {
    var optionalImageId = imageInfoDao.findPreviewImageId(postId);
    if (optionalImageId.isEmpty()) {
      log.error("For postId [{}] absent image", postId);
      return new ImageBase64Dto(null, null);
    }
    return loadBase64Image(optionalImageId.get());
  }

  private ImageBase64Dto loadBase64Image(UUID imageId) {
    return new ImageBase64Dto(imageId, convertToBase64(imageDataDao.loadImage(imageId)));
  }

  private boolean checkContentType(String type) {
    if (type == null) {
      return false;
    }
    String[] types = CONTENT_TYPES.split(",");
    return Arrays.asList(types).contains(type);
  }

}
