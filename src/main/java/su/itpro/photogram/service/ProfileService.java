package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.model.dto.ProfileEditDto;

/**
 * Предназначен для работы с профилем пользователя
 */
public interface ProfileService {

  /**
   * Загружает профиль по идентификатору аккаунта
   *
   * @param accountId - идентификатор аккаунта
   * @return - профиль пользователя
   */
  ProfileDto loadProfile(UUID accountId);

  /**
   * Обновляет данные в профиле пользователя
   *
   * @param dto - данные для обновления
   * @return обновлённый профиль
   */
  ProfileDto update(ProfileEditDto dto);

}
