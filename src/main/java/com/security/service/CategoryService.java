package com.security.service;

import com.security.dto.request.CategoryDto;
import com.security.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(CategoryDto categoryDto);

    Category updateOneById(Long categoryId, CategoryDto categoryDto);

    Category disableOneById(Long categoryId);
}
