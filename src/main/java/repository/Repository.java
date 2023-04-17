package repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E> {


    E create(E entity);

    Optional<E> findById(K key);

    void update(E entity);

    List<E> findAll();

    void delete(E entity);


}
