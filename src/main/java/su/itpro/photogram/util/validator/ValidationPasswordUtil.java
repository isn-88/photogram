package su.itpro.photogram.util.validator;

import su.itpro.photogram.model.entity.Account;

public class ValidationPasswordUtil {

  private ValidationPasswordUtil() {
  }

  public static void validationPasswordLength(String password, int minLength) {
    ValidationValueUtil.validationNullOrBlanc(password);
    if (password.length() < minLength) {
      throw new ValidationException("Password is too short");
    }
  }

  public static void validationLogin(Account account, String password) {
    if (account == null) {
      throw new ValidationException("Account is null");
    }
    ValidationValueUtil.validationNullOrBlanc(password, "Password is empty");
    ValidationValueUtil.validationNullOrBlanc(account.getPassword(), "Password is empty");

    if (!account.getPassword().equals(password)) {
      throw new ValidationException("Login or password incorrect");
    }
  }
}
