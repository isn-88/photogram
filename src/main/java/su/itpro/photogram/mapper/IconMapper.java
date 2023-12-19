package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.IconBase64Dto;
import su.itpro.photogram.model.entity.Icon;
import su.itpro.photogram.util.image.ImageUtil;

public class IconMapper implements Mapper<Icon, IconBase64Dto> {

  private static final IconMapper INSTANCE = new IconMapper();

  private IconMapper() {
  }

  public static IconMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public IconBase64Dto mapFrom(Icon icon) {
    byte[] data = icon.getData();
    if (data != null && data.length > 0) {
      return new IconBase64Dto(icon.getAccountId(), ImageUtil.convertToBase64(icon.getData()));
    }
    return new IconBase64Dto(icon.getAccountId(), "");
  }
}
