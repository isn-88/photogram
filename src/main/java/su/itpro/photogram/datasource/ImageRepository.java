package su.itpro.photogram.datasource;

import java.util.UUID;
import su.itpro.photogram.model.entity.Image;

public interface ImageRepository {

  void saveImage(Image image, byte[] data);

  byte[] loadImage(UUID imageId);

}
