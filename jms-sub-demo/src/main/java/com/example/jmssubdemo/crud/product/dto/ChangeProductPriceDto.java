package com.example.jmssubdemo.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProductPriceDto {

    private UUID uuid;
    private Long price;
}
