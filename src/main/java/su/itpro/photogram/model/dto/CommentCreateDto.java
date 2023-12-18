package su.itpro.photogram.model.dto;

import java.util.UUID;

public record CommentCreateDto(UUID accountId, UUID postId, String message) {

}
