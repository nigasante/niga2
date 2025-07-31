package com.newsapp.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ArticleCategories")
@IdClass(ArticleCategory.ArticleCategoryId.class)
public class ArticleCategory {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArticleID")
    private Article article;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID")
    private Category category;
    
    // Constructors
    public ArticleCategory() {}
    
    public ArticleCategory(Article article, Category category) {
        this.article = article;
        this.category = category;
    }
    
    // Getters and Setters
    public Article getArticle() {
        return article;
    }
    
    public void setArticle(Article article) {
        this.article = article;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    // Composite key class
    public static class ArticleCategoryId implements Serializable {
        private Integer article;
        private Integer category;
        
        public ArticleCategoryId() {}
        
        public ArticleCategoryId(Integer article, Integer category) {
            this.article = article;
            this.category = category;
        }
        
        // equals and hashCode
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ArticleCategoryId that = (ArticleCategoryId) obj;
            return article.equals(that.article) && category.equals(that.category);
        }
        
        @Override
        public int hashCode() {
            return article.hashCode() + category.hashCode();
        }
    }
}
