package com.newsapp.repository;

import com.newsapp.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, ArticleCategory.ArticleCategoryId> {
    
    void deleteByArticleArticleID(Integer articleId);
}
