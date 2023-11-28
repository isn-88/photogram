package su.itpro.photogram.util.converter;

import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class DateConverterTest {

  @Test
  void toSqlDate_isNull() {
    LocalDate localDate = null;

    Date actual = DateConverter.toSqlDate(localDate);

    assertNull(actual);
  }

  @Test
  void toSqlDate_isPresent() {
    LocalDate localDate = LocalDate.now();

    Date actual = DateConverter.toSqlDate(localDate);

    assertNotNull(actual);
    assertEquals(localDate, actual.toLocalDate());
  }

  @Test
  void fromSqlDate_isNull() {
    Date date = null;

    Optional<LocalDate> actual = DateConverter.fromSqlDate(date);

    assertNotNull(actual);
    assertFalse(actual.isPresent());
  }

  @Test
  void fromSqlDate_isPresent() {
    Date date = Date.valueOf(LocalDate.now());

    Optional<LocalDate> actual = DateConverter.fromSqlDate(date);

    assertNotNull(actual);
    assertTrue(actual.isPresent());
    assertEquals(date.toLocalDate(), actual.get());
  }

  @Test
  void fromTimestamp_isNull() {
    Timestamp timestamp = null;

    Optional<Instant> actual = DateConverter.fromTimestamp(timestamp);

    assertNotNull(actual);
    assertFalse(actual.isPresent());
  }

  @Test
  void fromTimestamp_isPresent() {
    Instant instantNow = Instant.now();
    Timestamp timestamp = new Timestamp(instantNow.toEpochMilli());

    Optional<Instant> actual = DateConverter.fromTimestamp(timestamp);

    assertNotNull(actual);
    assertTrue(actual.isPresent());
    assertEquals(instantNow.truncatedTo(SECONDS), actual.get().truncatedTo(SECONDS));
  }
}