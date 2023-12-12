package su.itpro.photogram.model.dto;

import java.util.UUID;

public record PostUpdateDto(UUID id, Boolean isActive, String description) {

}
