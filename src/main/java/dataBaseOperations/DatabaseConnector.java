package dataBaseOperations;

import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.logging.Logger;

public class DatabaseConnector {
    static final String URL = "jdbc:postgresql://localhost:5432/Mirapolis";
    static final String USER = "postgres";
    static final String PASSWORD = "qA9eLS9s";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DatabaseConnector.class);


    public void executeQuery() {
        String sql = "SELECT * FROM users";

        // Блок try-with-resources автоматически закроет соединения
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                logger.info("Имя пользователя: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            logger.info("Ошибка подключения или выполнения запроса: " + e.getMessage());
        }
    }

}