package com.security.service.impl;

import com.security.dto.request.ProductDto;
import com.security.exceptions.ObjectNotFoundException;
import com.security.persistence.entity.Category;
import com.security.persistence.entity.Product;

import com.security.persistence.repository.ProductRepository;
import com.security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    //@PreAuthorize("hasAuthorize('READ_ALL_PRODUCTS')")
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product create(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStatus(Product.ProductStatus.ENABLED);

        Category category = new Category();
        category.setId(productDto.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateById(ProductDto productDto, Long productId) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow( () -> new ObjectNotFoundException("Product not found with id " + productId));

        productFromDB.setName(productDto.getName());
        productFromDB.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setId(productDto.getCategoryId());
        productFromDB.setCategory(category);

        return productRepository.save(productFromDB);
    }

    @Override
    public Product disableById(Long productId) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow( () -> new ObjectNotFoundException("Product not found with id " + productId));
        productFromDB.setStatus(Product.ProductStatus.DISABLED);

        return productRepository.save(productFromDB);
    }
}
