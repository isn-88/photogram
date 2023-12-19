package su.itpro.photogram.service.impl;


import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.service.PasswordServiceException;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.ChangePasswordDto;
import su.itpro.photogram.model.dto.PasswordMatchDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.PasswordService;
import su.itpro.photogram.validator.PasswordChangeValidator;
import su.itpro.photogram.validator.PasswordMatchValidator;
import su.itpro.photogram.validator.ValidationResult;

public class PasswordServiceImpl implements PasswordService {

  private static final PasswordService INSTANCE = new PasswordServiceImpl();

  private final AccountDao accountDao;
  private final PasswordChangeValidator passwordChangeValidator;
  private final PasswordMatchValidator passwordMatchValidator;


  private PasswordServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    passwordChangeValidator = PasswordChangeValidator.getInstance();
    passwordMatchValidator = PasswordMatchValidator.getInstance();
  }

  public static PasswordService getInstance() {
    return INSTANCE;
  }


  @Override
  public void changePassword(ChangePasswordDto dto) {
    ValidationResult validationChangeResult = passwordChangeValidator.validate(dto);
    if (validationChangeResult.hasErrors()) {
      throw new ValidationException(validationChangeResult.getErrors());
    }

    Account account = accountDao.findById(dto.accountId()).orElseThrow(
        () -> new PasswordServiceException("Account not found")
    );

    var passwordMatchDto = new PasswordMatchDto(account.getPassword(), dto.currentPassword());
    ValidationResult validationMatchResult = passwordMatchValidator.validate(passwordMatchDto);
    if (validationMatchResult.hasErrors()) {
      throw new ValidationException(validationMatchResult.getErrors());
    }

    accountDao.changePassword(account, dto.newPassword());
  }

}
