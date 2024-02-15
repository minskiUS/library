package org.com.dao;

import org.com.model.Author;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    T create(T entity);
    T update(T entity);
    void delete(int id);

}
