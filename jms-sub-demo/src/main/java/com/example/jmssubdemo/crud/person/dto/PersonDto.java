package com.example.jmssubdemo.crud.person.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;

}
