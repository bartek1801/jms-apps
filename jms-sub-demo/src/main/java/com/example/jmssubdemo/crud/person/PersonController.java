package com.example.jmssubdemo.crud.person;

import com.example.jmssubdemo.crud.person.dto.CreatePersonDto;
import com.example.jmssubdemo.crud.person.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("person")
public class PersonController {

    private PersonService personService;
    private PersonRepository personRepository;

    public PersonController(PersonService personService,
                            PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<PersonDto> createPerson(@RequestBody CreatePersonDto personDto) {
        return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable UUID id) {
        return personRepository.getOne(id).toDto();
    }

}
