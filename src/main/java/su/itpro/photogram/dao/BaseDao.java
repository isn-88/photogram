package su.itpro.photogram.dao;

import java.util.List;
import java.util.Optional;

/**
 * Базовый интерфейс для выполнения основных CRUD-операций
 *
 * @param <K> тип идентификатора
 * @param <E> тип сущности
 */
public interface BaseDao<K, E> {

  /**
   * Производит поиск сущности по id
   *
   * @param id идентификатор сущности
   * @return найденная сущность
   */
  Optional<E> findById(K id);

  /**
   * Возвращает все сущности
   *
   * @return список сущностей
   */
  List<E> findAll();

  /**
   * Сохраняет сущность
   *
   * @param entity - сохраняемая сущность
   * @return сохранённая сущность
   */
  E save(E entity);

  /**
   * Обновляет сущность
   *
   * @param entity обновляемая сущность
   */
  void update(E entity);

  /**
   * Удаляет сущность
   *
   * @param id идентификатор удаляемой сущности
   */
  void delete(K id);

}
