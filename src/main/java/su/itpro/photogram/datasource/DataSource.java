package su.itpro.photogram.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import su.itpro.photogram.util.PropertiesUtil;

public class DataSource {

  private static final int POOL_SIZE_DEFAULT = 10;
  private static final String DB_DRIVER_NAME_KEY = "db.driver.name";
  private static final String DB_USER_KEY = "db.user";
  private static final String DB_PASSWORD_KEY = "db.password";
  private static final String DB_URL_KEY = "db.url";
  private static final String POOL_SIZE_KEY = "db.pool.size";
  private static BlockingQueue<Connection> pool;

  static {
    loadDriver();
    initConnectionPool();
  }


  private DataSource() {
  }

  public static Connection getConnection() {
    try {
      return pool.take();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void closeConnectionPool() {
    pool.forEach(connection -> ((ConnectionWrapper) connection).closeConnection());
  }

  private static void loadDriver() {
    try {
      Class.forName(PropertiesUtil.getProperty(DB_DRIVER_NAME_KEY));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static void initConnectionPool() {
    int size = PropertiesUtil.getInt(POOL_SIZE_KEY, POOL_SIZE_DEFAULT);
    pool = new ArrayBlockingQueue<>(size);
    for (int i = 0; i < size; i++) {
      pool.add(new ConnectionWrapper(openConnection(), pool));
    }
  }

  private static Connection openConnection() {
    try {
      return DriverManager.getConnection(
          PropertiesUtil.getProperty(DB_URL_KEY),
          PropertiesUtil.getProperty(DB_USER_KEY),
          PropertiesUtil.getProperty(DB_PASSWORD_KEY)
      );
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
