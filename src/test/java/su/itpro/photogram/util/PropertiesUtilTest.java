package su.itpro.photogram.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PropertiesUtilTest {

  @ParameterizedTest
  @MethodSource("getPropertyArguments")
  void checkGetProperty(String key, String expectedValue) {
    String actualResult = PropertiesUtil.getProperty(key);

    assertEquals(expectedValue, actualResult);
  }

  static Stream<Arguments> getPropertyArguments() {
    return Stream.of(
        Arguments.of("db.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"),
        Arguments.of("db.user", ""),
        Arguments.of("db.password", "")
    );
  }
}