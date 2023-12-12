package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.service.AccountServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;

public class AccountServiceImpl implements AccountService {

  private static final AccountService INSTANCE = new AccountServiceImpl();

  private final AccountDao accountDao;
  private final AccountMapper accountMapper;
  private final Logger log;


  private AccountServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountMapper = AccountMapper.getInstance();
    log = LoggerFactory.getLogger(PostServiceImpl.class);
  }

  public static AccountService getInstance() {
    return INSTANCE;
  }


  @Override
  public AccountDto findByUsername(String username) {
    return accountMapper.mapToAccountDto(
        accountDao.findByUsername(username).orElseThrow(
            () -> {
              log.info("findByUsername username: " + username);
              return new AccountServiceException("Account not found by username: " + username);
            }
        ));
  }

  @Override
  public AccountDto findByEmail(String email) {
    return accountMapper.mapToAccountDto(
        accountDao.findByEmail(email).orElseThrow(
            () -> {
              log.info("findByUsername email: " + email);
              return new AccountServiceException("Account not found by email: " + email);
            }
        ));
  }

  @Override
  public AccountDto findByPhone(String phone) {
    return accountMapper.mapToAccountDto(
        accountDao.findByPhone(phone).orElseThrow(
            () -> {
              log.info("findByUsername phone: " + phone);
              return new AccountServiceException("Account not found by phone: " + phone);
            }
        ));
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
    return accountMapper.mapToAccountDto(account);
  }
}
