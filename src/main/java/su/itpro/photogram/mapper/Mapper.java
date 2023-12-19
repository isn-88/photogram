package su.itpro.photogram.mapper;

/**
 * Интерфейс конвертации объектов
 *
 * @param <F> - объект источник преобразования
 * @param <T> - целевой тип объекта
 */
public interface Mapper<F, T> {

  /**
   * Метод для преобразования одного объекта в другой
   *
   * @param object - источник
   * @return результат преобразования
   */
  T mapFrom(F object);

}
