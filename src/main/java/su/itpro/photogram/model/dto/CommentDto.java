package su.itpro.photogram.model.dto;

import java.util.UUID;

public record CommentDto(UUID id,
                         UUID accountId,
                         UUID postId,
                         String elapsedTimeInfo,
                         Boolean isDeleted,
                         String message) {

}
