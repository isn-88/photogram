package su.itpro.photogram.util.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public class DateConverter {

  private DateConverter() {
  }

  public static Date toSqlDate(LocalDate date) {
    return (date != null) ? Date.valueOf(date) : null;
  }

  public static Optional<LocalDate> fromSqlDate(Date date) {
    if (date != null) {
      return Optional.of(date.toLocalDate());
    }
    return Optional.empty();
  }

  public static Optional<Instant> fromTimestamp(Timestamp timestamp) {
    if (timestamp != null) {
      return Optional.of(timestamp.toInstant());
    }
    return Optional.empty();
  }

}
