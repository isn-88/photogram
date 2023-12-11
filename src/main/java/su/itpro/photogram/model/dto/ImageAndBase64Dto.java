package su.itpro.photogram.model.dto;

import su.itpro.photogram.model.entity.Image;

public record ImageAndBase64Dto(Image image, String base64Image) {

}

