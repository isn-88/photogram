package su.itpro.photogram.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.IconDao;
import su.itpro.photogram.exception.dao.ImageOperationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.entity.Icon;
import su.itpro.photogram.service.IconService;
import su.itpro.photogram.util.image.ImageUtil;

public class IconServiceImpl implements IconService {

  private static final int DEFAULT_WIDTH = 200;
  private static final int DEFAULT_HEIGHT = 200;

  private static final IconService INSTANCE = new IconServiceImpl();

  private final IconDao iconDao;


  private IconServiceImpl() {
    iconDao = DaoFactory.INSTANCE.getIconDao();
  }

  public static IconService getInstance() {
    return INSTANCE;
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

  @Override
  public Optional<InputStream> loadIcon(UUID iconId) {
    try {
      Icon icon = iconDao.findById(iconId).orElseThrow(
          () -> new ImageOperationException("Icon " + iconId + " not found"));
      return Optional.of(new ByteArrayInputStream(icon.getData()));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

}
