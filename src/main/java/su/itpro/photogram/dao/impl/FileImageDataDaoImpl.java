package su.itpro.photogram.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.exception.dao.ImageOperationException;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.util.PropertiesUtil;
import su.itpro.photogram.util.image.ImageUtil;

public class FileImageDataDaoImpl implements ImageDataDao {

  private static final String ROOT_PATH = PropertiesUtil.getProperty("app.root.file.path");
  private static final String IMAGE_TYPE = PropertiesUtil.getProperty("image.output.format");
  private static final int DEFAULT_SHARDING_FACTOR = 1;

  private static final ImageDataDao INSTANCE = new FileImageDataDaoImpl();

  private final Logger log;
  private final int shardingFactor;


  private FileImageDataDaoImpl() {
    log = LoggerFactory.getLogger(FileImageDataDaoImpl.class);
    shardingFactor = getShardingFactor();
  }

  public static ImageDataDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Path saveImage(Image image, byte[] data) {
    Path fullPath = getFullPath(image.getId(), IMAGE_TYPE);
    try {
      Files.createDirectories(fullPath.getParent());
      Files.write(fullPath, data);
    } catch (IOException e) {
      log.error("Error save Image with ID [{}] to Path [{}]", image.getId(), fullPath);
      throw new ImageOperationException(e.getMessage());
    }
    return fullPath;
  }

  @Override
  public InputStream loadImage(Image image) {
    try {
      return Files.newInputStream(image.getFullPath());
    } catch (IOException e) {
      log.error("Error read Image file from path {}", image.getFullPath());
      throw new ImageOperationException(e.getMessage());
    }
  }

  private Path getFullPath(UUID imageId, String type) {
    return Path.of(
        ROOT_PATH,
        getShardingDirectory(imageId.toString()),
        ImageUtil.getImageName(imageId, type)
    );
  }

  private String getShardingDirectory(String id) {
    String hexId = id.replaceAll("-", "");
    return hexId.substring(0, shardingFactor);
  }

  private int getShardingFactor() {
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
