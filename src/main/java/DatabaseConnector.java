
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/my_database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public void executeQuery() {
        String sql = "SELECT * FROM users";

        // Блок try-with-resources автоматически закроет соединения
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Имя пользователя: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            System.err.println("Ошибка подключения или выполнения запроса: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.executeQuery();
    }
}