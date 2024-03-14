package com.security.service;

import com.security.dto.request.ProductDto;
import com.security.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ProductService {
    //@PreAuthorize("hasAuthorize('READ_ALL_PRODUCTS')")
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long productId);

    Product create(ProductDto product);

    Product updateById(ProductDto productDto, Long productId);

    Product disableById(Long productId);
}
