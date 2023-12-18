package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.CreateAccountDto;
import su.itpro.photogram.util.PropertiesUtil;

class RegistrationValidatorTest {

  private static final String PHONE = "01234567890";
  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";
  private static final String FULL_NAME = "Full Name";

  private static final String EMAIL_PATTERN = "%s@%s";

  private final RegistrationValidator validator = RegistrationValidator.getInstance();


  @Test
  void validate_correct() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, "my.name_test-1", PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_phoneAndEmailNotPresent() {
    CreateAccountDto dto = new CreateAccountDto(
        null, null, USERNAME, PASSWORD, "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("input.empty");
  }

  @Test
  void validate_usernameNotPresent() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, null, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertTrue(actualResult.hasErrors());
  }

  @Test
  void validate_phoneCorrect() {
    CreateAccountDto dto = new CreateAccountDto(
        "01234567890", null, USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_phoneIncorrect() {
    CreateAccountDto dto = new CreateAccountDto(
        "+01234567890", null, USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.invalid");
  }

  @Test
  void validate_phoneTooShort() {
    CreateAccountDto dto = new CreateAccountDto(
        "0", null, USERNAME, PASSWORD, "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.short");
  }

  @Test
  void validate_phoneTooLong() {
    CreateAccountDto dto = new CreateAccountDto(
        "012345678901234567890", null, USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.long");
  }

  @Test
  void validate_emailCorrect() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, "user@gmail.com", USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_emailIncorrectShortName() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, "u@gmail.com", USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailIncorrectStartSymbol() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, "+u@gmail.com", USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailIncorrectDomainOne() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, "user@gmail.c", USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailIncorrectDomainTwo() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, "user@ .com", USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailLocalTooLong() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, getEmailLocalLongest(), USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.long");
  }

  @Test
  void validate_emailDomainTooLong() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, getEmailDomainLongest(), USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.long");
  }

  @Test
  void validate_emailIncorrectWithoutSymbol() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, "user_mail.com", USERNAME, PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_usernameIncorrectStartDigit() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, "1username", PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.invalid");
  }

  @Test
  void validate_usernameIncorrectStartSymbol() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, "-username", PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.invalid");
  }

  @Test
  void validate_usernameIncorrectSymbol() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, "user@name", PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.invalid");
  }

  @Test
  void validate_usernameTooShort() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, "un", PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.short");
  }

  @Test
  void validate_usernameTooLong() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, "u23456789012345678901234567890123", PASSWORD, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.long");
  }

  @Test
  void validate_passwordIsNull() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, USERNAME, null, FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.empty");
  }

  @Test
  void validate_passwordIsBlank() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, USERNAME, "  ", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.empty");
  }
  @Test
  void validate_passwordIsIncorrect() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, USERNAME, "pa ss", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.invalid");
  }
  @Test
  void validate_passwordTooShort() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, USERNAME, "12", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.short");
  }

  @Test
  void validate_passwordTooLong() {
    CreateAccountDto dto = new CreateAccountDto(
        PHONE, null, USERNAME, "123456789", FULL_NAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.long");
  }

  private String getEmailLocalLongest() {
    int emailLocalMax = PropertiesUtil.getInt("validation.length.email.local.max", 16);
    return EMAIL_PATTERN.formatted("e".repeat(emailLocalMax + 1), "mail.com");
  }

  private String getEmailDomainLongest() {
    int emailDomainMax = PropertiesUtil.getInt("validation.length.email.local.max", 15);
    return EMAIL_PATTERN.formatted("email", "d".repeat(emailDomainMax - 3) + ".com");
  }

}