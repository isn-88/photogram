package su.itpro.photogram.validator;

import java.util.Objects;
import java.util.regex.Pattern;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.util.PropertiesUtil;

public class AccountUpdateValidator implements Validator<AccountUpdateDto> {

  private static final int PHONE_LENGTH_MIN =
      PropertiesUtil.getInt("validation.length.phone.min", 8);
  private static final int PHONE_LENGTH_MAX =
      PropertiesUtil.getInt("validation.length.phone.max", 13);
  private static final int EMAIL_LENGTH_LOCAL_MAX =
      PropertiesUtil.getInt("validation.length.email.local.max", 64);
  private static final int EMAIL_LENGTH_DOMAIN_MAX =
      PropertiesUtil.getInt("validation.length.email.domain.max", 256);
  private static final int USERNAME_LENGTH_MIN =
      PropertiesUtil.getInt("validation.length.username.min", 3);
  private static final int USERNAME_LENGTH_MAX =
      PropertiesUtil.getInt("validation.length.username.max", 32);


  private static final String PHONE_REGEX = """
      [0-9() \\-]+""";
  private static final String EMAIL_REGEX = """
      [a-zA-Z]\\S*@\\S+\\.\\S{2,3}""";
  private static final String USERNAME_REGEX = """
      [a-zA-Z][-a-zA-z0-9_\\.]+""";


  private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);
  private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
  private static final Pattern usernamePattern = Pattern.compile(USERNAME_REGEX);


  private static final AccountUpdateValidator INSTANCE = new AccountUpdateValidator();

  private AccountUpdateValidator() {
  }

  public static AccountUpdateValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(AccountUpdateDto dto) {
    ValidationResult errors = new ValidationResult();

    if (Objects.isNull(dto.phone()) && Objects.isNull(dto.email())) {
      errors.add(Error.of("input.empty", "message.account.input.empty"));
    } else {
      if (Objects.nonNull(dto.phone())) {
        String phone = dto.phone();
        if (!phonePattern.matcher(phone).matches()) {
          errors.add(Error.of("phone.invalid", "message.account.phone.invalid"));
        }
        if (phone.length() < PHONE_LENGTH_MIN) {
          errors.add(Error.of("phone.short", "message.account.phone.short"));
        }
        if (phone.length() > PHONE_LENGTH_MAX) {
          errors.add(Error.of("phone.long", "message.account.phone.long"));
        }
      }
      if (Objects.nonNull(dto.email())) {
        String email = dto.email();
        if (!emailPattern.matcher(email).matches()) {
          errors.add(Error.of("email.invalid", "message.account.email.invalid"));
        }
        if (getLengthLocalEmail(email) > EMAIL_LENGTH_LOCAL_MAX) {
          errors.add(Error.of("email.long", "message.account.email.long"));
        }
        if (getLengthDomainEmail(email) > EMAIL_LENGTH_DOMAIN_MAX) {
          errors.add(Error.of("email.long", "message.account.email.long"));
        }
      }
    }

    if (Objects.isNull(dto.username())) {
      errors.add(Error.of("username.empty", "message.account.username.empty"));
    } else {
      String username = dto.username();
      if (!usernamePattern.matcher(username).matches()) {
        errors.add(Error.of("username.invalid", "message.account.username.invalid"));
      }
      if (username.length() < USERNAME_LENGTH_MIN) {
        errors.add(Error.of("username.short", "message.account.username.short"));
      }
      if (username.length() > USERNAME_LENGTH_MAX) {
        errors.add(Error.of("username.long", "message.account.username.long"));
      }
    }

    return errors;
  }

  private int getLengthLocalEmail(String email) {
    int index = email.indexOf("@");
    return (index >= 0) ? index : email.length();
  }

  private int getLengthDomainEmail(String email) {
    int index = email.indexOf("@");
    return (index >= 0) ? email.length() - index - 1 : 0;
  }

}
