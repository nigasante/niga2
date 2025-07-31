package com.newsapp.controller;

import com.newsapp.dto.ArticleRequest;
import com.newsapp.dto.ArticleWithEditorDTO;
import com.newsapp.model.Article;
import com.newsapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    @GetMapping
    public ResponseEntity<List<ArticleWithEditorDTO>> getAllArticles(@RequestParam(required = false) Integer categoryId) {
        try {
            List<ArticleWithEditorDTO> articles;
            if (categoryId != null) {
                articles = articleService.getArticlesByCategoryWithEditor(categoryId);
            } else {
                articles = articleService.getAllArticlesWithEditor();
            }
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ArticleWithEditorDTO> getArticleById(@PathVariable Integer id) {
        try {
            ArticleWithEditorDTO article = articleService.getArticleByIdWithEditor(id);
            return ResponseEntity.ok(article);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/editor/{editorId}")
    public ResponseEntity<List<Article>> getArticlesByEditor(@PathVariable Integer editorId) {
        try {
            List<Article> articles = articleService.getArticlesByEditor(editorId);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Article>> getArticlesByStatus(@PathVariable String status) {
        try {
            List<Article> articles = articleService.getArticlesByStatus(status);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        try {
            Article article = articleService.createArticle(articleRequest);
            return ResponseEntity.ok(article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Integer id, 
                                         @Valid @RequestBody ArticleRequest articleRequest) {
        try {
            Article article = articleService.updateArticle(id, articleRequest);
            return ResponseEntity.ok(article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Integer id) {
        try {
            System.out.println("Attempting to delete article with ID: " + id);
            articleService.deleteArticle(id);
            System.out.println("Article with ID: " + id + " marked as deleted successfully");
            return ResponseEntity.ok("Article deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting article with ID: " + id + " - " + e.getMessage());
            return ResponseEntity.badRequest().body("Failed to delete article: " + e.getMessage());
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String keyword) {
        try {
            List<Article> articles = articleService.searchArticles(keyword);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
