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
import su.itpro.photogram.dao.ImageDao;
import su.itpro.photogram.datasource.ImageRepository;
import su.itpro.photogram.datasource.impl.FileImageRepositoryImpl;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.ImageBase64Dto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.service.ImageService;

public class ImageServiceImpl implements ImageService {

  private static final int IMAGE_WIDTH = 1080;

  private static final int IMAGE_HEIGHT = 1920;

  private static final String CONTENT_TYPES = "image/jpg,image/jpeg,image/png,image/gif";

  private static final ImageService INSTANCE = new ImageServiceImpl();


  private final Logger log;

  private final ImageRepository imageRepository;

  private final ImageDao imageDao;


  private ImageServiceImpl() {
    log = LoggerFactory.getLogger(PostServiceImpl.class);
    imageRepository = FileImageRepositoryImpl.getInstance();
    imageDao = DaoFactory.INSTANCE.getImageDao();
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
      imageRepository.saveImage(image, checkAndResize(
          file.getInputStream().readAllBytes(),
          IMAGE_WIDTH, IMAGE_HEIGHT
      ));
    } catch (IOException e) {
      log.error("Error save Image with file name {}", file.getSubmittedFileName());
    }
    imageDao.save(image);
    return image;
  }

  private ImageBase64Dto getBase64ImageByPostId(UUID postId) {
    return loadBase64Image(imageDao.findPreviewImageId(postId));
  }

  private ImageBase64Dto loadBase64Image(UUID imageId) {
    return new ImageBase64Dto(imageId, convertToBase64(imageRepository.loadImage(imageId)));
  }

  private boolean checkContentType(String type) {
    if (type == null) {
      return false;
    }
    String[] types = CONTENT_TYPES.split(",");
    return Arrays.asList(types).contains(type);
  }

}
