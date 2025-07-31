package com.newsapp.service;

import com.newsapp.model.Category;
import com.newsapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findByIsDeletedFalse();
    }
    
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
    
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameAndIsDeletedFalse(category.getName())) {
            throw new RuntimeException("Category with this name already exists");
        }
        category.setIsDeleted(false);
        return categoryRepository.save(category);
    }
    
    public Category updateCategory(Integer categoryId, Category categoryUpdate) {
        Category category = getCategoryById(categoryId);
        
        // Check if name already exists (excluding current category)
        Category existingCategory = categoryRepository.findByNameAndIsDeletedFalse(categoryUpdate.getName());
        if (existingCategory != null && !existingCategory.getCategoryID().equals(categoryId)) {
            throw new RuntimeException("Category with this name already exists");
        }
        
        category.setName(categoryUpdate.getName());
        category.setDescription(categoryUpdate.getDescription());
        
        return categoryRepository.save(category);
    }
    
    public void deleteCategory(Integer categoryId) {
        Category category = getCategoryById(categoryId);
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }
}
