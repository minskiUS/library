package org.com.model;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
    private String genre;
    private int pageCount;
    private String language;
    private int isbn;
    private Author author;

    public Book(String title, String genre, int pageCount, String language, int isbn, Author author) {
        this.title = title;
        this.genre = genre;
        this.pageCount = pageCount;
        this.language = language;
        this.isbn = isbn;
        this.author = author;
    }
}
