package su.itpro.photogram.dao;

import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.model.entity.Account;

/**
 * Предназначен для работы с аккаунтом пользователя
 */
public interface AccountDao extends BaseDao<UUID, Account> {

  /**
   * Проверка статуса аккаунта на доступность
   *
   * @param accountId - идентификатор аккаунта
   * @return true если аккаунт действующий (не заблокирован)
   */
  boolean checkStatus(UUID accountId);

  /**
   * Производит поиск аккаунта по имени пользователя
   *
   * @param username имя пользователя
   * @return Optional of Account
   */
  Optional<Account> findByUsername(String username);

  /**
   * Производит поиск аккаунта по email-адресу
   *
   * @param email пользователя
   * @return Optional of Account
   */
  Optional<Account> findByEmail(String email);

  /**
   * Производит поиск аккаунта по номеру телефона
   *
   * @param phone пользователя
   * @return Optional of Account
   */
  Optional<Account> findByPhone(String phone);

  /**
   * Проверяет существование аккаунта с указанным номером телефона
   *
   * @param phone номер телефона пользователя
   * @return true если аккаунт с данным номером телефона существует
   */
  boolean existsByPhone(String phone);

  /**
   * Проверяет существование аккаунта с указанным email
   *
   * @param email электронная почта пользователя
   * @return true если аккаунт с данной почтой существует
   */
  boolean existsByEmail(String email);

  /**
   * Проверяет существование аккаунта с указанным username
   *
   * @param username уникальное имя пользователя
   * @return true если аккаунт с данным username существует
   */
  boolean existsByUsername(String username);

  /**
   * Обновляет пароль для указанного аккаунта
   *
   * @param account аккаунт
   * @param password пароль
   */
  void changePassword(Account account, String password);

}
