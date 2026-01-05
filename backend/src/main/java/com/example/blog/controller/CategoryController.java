package com.example.blog.controller;

import com.example.blog.domain.dtos.CategoryResponsedto;
import com.example.blog.domain.dtos.CreateCategoryRequestDto;
import com.example.blog.serivce.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponsedto>> listAllCategories()
    {
        List<CategoryResponsedto> response=categoryService.listAllCategories();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoryResponsedto> addCategory(@Valid @RequestBody CreateCategoryRequestDto request)
    {
        return new ResponseEntity<>(categoryService.addCategory(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteCategory(@PathVariable UUID id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
