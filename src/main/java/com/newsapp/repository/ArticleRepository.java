package com.newsapp.repository;

import com.newsapp.dto.ArticleWithEditorDTO;
import com.newsapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    
    @Query("SELECT a FROM Article a WHERE a.isDeleted = false ORDER BY a.createdAt DESC")
    List<Article> findByIsDeletedFalse();
    
    @Query("SELECT a FROM Article a WHERE a.isDeleted = false ORDER BY a.createdAt DESC")
    List<Article> findByIsDeletedFalseOrderByCreatedAtDesc();
    
    @Query("SELECT a FROM Article a WHERE a.editorID = :editorID AND a.isDeleted = false ORDER BY a.createdAt DESC")
    List<Article> findByEditorIDAndIsDeletedFalse(@Param("editorID") Integer editorID);
    
    @Query("SELECT a FROM Article a WHERE a.status = :status AND a.isDeleted = false ORDER BY a.createdAt DESC")
    List<Article> findByStatusAndIsDeletedFalse(@Param("status") String status);
    
    @Query("SELECT DISTINCT a FROM Article a " +
           "INNER JOIN ArticleCategory ac ON a.articleID = ac.article.articleID " +
           "WHERE ac.category.categoryID = :categoryId AND a.isDeleted = false " +
           "ORDER BY a.createdAt DESC")
    List<Article> findByCategoryIdAndIsDeletedFalse(@Param("categoryId") Integer categoryId);
    
    @Query("SELECT a FROM Article a WHERE (a.title LIKE %:keyword% OR a.content LIKE %:keyword%) AND a.isDeleted = false ORDER BY a.createdAt DESC")
    List<Article> findByKeywordAndIsDeletedFalse(@Param("keyword") String keyword);
    
    @Query("SELECT new com.newsapp.dto.ArticleWithEditorDTO(a.articleID, a.title, a.content, a.editorID, u.name, a.status, a.publishDate, a.createdAt, a.updatedAt, a.isDeleted, a.imageUrl) " +
           "FROM Article a INNER JOIN User u ON a.editorID = u.userID " +
           "WHERE a.articleID = :articleId")
    ArticleWithEditorDTO findByIdWithEditor(@Param("articleId") Integer articleId);
    
    @Query("SELECT new com.newsapp.dto.ArticleWithEditorDTO(a.articleID, a.title, a.content, a.editorID, u.name, a.status, a.publishDate, a.createdAt, a.updatedAt, a.isDeleted, a.imageUrl) " +
           "FROM Article a INNER JOIN User u ON a.editorID = u.userID " +
           "WHERE a.isDeleted = false ORDER BY a.createdAt DESC")
    List<com.newsapp.dto.ArticleWithEditorDTO> findAllWithEditor();
    
    @Query("SELECT new com.newsapp.dto.ArticleWithEditorDTO(a.articleID, a.title, a.content, a.editorID, u.name, a.status, a.publishDate, a.createdAt, a.updatedAt, a.isDeleted, a.imageUrl) " +
           "FROM Article a INNER JOIN User u ON a.editorID = u.userID " +
           "INNER JOIN ArticleCategory ac ON a.articleID = ac.article.articleID " +
           "WHERE ac.category.categoryID = :categoryId AND a.isDeleted = false " +
           "ORDER BY a.createdAt DESC")
    List<com.newsapp.dto.ArticleWithEditorDTO> findByCategoryIdWithEditor(@Param("categoryId") Integer categoryId);
}
