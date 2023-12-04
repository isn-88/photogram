package su.itpro.photogram.dao;

import java.util.UUID;
import su.itpro.photogram.model.entity.Image;


/**
 * Предназначен для работы с изображениями
 */
public interface ImageDao extends BaseDao<UUID, Image> {


  /**
   * Производит поиск титульного изображения для публикации
   *
   * @param postId - id публикации
   * @return - id изображения
   */
  UUID findPreviewImageId(UUID postId);


}
