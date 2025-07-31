<<<<<<< HEAD
# Newspaper App - Spring Boot Version

This is a Spring Boot conversion of the original Flutter newspaper application. It provides the same functionality through a web interface and REST APIs.

## Features

- User authentication (login/signup)
- Article management (create, read, update, delete)
- Category management
- Role-based access control (Admin, Editor, User)
- Web interface similar to the original Flutter app
- SQL Server database integration

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- SQL Server database
- An IDE like IntelliJ IDEA or Eclipse

## Database Setup

1. Make sure SQL Server is running on `LAPTOP-7SVOI8TF\CAT`
2. Create a database named `NewspaperAppDB`
3. The application will automatically create the necessary tables on first run

Database connection details:
- Server: `LAPTOP-7SVOI8TF\CAT`
- Database: `NewspaperAppDB`
- Username: `sa`
- Password: `1234`

## Running the Application

1. Navigate to the project directory:
   ```bash
   cd d:\mobi-master\spring-boot
   ```

2. Clean and compile the project:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The application will start on port 8080. Access it at:
   ```
   http://localhost:8080
   ```

## Default Users

The application creates default users on first run:

| Role   | Email                | Password   |
|--------|---------------------|------------|
| Admin  | admin@newsapp.com   | admin123   |
| User   | user@newsapp.com    | user123    |
| Editor | editor@newsapp.com  | editor123  |

## API Endpoints

### Authentication
- `POST /api/users/login` - User login
- `POST /api/users/signup` - User registration

### Articles
- `GET /api/articles` - Get all articles (optional: ?categoryId=X)
- `GET /api/articles/{id}` - Get article by ID
- `GET /api/articles/editor/{editorId}` - Get articles by editor
- `POST /api/articles` - Create new article
- `PUT /api/articles/{id}` - Update article
- `DELETE /api/articles/{id}` - Delete article

### Categories
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create new category
- `PUT /api/categories/{id}` - Update category
- `DELETE /api/categories/{id}` - Delete category

## Web Interface

### Login Page (`/`)
- Email/password login form
- Registration modal for new users
- Automatic redirect to home page after successful login

### Home Page (`/home`)
- Article listing with category filtering
- Article detail modal
- Create/edit article functionality for editors/admins
- Role-based UI controls

## User Roles

1. **Admin (Role ID: 1)**
   - Full access to all features
   - Can manage all articles and categories
   - Can manage users

2. **User (Role ID: 2)**
   - Can view articles
   - Limited access to management features

3. **Editor (Role ID: 3)**
   - Can create and edit articles
   - Can manage categories
   - Can edit own articles

## Project Structure

```
src/
├── main/
│   ├── java/com/newsapp/
│   │   ├── NewspaperAppApplication.java
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── ArticleController.java
│   │   │   ├── CategoryController.java
│   │   │   ├── UserController.java
│   │   │   └── WebController.java
│   │   ├── dto/
│   │   │   ├── ArticleRequest.java
│   │   │   ├── LoginRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   └── SignupRequest.java
│   │   ├── model/
│   │   │   ├── Article.java
│   │   │   ├── ArticleCategory.java
│   │   │   ├── Category.java
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   ├── ArticleCategoryRepository.java
│   │   │   ├── ArticleRepository.java
│   │   │   ├── CategoryRepository.java
│   │   │   └── UserRepository.java
│   │   └── service/
│   │       ├── ArticleService.java
│   │       ├── CategoryService.java
│   │       ├── DataInitializationService.java
│   │       ├── JwtService.java
│   │       └── UserService.java
│   └── resources/
│       ├── application.properties
│       └── static/
│           ├── index.html
│           └── home.html
└── test/
```

## Configuration

Key configuration in `application.properties`:

```properties
# Database
spring.datasource.url=jdbc:sqlserver://LAPTOP-7SVOI8TF\\CAT:1433;databaseName=NewspaperAppDB
spring.datasource.username=sa
spring.datasource.password=1234

# Server
server.port=8080
server.servlet.context-path=/api

# JWT
jwt.secret=mySecretKey123456789mySecretKey123456789
jwt.expiration=86400000
```

## Development Notes

1. The application uses JPA/Hibernate for database operations
2. Spring Security is configured for CORS and basic authentication
3. JWT tokens are generated for authenticated users
4. The web interface uses vanilla JavaScript and communicates with REST APIs
5. Soft delete is implemented (records are marked as deleted, not physically removed)

## Comparison with Original Flutter App

| Feature | Flutter App | Spring Boot App |
|---------|-------------|-----------------|
| Platform | Mobile (Android/iOS) | Web |
| Frontend | Flutter/Dart | HTML/CSS/JavaScript |
| Backend | .NET Core API | Spring Boot |
| Database | SQL Server | SQL Server |
| Authentication | JWT | JWT |
| UI Framework | Material Design | Custom CSS |
| State Management | Flutter State | DOM Manipulation |

## Troubleshooting

1. **Database Connection Issues**
   - Verify SQL Server is running
   - Check connection string in application.properties
   - Ensure database `NewspaperAppDB` exists

2. **Port Already in Use**
   - Change port in application.properties: `server.port=8081`

3. **Compilation Errors**
   - Ensure Java 17+ is installed
   - Run `mvn clean install` to resolve dependencies

4. **CORS Issues**
   - The application is configured to allow all origins
   - Check browser console for specific CORS errors

## Future Enhancements

- File upload for article images
- Email notifications
- Advanced search functionality
- Article versioning
- Comment system
- Mobile-responsive design improvements
- Admin dashboard
- Analytics and reporting
=======
# mobi
>>>>>>> f57cff192d183a640a1f2e273b976dbdcb79d7bb
