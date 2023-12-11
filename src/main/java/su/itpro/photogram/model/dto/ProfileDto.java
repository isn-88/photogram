package su.itpro.photogram.model.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;

public record ProfileDto(UUID accountId,
                         String fullName,
                         Gender gender,
                         LocalDate birthdate,
                         String aboutMe) {

  public static ProfileDto of(Profile profile) {
    if (Objects.isNull(profile)) {
      return null;
    }
    return new ProfileDto(
        profile.getAccountId(),
        profile.getFullName(),
        profile.getGender(),
        profile.getBirthdate(),
        profile.getAboutMe()
    );
  }

}
