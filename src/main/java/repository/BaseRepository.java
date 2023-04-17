package repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E> implements Repository<K, E> {

    @Getter
    private final Session session;

    private final Class<E> clazz;

    @Override
    public E create(E entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public Optional<E> findById(K key) {
        return Optional.ofNullable(session.get(clazz, key));

    }

    @Override
    public void update(E entity) {
        session.update(entity);
    }

    @Override
    public List<E> findAll() {

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(clazz);
        criteria.from(clazz);
        return session.createQuery(criteria)
                .list();
    }

    @Override
    public void delete(E entity) {
        session.delete(entity);
    }
}
