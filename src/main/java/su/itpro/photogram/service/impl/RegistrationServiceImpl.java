package su.itpro.photogram.service.impl;

import java.util.UUID;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountCreateMapper;
import su.itpro.photogram.mapper.AccountUpdateDtoMapper;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.model.dto.LoginCheckExistsDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.validator.AccountUpdateValidator;
import su.itpro.photogram.validator.LoginExistsValidator;
import su.itpro.photogram.validator.PasswordValidator;

public class RegistrationServiceImpl implements RegistrationService {

  private static final RegistrationService INSTANCE = new RegistrationServiceImpl();

  private final AccountDao accountDao;
  private final AccountCreateMapper accountCreateMapper;
  private final AccountUpdateDtoMapper accountUpdateDtoMapper;
  private final AccountUpdateValidator accountUpdateValidator;
  private final PasswordValidator passwordValidator;
  private final LoginExistsValidator loginExistsValidator;


  private RegistrationServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountCreateMapper = AccountCreateMapper.getInstance();
    accountUpdateDtoMapper = AccountUpdateDtoMapper.getInstance();
    accountUpdateValidator = AccountUpdateValidator.getInstance();
    passwordValidator = PasswordValidator.getInstance();
    loginExistsValidator = LoginExistsValidator.getInstance();
  }

  public static RegistrationService getInstance() {
    return INSTANCE;
  }

  @Override
  public UUID createNewAccount(AccountChangeDto accountDto, Role role) {
    AccountUpdateDto accountUpdateDto = accountUpdateDtoMapper.mapFrom(accountDto);
    var validationRegistrationResult = accountUpdateValidator.validate(accountUpdateDto);
    if (validationRegistrationResult.hasErrors()) {
      throw new ValidationException(validationRegistrationResult.getErrors());
    }

    var validationPasswordResult = passwordValidator.validate(accountDto);
    if (validationPasswordResult.hasErrors()) {
      throw new ValidationException(validationPasswordResult.getErrors());
    }

    LoginCheckExistsDto existsDto =
        new LoginCheckExistsDto(accountDto.phone(), accountDto.email(), accountDto.username());
    var validationExistsResult = loginExistsValidator.validate(accountDao.exists(existsDto));
    if (validationExistsResult.hasErrors()) {
      throw new ValidationException(validationExistsResult.getErrors());
    }
    Account account = accountCreateMapper.mapFrom(accountDto);
    account.setRole(role);
    accountDao.save(account);
    return account.getId();
  }

}
