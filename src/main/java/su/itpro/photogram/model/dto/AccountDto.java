package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.UUID;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.model.enums.Status;

public record AccountDto(UUID id,
                         String phone,
                         String email,
                         String username,
                         Role role,
                         Status status,
                         Boolean isVerifiedPhone,
                         Boolean isVerifiedEmail,
                         Instant createDate) {


}
