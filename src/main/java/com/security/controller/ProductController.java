package com.security.controller;

import com.security.dto.request.ProductDto;
import com.security.persistence.entity.Product;
import com.security.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDto productDto ){
        Product product = productService.create(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<Product> updateById(@RequestBody @Valid ProductDto productDto, @PathVariable Long productId ){
        Product product = productService.updateById(productDto, productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping(value = "/{productId}/disabled")
    public ResponseEntity<Product> disableById(@PathVariable Long productId ){
        Product product = productService.disableById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable){
        Page<Product> productsPage = productService.findAll(pageable);
        if(productsPage.hasContent()){
            return ResponseEntity.status(HttpStatus.OK).body(productsPage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> finOne(@PathVariable Long productId){
        Optional<Product> product = productService.findById(productId);
        if(product.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
