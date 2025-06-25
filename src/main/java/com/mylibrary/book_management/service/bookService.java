package com.mylibrary.book_management.service;

import java.util.List; // Import your Book model
import java.util.Optional; // Import your BookRepository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylibrary.book_management.model.book;
import com.mylibrary.book_management.repository.bookRepository;

/**
 * Service class for managing Book-related business logic.
 * Interacts with the BookRepository to perform CRUD operations.
 */
@Service // Marks this class as a Spring service component
public class bookService {

    private final bookRepository bookRepository;

    @Autowired // Injects BookRepository instance into this service
    public bookService(bookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new book to the database.
     * @param book The book object to be added.
     * @return The saved book object (with generated ID).
     */
    public book addBook(book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieves all books from the database.
     * @return A list of all books.
     */
    public List<book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID.
     * @param id The ID of the book to retrieve.
     * @return An Optional containing the book if found, or empty if not found.
     */
    public Optional<book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Updates an existing book's details.
     * It first checks if the book exists, then updates its properties, and saves it.
     * @param id The ID of the book to update.
     * @param updatedBook The book object with updated details.
     * @return An Optional containing the updated book if found and updated, or empty if not found.
     */
    public Optional<book> updateBook(Long id, book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            // Update existing book's properties with values from updatedBook
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setPublishedYear(updatedBook.getPublishedYear());
            return bookRepository.save(existingBook); // Save the updated book back to the database
        });
    }

    /**
     * Deletes a book by its ID.
     * Checks if the book exists before attempting to delete.
     * @param id The ID of the book to delete.
     * @return True if the book was found and deleted, false otherwise.
     */
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
// --- New search methods leveraging repository methods ---

    /**
     * Finds books by author.
     * @param author The author's name.
     * @return A list of books by the specified author.
     */
    public List<book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * Finds books by published year.
     * @param year The published year.
     * @return A list of books published in the specified year.
     */
    public List<book> getBooksByPublishedYear(Integer year) {
        return bookRepository.findByPublishedYear(year);
    }

    /**
     * Finds books by genre.
     * @param genre The genre of the book.
     * @return A list of books in the specified genre.
     */
    public List<book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    /**
     * Finds books by title (partial and case-insensitive match).
     * @param title The title (or part of the title) to search for.
     * @return A list of books with titles matching the given string.
     */
    public List<book> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}


