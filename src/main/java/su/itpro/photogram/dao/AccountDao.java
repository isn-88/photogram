package su.itpro.photogram.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.model.dto.AccountFindDto;
import su.itpro.photogram.model.dto.LoginCheckExistsDto;
import su.itpro.photogram.model.dto.LoginExistsResultDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Status;

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
   * Поиск аккаунта по фильтрам
   *
   * @param searchDto - набор фильтров
   * @return - список найденных аккаунтов
   */
  List<Account> findAllBy(AccountFindDto searchDto);

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
   * Проверяет наличие зарегистрированного Аккаунта
   * с указанными номером телефона, email и именем пользователя
   *
   * @param dto - проверяемые данные
   * @return - результат проверки
   */
  LoginExistsResultDto exists(LoginCheckExistsDto dto);

  /**
   * Обновляет пароль для указанного аккаунта
   *
   * @param account аккаунт
   * @param password пароль
   */
  void changePassword(Account account, String password);

  /**
   * Обновляет статус аккаунта
   *
   * @param id - идентификатор статуса
   * @param status - обновляемый статус
   */
  void updateStatus(UUID id, Status status);
}
