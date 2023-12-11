package su.itpro.photogram.mapper;

public interface Mapper<D, T> {

  T map(D dto);
}
