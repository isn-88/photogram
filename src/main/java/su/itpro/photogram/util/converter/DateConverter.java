package su.itpro.photogram.util.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateConverter {

  private static final Logger LOG = LoggerFactory.getLogger(DateConverter.class);


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

  public static LocalDate parseDate(String value) {
    try {
      return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (DateTimeParseException e) {
      LOG.error("Error parse Date from String {}", value);
      return null;
    }
  }

}
