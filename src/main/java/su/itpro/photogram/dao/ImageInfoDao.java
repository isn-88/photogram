package su.itpro.photogram.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.model.entity.Image;

/**
 * Предназначен для работы с мета-информацией фотографий
 */
public interface ImageInfoDao extends BaseDao<UUID, Image> {

  /**
   * Производит поиск id - титульной фотографии для публикации
   *
   * @param postId - id публикации
   * @return - id фотографии
   */
  Optional<UUID> findPreviewImageId(UUID postId);

  /**
   * Поиск всех информации о всех фотографиях по id - публикации
   *
   * @param postId - id публикации
   * @return список метаинформации о фотографиях
   */
  List<Image> findAllByPostId(UUID postId);
}
