package su.itpro.photogram.model.dto;

import java.util.UUID;

public record LikeDto(UUID accountId, UUID postId, short score) {

}
