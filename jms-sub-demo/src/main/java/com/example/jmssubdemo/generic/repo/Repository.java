package com.example.jmssubdemo.generic.repo;

import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {

    void save(T t);

    T get(UUID id);

    Optional<T> findById(UUID uuid);

    void delete(UUID id);
}
