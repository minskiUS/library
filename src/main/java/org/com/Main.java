package org.com;

import lombok.extern.slf4j.Slf4j;
import org.com.dao.AuthorRepository;
import org.com.dao.BaseRepository;
import org.com.model.Author;
import org.com.service.AuthorService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "admin");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(8);
            AuthorService pushkin = new AuthorService(connection);
            pushkin.test();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }
}