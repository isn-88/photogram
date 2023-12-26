package su.itpro.photogram.model.dto;

import java.util.UUID;
import su.itpro.photogram.model.enums.PostStatus;

public record PostUpdateDto(UUID id, PostStatus status, String description) {

}
