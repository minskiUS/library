package org.com.service;

import lombok.extern.slf4j.Slf4j;
import org.com.dao.AuthorRepository;
import org.com.dao.BaseRepository;
import org.com.model.Author;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
@Slf4j
public class AuthorService {
    private BaseRepository<Author> authorRepository;

    public AuthorService() {
        this.authorRepository = new AuthorRepository();
    }
    public void test() {
        Author author = new Author("Alexander", "Pushkin");
        Author author1 = authorRepository.create(author);
        log.info(authorRepository.findAll().toString());
    }
}
