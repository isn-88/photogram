package su.itpro.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

  private static final Properties PROPERTIES = loadProperties();

  private PropertiesUtil() {
  }

  public static String getProperty(String name) {
    return PROPERTIES.getProperty(name);
  }

  private static Properties loadProperties() {

    try (InputStream resource = PropertiesUtil.class.getClassLoader()
        .getResourceAsStream("application.properties")) {
      Properties properties = new Properties();
      properties.load(resource);
      return properties;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
