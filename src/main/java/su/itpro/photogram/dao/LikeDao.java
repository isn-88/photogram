package su.itpro.photogram.dao;

import java.util.UUID;
import su.itpro.photogram.model.Like;

/**
 * Интерфейс по работе с оценками публикаций
 */
public interface LikeDao {

  int getScore(UUID id, UUID postId);

  /**
   * Посчитывает общий балл оценок для публикации
   *
   * @param postId - идентификатор публикации
   * @return - суммарный балл
   */
  int sumScore(UUID postId);

  /**
   * Проверяет наличие оценки публикации пользователем
   *
   * @param like - Оценка
   * @return - true если пользователь оценил публикацию
   */
  boolean exists(Like like);

  /**
   * Сохраняет оценку пользователя
   *
   * @param like - Оценка
   */
  void save(Like like);

  /**
   * Обновляет оценку пользователя
   *
   * @param like - Оценка
   * @return true если обновление прошло успешно
   */
  boolean update(Like like);

  /**
   * Удаляет все оценки пользователей указанной для публикации
   *
   * @param postId - идентификатор публикации
   */
  void deleteAllByPostId(UUID postId);
}
