package su.itpro.photogram.dao;

import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.model.entity.Image;


/**
 * Предназначен для работы с мета-информацией фотографий
 */
public interface ImageInfoDao extends BaseDao<UUID, Image> {


  /**
   * Производит поиск титульной фотографии для публикации
   *
   * @param postId - id публикации
   * @return - id фотографии
   */
  Optional<UUID> findPreviewImageId(UUID postId);


}
