package com.security.service.impl;

import com.security.dto.request.CategoryDto;
import com.security.exceptions.ObjectNotFoundException;
import com.security.persistence.entity.Category;
import com.security.persistence.repository.CategoryRepository;
import com.security.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateOneById(Long categoryId, CategoryDto categoryDto) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));
        categoryFromDB.setName(categoryDto.getName());
        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category disableOneById(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));
        categoryFromDB.setStatus(Category.CategoryStatus.DISABLED);
        return categoryRepository.save(categoryFromDB);
    }
}
