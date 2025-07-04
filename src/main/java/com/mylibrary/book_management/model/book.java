package com.mylibrary.book_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Book entity in the library management system.
 * This class is mapped to the 'books' table in the database.
 */
@Entity // Marks this class as a JPA entity
@Table(name = "books") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class book {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key to be auto-generated by the database
    private Long id;

    private String title;
    private String author;
    private String genre;
    private Integer publishedYear; // Using Integer for year, as it can be null sometimes, and covers range

    // Lombok automatically generates getters, setters, constructors thanks to @Data, @NoArgsConstructor, @AllArgsConstructor
    // If you don't use Lombok, you would manually write:
    /*
    public Book() {}

    public Book(Long id, String title, String author, String genre, Integer publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedYear = publishedYear;
    }

    // Getters and Setters for all fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... and so on for other fields
    */
}