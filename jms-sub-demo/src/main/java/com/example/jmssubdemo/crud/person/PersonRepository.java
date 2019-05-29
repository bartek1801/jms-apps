package com.example.jmssubdemo.crud.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface PersonRepository extends JpaRepository<Person, UUID> {

    List<Person> findByFirstNameAndLastNameAndAge(String firstName, String LastName, Integer age);

    List<Person> findByLastNameLike(String partOfLastName);


}
