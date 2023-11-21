package su.itpro.photogram.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

  private static final Properties APPLICATION = loadProperties("application.properties");
  private static final Properties HIKARICP = loadProperties("hikaricp.properties");

  private PropertiesUtil() {
  }

  public static String getProperty(String name) {
    return APPLICATION.getProperty(name);
  }

  public static Properties getApplicationProperties() {
    return APPLICATION;
  }

  public static Properties getHikariProperties() {
    return HIKARICP;
  }

  private static Properties loadProperties(String filename) {

    try (InputStream resource = PropertiesUtil.class.getClassLoader()
        .getResourceAsStream(filename)) {
      Properties properties = new Properties();
      properties.load(resource);
      return properties;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}