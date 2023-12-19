package su.itpro.photogram.validator;

import java.util.Objects;
import java.util.regex.Pattern;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.util.PropertiesUtil;


public class PasswordValidator implements Validator<AccountChangeDto> {

  private static final int PASSWORD_LENGTH_MIN =
      PropertiesUtil.getInt("validation.length.password.min", 8);
  private static final int PASSWORD_LENGTH_MAX =
      PropertiesUtil.getInt("validation.length.password.max", 64);


  private static final String PASSWORD_REGEX = """
      \\S+""";

  private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

  private static final PasswordValidator INSTANCE = new PasswordValidator();


  private PasswordValidator() {
  }

  public static PasswordValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(AccountChangeDto dto) {
    ValidationResult errors = new ValidationResult();

    if (Objects.isNull(dto.password()) || dto.password().isBlank()) {
      errors.add(Error.of("password.empty", "Пароль не должен быть пустым"));
    } else {
      String password = dto.password();
      if (!passwordPattern.matcher(password).matches()) {
        errors.add(Error.of("password.invalid", "Пароль содержит недопустимые символы"));
      }
      if (password.length() < PASSWORD_LENGTH_MIN) {
        errors.add(Error.of("password.short", "Пароль слишком короткий"));
      }
      if (password.length() > PASSWORD_LENGTH_MAX) {
        errors.add(Error.of("password.long", "Пароль слишком длинный"));
      }
    }
    return errors;
  }
}
