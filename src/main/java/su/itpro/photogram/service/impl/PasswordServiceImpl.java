package su.itpro.photogram.service.impl;


import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.service.PasswordServiceException;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.ChangePasswordDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.PasswordService;
import su.itpro.photogram.validator.PasswordChangeValidator;
import su.itpro.photogram.validator.ValidationResult;

public class PasswordServiceImpl implements PasswordService {

  private static final PasswordService INSTANCE = new PasswordServiceImpl();
  private static final int MIN_LENGTH_PASSWORD = 8;

  private final AccountDao accountDao;
  private final PasswordChangeValidator passwordChangeValidator;


  private PasswordServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    passwordChangeValidator = PasswordChangeValidator.getInstance();
  }

  public static PasswordService getInstance() {
    return INSTANCE;
  }


  @Override
  public void changePassword(ChangePasswordDto dto) {
    ValidationResult validationResult = passwordChangeValidator.validate(dto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    Account account = accountDao.findById(dto.accountId()).orElseThrow(
        () -> new PasswordServiceException("Account not found")
    );

    passwordChangeValidator.validateMatch(account.getPassword(), dto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    accountDao.changePassword(account, dto.newPassword());
  }

}
