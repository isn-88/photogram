package su.itpro.photogram.model.dto;

import java.util.UUID;

public record PostWithPreviewImage(UUID postId, String base64Image) {

}
