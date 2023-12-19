package su.itpro.photogram.service.impl;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountCreateMapper;
import su.itpro.photogram.mapper.AccountDtoMapper;
import su.itpro.photogram.mapper.AccountUpdateDtoMapper;
import su.itpro.photogram.mapper.ProfileCreateMapper;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.model.dto.LoginCheckExistsDto;
import su.itpro.photogram.model.dto.ProfileCreateDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.validator.AccountUpdateValidator;
import su.itpro.photogram.validator.LoginExistsValidator;
import su.itpro.photogram.validator.PasswordValidator;

public class RegistrationServiceImpl implements RegistrationService {

  private static final RegistrationService INSTANCE = new RegistrationServiceImpl();

  private final AccountDao accountDao;
  private final AccountDtoMapper accountDtoMapper;
  private final AccountCreateMapper accountCreateMapper;
  private final AccountUpdateDtoMapper accountUpdateDtoMapper;
  private final ProfileCreateMapper profileCreateMapper;
  private final ProfileDao profileDao;
  private final AccountUpdateValidator accountUpdateValidator;
  private final PasswordValidator passwordValidator;
  private final LoginExistsValidator loginExistsValidator;


  private RegistrationServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountDtoMapper = AccountDtoMapper.getInstance();
    accountCreateMapper = AccountCreateMapper.getInstance();
    accountUpdateDtoMapper = AccountUpdateDtoMapper.getInstance();
    profileCreateMapper = ProfileCreateMapper.getInstance();
    profileDao = DaoFactory.INSTANCE.getProfileDao();
    accountUpdateValidator = AccountUpdateValidator.getInstance();
    passwordValidator = PasswordValidator.getInstance();
    loginExistsValidator = LoginExistsValidator.getInstance();
  }

  public static RegistrationService getInstance() {
    return INSTANCE;
  }

  @Override
  public void registerNewAccount(AccountChangeDto accountDto) {
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

    Account account = accountDao.save(accountCreateMapper.mapFrom(accountDto));
    Profile profile = profileCreateMapper.mapFrom(
        new ProfileCreateDto(accountDto.fullName(), Gender.UNDEFINE));
    profile.setAccountId(account.getId());
    profileDao.save(profile);
    accountDtoMapper.mapFrom(account);
  }

}
