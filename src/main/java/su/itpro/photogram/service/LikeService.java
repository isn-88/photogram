package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.dto.LikeDto;

/**
 * Сервис для работы с оценками публикаций пользователями
 */
public interface LikeService {

  /**
   * Получить оценку пользователя
   *
   * @param accountId - идентификатор аккаунта
   * @param postId - идентификатор публикации
   * @return - оценка пользователя
   */
  int getScore(UUID accountId, UUID postId);

  /**
   * Общий счёт оценок публикации
   *
   * @param postId - идентификатор публикации
   * @return - суммарная оценка
   */
  int getTotalScore(UUID postId);

  /**
   * Изменяет оценку пользователя
   *
   * @param dto - данные для обновления
   */
  void update(LikeDto dto);

  /**
   * Удаляет все оценки для указанной публикации
   *
   * @param postId - идентификатор публикации
   */
  void deleteAllByPostId(UUID postId);
}
