package su.itpro.photogram.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

  Optional<E> findById(K id);

  List<E> findAll();

  void save(E entity);

  void update(E entity);

  boolean delete(K id);

}
