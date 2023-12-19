package su.itpro.photogram.util;

import java.time.Duration;
import java.time.Instant;

public final class CommentUtil {

  private static final String CREATE_PREFIX = "Опубликовано: %s";
  private static final String CHANGE_PREFIX = "Изменено: %s";
  private static final String DELETE_PREFIX = "Удалено: %s";
  private static final String NOW_TEXT = "сейчас";
  private static final String ELAPSED_PATTERN = "%s %s. назад";


  private CommentUtil() {
  }

  public static String getCommentElapsedTime(Instant createTime,
                                             Instant changeTime,
                                             Boolean isDeleted) {
    if (createTime == null) {
      return "";
    }

    Instant now = Instant.now();
    if (isDeleted != null && isDeleted) {
      return DELETE_PREFIX.formatted(timeElapsed(changeTime, now));
    }
    if (changeTime != null) {
      return CHANGE_PREFIX.formatted(timeElapsed(changeTime, now));
    } else {
      return CREATE_PREFIX.formatted(timeElapsed(createTime, now));
    }
  }

  private static String timeElapsed(Instant startTime, Instant endTime) {
    if (endTime == null) {
      return "-";
    }
    long elapsed;
    if ((elapsed = Duration.between(startTime, endTime).toDays()) > 0) {
      return ELAPSED_PATTERN.formatted(elapsed, "д");
    } else if ((elapsed = Duration.between(startTime, endTime).toHours()) > 0) {
      return ELAPSED_PATTERN.formatted(elapsed, "ч");
    } else if ((elapsed = Duration.between(startTime, endTime).toMinutes()) > 0) {
      return ELAPSED_PATTERN.formatted(elapsed, "м");
    } else {
      return NOW_TEXT;
    }
  }

}
