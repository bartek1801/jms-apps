package com.example.jmssubdemo.crud.person;

import com.example.jmssubdemo.crud.person.dto.CreatePersonDto;
import com.example.jmssubdemo.crud.person.dto.PersonDto;
import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@ToString
class Person extends BaseEntity {

    //    @Id
//    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;

    Person(CreatePersonDto personDto) {
        super();
        this.firstName = personDto.getFirstName();
        this.lastName = personDto.getLastName();
        this.age = personDto.getAge();
    }

    void changeFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    Person update(PersonDto personDto) {
        if (personDto.getFirstName() != null) {
            this.firstName = personDto.getFirstName();
        }
        if (personDto.getLastName() != null) {
            this.lastName = personDto.getLastName();
        }
        if (personDto.getAge() != null) {
            this.age = personDto.getAge();
        }
        return this;
    }


    PersonDto toDto() {
        return new PersonDto(
                super.getId(),
                this.firstName,
                this.lastName,
                this.age
        );
    }


}
