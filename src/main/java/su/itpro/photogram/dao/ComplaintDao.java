package su.itpro.photogram.dao;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.ComplainFindDto;
import su.itpro.photogram.model.entity.Complaint;

/**
 * Интерфейс по работе с обращениями пользователей
 */
public interface ComplaintDao {

  /**
   * Проверяет наличие обращения пользователя на публикацию
   *
   * @param accountId - идентификатор аккаунта пользователя
   * @param postId - идентификатор публикации
   * @return - true если имеется обращение
   */
  boolean exist(UUID accountId, UUID postId);

  /**
   * Поиск обращений с фильтрацией по статусу
   *
   * @param complainFindDto - фильтр
   * @return - список обращений
   */
  List<Complaint> findAllBy(ComplainFindDto complainFindDto);

  /**
   * Сохраняет обращение
   *
   * @param complaint - обращение
   */
  void save(Complaint complaint);

  /**
   * Обновляет обращение
   *
   * @param complaint - обращение
   */
  void update(Complaint complaint);

  /**
   * Удаляет все обращения для публикации
   *
   * @param postId - идентификатор обращения
   */
  void deleteAllByPostId(UUID postId);
}
