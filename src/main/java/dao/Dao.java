package dao;

import java.util.List;
import java.util.Optional;

public interface Dao <E,T>{

    List<T> findAll();

    Optional<T> findById(E id);

    boolean delete(E id);

    void update(E entity);

    T save(T entity);

}
