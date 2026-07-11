package bd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class SqliteConnect {
    private Connection conn = null;
    private static final Logger log = LoggerFactory.getLogger(SqliteConnect.class);
    
    public void connect(String dbPath) {
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            log.info("Соединение с БД установлено.");
        } catch (SQLException e) {
            log.info("Ошибка подключения: {}", e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    // Универсальный метод для выполнения CREATE, INSERT, UPDATE, DELETE
    public void executeUpsert(String sql) {
        if (conn == null) {
            log.info("Нет соединения с базой данных!");
            return;
        }
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            log.info("Запрос успешно выполнен.");
        } catch (SQLException e) {
            log.info("Ошибка выполнения запроса: {}", e.getMessage());
        }
    }

    // Метод для закрытия соединения
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                log.info("Соединение с БД успешно закрыто.");
            }
        } catch (SQLException e) {
            log.info("Ошибка при закрытии соединения: {}", e.getMessage());
        }
    }
}
