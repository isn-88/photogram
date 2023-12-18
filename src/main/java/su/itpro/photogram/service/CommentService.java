package su.itpro.photogram.service;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.CommentCreateDto;
import su.itpro.photogram.model.dto.CommentDto;

/**
 * Интерфейс сервиса по работе с комментариями пользователей к постам
 */
public interface CommentService {

  /**
   * Получает список комментариев по указанному идентификатору публикации
   *
   * @param postId идентификатор публикации
   * @return список MessageDto
   */
  List<CommentDto> findAllBy(UUID postId);

  /**
   * Сохраняет новый комментарий
   *
   * @param dto - данные для создания комментария
   */
  void saveComment(CommentCreateDto dto);

  /**
   * Удаляет все комментарии к посту
   *
   * @param postId - идентификатор поста
   */
  void deleteAllCommentsByPostId(UUID postId);
}
