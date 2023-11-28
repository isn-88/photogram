package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.AccountServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;

public class AccountServiceImpl implements AccountService {

  private static final AccountService INSTANCE = new AccountServiceImpl();

  private final AccountDao accountDao = DaoFactory.INSTANCE.getAccountDao();

  private AccountServiceImpl() {
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
  public void updatePassword(Account account, String newPassword) {
    accountDao.changePassword(account, newPassword);
  }

  @Override
  public Account findByUsername(String username) {
    return accountDao.findByUsername(username)
        .orElseThrow(() -> new AccountServiceException("Account not found"));
  }

  @Override
  public Account findByEmail(String email) {
    return accountDao.findByEmail(email)
        .orElseThrow(() -> new AccountServiceException("Account not found"));
  }

  @Override
  public Account findByPhone(String phone) {
    return accountDao.findByPhone(phone)
        .orElseThrow(() -> new AccountServiceException("Account not found"));
  }

  @Override
  public Account update(String username, String newPhone, String newEmail, String newUsername) {
    Account account = accountDao.findByUsername(username)
        .orElseThrow(() -> new AccountServiceException("Account not found "));

    optionalOf(newPhone).ifPresent(account::setPhone);
    optionalOf(newEmail).ifPresent(account::setEmail);
    optionalOf(newUsername).ifPresent(account::setUsername);

    accountDao.update(account);
    return account;
  }
}
