package su.itpro.photogram.util;

import static org.junit.jupiter.api.Assertions.*;
import static su.itpro.photogram.util.ServletUtil.*;

import org.junit.jupiter.api.Test;

class ServletUtilTest {

  @Test
  void variableOfQueryPath_isNull() {
    String pathParam = null;

    String actual = variableOfQueryPath(pathParam);

    assertNull(actual);
  }

  @Test
  void variableOfQueryPath_withSlash() {
    String pathParam = "value";

    String actual = variableOfQueryPath("/" + pathParam);

    assertEquals(pathParam, actual);
  }

  @Test
  void variableOfQueryPath_withoutSlash() {
    String pathParam = "value";

    String actual = variableOfQueryPath(pathParam);

    assertEquals(pathParam, actual);
  }

  @Test
  void valueOrNull_isNull() {
    String value = null;

    String actual = valueOrNull(value);

    assertNull(actual);
  }

  @Test
  void valueOrNull_isEmpty() {
    String value = " ";

    String actual = valueOrNull(value);

    assertNull(actual);
  }

  @Test
  void valueOrNull_isPresent() {
    String value = "value";

    String actual = valueOrNull(value);

    assertEquals(value, actual);
  }
}