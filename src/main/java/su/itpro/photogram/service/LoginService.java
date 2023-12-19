package su.itpro.photogram.service;

import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.LoginDto;

/**
 * Интерфейс сервиса по входу пользователя на сайт
 */
public interface LoginService {

  /**
   * Осуществляет аутентификацию пользователя
   *
   * @param dto - данные для входа
   * @return - аккаунт
   */
  AccountDto login(LoginDto dto);

}
