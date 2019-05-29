package com.example.jmssubdemo.crud.person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class JPATestPersonRepository implements PersonRepository {

    private static Map<UUID, Person> REPO = new HashMap<>();

    static {

        Person person = new Person("John", "Doe", 25);
        REPO.put(person.getId(), person);
    }


    @Override
    public Person save(Person person) {
        UUID id = person.getId();
        REPO.put(id, person);
        return REPO.get(id);
    }

    @Override
    public Person getOne(UUID id) {
        return REPO.get(id);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return Optional.ofNullable(getOne(id));
    }

}
