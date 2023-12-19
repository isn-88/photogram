package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.LoginExistsResultDto;

class LoginExistsValidatorTest {

  LoginExistsValidator validator = LoginExistsValidator.getInstance();

  @Test
  void validate_correct() {
    var existsDto = new LoginExistsResultDto(false, false, false);

    ValidationResult actualResult = validator.validate(existsDto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_existsPhone() {
    var existsDto = new LoginExistsResultDto(true, false, false);

    ValidationResult actualResult = validator.validate(existsDto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.exists");
  }

  @Test
  void validate_existsEmail() {
    var existsDto = new LoginExistsResultDto(false, true, false);

    ValidationResult actualResult = validator.validate(existsDto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("email.exists");
  }

  @Test
  void validate_existsUsername() {
    var existsDto = new LoginExistsResultDto(false, false, true);

    ValidationResult actualResult = validator.validate(existsDto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("username.exists");
  }

  @Test
  void validate_existsAll() {
    var existsDto = new LoginExistsResultDto(true, true, true);

    ValidationResult actualResult = validator.validate(existsDto);

    assertThat(actualResult.getErrors()).hasSize(3);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("phone.exists");
    assertThat(actualResult.getErrors().get(1).code()).isEqualTo("email.exists");
    assertThat(actualResult.getErrors().get(2).code()).isEqualTo("username.exists");
  }
}