package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.ChangePasswordDto;

class PasswordChangeValidatorTest {

  private static final String CURRENT_PASSWORD = "currentAccountPassword";
  private static final String NEW_PASSWORD = "newPass";
  private static final String CHECK_PASSWORD = "newPass";


  private final PasswordChangeValidator validator = PasswordChangeValidator.getInstance();

  @Test
  void validate_correct() {
    ChangePasswordDto dto = getChangePasswordDto();

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_currentPasswordIsNull() {
    ChangePasswordDto dto = getChangePasswordDto(null, NEW_PASSWORD, CHECK_PASSWORD);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.current.empty");
  }

  @Test
  void validate_currentPasswordIsBlank() {
    ChangePasswordDto dto = getChangePasswordDto("  ", NEW_PASSWORD, CHECK_PASSWORD);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.current.empty");
  }

  @Test
  void validate_newPasswordIsNull() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, null, CHECK_PASSWORD);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.new.empty");
  }

  @Test
  void validate_newPasswordIsBlank() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, "  ", CHECK_PASSWORD);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.new.empty");
  }

  @Test
  void validate_newPasswordInvalid() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, "New pass", "New pass");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.new.invalid");
  }

  @Test
  void validate_newPasswordTooShort() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, "p", "p");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.new.short");
  }

  @Test
  void validate_newPasswordTooLong() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, "password1", "password1");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.new.long");
  }

  @Test
  void validate_checkPasswordIsNull() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, NEW_PASSWORD, null);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.check.empty");
  }

  @Test
  void validate_checkPasswordIsBlank() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, NEW_PASSWORD, "  ");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.check.empty");
  }

  @Test
  void validate_checkPasswordMismatch() {
    ChangePasswordDto dto = getChangePasswordDto(CURRENT_PASSWORD, NEW_PASSWORD, NEW_PASSWORD + "w");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.check.mismatch");
  }


  private static ChangePasswordDto getChangePasswordDto(String current, String change, String check) {
    return new ChangePasswordDto(UUID.randomUUID(), current, change, check);
  }

  private static ChangePasswordDto getChangePasswordDto() {
    return new ChangePasswordDto(UUID.randomUUID(),
                                 CURRENT_PASSWORD,
                                 NEW_PASSWORD,
                                 CHECK_PASSWORD);
  }
}