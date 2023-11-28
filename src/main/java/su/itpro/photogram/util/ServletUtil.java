package su.itpro.photogram.util;

import javax.servlet.http.HttpServletRequest;

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

  public static String getValueAndStrip(HttpServletRequest request, String name) {
    return stringStrip(getValue(request, name));
  }

  private static String stringStrip(String value) {
    if (value == null) {
      return null;
    }
    return value.strip();
  }

  public static String valueOrNull(String value) {
    if (value != null && !value.isBlank()) {
      return value;
    }
    return null;
  }
}
