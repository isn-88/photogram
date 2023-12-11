package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.RegistrationDto;

class RegistrationValidatorTest {

  private RegistrationValidator validator = RegistrationValidator.getInstance();


  @Test
  void validate_correct() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, "my.name_test-1", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_phoneAndEmailNotPresent() {
    RegistrationDto dto = new RegistrationDto(
        null, null, "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.login");
  }

  @Test
  void validate_usernameNotPresent() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, null, "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertTrue(actualResult.hasErrors());
  }

  @Test
  void validate_phoneCorrect() {
    RegistrationDto dto = new RegistrationDto(
        "01234567890", null, "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_phoneIncorrect() {
    RegistrationDto dto = new RegistrationDto(
        "+0 (123)456 78 90", null, "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.phone");
  }

  @Test
  void validate_phoneTooShort() {
    RegistrationDto dto = new RegistrationDto(
        "012345", null, "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.phone");
  }

  @Test
  void validate_phoneTooLong() {
    RegistrationDto dto = new RegistrationDto(
        "012345678901234567890", null, "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.phone");
  }

  @Test
  void validate_emailCorrect() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", "user@gmail.com", "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_emailIncorrectShortName() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", "u@gmail.com", "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.email");
  }

  @Test
  void validate_emailIncorrectStartSymbol() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", "+u@gmail.com", "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.email");
  }

  @Test
  void validate_emailIncorrectDomainOne() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", "user@gmail.c", "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.email");
  }

  @Test
  void validate_emailIncorrectDomainTwo() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", "user@ .com", "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.email");
  }

  @Test
  void validate_emailTooLong() {
    RegistrationDto dto = new RegistrationDto(
        "012345678901234567890", null, "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.phone");
  }

  @Test
  void validate_emailIncorrectWithoutSymbol() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", "user_gmail.com", "username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.email");
  }

  @Test
  void validate_usernameIncorrectStartDigit() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, "1username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.username");
  }

  @Test
  void validate_usernameIncorrectStartSymbol() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, "-username", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.username");
  }

  @Test
  void validate_usernameIncorrectSymbol() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, "user@name", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.username");
  }

  @Test
  void validate_usernameTooShort() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, "un", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.username");
  }

  @Test
  void validate_usernameTooLong() {
    RegistrationDto dto = new RegistrationDto(
        "0 (123) 456-78-90", null, "u234567890123456789012345678901", "password", "Full Name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("invalid.username");
  }

}