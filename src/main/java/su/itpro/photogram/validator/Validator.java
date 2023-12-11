package su.itpro.photogram.validator;

public interface Validator<T> {

  ValidationResult validate(T object);
}
