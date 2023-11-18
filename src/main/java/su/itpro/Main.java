package su.itpro;

import java.sql.Connection;
import java.sql.SQLException;
import su.itpro.util.DataSource;

public class Main {

  private Main() {
  }

  public static void main(String[] args) {

    try (Connection connection = DataSource.getConnection()) {
      System.out.println(connection.getSchema());

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}