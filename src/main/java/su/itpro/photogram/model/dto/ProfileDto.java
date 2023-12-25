package su.itpro.photogram.model.dto;

import java.time.LocalDate;
import java.util.UUID;
import su.itpro.photogram.model.enums.Gender;

public record ProfileDto(UUID accountId,
                         String fullName,
                         Gender gender,
                         LocalDate birthdate,
                         String aboutMe) {

  public String getAboutMeForPage() {
    return changeLineSeparator(aboutMe);
  }

  private static String changeLineSeparator(String value) {
    if (value == null) {
      return null;
    }
    return value.replaceAll(System.lineSeparator(), "<br/>");
  }

}
