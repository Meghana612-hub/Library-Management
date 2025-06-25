package com.mylibrary.book_management.controller;

import java.util.Collections; // Import your Book model
import java.util.List; // Import your BookService
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; // Import for Collections.singletonMap
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Import for Map
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylibrary.book_management.model.book;
import com.mylibrary.book_management.service.bookService;
/**
 * REST Controller for managing Book resources.
 * Handles HTTP requests and delegates to the BookService.
 */
@RestController // Marks this class as a REST controller, making it capable of handling incoming web requests
@RequestMapping("/api/books") // Base URL path for all endpoints in this controller, e.g., http://localhost:8080/api/books
public class bookController {

    private final bookService bookService;

    @Autowired // Injects the BookService instance into this controller
    public bookController(bookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint to add a new book (CREATE operation).
     * HTTP Method: POST
     * URL: /api/books
     * Request Body: Book object in JSON format (e.g., {"title": "New Book", "author": "Someone"})
     * @param book The book object to be created, deserialized from the request body.
     * @return ResponseEntity with the created book and HTTP status 201 (Created).
     */
    @PostMapping // Maps HTTP POST requests to /api/books
    public ResponseEntity<book> addBook(@RequestBody book book) {
        // @RequestBody binds the HTTP request body (JSON) to the Book object
        book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED); // Return 201 Created status, indicating successful resource creation
    }

    /**
     * Endpoint to get all books (READ operation).
     * HTTP Method: GET
     * URL: /api/books
     * @return ResponseEntity with a list of all books and HTTP status 200 (OK).
     */
    @GetMapping // Maps HTTP GET requests to /api/books
    public ResponseEntity<List<book>> getAllBooks() {
        List<book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK); // Return 200 OK status
    }

    /**
     * Endpoint to get a book by its ID (READ operation).
     * HTTP Method: GET
     * URL: /api/books/{id} (e.g., /api/books/1)
     * @param id The ID of the book to retrieve, extracted from the URL path.
     * @return ResponseEntity with the book if found (200 OK), or 404 Not Found with a custom message if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id) { // CHANGED: From ResponseEntity<Book> to ResponseEntity<Object>
        return bookService.getBookById(id)
                .map(book -> new ResponseEntity<Object>(book, HttpStatus.OK)) // CHANGED: Explicitly specifying <Object> here
                .orElseGet(() -> {
                    // If book not found, create a custom error message
                    Map<String, String> errorMessage = Collections.singletonMap(
                        "message",
                        "The book with ID " + id + " is not present in our library. Please check the ID and try again."
                    );
                    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND); // Return 404 Not Found with the custom message
                });
    }
// --- New Search Endpoints ---

    /**
     * Endpoint to search books by author.
     * HTTP Method: GET
     * URL: /api/books/search/author/{authorName}
     * @param authorName The name of the author to search for.
     * @return ResponseEntity with a list of books from that year (200 OK),
     * or 404 Not Found with a custom message if no books are found.
     */
   @GetMapping("/search/author/{authorName}")
    public ResponseEntity<Object> searchBooksByAuthor(@PathVariable String authorName) {
        List<book> books = bookService.getBooksByAuthor(authorName);
        if (books.isEmpty()) {
            Map<String, String> errorMessage = Collections.singletonMap(
                "message",
                "No books found for author '" + authorName + "'. Please check the spelling and try again."
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    /**
     * Endpoint to search books by published year.
     * HTTP Method: GET
     * URL: /api/books/search/year/{publishedYear}
     * @param publishedYear The year to search for.
     * @return ResponseEntity with a list of books from that year (200 OK),
     * or 404 Not Found with a custom message if no books are found.
     */
    @GetMapping("/search/year/{publishedYear}")
    public ResponseEntity<Object> searchBooksByYear(@PathVariable Integer publishedYear) {
        List<book> books = bookService.getBooksByPublishedYear(publishedYear);
        if (books.isEmpty()) {
            Map<String, String> errorMessage = Collections.singletonMap(
                "message",
                "No books found for year '" + publishedYear + "'. Please check the year and try again."
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to search books by genre.
     * HTTP Method: GET
     * URL: /api/books/search/genre/{genreName}
     * @param genreName The genre to search for.
     * @return ResponseEntity with a list of books in that genre (200 OK),
     * or 404 Not Found with a custom message if no books are found.
     */
    @GetMapping("/search/genre/{genreName}")
    public ResponseEntity<Object> searchBooksByGenre(@PathVariable String genreName) {
        List<book> books = bookService.getBooksByGenre(genreName);
        if (books.isEmpty()) {
            Map<String, String> errorMessage = Collections.singletonMap(
                "message",
                "No books found for genre '" + genreName + "'. Please check the spelling and try again."
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

/**
     * Endpoint to search books by title.
     * HTTP Method: GET
     * URL: /api/books/search/title/{bookTitle}
     * @param bookTitle The title (or part of the title) to search for.
     * @return ResponseEntity with a list of books matching the title (200 OK),
     * or 404 Not Found with a custom message if no books are found.
     */
    @GetMapping("/search/title/{bookTitle}")
    public ResponseEntity<Object> searchBooksByTitle(@PathVariable String bookTitle) {
        List<book> books = bookService.getBooksByTitle(bookTitle);
        if (books.isEmpty()) {
            Map<String, String> errorMessage = Collections.singletonMap(
                "message",
                "No books found with title containing '" + bookTitle + "'. Please check the spelling and try again."
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    // --- End New Search Endpoints ---

    /**
     * Endpoint to update an existing book by its ID (UPDATE operation).
     * HTTP Method: PUT
     * URL: /api/books/{id}
     * Request Body: Book object with updated details in JSON format
     * @param id The ID of the book to update, extracted from the URL path.
     * @param bookDetails The book object with updated details, deserialized from the request body.
     * @return ResponseEntity with the updated book if found (200 OK), or 404 Not Found.
     */
    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/books/{id}
    public ResponseEntity<book> updateBook(@PathVariable Long id, @RequestBody book bookDetails) {
        return bookService.updateBook(id, bookDetails)
                .map(updatedBook -> new ResponseEntity<>(updatedBook, HttpStatus.OK)) // If updated, return 200 OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // If not found, return 404 Not Found
    }

    
    /**
     * Endpoint to delete a book by its ID (DELETE operation).
     * HTTP Method: DELETE
     * URL: /api/books/{id}
     * @param id The ID of the book to delete, extracted from the URL path.
     * @return ResponseEntity with a success message (200 OK) if deleted, or 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) { // Changed return type to Object
        if (bookService.deleteBook(id)) {
            Map<String, String> successMessage = Collections.singletonMap( // Added success message map
                "message",
                "The book with ID " + id + " has been successfully deleted."
            );
            return new ResponseEntity<>(successMessage, HttpStatus.OK); // Return 200 OK with success message
        } else {
            // Re-using the existing error message for consistency if book is not found
            Map<String, String> errorMessage = Collections.singletonMap(
                "message",
                "The book with ID " + id + " is not present in our library and could not be deleted."
            );
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND); // Return 404 Not Found with custom message
        }
    }
}
