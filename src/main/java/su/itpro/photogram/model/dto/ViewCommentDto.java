package su.itpro.photogram.model.dto;

import java.time.LocalDateTime;

public record ViewCommentDto(String username, String message, LocalDateTime createDate) {

}
