package com.example.jmssubdemo.crud;

import com.example.jmssubdemo.crud.dto.ProductDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Product {

    @Id
    private UUID uuid;
    private String name;
    private Long price;
    @Version
    private long version;

    Product(UUID uuid, String name, Long price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }

    ProductDto toDto() {
        return new ProductDto(
                this.uuid,
                this.name,
                this.price
        );
    }


    Product changePrice(Long price) {
        this.price = price;
        return this;
    }


}
