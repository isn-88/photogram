package su.itpro.photogram.dao;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.entity.Comment;


/**
 * Интерфейс для работы с комментариями пользователей
 */
public interface CommentDao extends BaseDao<UUID, Comment> {

  /**
   * Производит поиск всех сообщений для публикации
   *
   * @param postId - идентификатор публикации
   * @return список всех сообщений
   */
  List<Comment> findAllByPostId(UUID postId);

  /**
   * Удаляет все сообщения для указанной публикации
   *
   * @param postId - идентификатор публикации
   */
  void deleteByPostId(UUID postId);

}
