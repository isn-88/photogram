package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.AccountUpdateDto;
import su.itpro.photogram.util.PropertiesUtil;

class AccountUpdateValidatorTest {

  private static final String PHONE = "01234567890";
  private static final String USERNAME = "username";

  private static final String EMAIL_PATTERN = "%s@%s";

  private final AccountUpdateValidator validator = AccountUpdateValidator.getInstance();


  @Test
  void validate_correct() {
    AccountUpdateDto dto = new AccountUpdateDto(
        PHONE, null, "my.name_test-1");

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_phoneAndEmailNotPresent() {
    AccountUpdateDto dto = new AccountUpdateDto(null, null, USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("input.empty");
  }

  @Test
  void validate_usernameNotPresent() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, null, null);

    ValidationResult actualResult = validator.validate(dto);

    assertTrue(actualResult.hasErrors());
  }

  @Test
  void validate_phoneCorrect() {
    AccountUpdateDto dto = new AccountUpdateDto("01234567890", null, USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_phoneIncorrect() {
    AccountUpdateDto dto = new AccountUpdateDto("+01234567890", null, USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.invalid");
  }

  @Test
  void validate_phoneTooShort() {
    AccountUpdateDto dto = new AccountUpdateDto("0", null, USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.short");
  }

  @Test
  void validate_phoneTooLong() {
    AccountUpdateDto dto = new AccountUpdateDto("012345678901234567890", null, USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.long");
  }

  @Test
  void validate_emailCorrect() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, "user.name@mail.com", USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_emailIncorrectShortName() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, "@mail.com", USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailIncorrectStartSymbol() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, "+u@mail.com", USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailIncorrectDomainOne() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, "user@mail.c", USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailIncorrectDomainTwo() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, "user@ .com", USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_emailLocalTooLong() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, getEmailLocalLongest(), USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.long");
  }

  @Test
  void validate_emailDomainTooLong() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, getEmailDomainLongest(), USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.long");
  }

  @Test
  void validate_emailIncorrectWithoutSymbol() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, "user_mail.com", USERNAME);

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.invalid");
  }

  @Test
  void validate_usernameIncorrectStartDigit() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, null, "1username");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.invalid");
  }

  @Test
  void validate_usernameIncorrectStartSymbol() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, null, "-username");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.invalid");
  }

  @Test
  void validate_usernameIncorrectSymbol() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, null, "user@name");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.invalid");
  }

  @Test
  void validate_usernameTooShort() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, null, "un");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.short");
  }

  @Test
  void validate_usernameTooLong() {
    AccountUpdateDto dto = new AccountUpdateDto(PHONE, null, "u23456789012345678901234567890123");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.long");
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