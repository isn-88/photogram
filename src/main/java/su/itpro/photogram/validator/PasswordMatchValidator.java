package su.itpro.photogram.validator;

import su.itpro.photogram.model.dto.PasswordMatchDto;

public class PasswordMatchValidator implements Validator<PasswordMatchDto> {

  private static final PasswordMatchValidator INSTANCE = new PasswordMatchValidator();


  private PasswordMatchValidator() {
  }


  public static PasswordMatchValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(PasswordMatchDto dto) {
    ValidationResult errors = new ValidationResult();

    if (!dto.currentAccountPassword().equals(dto.inputCurrentPassword())) {
      errors.add(Error.of("password.current.mismatch", "message.password.current.mismatch"));
    }

    return errors;
  }

}
