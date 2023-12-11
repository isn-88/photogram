package su.itpro.photogram.model.dto;

import java.time.LocalDate;
import java.util.UUID;
import su.itpro.photogram.model.enums.Gender;

public record ProfileEditDto(UUID id,
                             String fullName,
                             LocalDate birthdate,
                             Gender gender,
                             String aboutMe) {

}
