package su.itpro.photogram.service;

import su.itpro.photogram.model.dto.AccountDto;

/**
 * Интерфейс сервиса по входу пользователя на сайт
 */
public interface LoginService {

  /**
   * Осуществляет аутентификацию пользователя
   *
   * @param login - имя пользователя, номер телефона или email
   * @param password - пароль
   * @return - аккаунт
   */
  AccountDto login(String login, String password);

}
