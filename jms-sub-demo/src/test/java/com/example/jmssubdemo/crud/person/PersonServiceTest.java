package com.example.jmssubdemo.crud.person;


import com.example.jmssubdemo.crud.person.dto.CreatePersonDto;
import com.example.jmssubdemo.crud.person.dto.PersonDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static com.example.jmssubdemo.crud.person.BaseEntity.ArchiveStatus.ARCHIVE;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Before
    public void cleanUp() {
        personRepository.deleteAll();
    }

    @Test
    public void shouldSavePersonInRepo() {
        //given
        CreatePersonDto personDto = new CreatePersonDto("Mike", "Smith", 33);

        //when
        PersonDto createdPerson = personService.createPerson(personDto);

        //then
        Assertions.assertThat(createdPerson).isNotNull();
        Assertions.assertThat(createdPerson.getFirstName()).isEqualTo("Mike");
        Assertions.assertThat(personRepository.getOne(createdPerson.getId())).isNotNull();
    }

    @Test
    public void shouldChangePersonInRepo() {
        //given
        CreatePersonDto personDto = new CreatePersonDto("Mike", "Smith", 33);
        PersonDto createdPerson = personService.createPerson(personDto);
        PersonDto personDtoForUpdate = new PersonDto(createdPerson.getId(), "Michael", "Smiths", 30);

        //when
        PersonDto updatedPerson = personService.updatePerson(personDtoForUpdate);

        //then
        Assertions.assertThat(updatedPerson).isNotNull();
        Assertions.assertThat(personRepository.getOne(updatedPerson.getId())).isNotNull();
        Assertions.assertThat(updatedPerson).isEqualTo(personDtoForUpdate);
    }

    @Test
    public void shouldDeletePersonFromRepo() {
        //given
        CreatePersonDto personDto = new CreatePersonDto("Mike", "Smith", 33);
        PersonDto createdPerson = personService.createPerson(personDto);
        UUID createdPersonId = createdPerson.getId();

        //when
        personService.deletePerson(createdPersonId);

        //then
        Person deletedPerson = personRepository.getOne(createdPersonId);
        Assertions.assertThat(deletedPerson).isNotNull();
        Assertions.assertThat(deletedPerson.getArchiveStatus()).isEqualTo(ARCHIVE);
    }

    @Test
    public void repoTest() {
        CreatePersonDto mike = new CreatePersonDto("Mike", "Smith", 33);
        CreatePersonDto john = new CreatePersonDto("Mike", "Smiths", 30);
        CreatePersonDto andy = new CreatePersonDto("Andy", "Snow", 30);
        PersonDto createdMike = personService.createPerson(mike);
        PersonDto createdJohn = personService.createPerson(john);
        PersonDto createdAndy = personService.createPerson(andy);

        List<Person> list = personRepository.findByLastNameLike("Smi%");

        Assertions.assertThat(list.size()).isEqualTo(2);

    }
}
