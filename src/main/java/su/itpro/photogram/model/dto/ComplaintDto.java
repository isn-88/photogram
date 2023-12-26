package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.UUID;
import su.itpro.photogram.model.enums.ComplainStatus;

public record ComplaintDto(UUID accountId,
                           String username,
                           UUID postId,
                           String message,
                           ComplainStatus status,
                           Instant createTime,
                           Instant closeTime) {

}
