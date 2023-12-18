package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.service.AccountServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountDtoMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;

public class AccountServiceImpl implements AccountService {

  private static final AccountService INSTANCE = new AccountServiceImpl();

  private final AccountDao accountDao;
  private final AccountDtoMapper accountDtoMapper;
  private final Logger log;


  private AccountServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountDtoMapper = AccountDtoMapper.getInstance();
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
  public boolean existsByPhone(String phone) {
    return accountDao.existsByPhone(phone);
  }

  @Override
  public boolean existsByEmail(String email) {
    return accountDao.existsByEmail(email);
  }

  @Override
  public boolean existsByUsername(String username) {
    return accountDao.existsByUsername(username);
  }

  @Override
  public AccountDto update(String username, AccountUpdateDto dto) {
    Account account = accountDao.findByUsername(username).orElseThrow(
        () -> {
          log.info("findByUsername username: " + username);
          return new AccountServiceException("Account not found by username: " + username);
        }
    );

    optionalOf(dto.phone()).ifPresent(account::setPhone);
    optionalOf(dto.email()).ifPresent(account::setEmail);
    optionalOf(dto.username()).ifPresent(account::setUsername);

    accountDao.update(account);
    return accountDtoMapper.mapFrom(account);
  }
}
