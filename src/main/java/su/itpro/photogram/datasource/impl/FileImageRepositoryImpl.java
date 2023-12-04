package su.itpro.photogram.datasource.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.datasource.ImageRepository;
import su.itpro.photogram.datasource.exception.FileRepositoryException;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.util.PropertiesUtil;

public class FileImageRepositoryImpl implements ImageRepository {

  private static final String ROOT_PATH = PropertiesUtil.getProperty("app.root.file.path");

  private static final String IMAGE_FORMAT_BY_DEFAULT = ".jpg";

  private static final int DEFAULT_SHARDING_FACTOR = 1;

  private static final ImageRepository INSTANCE = new FileImageRepositoryImpl();


  private final int shardingSize;

  private final Logger log;

  private FileImageRepositoryImpl() {
    shardingSize = getShardingSize();
    log = LoggerFactory.getLogger(FileImageRepositoryImpl.class);
  }

  public static ImageRepository getInstance() {
    return INSTANCE;
  }


  @Override
  public void saveImage(Image image, byte[] data) {
    try {
      Path fullPath = getFullPath(image.getId().toString(), IMAGE_FORMAT_BY_DEFAULT);
      Files.createDirectories(fullPath.getParent());
      Files.write(fullPath, data);
    } catch (IOException e) {
      log.error("Error save Image {}", image.getFileName());
      throw new FileRepositoryException(e.getMessage());
    }
  }

  @Override
  public byte[] loadImage(UUID imageId) {
    Path path = getFullPath(imageId.toString(), IMAGE_FORMAT_BY_DEFAULT);
    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      log.error("Error read Image file from path {}", path);
    }
    return new byte[0];
  }

  private Path getFullPath(String id, String type) {
    return Path.of(ROOT_PATH, getShardingDirectory(id), id + type);
  }

  private String getShardingDirectory(String id) {
    String hexId = id.replaceAll("-", "");
    return hexId.substring(0, shardingSize);
  }

  private int getShardingSize() {
    String value = PropertiesUtil.getProperty("app.sharding.size");
    int sharding = DEFAULT_SHARDING_FACTOR;
    try {
      sharding = Integer.parseInt(value.strip());
    } catch (NumberFormatException e) {
      log.warn("Error parse app.sharding.size, use default value: {}", DEFAULT_SHARDING_FACTOR);
    }
    return sharding;
  }

}
