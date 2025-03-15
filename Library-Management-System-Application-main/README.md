# Library-Management-System | Springboot Application
  This project is a RESTful application built using Spring Boot, designed for managing a library system with enhanced security. The application implements form-based authentication, role-based access control, and exception handling, offering restricted access to APIs based on user roles (Admin, Librarian, Member)

## Features
#### 1. User Roles and Permissions:
  * Admin: Full access to manage users and books.
  * Librarian: Manage (create, update, delete) book records.
  * Member: View and borrow books.
#### 2. Entities:
 * User: id, username, password, role
 * Book: id, title, author, genre, availabilityStatus (e.g., AVAILABLE, BORROWED)
 * BorrowRecord: id, userId, bookId, borrowDate, returnDate
#### Security:
 * Role-based access control using Spring Security annotations
 * Password hashing using BCrypt
#### Exception Handling:
 * Custom exceptions for scenarios like BookNotAvailableException, UserNotFoundException, and UnauthorizedAccessException
 * Global Exception Handler for consistent error responses
 * 
## Technologies Used:
 * Java
 * Spring Boot
 * Spring Security
 * MySQL 
 * BCrypt Password Encoder
 * Postman



## Usage
### API Endpoints
#### Admin APIs:
  * POST /api/admin/users: Create new users
  * PUT /api/admin/users/{userId}: Update user details
  * DELETE /api/admin/users/{userId}: Delete users
#### Librarian APIs:
  * POST /api/librarian/books: Add new books
  * PUT /api/librarian/books/{bookId}: Update book details
  * DELETE /api/librarian/books/{bookId}: Delete a book
#### Member APIs
  * GET /api/member/books: View available books
  * POST /api/member/borrow/{bookId}: Borrow a book

#### Custom Exception Handling
  * BookNotAvailableException: Thrown when a user tries to borrow an unavailable book.
  * UserNotFoundException: Thrown when specified user details are not found.
  * Global Exception Handler: Provides a consistent format for all API error responses

