package su.itpro.photogram.model.dto;

import java.time.LocalDate;
import java.util.UUID;
import su.itpro.photogram.model.enums.Gender;

public record ProfileEditDto(UUID id,
                             String fullName,
                             LocalDate birthdate,
                             Gender gender,
                             String aboutMe) {

  public ProfileEditDto(UUID id, String fullName) {
    this(id, fullName, null, null, null);
  }

  public ProfileEditDto(UUID id,
                        String fullName,
                        LocalDate birthdate,
                        Gender gender,
                        String aboutMe) {
    this.id = id;
    this.fullName = fullName;
    this.birthdate = birthdate;
    this.gender = gender;
    this.aboutMe = aboutMe;
  }
}
