package su.itpro.photogram.dao;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.model.enums.PostStatus;


/**
 * Предназначен для работы с публикациями пользователя
 */
public interface PostDao extends BaseDao<UUID, Post> {

  /**
   * Получает список последних публикаций пользователя
   *
   * @param accountId - id аккаунта пользователя
   * @param limit - ограничение количества получаемых публикаций
   * @return - список публикаций
   */
  List<Post> findTopByAccountIdAndLimit(UUID accountId, List<PostStatus> statuses, int limit);

  /**
   * Производит подсчёт активных публикаций
   *
   * @param accountId - идентификатор аккаунта
   * @return количество публикаций
   */
  int countPosts(UUID accountId);

}
