package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_system";
    private static final String USER = "root";
    private static final String PASSWORD = "2222";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            return null;
        }
    }
}

