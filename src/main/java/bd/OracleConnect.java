package bd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnect {
    private Connection conn = null;
    private static final Logger log = LoggerFactory.getLogger(OracleConnect.class);

    public void connect(String dbPath, String user, String password) {
        try {
            String url = "jdbc:oracle:thin:@//" + dbPath;
            conn = DriverManager.getConnection(url, user, password);
            log.info("Соединение с Oracle установлено.");
        } catch (SQLException e) {
            log.error("Ошибка подключения: {}", e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                log.info("Соединение с Oracle закрыто.");
            }
        } catch (SQLException e) {
            log.error("Ошибка закрытия соединения: {}", e.getMessage(), e);
        }
    }
}