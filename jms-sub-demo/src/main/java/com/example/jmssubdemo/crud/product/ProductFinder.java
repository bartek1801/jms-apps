package com.example.jmssubdemo.crud;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ProductFinder extends JpaRepository<Product, UUID> {

    List<Product> findProductsByName(String name);
}
