package su.itpro.photogram.util;

import java.util.Optional;

public class ServiceUtil {

  private ServiceUtil() {
  }


  public static Optional<String> optionalOf(String value) {
    if (value != null && !value.isBlank()) {
      return Optional.of(value);
    }
    return Optional.empty();
  }
}

