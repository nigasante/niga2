package com.newsapp.service;

import com.newsapp.model.Article;
import com.newsapp.model.ArticleCategory;
import com.newsapp.model.Category;
import com.newsapp.model.User;
import com.newsapp.repository.ArticleCategoryRepository;
import com.newsapp.repository.ArticleRepository;
import com.newsapp.repository.CategoryRepository;
import com.newsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DataInitializationService implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
    
    private void initializeData() {
        // Create users if they don't exist
        if (userRepository.count() == 0) {
            createDefaultUsers();
        }
        
        // Create categories if they don't exist
        if (categoryRepository.count() == 0) {
            createDefaultCategories();
        }
        
        // Create sample articles if they don't exist
        if (articleRepository.count() == 0) {
            createSampleArticles();
        }
    }
    
    private void createDefaultUsers() {
        // Admin user
        User admin = new User();
        admin.setName("Admin");
        admin.setEmail("admin@newsapp.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRoleID(1); // Admin role
        admin.setIsDeleted(false);
        userRepository.save(admin);
        
        // Regular user
        User user = new User();
        user.setName("User");
        user.setEmail("user@newsapp.com");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRoleID(2); // User role
        user.setIsDeleted(false);
        userRepository.save(user);
        
        // Editor user
        User editor = new User();
        editor.setName("Editor");
        editor.setEmail("editor@newsapp.com");
        editor.setPassword(passwordEncoder.encode("editor123"));
        editor.setRoleID(3); // Editor role
        editor.setIsDeleted(false);
        userRepository.save(editor);
        
        System.out.println("Default users created:");
        System.out.println("Admin: admin@newsapp.com / admin123");
        System.out.println("User: user@newsapp.com / user123");
        System.out.println("Editor: editor@newsapp.com / editor123");
    }
    
    private void createDefaultCategories() {
        Category politics = new Category("Chính trị", "Tin tức chính trị trong nước và quốc tế");
        politics.setIsDeleted(false);
        categoryRepository.save(politics);
        
        Category economy = new Category("Kinh tế", "Tin tức kinh tế, tài chính");
        economy.setIsDeleted(false);
        categoryRepository.save(economy);
        
        Category technology = new Category("Công nghệ", "Tin tức công nghệ, khoa học");
        technology.setIsDeleted(false);
        categoryRepository.save(technology);
        
        Category sports = new Category("Thể thao", "Tin tức thể thao trong nước và quốc tế");
        sports.setIsDeleted(false);
        categoryRepository.save(sports);
        
        Category entertainment = new Category("Giải trí", "Tin tức giải trí, văn hóa");
        entertainment.setIsDeleted(false);
        categoryRepository.save(entertainment);
        
        System.out.println("Default categories created");
    }
    
    private void createSampleArticles() {
        User editor = userRepository.findByEmail("editor@newsapp.com").orElse(null);
        Category technology = categoryRepository.findByNameAndIsDeletedFalse("Công nghệ");
        Category economy = categoryRepository.findByNameAndIsDeletedFalse("Kinh tế");
        
        if (editor != null && technology != null) {
            // Sample technology article
            Article techArticle = new Article();
            techArticle.setTitle("Xu hướng công nghệ AI trong năm 2025");
            techArticle.setContent("Trí tuệ nhân tạo (AI) đang phát triển với tốc độ chóng mặt và năm 2025 hứa hẹn sẽ mang đến nhiều đột phá quan trọng. Các ứng dụng AI ngày càng được tích hợp sâu hơn vào cuộc sống hàng ngày, từ việc hỗ trợ trong công việc, giáo dục, y tế cho đến giải trí.\n\nMột trong những xu hướng nổi bật nhất là sự phát triển của AI tạo sinh (Generative AI), với khả năng tạo ra nội dung text, hình ảnh, video và âm thanh ngày càng tinh vi. Các mô hình ngôn ngữ lớn như GPT, Claude, và Gemini đang được cải tiến liên tục để hiểu và phản hồi tự nhiên hơn.\n\nTrong lĩnh vực doanh nghiệp, AI đang được ứng dụng rộng rãi để tự động hóa quy trình, phân tích dữ liệu và đưa ra quyết định thông minh. Nhiều công ty đang đầu tư mạnh vào việc xây dựng hệ thống AI để tăng hiệu quả và giảm chi phí vận hành.");
            techArticle.setEditorID(editor.getUserID());
            techArticle.setStatus("Published");
            techArticle.setPublishDate(LocalDateTime.now());
            techArticle.setIsDeleted(false);
            
            Article savedTechArticle = articleRepository.save(techArticle);
            
            ArticleCategory articleCategory1 = new ArticleCategory(savedTechArticle, technology);
            articleCategoryRepository.save(articleCategory1);
        }
        
        if (editor != null && economy != null) {
            // Sample economy article
            Article economyArticle = new Article();
            economyArticle.setTitle("Thị trường chứng khoán Việt Nam triển vọng 2025");
            economyArticle.setContent("Thị trường chứng khoán Việt Nam năm 2024 đã có những diễn biến tích cực và hứa hẹn sẽ tiếp tục phát triển trong năm 2025. Các chuyên gia dự báo VN-Index có thể đạt mức 1,400-1,500 điểm trong năm tới.\n\nCác yếu tố hỗ trợ thị trường gồm:\n- Kinh tế vĩ mô ổn định với tăng trưởng GDP dự kiến đạt 6.5-7%\n- Dòng tiền ngoại tiếp tục đổ vào thị trường sau khi nâng hạng\n- Các doanh nghiệp lớn có kết quả kinh doanh khả quan\n- Chính sách hỗ trợ từ Chính phủ cho thị trường tài chính\n\nTuy nhiên, nhà đầu tư cần lưu ý các rủi ro từ biến động kinh tế toàn cầu, lạm phát và chính sách tiền tệ của các ngân hàng trung ương lớn.");
            economyArticle.setEditorID(editor.getUserID());
            economyArticle.setStatus("Published");
            economyArticle.setPublishDate(LocalDateTime.now().minusDays(1));
            economyArticle.setIsDeleted(false);
            
            Article savedEconomyArticle = articleRepository.save(economyArticle);
            
            ArticleCategory articleCategory2 = new ArticleCategory(savedEconomyArticle, economy);
            articleCategoryRepository.save(articleCategory2);
        }
        
        System.out.println("Sample articles created");
    }
}
