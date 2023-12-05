package su.itpro.photogram.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesUtil {

  private static final Properties APPLICATION = loadProperties("application.properties");
  private static final Properties HIKARICP = loadProperties("hikaricp.properties");
  private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

  private PropertiesUtil() {
  }

  public static String getProperty(String name) {
    return APPLICATION.getProperty(name);
  }

  public static int getInt(String name, int defaultValue) {
    String property = getProperty(name).strip();
    try {
      return Integer.parseInt(property);
    } catch (NumberFormatException e) {
      LOG.error("Error: parse integer value: [{}] for property [{}] use default value [{}]",
                property, name, defaultValue);
      return defaultValue;
    }
  }

  public static double getDouble(String name, double defaultValue) {
    String property = getProperty(name).strip();
    try {
      return Double.parseDouble(property);
    } catch (NumberFormatException e) {
      LOG.error("Error: parse double value: [{}] for property [{}] use default value [{}]",
                property, name, defaultValue);
      return defaultValue;
    }
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
