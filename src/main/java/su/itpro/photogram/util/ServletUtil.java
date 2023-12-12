package su.itpro.photogram.util;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import su.itpro.photogram.util.converter.DateConverter;

public class ServletUtil {

  private ServletUtil() {
  }

  public static String variableOfQueryPath(String path) {
    if (valueOrNull(path) == null) {
      return null;
    }
    return path.startsWith("/") ? path.substring(1) : path;
  }

  public static String getValue(HttpServletRequest request, String name) {
    String inputValue = request.getParameter(name);
    return (valueOrNull(inputValue) != null) ? inputValue : null;
  }

  public static boolean getBoolean(HttpServletRequest request, String name) {
    return Boolean.parseBoolean(request.getParameter(name));
  }

  public static String getValueAndStrip(HttpServletRequest request, String name) {
    return stringStrip(getValue(request, name));
  }

  public static LocalDate parseDate(HttpServletRequest request, String name) {
    String inputValue = valueOrNull(request.getParameter(name));
    return (inputValue != null) ? DateConverter.parseDate(inputValue) : null;
  }

  public static String valueOrNull(String value) {
    if (value != null && !value.isBlank()) {
      return value;
    }
    return null;
  }

  private static String stringStrip(String value) {
    if (value == null) {
      return null;
    }
    return value.strip();
  }
}
