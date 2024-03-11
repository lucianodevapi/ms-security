package com.security.controller;

import com.security.dto.request.CategoryDto;
import com.security.persistence.entity.Category;
import com.security.service.CategoryService;
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
@RequestMapping(value = "/category")
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable){

        Page<Category> categoriesPage = categoryService.findAll(pageable);

        if(categoriesPage.hasContent()){
            return ResponseEntity.ok(categoriesPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOneById(@PathVariable Long categoryId){

        Optional<Category> category = categoryService.findOneById(categoryId);

        if(category.isPresent()){
            return ResponseEntity.ok(category.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid CategoryDto categoryDto){
        Category category = categoryService.createOne(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateOneById(@PathVariable Long categoryId, @RequestBody @Valid CategoryDto categoryDto){
        Category category = categoryService.updateOneById(categoryId, categoryDto);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disableOneById(@PathVariable Long categoryId){
        Category category = categoryService.disableOneById(categoryId);
        return ResponseEntity.ok(category);
    }
}
