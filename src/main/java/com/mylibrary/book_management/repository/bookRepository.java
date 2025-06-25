package com.mylibrary.book_management.repository;

import java.util.List;       // Import  Book model

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylibrary.book_management.model.book;                               // Import List for returning multiple books

/**
 * Repository interface for Book entity.
 * Extends JpaRepository to inherit standard CRUD operations.
 * JpaRepository<T, ID> where T is the entity type and ID is the type of its primary key.
 */
@Repository // Marks this interface as a Spring Data JPA repository
public interface bookRepository extends JpaRepository<book, Long> {
    // Spring Data JPA automatically provides methods like:
    // - save(Book book): Saves a book (create/update)
    // - findById(Long id): Retrieves a book by its ID
    // - findAll(): Retrieves all books
    // - deleteById(Long id): Deletes a book by its ID
    // - existsById(Long id): Checks if a book with the given ID exists

     // New custom query methods for searching:

    /**
     * Finds books by a specific author.
     * Spring Data JPA automatically generates the query for this method.
     * @param author The author's name to search for.
     * @return A list of books by the specified author.
     */
    List<book> findByAuthor(String author);

    /**
     * Finds books by a specific published year.
     * Spring Data JPA automatically generates the query for this method.
     * @param publishedYear The published year to search for.
     * @return A list of books published in the specified year.
     */
    List<book> findByPublishedYear(Integer publishedYear);

    /**
     * Finds books by a specific genre.
     * Spring Data JPA automatically generates the query for this method.
     * @param genre The genre to search for.
     * @return A list of books in the specified genre.
     */
    List<book> findByGenre(String genre);
/**
     * Finds books by a specific title.
     * Spring Data JPA automatically generates the query for this method.
     * @param title The genre to search for.
     * @return A list of books in the specified title.
     */

    List<book> findByTitleContainingIgnoreCase(String title);
}

