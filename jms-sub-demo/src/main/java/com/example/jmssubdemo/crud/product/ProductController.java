package com.example.jmssubdemo.crud;

import com.example.jmssubdemo.crud.dto.ChangeProductPriceDto;
import com.example.jmssubdemo.crud.dto.CreateProductDto;
import com.example.jmssubdemo.crud.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/products")
@RestController
public class ProductController {

    private ProductService productService;
    private ProductFinder productFinder;

    public ProductController(ProductService productService, ProductFinder productFinder) {
        this.productService = productService;
        this.productFinder = productFinder;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto adsProduct(@RequestBody CreateProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PutMapping("/change-price")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductDto changeProductDetails(@RequestBody ChangeProductPriceDto productDto) {
        return productService.changeProductPrice(productDto);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID uuid) {
        return new ResponseEntity<>(productService.findById(uuid), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productFinder
                .findAll()
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/find-by/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findProductsByName(@PathVariable String name) {
        List<ProductDto> productsByName = productFinder
                .findProductsByName(name)
                .stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
        return productsByName;
//        return new ResponseEntity<>(productsByName, HttpStatus.OK);
    }


    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable UUID uuid) {
        productService.deleteProduct(uuid);
    }

}
