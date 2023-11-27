package su.itpro.photogram.util;

public class ServletUtil {

  private ServletUtil() {
  }

  public static String variableOfQueryPath(String path) {
    if (ServiceUtil.valueOrNull(path) == null) {
      return null;
    }
    return path.startsWith("/") ? path.substring(1) : path;
  }

}
