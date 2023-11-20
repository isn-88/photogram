package su.itpro.photogram.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.util.PropertiesUtil;

class PropertiesUtilTest {

  @Test
  void getProperty() {
    String propertyKey = "test.key";
    String propertyValue = "value";

    String actual = PropertiesUtil.getProperty(propertyKey);

    assertNotNull(actual);
    assertEquals(propertyValue, actual);
  }
}