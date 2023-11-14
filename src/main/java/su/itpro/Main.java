package su.itpro;

import java.sql.Connection;
import java.sql.SQLException;
import su.itpro.util.ConnectionManager;

public class Main {

  private Main() {
  }

  public static void main(String[] args) {

    try (Connection connection = ConnectionManager.open()) {
      System.out.println(connection.getSchema());

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}