package bd;
import java.sql.*;

public class SqliteConnect {
    private Connection conn = null;

    public void connect(String dbPath) {
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            System.out.println("Соединение с БД установлено.");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    // Универсальный метод для выполнения CREATE, INSERT, UPDATE, DELETE
    public void executeUpsert(String sql) {
        if (conn == null) {
            System.out.println("Нет соединения с базой данных!");
            return;
        }
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Запрос успешно выполнен.");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    // Метод для закрытия соединения
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Соединение с БД успешно закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}
