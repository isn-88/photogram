package su.itpro.photogram.service;

import su.itpro.photogram.model.dto.CreateAccountDto;

/**
 * Интерфейс регистрации новых пользователей
 */
public interface RegistrationService {

  /**
   * Регистрирует нового пользователя
   *
   * @param dto - данные из формы регистрации
   */
  void registerNewAccount(CreateAccountDto dto);

}
