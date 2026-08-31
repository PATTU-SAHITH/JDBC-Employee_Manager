package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        if (URL == null || URL.isBlank()) {
            throw new SQLException("DB_URL is not set. Configure the database connection environment variables before starting the application.");
        }
        if (USER == null || USER.isBlank()) {
            throw new SQLException("DB_USER is not set. Configure the database connection environment variables before starting the application.");
        }
        if (PASSWORD == null) {
            throw new SQLException("DB_PASSWORD is not set. Configure the database connection environment variables before starting the application.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
