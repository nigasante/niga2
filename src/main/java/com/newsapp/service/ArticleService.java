package com.newsapp.service;

import com.newsapp.dto.ArticleRequest;
import com.newsapp.dto.ArticleWithEditorDTO;
import com.newsapp.model.Article;
import com.newsapp.model.ArticleCategory;
import com.newsapp.model.Category;
import com.newsapp.repository.ArticleCategoryRepository;
import com.newsapp.repository.ArticleRepository;
import com.newsapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;
    
    public List<ArticleWithEditorDTO> getAllArticlesWithEditor() {
        return articleRepository.findAllWithEditor();
    }
    
    public List<Article> getAllArticles() {
        return articleRepository.findByIsDeletedFalseOrderByCreatedAtDesc();
    }
    
    public List<ArticleWithEditorDTO> getArticlesByCategoryWithEditor(Integer categoryId) {
        return articleRepository.findByCategoryIdWithEditor(categoryId);
    }
    
    public List<Article> getArticlesByCategory(Integer categoryId) {
        return articleRepository.findByCategoryIdAndIsDeletedFalse(categoryId);
    }
    
    public List<Article> getArticlesByEditor(Integer editorId) {
        return articleRepository.findByEditorIDAndIsDeletedFalse(editorId);
    }
    
    public List<Article> getArticlesByStatus(String status) {
        return articleRepository.findByStatusAndIsDeletedFalse(status);
    }
    
    public Article getArticleById(Integer articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }
    
    public ArticleWithEditorDTO getArticleByIdWithEditor(Integer articleId) {
        ArticleWithEditorDTO article = articleRepository.findByIdWithEditor(articleId);
        if (article == null) {
            throw new RuntimeException("Article with editor info not found");
        }
        return article;
    }
    
    @Transactional
    public Article createArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setEditorID(articleRequest.getEditorID());
        article.setStatus(articleRequest.getStatus());
        article.setImageUrl(articleRequest.getImageUrl());
        
        // Set publish date - if not provided or status is Published, set to now
        if (articleRequest.getPublishDate() != null) {
            article.setPublishDate(articleRequest.getPublishDate());
        } else if ("Published".equals(articleRequest.getStatus())) {
            article.setPublishDate(LocalDateTime.now());
        }
        
        // Ensure isDeleted is set to false
        article.setIsDeleted(false);
        
        // CreatedAt and UpdatedAt will be set automatically by @CreationTimestamp and @UpdateTimestamp
        
        Article savedArticle = articleRepository.save(article);
        
        // Add categories
        if (articleRequest.getCategoryIDs() != null) {
            for (Integer categoryId : articleRequest.getCategoryIDs()) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
                
                ArticleCategory articleCategory = new ArticleCategory(savedArticle, category);
                articleCategoryRepository.save(articleCategory);
            }
        }
        
        return savedArticle;
    }
    
    @Transactional
    public Article updateArticle(Integer articleId, ArticleRequest articleRequest) {
        Article article = getArticleById(articleId);
        
        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());
        article.setStatus(articleRequest.getStatus());
        article.setImageUrl(articleRequest.getImageUrl());
        
        // Update publish date if provided or if status changes to Published
        if (articleRequest.getPublishDate() != null) {
            article.setPublishDate(articleRequest.getPublishDate());
        } else if ("Published".equals(articleRequest.getStatus()) && article.getPublishDate() == null) {
            article.setPublishDate(LocalDateTime.now());
        }
        
        article.setUpdatedAt(LocalDateTime.now());
        
        // Remove existing categories
        articleCategoryRepository.deleteByArticleArticleID(articleId);
        
        // Add new categories
        if (articleRequest.getCategoryIDs() != null) {
            for (Integer categoryId : articleRequest.getCategoryIDs()) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
                
                ArticleCategory articleCategory = new ArticleCategory(article, category);
                articleCategoryRepository.save(articleCategory);
            }
        }
        
        return articleRepository.save(article);
    }
    
    @Transactional
    public void deleteArticle(Integer articleId) {
        Article article = getArticleById(articleId);
        
        // First delete related ArticleCategory entries
        articleCategoryRepository.deleteByArticleArticleID(articleId);
        
        // Then delete the article itself (hard delete)
        articleRepository.delete(article);
    }
    
    public List<Article> searchArticles(String keyword) {
        return articleRepository.findByKeywordAndIsDeletedFalse(keyword);
    }
}
