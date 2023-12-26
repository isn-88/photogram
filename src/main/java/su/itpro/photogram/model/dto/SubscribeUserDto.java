package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.UUID;

public record SubscribeUserDto(UUID accountId,
                               UUID subscribeId,
                               String subscribeUsername,
                               Instant time) {

}
