package su.itpro.photogram.exception.service;

public class LikeServiceException extends RuntimeException {

  public LikeServiceException(String message) {
    super(message);
  }

  public LikeServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
