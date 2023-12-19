package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.PasswordMatchDto;

class PasswordMatchValidatorTest {


  private static final String ACCOUNT_PASSWORD = "password";
  private static final PasswordMatchValidator validator = PasswordMatchValidator.getInstance();

  @Test
  void validateMatch_correct() {
    var dto = new PasswordMatchDto(ACCOUNT_PASSWORD, "password");

    ValidationResult actualResult = validator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validateMatch_mismatch() {
    var dto = new PasswordMatchDto(ACCOUNT_PASSWORD, "another");

    ValidationResult actualResult = validator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.current.mismatch");
  }

}