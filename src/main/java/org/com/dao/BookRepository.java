package org.com.dao;

import org.com.model.Book;

import java.util.List;
import java.util.Optional;

public class BookRepository implements BaseRepository<Book> {
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Book create(Book entity) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
