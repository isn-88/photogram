package su.itpro.photogram.service.impl;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static su.itpro.photogram.util.image.ImageUtil.checkAndResize;
import static su.itpro.photogram.util.image.ImageUtil.convertToBase64;

import java.io.IOException;
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
import su.itpro.photogram.model.dto.ImageAndBase64Dto;
import su.itpro.photogram.model.dto.ImageIdAndBase64Dto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.entity.Image;
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

  @Override
  public List<ImageAndBase64Dto> findImagesBy(UUID postId) {
    List<Image> images = imageInfoDao.findAllByPostId(postId);
    return images.stream()
        .map(image -> new ImageAndBase64Dto(
            image,
            convertToBase64(imageDataDao.loadImage(image.getId()))
        ))
        .collect(toList());
  }

  public void saveImages(UUID accountId, UUID postId, Collection<Part> files) {
    for (Part file : files) {
      if (checkContentType(file.getContentType())) {
        if (file.getName() != null && file.getName().startsWith("image-")) {
          saveImage(accountId, postId, file, getOrdinal(file.getName()));
        }
      }
    }
  }

  @Override
  public Map<UUID, ImageIdAndBase64Dto> loadPreviewImageFilesBy(List<PostDto> posts) {
    return posts.stream()
        .collect(toMap(PostDto::id, post -> getBase64ImageByPostId(post.id())));
  }

  private void saveImage(UUID accountId, UUID postId, Part file, int ordinal) {
    String fileName = file.getSubmittedFileName();
    Image image = new Image(accountId, postId, fileName, ordinal);
    try {
      imageDataDao.saveImage(image.getId(), checkAndResize(
          file.getInputStream().readAllBytes(), imageWidth, imageHeight
      ));
    } catch (IOException e) {
      log.error("Error save Image with file name {}", fileName);
    }
    imageInfoDao.save(image);
  }

  private ImageIdAndBase64Dto getBase64ImageByPostId(UUID postId) {
    var optionalImageId = imageInfoDao.findPreviewImageId(postId);
    if (optionalImageId.isEmpty()) {
      log.error("For postId [{}] absent image", postId);
      return new ImageIdAndBase64Dto(null, null);
    }
    return loadBase64Image(optionalImageId.get());
  }

  private ImageIdAndBase64Dto loadBase64Image(UUID imageId) {
    return new ImageIdAndBase64Dto(imageId, convertToBase64(imageDataDao.loadImage(imageId)));
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
