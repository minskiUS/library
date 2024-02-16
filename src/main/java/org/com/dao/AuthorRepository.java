package org.com.dao;

import lombok.extern.slf4j.Slf4j;
import org.com.model.Author;
import org.com.config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AuthorRepository implements BaseRepository<Author> {
    private Connection connection;
    public AuthorRepository(){
        this.connection = DatabaseConfig.getSingletonConnection();
    }

    @Override
    public List<Author> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from authors");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Author> authorList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Author author = new Author(id, firstName, lastName);
                authorList.add(author);
            }
            return authorList;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public Author create(Author entity) {
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
    public Author update(Author entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update authors set (firstName, lastName) values (?, ?) where id = ?");
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete * from authors where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Optional<Author> findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from authors where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int authorId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Author author = new Author(authorId, firstName, lastName);
                return Optional.of(author);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
}
