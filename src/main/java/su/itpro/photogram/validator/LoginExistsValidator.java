package su.itpro.photogram.validator;

import su.itpro.photogram.model.dto.LoginExistsResultDto;

public class LoginExistsValidator implements Validator<LoginExistsResultDto> {

  private static final LoginExistsValidator INSTANCE = new LoginExistsValidator();


  private LoginExistsValidator() {
  }

  public static LoginExistsValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(LoginExistsResultDto dto) {
    ValidationResult errors = new ValidationResult();

    if (dto.isExistsPhone()) {
      errors.add(Error.of("phone.exists", "message.login.phone.exists"));
    }
    if (dto.isExistsEmail()) {
      errors.add(Error.of("email.exists", "message.login.email.exists"));
    }
    if (dto.isExistsUsername()) {
      errors.add(Error.of("username.exists", "message.login.username.exists"));
    }

    return errors;
  }
}
