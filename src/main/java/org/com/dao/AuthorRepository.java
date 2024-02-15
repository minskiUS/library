package org.com.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class AuthorRepository implements BaseRepository<Author> {
    private Connection connection;

    @Override
    public List<Author> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from authors");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Author> authorList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
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
        } catch (SQLException e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Author update(Author entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Author> findById(int id) {
        return Optional.empty();
    }
}
