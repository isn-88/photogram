package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;

/**
 * Интерфейс сервиса по работе с аккаунтами
 */
public interface AccountService {

  /**
   * Проверка статуса аккаунта на доступность
   *
   * @param accountId - идентификатор аккаунта
   * @return - true если аккаунт действующий (не заблокирован)
   */
  boolean checkStatus(UUID accountId);

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
   * Проверяет наличие аккаунта с указанным номером телефона
   *
   * @param phone - номер телефона
   * @return - true если указанный номер телефона зарегистрирован
   */
  boolean existsByPhone(String phone);

  /**
   * Проверяет наличие аккаунта с указанной электронной почтой
   *
   * @param email - электронная почта
   * @return - true если указанная почта зарегистрирована
   */
  boolean existsByEmail(String email);

  /**
   * Проверяет наличие аккаунта с указанным именем пользователя
   *
   * @param username - уникальное имя пользователя
   * @return true если данное имя пользователя зарегистрировано
   */
  boolean existsByUsername(String username);

  /**
   * Обновляет данные для входа в ЛК
   *
   * @param username - имя пользователя
   * @param dto - обновляемые данные
   * @return обновлённый аккаунт
   */
  AccountDto update(String username, AccountUpdateDto dto);

}
