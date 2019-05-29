package com.example.jmssubdemo.crud;

import com.example.jmssubdemo.crud.dto.CreateProductDto;

import java.util.UUID;

class ProductFactory {


    static Product createFromDto(CreateProductDto productDto) {
        return new Product(
                UUID.randomUUID(),
                productDto.getName(),
                productDto.getPrice()
        );
    }
}
