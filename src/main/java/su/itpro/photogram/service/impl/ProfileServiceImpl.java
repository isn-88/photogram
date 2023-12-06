package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import java.util.UUID;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ProfileService;
import su.itpro.photogram.service.exception.ProfileServiceException;

public class ProfileServiceImpl implements ProfileService {

  private static final ProfileServiceImpl INSTANCE = new ProfileServiceImpl();

  private final AccountService accountService;

  private final ProfileDao profileDao;


  private ProfileServiceImpl() {
    accountService = AccountServiceImpl.getInstance();
    profileDao = DaoFactory.INSTANCE.getProfileDao();
  }

  public static ProfileServiceImpl getInstance() {
    return INSTANCE;
  }


  public Profile registerNewProfile(UUID accountId, String fullName) {
    Profile newProfile = new Profile(accountId, fullName);
    profileDao.save(newProfile);
    return newProfile;
  }

  public Profile loadProfile(UUID accountId) {
    return profileDao.findById(accountId)
        .orElseThrow(() -> new ProfileServiceException("Profile not found"));
  }

  //TODO add all profile values
  public void update(String username, String fullName) {
    Account account = accountService.findByUsername(username);
    account.setProfile(loadProfile(account.getId()));
    Profile profile = account.getProfile();
    optionalOf(fullName).ifPresent(profile::setFullName);

    profileDao.update(profile);
  }

}
