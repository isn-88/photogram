package su.itpro.photogram.dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.dao.exception.ImageOperationException;
import su.itpro.photogram.util.PropertiesUtil;

public class FileImageDataDaoImpl implements ImageDataDao {

  private static final String ROOT_PATH = PropertiesUtil.getProperty("app.root.file.path");

  private static final String IMAGE_FORMAT_BY_DEFAULT = ".jpg";

  private static final int DEFAULT_SHARDING_FACTOR = 1;

  private static final ImageDataDao INSTANCE = new FileImageDataDaoImpl();


  private final int shardingSize;

  private final Logger log;


  private FileImageDataDaoImpl() {
    shardingSize = getShardingSize();
    log = LoggerFactory.getLogger(FileImageDataDaoImpl.class);
  }

  public static ImageDataDao getInstance() {
    return INSTANCE;
  }

  @Override
  public void saveImage(UUID imageId, byte[] data) {
    Path fullPath = getFullPath(imageId.toString(), IMAGE_FORMAT_BY_DEFAULT);
    try {
      Files.createDirectories(fullPath.getParent());
      Files.write(fullPath, data);
    } catch (IOException e) {
      log.error("Error save Image with ID [{}] to Path [{}]", imageId, fullPath);
      throw new ImageOperationException(e.getMessage());
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
