package com.example.jmssubdemo.crud.person.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreatePersonDto {

    //    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;

//    public CreatePersonDto(String firstName, String lastName, Integer age) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//    }
}
