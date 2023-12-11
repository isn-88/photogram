package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.validator.ValidationPasswordUtil.validationPasswordLength;
import static su.itpro.photogram.util.validator.ValidationValueUtil.validationNullOrBlanc;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.service.LoginServiceException;
import su.itpro.photogram.exception.service.PasswordServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.ChangePasswordDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.PasswordService;

public class PasswordServiceImpl implements PasswordService {

  private static final PasswordService INSTANCE = new PasswordServiceImpl();
  private static final int MIN_LENGTH_PASSWORD = 8;

  private final AccountDao accountDao;


  private PasswordServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
  }

  public static PasswordService getInstance() {
    return INSTANCE;
  }

  @Override
  public void changePassword(ChangePasswordDto dto) {

    validationNullOrBlanc(dto.username(), "Username must not be empty");
    Account account = accountDao.findByUsername(dto.username()).orElseThrow(
        () -> new PasswordServiceException("Account not found")
    );

    if (!(account.getPassword()).equals(dto.currentPassword())) {
      throw new PasswordServiceException("Current password is incorrect");
    }
    validationInputNewPassword(dto.newPassword(), dto.checkPassword());
    accountDao.changePassword(account, dto.newPassword());
  }

  private void validationInputNewPassword(String newPassword, String checkPassword) {
    checkRules(newPassword);
    if (!newPassword.equals(checkPassword)) {
      throw new LoginServiceException("Passwords don't match");
    }
  }

  private void checkRules(String password) {
    validationNullOrBlanc(password);
    validationPasswordLength(password, MIN_LENGTH_PASSWORD);
  }

}
