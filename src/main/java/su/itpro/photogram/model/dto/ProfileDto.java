package su.itpro.photogram.model.dto;

import java.time.LocalDate;
import java.util.UUID;
import su.itpro.photogram.model.enums.Gender;

public record ProfileDto(UUID accountId,
                         String fullName,
                         Gender gender,
                         LocalDate birthdate,
                         String aboutMe) {

}
