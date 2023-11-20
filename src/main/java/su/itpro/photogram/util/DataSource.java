package su.itpro.photogram.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class DataSource {

  private static final HikariConfig CONFIG = new HikariConfig(PropertiesUtil.getHikariProperties());
  private static final HikariDataSource CONNECTION_POOL = new HikariDataSource(CONFIG);

  private DataSource() {
  }

  public static Connection getConnection() throws SQLException {
    return CONNECTION_POOL.getConnection();
  }

}
