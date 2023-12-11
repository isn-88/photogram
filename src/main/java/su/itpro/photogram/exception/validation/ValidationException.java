package su.itpro.photogram.exception.validation;

import java.util.List;
import su.itpro.photogram.validator.Error;

public class ValidationException extends RuntimeException {

  private final List<Error> errors;

  public ValidationException(List<Error> errors) {
    this.errors = errors;
  }

  public List<Error> getErrors() {
    return errors;
  }
}
