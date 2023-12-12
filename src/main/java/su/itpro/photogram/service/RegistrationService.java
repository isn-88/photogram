package su.itpro.photogram.service;

import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.RegistrationDto;

/**
 * Интерфейс регистрации новых пользователей
 */
public interface RegistrationService {

  /**
   * Регистрирует нового пользователя
   *
   * @param dto - данные из формы регистрации
   * @return созданный аккаунт
   */
  AccountDto registerNewAccount(RegistrationDto dto);

}
