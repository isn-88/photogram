package su.itpro.photogram.service.impl;

import java.util.Optional;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.exception.DaoException;
import su.itpro.photogram.exception.service.LoginServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Status;
import su.itpro.photogram.service.LoginService;
import su.itpro.photogram.util.validator.ValidationPasswordUtil;
import su.itpro.photogram.util.validator.ValidationValueUtil;

public class LoginServiceImpl implements LoginService {

  private static final LoginService INSTANCE = new LoginServiceImpl();

  private final AccountDao accountDao;
  private final AccountMapper accountMapper;


  private LoginServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountMapper = AccountMapper.getInstance();
  }

  public static LoginService getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountDto login(String login, String password) {
    ValidationValueUtil.validationNullOrBlanc(login, "Login must be empty");
    ValidationValueUtil.validationNullOrBlanc(password, "Password must be empty");

    Optional<Account> accountOpt;
    try {
      if (login.contains("@")) {
        accountOpt = accountDao.findByEmail(login);
      } else if (login.startsWith("+")) {
        accountOpt = accountDao.findByPhone(login.substring(1));
      } else if (Character.isDigit(login.charAt(0))) {
        accountOpt = accountDao.findByPhone(login);
      } else {
        accountOpt = accountDao.findByUsername(login);
      }
    } catch (DaoException e) {
      throw new LoginServiceException("Login or password incorrect");
    }

    Account account = accountOpt.orElseThrow(
        () -> new LoginServiceException("Account not found"));

    if (!account.getStatus().equals(Status.ACTIVE)) {
      throw new LoginServiceException("Account not active");
    }

    ValidationPasswordUtil.validationLogin(account, password);
    return accountMapper.mapToAccountDto(account);
  }
}
