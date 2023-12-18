package su.itpro.photogram.service.impl;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountCreateMapper;
import su.itpro.photogram.mapper.AccountDtoMapper;
import su.itpro.photogram.mapper.ProfileCreateMapper;
import su.itpro.photogram.model.dto.CreateAccountDto;
import su.itpro.photogram.model.dto.ProfileCreateDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.validator.RegistrationValidator;

public class RegistrationServiceImpl implements RegistrationService {

  private static final RegistrationService INSTANCE = new RegistrationServiceImpl();

  private final AccountDao accountDao;
  private final AccountDtoMapper accountDtoMapper;
  private final AccountCreateMapper accountCreateMapper;
  private final ProfileCreateMapper profileCreateMapper;
  private final ProfileDao profileDao;
  private final RegistrationValidator registrationValidator;


  private RegistrationServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountDtoMapper = AccountDtoMapper.getInstance();
    accountCreateMapper = AccountCreateMapper.getInstance();
    profileCreateMapper = ProfileCreateMapper.getInstance();
    profileDao = DaoFactory.INSTANCE.getProfileDao();
    registrationValidator = RegistrationValidator.getInstance();
  }

  public static RegistrationService getInstance() {
    return INSTANCE;
  }

  @Override
  public void registerNewAccount(CreateAccountDto dto) {
    var validationResult = registrationValidator.validate(dto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    Account account = accountDao.save(accountCreateMapper.mapFrom(dto));
    Profile profile = profileCreateMapper.mapFrom(
        new ProfileCreateDto(dto.fullName(), Gender.UNDEFINE));
    profile.setAccountId(account.getId());
    account.setProfile(profileDao.save(profile));
    accountDtoMapper.mapFrom(account);
  }

}
