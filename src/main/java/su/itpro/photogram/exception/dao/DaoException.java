package su.itpro.photogram.exception.dao;

public class DaoException extends RuntimeException {

  public DaoException(String message, String error) {
    super(message + "\n" + error);
  }
}
