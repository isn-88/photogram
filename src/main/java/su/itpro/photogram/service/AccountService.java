package su.itpro.photogram.service;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountFindDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.model.enums.Status;

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
   * Производит поиск аккаунта по идентификатору
   *
   * @param id - идентификатор аккаунта
   * @return аккаунт
   */
  AccountDto findById(UUID id);

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
   * Производит поиск аккаунтов подходящих под заданные фильтры
   *
   * @param searchDto - фильтры поиска
   * @return - список аккаунтов
   */
  List<AccountDto> findAllBy(AccountFindDto searchDto);

  /**
   * Обновляет данные для входа в ЛК
   *
   * @param accountId - идентификатор аккаунта
   * @param dto - обновляемые данные
   * @return обновлённый аккаунт
   */
  AccountDto update(UUID accountId, AccountUpdateDto dto);

  /**
   * Обновляет статус аккаунта
   *
   * @param id - идентификатор аккаунта
   * @param status - обновляемый статус
   */
  void updateStatus(UUID id, Status status);
}
