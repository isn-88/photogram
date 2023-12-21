package su.itpro.photogram.service;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для работы с иконками пользователей
 */
public interface IconService {

  /**
   * Сохраняет или обновляет иконку
   *
   * @param accountId - идентификатор аккаунта
   * @param iconData - данные изображения
   */
  void saveOrUpdate(UUID accountId, byte[] iconData);

  /**
   * Удаляет иконку
   *
   * @param accountId - идентификатор аккаунта
   */
  void delete(UUID accountId);

  /**
   * Отдаёт поток для получения иконки
   *
   * @param iconId - идентификатор иконки
   * @return - поток данных
   */
  Optional<InputStream> loadIcon(UUID iconId);
}
