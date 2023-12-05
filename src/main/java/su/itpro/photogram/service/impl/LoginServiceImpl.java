package su.itpro.photogram.service.impl;

import su.itpro.photogram.dao.exception.DaoException;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.LoginService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.exception.PasswordServiceException;
import su.itpro.photogram.util.validation.ValidationPasswordUtil;
import su.itpro.photogram.util.validation.ValidationValueUtil;

public class LoginServiceImpl implements LoginService {

  private static final LoginService INSTANCE = new LoginServiceImpl();

  private final AccountService accountService = AccountServiceImpl.getInstance();

  private final ProfileService profileService = ProfileServiceImpl.getInstance();

  private LoginServiceImpl() {
  }

  public static LoginService getInstance() {
    return INSTANCE;
  }

  @Override
  public Account login(String login, String password) {
    ValidationValueUtil.validationNullOrBlanc(login, "Login must be empty");
    ValidationValueUtil.validationNullOrBlanc(password, "Password must be empty");

    Account account;
    try {
      if (login.contains("@")) {
        account = accountService.findByEmail(login);
      } else if (login.startsWith("+")) {
        account = accountService.findByPhone(login.substring(1));
      } else if (Character.isDigit(login.charAt(0))) {
        account = accountService.findByPhone(login);
      } else {
        account = accountService.findByUsername(login);
      }
    } catch (DaoException e) {
      throw new PasswordServiceException("Login or password incorrect");
    }

    ValidationPasswordUtil.validationLogin(account, password);
    account.setProfile(profileService.loadProfile(account.getId()));
    return account;
  }
}
