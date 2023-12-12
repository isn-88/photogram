package su.itpro.photogram.service.impl;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountMapper;
import su.itpro.photogram.mapper.ProfileMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.RegistrationDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.service.RegistrationService;
import su.itpro.photogram.validator.RegistrationValidator;

public class RegistrationServiceImpl implements RegistrationService {

  private static final RegistrationService INSTANCE = new RegistrationServiceImpl();

  private final AccountDao accountDao;
  private final AccountMapper accountMapper;
  private final ProfileMapper profileMapper;
  private final ProfileDao profileDao;
  private final RegistrationValidator validator;


  private RegistrationServiceImpl() {
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountMapper = AccountMapper.getInstance();
    profileMapper = ProfileMapper.getInstance();
    profileDao = DaoFactory.INSTANCE.getProfileDao();
    validator = RegistrationValidator.getInstance();
  }

  public static RegistrationService getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountDto registerNewAccount(RegistrationDto dto) {
    var validationResult = validator.validate(dto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    Account account = accountDao.save(accountMapper.mapToAccount(dto));
    Profile profile = profileMapper.mapToProfile(dto);
    profile.setAccountId(account.getId());
    account.setProfile(profileDao.save(profile));
    return accountMapper.mapToAccountDto(account);
  }

}
