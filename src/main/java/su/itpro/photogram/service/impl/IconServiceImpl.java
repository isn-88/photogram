package su.itpro.photogram.service.impl;

import java.util.UUID;
import su.itpro.photogram.dao.IconDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.IconMapper;
import su.itpro.photogram.model.dto.IconBase64Dto;
import su.itpro.photogram.model.entity.Icon;
import su.itpro.photogram.service.IconService;
import su.itpro.photogram.util.image.ImageUtil;

public class IconServiceImpl implements IconService {

  private static final int DEFAULT_WIDTH = 200;
  private static final int DEFAULT_HEIGHT = 200;

  private static final IconService INSTANCE = new IconServiceImpl();

  private final IconDao iconDao;
  private final IconMapper iconMapper;


  private IconServiceImpl() {
    iconDao = DaoFactory.INSTANCE.getIconDao();
    iconMapper = IconMapper.getInstance();
  }

  public static IconService getInstance() {
    return INSTANCE;
  }

  @Override
  public IconBase64Dto findById(UUID accountId) {
    Icon icon = iconDao.findById(accountId).orElseGet(() -> new Icon(accountId));
    return iconMapper.mapFrom(icon);
  }

  @Override
  public void saveOrUpdate(UUID accountId, byte[] iconData) {
    if (iconDao == null || iconData.length == 0) {
      return;
    }
    byte[] resizedIcon = ImageUtil.checkAndResize(iconData, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    Icon icon = new Icon(accountId, resizedIcon);
    if (iconDao.exists(accountId)) {
      iconDao.update(icon);
    } else {
      iconDao.save(icon);
    }
  }

  @Override
  public void delete(UUID accountId) {
    iconDao.delete(accountId);
  }

}
