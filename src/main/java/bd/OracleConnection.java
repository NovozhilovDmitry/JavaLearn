package bd;

import orchestrator.Orchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
    private static Connection conn = null;
    private static final Logger log = LoggerFactory.getLogger(OracleConnection.class);

    public void connect(String dbPath, String bdUser, String bdPassword) {
        try {
            String url = "jdbc:oracle:thin:@" + dbPath;
            conn = DriverManager.getConnection(url, bdUser, bdPassword);
            log.info("Соединение с БД установлено.");
        } catch (SQLException e) {
            log.info("Ошибка подключения: {}", e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return conn;
    }
}
