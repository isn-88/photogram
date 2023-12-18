package su.itpro.photogram.validator;

import java.util.Objects;
import java.util.regex.Pattern;
import su.itpro.photogram.model.dto.ChangePasswordDto;
import su.itpro.photogram.util.PropertiesUtil;

public class PasswordChangeValidator implements Validator<ChangePasswordDto> {

  private static final int PASSWORD_LENGTH_MIN =
      PropertiesUtil.getInt("validation.length.password.min", 8);
  private static final int PASSWORD_LENGTH_MAX =
      PropertiesUtil.getInt("validation.length.password.max", 64);

  private static final String PASSWORD_REGEX = """
      \\S+""";

  private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

  private static final PasswordChangeValidator INSTANCE = new PasswordChangeValidator();


  private PasswordChangeValidator() {
  }

  public static PasswordChangeValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(ChangePasswordDto dto) {
    ValidationResult errors = new ValidationResult();

    if (Objects.isNull(dto.currentPassword()) || dto.currentPassword().isBlank()) {
      errors.add(Error.of("password.current.empty", "Не указан текущий пароль"));
    }

    if (Objects.isNull(dto.newPassword()) || dto.newPassword().isBlank()) {
      errors.add(Error.of("password.new.empty", "Не указан новый пароль"));
    } else {
      String newPassword = dto.newPassword();
      if (!passwordPattern.matcher(newPassword).matches()) {
        errors.add(Error.of("password.new.invalid", "Новый пароль содержит недопустимые символы"));
      }
      if (newPassword.length() < PASSWORD_LENGTH_MIN) {
        errors.add(Error.of("password.new.short", "Новый пароль слишком короткий"));
      }
      if (newPassword.length() > PASSWORD_LENGTH_MAX) {
        errors.add(Error.of("password.new.long", "Новый пароль слишком длинный"));
      }
      if (Objects.isNull(dto.checkPassword()) || dto.checkPassword().isBlank()) {
        errors.add(Error.of("password.check.empty", "Не указан проверочный пароль"));
      } else {
        String checkPassword = dto.checkPassword();
        if (!newPassword.equals(checkPassword)) {
          errors.add(Error.of("password.check.mismatch", "Проверочный пароль не совпадает"));
        }
      }
    }
    return errors;
  }

  public ValidationResult validateMatch(String accountPassword, ChangePasswordDto dto) {
    ValidationResult errors = new ValidationResult();

    if (!accountPassword.equals(dto.currentPassword())) {
      errors.add(Error.of("password.current.mismatch", "Текущий пароль не совпадает"));
    }
    return errors;
  }

}
