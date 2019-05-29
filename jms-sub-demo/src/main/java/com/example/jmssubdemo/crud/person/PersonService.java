package com.example.jmssubdemo.crud.person;

import com.example.jmssubdemo.crud.person.dto.CreatePersonDto;
import com.example.jmssubdemo.crud.person.dto.PersonDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
class PersonService {

    private PersonRepository personRepository;

    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    PersonDto createPerson(CreatePersonDto personDto) {
        checkIfThisPersonExist(personDto);
        Person person = new Person(personDto);
        return personRepository.save(person).toDto();
    }

    private void checkIfThisPersonExist(CreatePersonDto personDto) {
        List<Person> peopleWithTheSameDetails = personRepository
                .findByFirstNameAndLastNameAndAge(personDto.getFirstName(), personDto.getLastName(), personDto.getAge());
        if (!peopleWithTheSameDetails.isEmpty()) {
            throw new EntityExistsException(format("Person %s already exist", personDto.toString()));
        }
    }


    @Transactional
    PersonDto updatePerson(PersonDto personDtoForUpdate) {
        return personRepository
                .findById(personDtoForUpdate.getId())
                .map(person -> update(person, personDtoForUpdate))
                .orElseThrow(() -> new EntityNotFoundException(format("There is no entity with id %s", personDtoForUpdate.getId())))
                .toDto();
    }

    private Person update(Person person, PersonDto personDtoForUpdate) {
        Person updatedPerson = person.update(personDtoForUpdate);
        personRepository.save(updatedPerson);
        return personRepository.getOne(updatedPerson.getId());
    }

    @Transactional
    void deletePerson(UUID id) {
        personRepository.findById(id)
                .map(BaseEntity::markAsRemoved)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no entity with id %s", id)));

    }
}
