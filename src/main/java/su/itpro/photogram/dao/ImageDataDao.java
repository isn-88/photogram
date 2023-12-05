package su.itpro.photogram.dao;

import java.util.UUID;

/**
 * Предназначен для работы с хранилищем изображений
 */
public interface ImageDataDao {

  /**
   * Сохраняет изображение
   *
   * @param imageId - id изображения
   * @param data - массив байт
   */
  void saveImage(UUID imageId, byte[] data);

  /**
   * Загружает изображение
   *
   * @param imageId id изображения
   * @return массив байт
   */
  byte[] loadImage(UUID imageId);

}
