package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.UUID;

public record PostDto(UUID id,
                      UUID accountId,
                      Boolean isActive,
                      String description,
                      Instant createDate) {


  public String getDescriptionForPage() {
    return changeLineSeparator(description);
  }

  private static String changeLineSeparator(String value) {
    if (value == null) {
      return null;
    }
    return value.replaceAll(System.lineSeparator(), "<br/>");
  }

}
