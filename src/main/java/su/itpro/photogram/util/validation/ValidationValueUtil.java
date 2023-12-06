package su.itpro.photogram.util.validation;

public class ValidationValueUtil {

  private ValidationValueUtil() {
  }


  public static void validationNullOrBlanc(String string) {
    validationNullOrBlanc(string, "Value must not be empty");
  }

  public static void validationNullOrBlanc(String string, String error) {
    if (string == null || string.isBlank()) {
      throw new ValidationException(error);
    }
  }

}
