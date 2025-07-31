package com.newsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Articles")
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArticleID")
    private Integer articleID;
    
    @NotBlank
    @Column(name = "Title")
    private String title;
    
    @NotBlank
    @Column(name = "Content", columnDefinition = "TEXT")
    private String content;
    
    @NotNull
    @Column(name = "EditorID")
    private Integer editorID;
    
    @NotBlank
    @Column(name = "Status")
    private String status;
    @Column(name = "ImageUrl")
    private String imageUrl;
    
    @Column(name = "PublishDate")
    private LocalDateTime publishDate;
    
    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;
    
    @Column(name = "IsDeleted")
    private Boolean isDeleted = false;
    
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticleCategory> articleCategories;
    
    // Constructors
    public Article() {}
    
    public Article(String title, String content, Integer editorID, String status) {
        this.title = title;
        this.content = content;
        this.editorID = editorID;
        this.status = status;
    }
    
    // Getters and Setters
    public Integer getArticleID() {
        return articleID;
    }
    
    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Integer getEditorID() {
        return editorID;
    }
    
    public void setEditorID(Integer editorID) {
        this.editorID = editorID;
    }
    
    public String getStatus() {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getPublishDate() {
        return publishDate;
    }
    
    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }
    
    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }
}
