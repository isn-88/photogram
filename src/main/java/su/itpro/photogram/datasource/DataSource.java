package su.itpro.photogram.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import su.itpro.photogram.util.PropertiesUtil;

public class DataSource {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static final HikariConfig CONFIG = new HikariConfig(PropertiesUtil.getHikariProperties());

  private static final HikariDataSource CONNECTION_POOL = new HikariDataSource(CONFIG);


  private DataSource() {
  }

  public static synchronized Connection getConnection() throws SQLException {
    return CONNECTION_POOL.getConnection();
  }

}
