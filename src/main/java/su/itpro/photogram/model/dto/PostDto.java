package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import su.itpro.photogram.model.entity.Post;

public record PostDto(UUID id,
                      UUID accountId,
                      Boolean isActive,
                      String description,
                      Instant createDate) {


  public static PostDto of(Post post) {
    if (Objects.isNull(post)) {
      return null;
    }
    return new PostDto(
        post.getId(),
        post.getAccountId(),
        post.getActive(),
        post.getDescription(),
        post.getCreateDate()
    );
  }

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
