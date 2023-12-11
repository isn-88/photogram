package su.itpro.photogram.service;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.PostCreateDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.PostUpdateDto;

/**
 * Интерфейс для обработки и публикации постов пользователя
 */
public interface PostService {

  /**
   * Создаёт новую публикацию
   *
   * @param username - имя пользователя
   * @param dto - содержит данные для создания публикации
   */
  void createNewPost(String username, PostCreateDto dto);

  /**
   * Производит поиск последних публикаций пользователя
   * с учётом флага, который позволяет включать неактивные посты,
   * а так же с ограничением количества запрашиваемых постов
   *
   * @param accountId - идентификатор аккаунта пользователя
   * @param onlyIsActive - true = только активные посты
   * @param limit - ограничение по количеству запрашиваемых постов
   * @return - список постов пользователя
   */
  List<PostDto> findTopPostIdByAccountIdAndLimit(UUID accountId, boolean onlyIsActive, int limit);

  /**
   * Производит поиск поста по указанному идентификатору
   *
   * @param postId - идентификатор поста
   * @return - пост
   */
  PostDto findById(UUID postId);

  /**
   * Обновляет описание и активность поста
   *
   * @param dto - данные для обновления
   */
  void update(PostUpdateDto dto);

}
