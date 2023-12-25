package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.model.enums.Role;

/**
 * Интерфейс регистрации новых пользователей
 */
public interface RegistrationService {

  /**
   * Регистрирует нового пользователя
   *
   * @param dto - данные из формы регистрации
   * @param role - тип аккаунта
   * @return - идентификатор созданного аккаунта
   */
  UUID createNewAccount(AccountChangeDto dto, Role role);

}
