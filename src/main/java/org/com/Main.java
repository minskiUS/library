package org.com;

import lombok.extern.slf4j.Slf4j;
import org.com.config.DatabaseConfig;

import java.sql.Connection;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Connection singletonConnection = DatabaseConfig.getSingletonConnection();

    }
}