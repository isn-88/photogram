package su.itpro.photogram.dao.exception;

public class DaoException extends RuntimeException {

  public DaoException(String message, String error) {
    super(message + "\n" + error);
  }
}
