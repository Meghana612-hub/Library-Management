# Book Management REST API  📚

This is a **Spring Boot application** that provides a RESTful API for managing a collection of books. 
It supports standard **CRUD** (Create, Read, Update, Delete) **operations** and **advanced search functionalities** by author, year, genre, and title.

## Features ✨

+ **Add Book:** Create new book records. ➕

+ **Get All Books:** Retrieve a list of all books in the library. 📖

+ **Get Book by ID:** Retrieve a single book by its unique identifier.🆔

+ **Update Book:** Modify existing book details.✍️

+ **Delete Book:** Remove a book from the library.🗑️

+ **Search by Author:** Find books by a specific author.🧑‍💻

+ **Search by Year:** Find books published in a particular year.🗓️

+ **Search by Genre:** Find books belonging to a specific genre.🏷️

+ **Search by Title:** Find books by a partial or full title (case-insensitive). 🔍

+ **H2 Database:** Uses a file-based H2 in-memory database for development, allowing data persistence across application restarts.💾 

+ **Custom Not Found Messages:** Provides informative JSON messages for 404 Not Found responses across various endpoints.💬

## Technologies Used 🛠️

+ **Java 17**: Programming Language

+ **Spring Boot 3.x**: Framework for building standalone, production-grade Spring-based applications.

+ **Spring Data JPA**: Simplifies data access layer development.

+ **Hibernate**: JPA implementation for ORM.

+ **H2 Database**: In-memory / file-based relational database for development and testing.

+ **Lombok**: Library to reduce boilerplate code (getters, setters, constructors).

+ **Maven**: Build automation tool.

## Prerequisites ✅

Before you begin, ensure you have the following installed on your machine:

+ **Java Development Kit (JDK) 17 or higher:**

Verify with: java -version

+ **Apache Maven 3.6.x or higher:**

Verify with: mvn -v

+ **Visual Studio Code (VS Code): Or any other preferred IDE (IntelliJ IDEA, Eclipse).**

+ **Postman :** For testing the API endpoints.


## Setup and Running the Application 🚀

+ Clone or Download the Project:If provided as a zip file, extract it to your desired location or if using Git, clone the repository:
  git clone <repository-url>
  cd book-management

+ Open in VS Code:
  - Go to File > Open Folder... and select the book-management project directory.
  - Navigate to src/main/resources/application.properties.
  - Ensure the H2 database URL is configured for file-based persistence:
  - spring.datasource.url=jdbc:h2:file:./data/librarydb

+ Run the Spring Boot Application:
  - Open the integrated terminal in VS Code (View > Terminal or Ctrl + ).
   - Navigate to the project root directory (where pom.xml is located).
   - Run the application using Maven:mvn spring-boot:run

### API Endpoints 🔌

Once the application is running, you can interact with it using Postman. All endpoints are prefixed with /api/books.

Base URL: http://localhost:8085/api/books

**1. Add a New Book (CREATE)** ➕
+ URL: /api/books
+ Method: POST
+ Success Response: 201 Created with the created book object (including generated id).

 **2. Get All Books (READ)**  📖
+ URL: /api/books
+ Method: GET
+ Success Response: 200 OK with a JSON array of all books.

**3. Get Book by ID (READ)** 🆔
+ URL: /api/books/{id} (e.g., /api/books/1)
+ Method: GET
+ Success Response: 200 OK with the book object.
+ Error Response: 404 Not Found with a custom message: {"message": "The book with ID {id} is not present in our library. Please check the ID and try again."}

**4. Update a Book (UPDATE)** ✍️
+ URL: /api/books/{id} (e.g., /api/books/1)
+ Method: PUT
+ Success Response: 200 OK with the updated book object.

**5. Delete a Book (DELETE)** 🗑️
+ URL: /api/books/{id} (e.g., /api/books/1)
+ Method: DELETE
+ Success Response: 200 OK with a custom message: {"message": "The book with ID {id} has been successfully deleted."}
+ Error Response: 404 Not Found with a custom message: {"message": "The book with ID {id} is not present in our library and could not be deleted."}

**6. Search Books by Author** 🧑‍💻
+ URL: /api/books/search/author/{authorName} (e.g., /api/books/search/author/J.R.R. Tolkien)
+ Method: GET
+ Success Response: 200 OK with a JSON array of books by the specified author.
+ Error Response: 404 Not Found with a custom message: {"message": "No books found for author '{authorName}'. Please check the spelling and try again."}

**7. Search Books by Year** 🗓️
+ URL: /api/books/search/year/{publishedYear} (e.g., /api/books/search/year/1954)
+ Method: GET
+ Success Response: 200 OK with a JSON array of books published in the specified year.
+ Error Response: 404 Not Found with a custom message: {"message": "No books found for year '{publishedYear}'. Please check the year and try again."}

**8. Search Books by Genre** 🏷️
+ URL: /api/books/search/genre/{genreName} (e.g., /api/books/search/genre/Fantasy)
+ Method: GET
+ Success Response: 200 OK with a JSON array of books in the specified genre.
+ Error Response: 404 Not Found with a custom message: {"message": "No books found for genre '{genreName}'. Please check the spelling and try again."}

**9. Search Books by Title** 🔍
+ URL: /api/books/search/title/{bookTitle} (e.g., /api/books/search/title/Hitchhiker)
+ Method: GET
+ Success Response: 200 OK with a JSON array of books whose titles contain the search string (case-insensitive).
+ Error Response: 404 Not Found with a custom message: {"message": "No books found with title containing '{bookTitle}'. Please check the spelling and try again."}

### H2 Database Console 📊

You can access the H2 Database Console to view the data directly

+ URL: http://localhost:8081/h2-console
+ JDBC URL: jdbc:h2:file:./data/librarydb
+ User Name: sa
+ Password: password
Click "Connect" to view your database tables and data.
