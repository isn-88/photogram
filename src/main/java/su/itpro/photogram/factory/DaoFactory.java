package su.itpro.photogram.factory;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.dao.impl.AccountDaoImpl;
import su.itpro.photogram.dao.impl.ProfileDaoImpl;

public enum DaoFactory {

  INSTANCE;

  private final AccountDao accountDaoImpl = AccountDaoImpl.getInstance();

  private final ProfileDao profileDaoImpl = ProfileDaoImpl.getInstance();

  DaoFactory() {
  }

  public AccountDao getAccountDao() {
    return accountDaoImpl;
  }

  public ProfileDao getProfileDao() {
    return profileDaoImpl;
  }
}
