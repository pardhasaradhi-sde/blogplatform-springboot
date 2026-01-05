package com.example.blog.serivce;

import com.example.blog.domain.dtos.CategoryResponsedto;
import com.example.blog.domain.dtos.CreateCategoryRequestDto;
import com.example.blog.domain.entities.Category;
import com.example.blog.mappers.CategoryMapper;
import com.example.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponsedto> listAllCategories() {
        List<Category> categories=categoryRepository.findAllWithPostCount();
        List<CategoryResponsedto> responses=categories.stream().map(categoryMapper::toCategoryResponse).toList();
        return responses;
    }

    public CategoryResponsedto addCategory(CreateCategoryRequestDto request) {
        if(categoryRepository.existsByNameIgnoreCase(request.getName()))
        {
            throw new RuntimeException("category already listed");
        }
        Category category = categoryMapper.toCategory(request);
        categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    public void deleteCategory(UUID id) {
        Optional<Category>  category = categoryRepository.findById(id);
        if(category.isPresent()) {
            if (category.get().getPosts().size() > 0) {
                throw new IllegalStateException("Category has Posts associated with it");
            }
            categoryRepository.deleteById(id);
        }
    }
}
