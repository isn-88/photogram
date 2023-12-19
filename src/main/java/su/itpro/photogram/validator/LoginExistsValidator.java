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
      errors.add(Error.of("phone.exists", "Номер телефона уже используется"));
    }
    if (dto.isExistsEmail()) {
      errors.add(Error.of("email.exists", "Email уже используется"));
    }
    if (dto.isExistsUsername()) {
      errors.add(Error.of("username.exists", "Имя пользователя уже используется"));
    }

    return errors;
  }
}
