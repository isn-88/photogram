package su.itpro.photogram.model.dto;

import java.util.UUID;
import su.itpro.photogram.model.enums.ComplainStatus;

public record ComplaintActionDto(UUID accountId,
                                 UUID postId,
                                 ComplainStatus status) {

}
