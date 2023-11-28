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

}