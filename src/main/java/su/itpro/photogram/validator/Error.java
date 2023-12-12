package su.itpro.photogram.validator;

public record Error(String code, String message) {

  public static Error of(String code, String message) {
    return new Error(code, message);
  }
}
