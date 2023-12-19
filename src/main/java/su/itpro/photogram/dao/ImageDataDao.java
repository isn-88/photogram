package su.itpro.photogram.dao;

import java.io.InputStream;
import java.nio.file.Path;
import su.itpro.photogram.model.entity.Image;

/**
 * Предназначен для работы с хранилищем изображений
 */
public interface ImageDataDao {

  /**
   * Сохраняет изображение
   *
   * @param image - информация об изображении
   * @param data  - массив байт
   * @return - полный путь к файлу изображения
   */
  Path saveImage(Image image, byte[] data);

  /**
   * Загружает изображение
   *
   * @param image информация об изображении
   * @return поток данных
   */
  InputStream loadImage(Image image);

}
