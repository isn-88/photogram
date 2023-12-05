package su.itpro.photogram.factory;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.dao.ImageInfoDao;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.dao.impl.AccountDaoImpl;
import su.itpro.photogram.dao.impl.FileImageDataDaoImpl;
import su.itpro.photogram.dao.impl.ImageInfoDaoImpl;
import su.itpro.photogram.dao.impl.PostDaoImpl;
import su.itpro.photogram.dao.impl.ProfileDaoImpl;

public enum DaoFactory {

  INSTANCE;

  private final AccountDao accountDaoImpl;
  private final ProfileDao profileDaoImpl;
  private final PostDao postDaoImpl;
  private final ImageInfoDao imageInfoDaoImpl;
  private final ImageDataDao imageDataDaoImpl;


  DaoFactory() {
    accountDaoImpl = AccountDaoImpl.getInstance();
    profileDaoImpl = ProfileDaoImpl.getInstance();
    postDaoImpl = PostDaoImpl.getInstance();
    imageInfoDaoImpl = ImageInfoDaoImpl.getInstance();
    imageDataDaoImpl = FileImageDataDaoImpl.getInstance();
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

  public ImageInfoDao getImageDao() {
    return imageInfoDaoImpl;
  }

  public ImageDataDao getImageDataDao() {
    return imageDataDaoImpl;
  }
}
