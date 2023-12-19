package su.itpro.photogram.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import su.itpro.photogram.model.dto.LoginDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Status;

class LoginValidatorTest {

  private static final String LOGIN = "login";
  private static final String PASSWORD = "password";
  private static final LoginDto DTO = createDto();


  private static final Account ACCOUNT = createAccount(Status.ACTIVE);

  private final LoginValidator loginValidator = LoginValidator.getInstance();


  @Test
  void validate_correct() {
    LoginDto dto = createDto();

    ValidationResult actualResult = loginValidator.validate(dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validate_loginIsNull() {
    LoginDto dto = new LoginDto(null, PASSWORD);

    ValidationResult actualResult = loginValidator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("login.empty");
  }

  @Test
  void validate_loginIsBlank() {
    LoginDto dto = new LoginDto("   ", PASSWORD);

    ValidationResult actualResult = loginValidator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("login.empty");
  }

  @Test
  void validate_passwordIsNull() {
    LoginDto dto = new LoginDto(LOGIN, null);

    ValidationResult actualResult = loginValidator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.empty");
  }

  @Test
  void validate_passwordIsBlank() {
    LoginDto dto = new LoginDto(LOGIN, "   ");

    ValidationResult actualResult = loginValidator.validate(dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.empty");
  }

  @Test
  void validateLogin_correct() {
    Account account = createAccount(Status.ACTIVE);
    LoginDto dto = createDto();

    ValidationResult actualResult = loginValidator.validateLogin(Optional.of(account), dto);

    assertFalse(actualResult.hasErrors());
  }

  @Test
  void validateLogin_AccountNotPresent() {
    Account account = null;
    LoginDto dto = createDto();

    ValidationResult actualResult = loginValidator.validateLogin(Optional.ofNullable(account), dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("account.not.found");
  }

  @Test
  void validateLogin_accountBlocked() {
    Account account = createAccount(Status.BLOCKED);
    LoginDto dto = createDto();

    ValidationResult actualResult = loginValidator.validateLogin(Optional.of(account), dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("account.blocked");
  }

  @Test
  void validateLogin_accountDeleted() {
    Account account = createAccount(Status.DELETED);
    LoginDto dto = createDto();

    ValidationResult actualResult = loginValidator.validateLogin(Optional.of(account), dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("account.deleted");
  }

  @Test
  void validateLogin_passwordIncorrect() {
    Account account = createAccount(Status.ACTIVE);
    LoginDto dto = new LoginDto(LOGIN, PASSWORD + "1");

    ValidationResult actualResult = loginValidator.validateLogin(Optional.of(account), dto);

    assertThat(actualResult.getErrors()).hasSize(1);
    assertThat(actualResult.getErrors().get(0).code()).isEqualTo("password.incorrect");
  }


  private static LoginDto createDto() {
    return new LoginDto(LOGIN, PASSWORD);
  }

  private static Account createAccount(Status status) {
    return new Account(UUID.randomUUID(),
                       null,
                       "1234567890",
                       "email@mail.com",
                       "username",
                       PASSWORD,
                       null,
                       status,
                       null,
                       null,
                       null);
  }

}