package org.com.dao;

import lombok.extern.slf4j.Slf4j;
import org.com.model.Author;
import org.com.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Slf4j

public class BookRepository implements BaseRepository<Book> {
    private Connection connection;
    @Override
    public List<Book> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from authors");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> bookList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String lastName = resultSet.getString("lastName");
                Book book = new Book(id, firstName, lastName);
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public Book create(Book entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into author (firstName, lastName) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            int id = (int) generatedKeys.getLong(1);
            entity.setId(id);
            return entity;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Book update(Book entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update authors set (firstName, lastName) values (?, ?) where id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            int id = (int) generatedKeys.getLong(1);
            entity.setId(id);
            return entity;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete * from books where id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from authors where id = ?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            int bookId = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            Book book = new Book(bookId, firstName, lastName);
            return Optional.of(book);


        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
}
}
