package com.example.jmssubdemo.crud;

import com.example.jmssubdemo.crud.dto.ChangeProductPriceDto;
import com.example.jmssubdemo.crud.dto.CreateProductDto;
import com.example.jmssubdemo.crud.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Service
class ProductService {

    private ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    ProductDto addProduct(CreateProductDto productDto) {
        Product product = ProductFactory.createFromDto(productDto);
        return productRepository.save(product)
                .toDto();
    }


    ProductDto changeProductPrice(ChangeProductPriceDto productDto) {
        Product product = productRepository
                .findById(productDto.getUuid())
                .map(prod -> prod.changePrice(productDto.getPrice()))
                .orElseThrow(() -> new EntityNotFoundException(format("There is no product with id %s", productDto.getUuid())));
        return productRepository
                .save(product)
                .toDto();
    }

    ProductDto findById(UUID uuid) {
        return productRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(format("There is no product with id %s", uuid)))
                .toDto();
    }


    void deleteProduct(UUID uuid) {
        productRepository.deleteById(uuid);
    }
}
