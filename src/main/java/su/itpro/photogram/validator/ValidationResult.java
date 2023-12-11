package su.itpro.photogram.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

  private final List<Error> errors = new ArrayList<>();

  public void add(Error error) {
    errors.add(error);
  }

  public boolean hasErrors() {
    return !errors.isEmpty();
  }

  public List<Error> getErrors() {
    return new ArrayList<>(errors);
  }
}
