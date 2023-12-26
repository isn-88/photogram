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
      errors.add(Error.of("password.current.empty", "message.password.current.empty"));
    }

    if (Objects.isNull(dto.newPassword()) || dto.newPassword().isBlank()) {
      errors.add(Error.of("password.new.empty", "message.password.new.empty"));
    } else {
      String newPassword = dto.newPassword();
      if (!passwordPattern.matcher(newPassword).matches()) {
        errors.add(Error.of("password.new.invalid", "message.password.new.invalid"));
      }
      if (newPassword.length() < PASSWORD_LENGTH_MIN) {
        errors.add(Error.of("password.new.short", "message.password.new.short"));
      }
      if (newPassword.length() > PASSWORD_LENGTH_MAX) {
        errors.add(Error.of("password.new.long", "message.password.new.long"));
      }
      if (Objects.isNull(dto.checkPassword()) || dto.checkPassword().isBlank()) {
        errors.add(Error.of("password.check.empty", "message.password.check.empty"));
      } else {
        String checkPassword = dto.checkPassword();
        if (!newPassword.equals(checkPassword)) {
          errors.add(Error.of("password.check.mismatch", "message.password.check.mismatch"));
        }
      }
    }
    return errors;
  }

}
