package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.service.AccountServiceException;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountDtoMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.model.dto.LoginCheckExistsDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.validator.AccountUpdateValidator;
import su.itpro.photogram.validator.LoginExistsValidator;

public class AccountServiceImpl implements AccountService {

  private static final AccountService INSTANCE = new AccountServiceImpl();

  private final AccountDao accountDao;
  private final AccountDtoMapper accountDtoMapper;
  private final AccountUpdateValidator accountUpdateValidator;
  private final LoginExistsValidator loginExistsValidator;
  private final Logger log;


  private AccountServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountDtoMapper = AccountDtoMapper.getInstance();
    accountUpdateValidator = AccountUpdateValidator.getInstance();
    loginExistsValidator = LoginExistsValidator.getInstance();
    log = LoggerFactory.getLogger(PostServiceImpl.class);
  }

  public static AccountService getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean checkStatus(UUID accountId) {
    try {
      return accountDao.checkStatus(accountId);
    } catch (Exception e) {
      log.error("Error from check account status {}", e.getMessage());
      return false;
    }
  }

  @Override
  public AccountDto findByUsername(String username) {
    return accountDtoMapper.mapFrom(
        accountDao.findByUsername(username).orElseThrow(
            () -> {
              log.info("findByUsername username: " + username);
              return new AccountServiceException("Account not found by username: " + username);
            }
        ));
  }

  @Override
  public AccountDto findByEmail(String email) {
    return accountDtoMapper.mapFrom(
        accountDao.findByEmail(email).orElseThrow(
            () -> {
              log.info("findByUsername email: " + email);
              return new AccountServiceException("Account not found by email: " + email);
            }
        ));
  }

  @Override
  public AccountDto findByPhone(String phone) {
    return accountDtoMapper.mapFrom(
        accountDao.findByPhone(phone).orElseThrow(
            () -> {
              log.info("findByUsername phone: " + phone);
              return new AccountServiceException("Account not found by phone: " + phone);
            }
        ));
  }

  @Override
  public AccountDto update(UUID accountId, AccountUpdateDto updateDto) {

    var validationResult = accountUpdateValidator.validate(updateDto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    Account account = accountDao.findById(accountId).orElseThrow(
        () -> {
          log.info("Account findById: " + accountId);
          return new AccountServiceException("Account not found by id: " + accountId);
        }
    );

    var loginCheckExistsDto = prepareExistsDto(account, updateDto);
    var existsResultDto = accountDao.exists(loginCheckExistsDto);
    var validationExistsResult = loginExistsValidator.validate(existsResultDto);
    if (validationExistsResult.hasErrors()) {
      throw new ValidationException(validationExistsResult.getErrors());
    }

    optionalOf(updateDto.phone()).ifPresent(account::setPhone);
    optionalOf(updateDto.email()).ifPresent(account::setEmail);
    optionalOf(updateDto.username()).ifPresent(account::setUsername);

    accountDao.update(account);
    return accountDtoMapper.mapFrom(account);
  }

  private LoginCheckExistsDto prepareExistsDto(Account account, AccountUpdateDto updateDto) {
    return new LoginCheckExistsDto(
        checkFieldUpdate(account.getPhone(), updateDto.phone()),
        checkFieldUpdate(account.getEmail(), updateDto.email()),
        checkFieldUpdate(account.getUsername(), updateDto.username())
    );

  }

  private String checkFieldUpdate(String accountField, String updateField) {
    return (Objects.nonNull(updateField) && !Objects.equals(updateField, accountField))
           ? updateField
           : null;
  }

}
