package su.itpro.photogram.service.impl;

import su.itpro.photogram.exception.PasswordServiceException;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.PasswordService;
import su.itpro.photogram.util.validation.ValidationPasswordUtil;
import su.itpro.photogram.util.validation.ValidationValueUtil;


public class PasswordServiceImpl implements PasswordService {

  private static final PasswordService INSTANCE = new PasswordServiceImpl();
  private static final int MIN_LENGTH_PASSWORD = 8;

  private final AccountService accountService = AccountServiceImpl.getInstance();


  private PasswordServiceImpl() {
  }

  public static PasswordService getInstance() {
    return INSTANCE;
  }

  @Override
  public void changePassword(String username, String currentPassword,
                             String newPassword, String checkPassword) {

    ValidationValueUtil.validationNullOrBlanc(username,
                                              "Username must not be empty");
    Account account = accountService.findByUsername(username);

    if (!(account.getPassword()).equals(currentPassword)) {
      throw new PasswordServiceException("Current password is is incorrect");
    }
    validationInputNewPassword(newPassword, checkPassword);
    accountService.updatePassword(account, newPassword);
  }

  private void validationInputNewPassword(String newPassword, String checkPassword) {
    checkRules(newPassword);
    if (!newPassword.equals(checkPassword)) {
      throw new PasswordServiceException("Passwords don't match");
    }
  }

  private void checkRules(String password) {
    ValidationValueUtil.validationNullOrBlanc(password);
    ValidationPasswordUtil.validationPasswordLength(password, MIN_LENGTH_PASSWORD);
  }

}
