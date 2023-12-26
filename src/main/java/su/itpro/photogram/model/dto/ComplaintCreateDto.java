package su.itpro.photogram.model.dto;

import java.util.UUID;

public record ComplaintCreateDto(UUID accountId,
                                 UUID postId,
                                 String message) {

}
