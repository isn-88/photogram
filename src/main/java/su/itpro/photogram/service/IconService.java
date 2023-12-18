package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.dto.IconBase64Dto;

/**
 * Интерфейс для работы с иконками пользователей
 */
public interface IconService {

  /**
   * Производить поиск иконки
   *
   * @param accountId - идентификатор аккаунта
   * @return - данные изображения
   */
  IconBase64Dto findById(UUID accountId);

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

}
