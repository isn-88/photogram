package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.UUID;
import su.itpro.photogram.model.enums.PostStatus;

public record PostDto(UUID id,
                      UUID accountId,
                      PostStatus status,
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
