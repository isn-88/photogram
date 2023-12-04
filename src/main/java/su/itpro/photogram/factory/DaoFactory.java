package su.itpro.photogram.factory;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.ImageDao;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.dao.impl.AccountDaoImpl;
import su.itpro.photogram.dao.impl.ImageDaoImpl;
import su.itpro.photogram.dao.impl.PostDaoImpl;
import su.itpro.photogram.dao.impl.ProfileDaoImpl;

public enum DaoFactory {

  INSTANCE;

  private final AccountDao accountDaoImpl = AccountDaoImpl.getInstance();

  private final ProfileDao profileDaoImpl = ProfileDaoImpl.getInstance();

  private final PostDao postDaoImpl = PostDaoImpl.getInstance();

  private final ImageDao imageDaoImpl = ImageDaoImpl.getInstance();


  DaoFactory() {
  }

  public AccountDao getAccountDao() {
    return accountDaoImpl;
  }

  public ProfileDao getProfileDao() {
    return profileDaoImpl;
  }

  public PostDao getPostDao() {
    return postDaoImpl;
  }

  public ImageDao getImageDao() {
    return imageDaoImpl;
  }
}
