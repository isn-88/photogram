package su.itpro.photogram.service;

import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;

/**
 * Интерфейс сервиса по работе с аккаунтами
 */
public interface AccountService {

  /**
   * Производит поиск аккаунта по уникальному имени пользователя
   *
   * @param username - имя пользователя
   * @return аккаунт
   */
  AccountDto findByUsername(String username);

  /**
   * Производит поиск аккаунта по email-у пользователя
   *
   * @param email - email пользователя
   * @return аккаунт
   */
  AccountDto findByEmail(String email);

  /**
   * Производит поиск аккаунта по номеру телефона пользователя
   *
   * @param phone - номер телефона пользователя
   * @return аккаунт
   */
  AccountDto findByPhone(String phone);

  /**
   * Обновляет данные для входа в ЛК
   *
   * @param username - имя пользователя
   * @param dto - обновляемые данные
   * @return обновлённый аккаунт
   */
  AccountDto update(String username, AccountUpdateDto dto);

}
