package su.itpro.photogram.factory;

import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.dao.CommentDao;
import su.itpro.photogram.dao.IconDao;
import su.itpro.photogram.dao.ImageDataDao;
import su.itpro.photogram.dao.ImageInfoDao;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.dao.SubscribeDao;
import su.itpro.photogram.dao.impl.AccountDaoImpl;
import su.itpro.photogram.dao.impl.CommentDaoImpl;
import su.itpro.photogram.dao.impl.FileImageDataDaoImpl;
import su.itpro.photogram.dao.impl.IconDaoImpl;
import su.itpro.photogram.dao.impl.ImageInfoDaoImpl;
import su.itpro.photogram.dao.impl.PostDaoImpl;
import su.itpro.photogram.dao.impl.ProfileDaoImpl;
import su.itpro.photogram.dao.impl.SubscribeDaoImpl;

public enum DaoFactory {

  INSTANCE;

  private final AccountDao accountDao;
  private final ProfileDao profileDao;
  private final PostDao postDao;
  private final ImageInfoDao imageInfoDao;
  private final ImageDataDao imageDataDao;
  private final CommentDao commentDao;
  private final IconDao iconDao;
  private final SubscribeDao subscribeDao;


  DaoFactory() {
    accountDao = AccountDaoImpl.getInstance();
    profileDao = ProfileDaoImpl.getInstance();
    postDao = PostDaoImpl.getInstance();
    imageInfoDao = ImageInfoDaoImpl.getInstance();
    imageDataDao = FileImageDataDaoImpl.getInstance();
    commentDao = CommentDaoImpl.getInstance();
    iconDao = IconDaoImpl.getInstance();
    subscribeDao = SubscribeDaoImpl.getInstance();
  }

  public AccountDao getAccountDao() {
    return accountDao;
  }

  public ProfileDao getProfileDao() {
    return profileDao;
  }

  public PostDao getPostDao() {
    return postDao;
  }

  public ImageInfoDao getImageInfoDao() {
    return imageInfoDao;
  }

  public ImageDataDao getImageDataDao() {
    return imageDataDao;
  }

  public CommentDao getCommentDao() {
    return commentDao;
  }

  public IconDao getIconDao() {
    return iconDao;
  }

  public SubscribeDao getSubscribeDao() {
    return subscribeDao;
  }
}
