package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.exception.AccountServiceException;

public class AccountServiceImpl implements AccountService {

  private static final AccountService INSTANCE = new AccountServiceImpl();


  private final Logger log;

  private final AccountDao accountDao;


  private AccountServiceImpl() {
    log = LoggerFactory.getLogger(PostServiceImpl.class);
    accountDao = DaoFactory.INSTANCE.getAccountDao();
  }

  public static AccountService getInstance() {
    return INSTANCE;
  }

  @Override
  public Account registerNewAccount(String phone, String email, String username, String password) {
    Account newAccount = new Account(phone, email, username, password);
    accountDao.save(newAccount);
    return newAccount;
  }

  @Override
  public void updatePassword(final Account account, String newPassword) {
    accountDao.changePassword(account, newPassword);
  }

  @Override
  public Account findByUsername(String username) {
    return accountDao.findByUsername(username).orElseThrow(
        () -> {
          log.info("findByUsername username: " + username);
          return new AccountServiceException("Account not found by username: " + username);
        }
    );
  }

  @Override
  public Account findByEmail(String email) {
    return accountDao.findByEmail(email).orElseThrow(
        () -> {
          log.info("findByUsername email: " + email);
          return new AccountServiceException("Account not found by email: " + email);
        }
    );
  }

  @Override
  public Account findByPhone(String phone) {
    return accountDao.findByPhone(phone).orElseThrow(
        () -> {
          log.info("findByUsername phone: " + phone);
          return new AccountServiceException("Account not found by phone: " + phone);
        }
    );
  }

  @Override
  public Account update(String username, String newPhone, String newEmail, String newUsername) {
    Account account = accountDao.findByUsername(username).orElseThrow(
        () -> {
          log.info("findByUsername username: " + username);
          return new AccountServiceException("Account not found by username: " + username);
        }
    );

    optionalOf(newPhone).ifPresent(account::setPhone);
    optionalOf(newEmail).ifPresent(account::setEmail);
    optionalOf(newUsername).ifPresent(account::setUsername);

    accountDao.update(account);
    return account;
  }
}
