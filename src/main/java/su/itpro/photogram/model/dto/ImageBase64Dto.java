package su.itpro.photogram.model.dto;

import java.util.UUID;

public record ImageBase64Dto(UUID imageId, String base64Image) {
}
