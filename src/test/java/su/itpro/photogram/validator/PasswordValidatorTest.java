package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.AccountChangeDto;

class PasswordValidatorTest {

  private static final String PHONE = "01234567890";
  private static final String USERNAME = "username";
  private static final String FULL_NAME = "Full Name";


  private final PasswordValidator validator = PasswordValidator.getInstance();

  @Test
  void validate_passwordIsNull() {
    AccountChangeDto dto = new AccountChangeDto(
        PHONE, null, USERNAME, null, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.empty");
  }

  @Test
  void validate_passwordIsBlank() {
    AccountChangeDto dto = new AccountChangeDto(
        PHONE, null, USERNAME, "  ", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.empty");
  }
  @Test
  void validate_passwordIsIncorrect() {
    AccountChangeDto dto = new AccountChangeDto(
        PHONE, null, USERNAME, "pa ss", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.invalid");
  }
  @Test
  void validate_passwordTooShort() {
    AccountChangeDto dto = new AccountChangeDto(
        PHONE, null, USERNAME, "12", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.short");
  }

  @Test
  void validate_passwordTooLong() {
    AccountChangeDto dto = new AccountChangeDto(
        PHONE, null, USERNAME, "123456789", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.long");
  }

}