package su.itpro.photogram.validator;

import java.util.Objects;
import java.util.Optional;
import su.itpro.photogram.model.dto.LoginDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Status;

public class LoginValidator implements Validator<LoginDto> {

  private static final LoginValidator INSTANCE = new LoginValidator();


  private LoginValidator() {
  }

  public static LoginValidator getInstance() {
    return INSTANCE;
  }

  @Override
  public ValidationResult validate(LoginDto dto) {
    ValidationResult errors = new ValidationResult();

    if (Objects.isNull(dto.login()) || dto.login().isBlank()) {
      errors.add(Error.of("login.empty", "Не указан логин"));
    }

    if (Objects.isNull(dto.password()) || dto.password().isBlank()) {
      errors.add(Error.of("password.empty", "Не указан пароль"));
    }

    return errors;
  }

  public ValidationResult validateLogin(Optional<Account> accountOpt, LoginDto dto) {
    ValidationResult errors = new ValidationResult();
    if (accountOpt.isPresent()) {
      Account account = accountOpt.get();
      if (account.getStatus().equals(Status.BLOCKED)) {
        errors.add(Error.of("account.blocked", "Аккаунт заблокирован"));
      }
      if (account.getStatus().equals(Status.DELETED)) {
        errors.add(Error.of("account.deleted", "Аккаунт удалён"));
      }
      if (!account.getPassword().equals(dto.password())) {
        errors.add(Error.of("password.incorrect", "Пароль не подходит"));
      }
    } else {
      errors.add(Error.of("account.not.found", "Аккаунт не найден"));
    }
    return errors;
  }
}
