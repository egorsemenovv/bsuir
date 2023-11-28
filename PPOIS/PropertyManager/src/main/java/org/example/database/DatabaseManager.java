package org.example.database;

import org.example.utils.PropertiesUtil;

import java.sql.*;

public abstract class DatabaseManager {
    public static final String USERNAME_KEY = "db.username";
    public static final String PASSWORD_KEY = "db.password";
    public static final String URL_KEY = "db.url";

    /**
     * opens new connection to database
     *
     * @return connection to database
     */
    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}