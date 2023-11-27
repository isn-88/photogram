package su.itpro.photogram.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServletUtilTest {

  @Test
  void variableOfQueryPath_isNull() {
    String pathParam = null;

    String actual = ServletUtil.variableOfQueryPath(pathParam);

    assertNull(actual);
  }

  @Test
  void variableOfQueryPath_withSlash() {
    String pathParam = "value";

    String actual = ServletUtil.variableOfQueryPath("/" + pathParam);

    assertEquals(pathParam, actual);
  }

  @Test
  void variableOfQueryPath_withoutSlash() {
    String pathParam = "value";

    String actual = ServletUtil.variableOfQueryPath(pathParam);

    assertEquals(pathParam, actual);
  }
}