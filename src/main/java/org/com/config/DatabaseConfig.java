package org.com.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Getter
public class DatabaseConfig {
    private static DatabaseConfig instance = null;

    private Connection connection;
    private String url = "jdbc:mysql://127.0.0.1:3306/library";
    private String username = "root";
    private String password = "admin";

    private DatabaseConfig() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
    public static synchronized Connection getSingletonConnection() {
        if (instance == null) {
            instance = new DatabaseConfig();
            return instance.getConnection();
        }
        return instance.getConnection();
    }


}
