package su.itpro.photogram.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.Part;
import su.itpro.photogram.model.dto.PostDto;

/**
 * Интерфейс для работы с изображениями
 *
 */
public interface ImageService {

  /**
   * Производит идентификаторов изображений по указанному идентификатору публикации
   *
   * @param postId - идентификатор публикации
   * @return - список идентификаторов изображений
   */
  List<UUID> findAllImageIdsByPostId(UUID postId);

  /**
   * Производит поиск идентификаторов титульных изображений для указанных публикаций
   *
   * @param posts - публикации
   * @return - идентификаторы изображений
   */
  Map<UUID, UUID> findPreviewImageIdByPostIds(List<PostDto> posts);

  /**
   * Сохраняет все изображения
   *
   * @param accountId - идентификатор аккаунта
   * @param postId - идентификатор публикации
   * @param files - набор файлов с изображениями
   */
  void saveImages(UUID accountId, UUID postId, Collection<Part> files);

  /**
   * Отдаёт поток для получения изображения
   *
   * @param imageId - идентификатор изображения
   * @return - поток данных
   */
  Optional<InputStream> loadImage(UUID imageId);

  /**
   * Удаляет все изображения для указанной публикации
   *
   * @param postId - идентификатор публикации
   */
  void deleteImagesByPostId(UUID postId);
}
