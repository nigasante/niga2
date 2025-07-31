package com.newsapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleRequest {
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String content;
    
    @NotNull
    private Integer editorID;
    
    @NotBlank
    private String status;
    
    private LocalDateTime publishDate;
    
    private List<Integer> categoryIDs;
    private String imageUrl;
    
    // Constructors
    public ArticleRequest() {}
    
    public ArticleRequest(String title, String content, Integer editorID, String status) {
        this.title = title;
        this.content = content;
        this.editorID = editorID;
        this.status = status;
    }
    
    // Getters and Setters
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
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getPublishDate() {
        return publishDate;
    }
    
    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
    
    public List<Integer> getCategoryIDs() {
        return categoryIDs;
    }
    
    public void setCategoryIDs(List<Integer> categoryIDs) {
        this.categoryIDs = categoryIDs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
