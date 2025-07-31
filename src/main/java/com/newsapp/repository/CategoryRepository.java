package com.newsapp.repository;

import com.newsapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    List<Category> findByIsDeletedFalse();
    
    Category findByNameAndIsDeletedFalse(String name);
    
    boolean existsByNameAndIsDeletedFalse(String name);
}
