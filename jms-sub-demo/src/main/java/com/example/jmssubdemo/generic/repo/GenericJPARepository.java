package com.example.jmssubdemo.generic.repo;

import com.example.jmssubdemo.crud.person.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

public abstract class GenericJPARepository<T extends BaseEntity> implements Repository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;

    protected GenericJPARepository() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) {
        entityManager.persist(t);
    }

    @Override
    public T get(UUID id) {
        T t = entityManager.find(clazz, id, LockModeType.OPTIMISTIC);
        if (t == null)
            throw new EntityNotFoundException(format("There is no entity with id %s", id));
        return t;
    }

    @Override
    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(get(id));

    }

    @Override
    public void delete(UUID id) {
        T entity = get(id);
        entity.markAsRemoved();
    }
}
