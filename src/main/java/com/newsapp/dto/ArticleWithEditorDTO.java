package com.newsapp.dto;

import java.time.LocalDateTime;

public class ArticleWithEditorDTO {
    private Integer articleID;
    private String title;
    private String content;
    private Integer editorID;
    private String editorName;
    private String status;
    private LocalDateTime publishDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private String imageUrl;

    // Constructors
    public ArticleWithEditorDTO() {}

    public ArticleWithEditorDTO(Integer articleID, String title, String content, 
                                Integer editorID, String editorName, String status, 
                                LocalDateTime publishDate, LocalDateTime createdAt, 
                                LocalDateTime updatedAt, Boolean isDeleted, String imageUrl) {
        this.articleID = articleID;
        this.title = title;
        this.content = content;
        this.editorID = editorID;
        this.editorName = editorName;
        this.status = status;
        this.publishDate = publishDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.imageUrl = imageUrl;
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

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
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
}
