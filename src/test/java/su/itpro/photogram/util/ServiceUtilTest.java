package su.itpro.photogram.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class ServiceUtilTest {

  @Test
  void optionalOf_nullValue() {
    String value = null;

    Optional<String> actual = ServiceUtil.optionalOf(value);

    assertFalse(actual.isPresent());
  }

  @Test
  void optionalOf_emptyValue() {
    String value = "    ";

    Optional<String> actual = ServiceUtil.optionalOf(value);

    assertFalse(actual.isPresent());
  }

  @Test
  void optionalOf_presentValue() {
    String value = "value";

    Optional<String> actual = ServiceUtil.optionalOf(value);

    assertTrue(actual.isPresent());
  }

  @Test
  void valueOrNull_isNull() {
    String value = null;

    String actual = ServiceUtil.valueOrNull(value);

    assertNull(actual);
  }

  @Test
  void valueOrNull_isEmpty() {
    String value = " ";

    String actual = ServiceUtil.valueOrNull(value);

    assertNull(actual);
  }

  @Test
  void valueOrNull_isPresent() {
    String value = "value";

    String actual = ServiceUtil.valueOrNull(value);

    assertEquals(value, actual);
  }

}