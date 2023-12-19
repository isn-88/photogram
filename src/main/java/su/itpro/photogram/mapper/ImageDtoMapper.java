package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.ImageDto;
import su.itpro.photogram.model.entity.Image;

public class ImageDtoMapper implements Mapper<Image, ImageDto> {

  private static final ImageDtoMapper INSTANCE = new ImageDtoMapper();


  private ImageDtoMapper() {
  }

  public static ImageDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public ImageDto mapFrom(Image image) {
    return new ImageDto(image.getId(), image.getId().toString());
  }
}
