package su.itpro.photogram.dao;

import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.model.entity.Account;

/**
 * Предназначен для работы с аккаунтом пользователя
 */
public interface AccountDao extends BaseDao<UUID, Account> {

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
   * Обновляет пароль для указанного аккаунта
   *
   * @param account аккаунт
   * @param password пароль
   */
  void changePassword(Account account, String password);

}
