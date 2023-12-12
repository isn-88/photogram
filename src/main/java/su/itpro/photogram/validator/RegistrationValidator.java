package su.itpro.photogram.validator;

import java.util.Objects;
import java.util.regex.Pattern;
import su.itpro.photogram.model.dto.RegistrationDto;

public class RegistrationValidator implements Validator<RegistrationDto> {

  private static final int PHONE_LENGTH_MIN = 10;
  private static final int PHONE_LENGTH_MAX = 20;
  private static final int EMAIL_LENGTH_LOCAL_MAX = 64;
  private static final int EMAIL_LENGTH_DOMAIN_MAX = 255;
  private static final int EMAIL_LENGTH_TOTAL_MAX = 320;
  private static final int USERNAME_LENGTH_MIN = 3;
  private static final int USERNAME_LENGTH_MAX = 30;

  private static final String PHONE_REGEX = """
      [0-9() \\-]+""";
  private static final String EMAIL_REGEX = """
      [a-zA-Z]\\w+@\\w+\\.\\w{2,3}""";
  private static final String USERNAME_REGEX = """
      [a-zA-Z][-a-zA-z0-9_\\.]+""";

  private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);
  private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
  private static final Pattern usernamePattern = Pattern.compile(USERNAME_REGEX);

  private static final RegistrationValidator INSTANCE = new RegistrationValidator();

  private RegistrationValidator() {
  }

  public static RegistrationValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(RegistrationDto dto) {
    ValidationResult errors = new ValidationResult();

    if (Objects.isNull(dto.username())) {
      errors.add(Error.of("invalid.username", "Username should be present"));
    }
    if (Objects.isNull(dto.phone()) && Objects.isNull(dto.email())) {
      errors.add(Error.of("invalid.login", "Phone or email should be present"));
    }
    if (Objects.nonNull(dto.phone())) {
      if (!phonePattern.matcher(dto.phone()).matches()) {
        errors.add(Error.of("invalid.phone", "Phone number contains invalid characters"));
      }
      if (dto.phone().length() < PHONE_LENGTH_MIN) {
        errors.add(Error.of("invalid.phone", "Phone number too short"));
      }
      if (dto.phone().length() > PHONE_LENGTH_MAX) {
        errors.add(Error.of("invalid.phone", "Phone number too long"));
      }
    }
    if (Objects.nonNull(dto.email())) {
      if (!emailPattern.matcher(dto.email()).matches()) {
        errors.add(Error.of("invalid.email", "Email address contains invalid characters"));
      }
      if (dto.email().length() > EMAIL_LENGTH_TOTAL_MAX) {
        errors.add(Error.of("invalid.email", "Email address too long"));
      }
    }
    if (Objects.nonNull(dto.username())) {
      if (!usernamePattern.matcher(dto.username()).matches()) {
        errors.add(Error.of("invalid.username", "Username contains invalid characters"));
      }
      if (dto.username().length() < USERNAME_LENGTH_MIN) {
        errors.add(Error.of("invalid.username", "Username too short"));
      }
      if (dto.username().length() > USERNAME_LENGTH_MAX) {
        errors.add(Error.of("invalid.username", "Username too long"));
      }
    }
    return errors;
  }
}
