package su.itpro.photogram.dao;

import java.util.UUID;
import su.itpro.photogram.model.entity.Icon;

/**
 * Интерфейс для работы с фотографиями профиля пользователя
 */
public interface IconDao extends BaseDao<UUID, Icon> {

  /**
   * Проверяет наличие сохранённой фотографии
   *
   * @param accountId - идентификатор аккаунта
   * @return - true при наличии сохранённой фотографии
   */
  boolean exists(UUID accountId);

}
