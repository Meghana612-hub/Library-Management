# Book Management REST API

This is a **Spring Boot application** that provides a RESTful API for managing a collection of books. 
It supports standard **CRUD** (Create, Read, Update, Delete) **operations** and **advanced search functionalities** by author, year, genre, and title.

## Features

+ **Add Book:** Create new book records.

+ **Get All Books:** Retrieve a list of all books in the library.

+ **Get Book by ID:** Retrieve a single book by its unique identifier.

+ **Update Book:** Modify existing book details.

+ **Delete Book:** Remove a book from the library.

+ **Search by Author:** Find books by a specific author.

+ **Search by Year:** Find books published in a particular year.

+ **Search by Genre:** Find books belonging to a specific genre.

+ **Search by Title:** Find books by a partial or full title (case-insensitive).

+ **H2 Database:** Uses a file-based H2 in-memory database for development, allowing data persistence across application restarts.

+ **Custom Not Found Messages:** Provides informative JSON messages for 404 Not Found responses across various endpoints.

## Technologies Used

+ **Java 17**: Programming Language

+ **Spring Boot 3.x**: Framework for building standalone, production-grade Spring-based applications.

+ **Spring Data JPA**: Simplifies data access layer development.

+ **Hibernate**: JPA implementation for ORM.

+ **H2 Database**: In-memory / file-based relational database for development and testing.

+ **Lombok**: Library to reduce boilerplate code (getters, setters, constructors).

+ **Maven**: Build automation tool.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

+ **Java Development Kit (JDK) 17 or higher:**

Verify with: java -version

+ **Apache Maven 3.6.x or higher:**

Verify with: mvn -v

+ **Visual Studio Code (VS Code): Or any other preferred IDE (IntelliJ IDEA, Eclipse).**

+ **Postman :** For testing the API endpoints.
