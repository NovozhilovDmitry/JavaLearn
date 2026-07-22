package bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleConnection {
    private static final Logger log = LoggerFactory.getLogger(OracleConnection.class);
    private final HikariDataSource dataSource;

    public OracleConnection(String dbPath, String user, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@//" + dbPath);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(10); // Максимальное количество соединений
        config.setMinimumIdle(2); // Минимальное количество постоянно открытых соединений
        config.setConnectionTimeout(30000); // Таймаут ожидания свободного соединения
        config.setMaxLifetime(1800000); // Время жизни соединения
        config.setValidationTimeout(5000); // Проверка соединения
        config.setAutoCommit(false);
        config.setPoolName("JiraOraclePool"); // Осмысленное имя пула
        dataSource = new HikariDataSource(config);
        log.info("Пул соединений Oracle создан");
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close();
            log.info("Пул соединений Oracle закрыт");
        }
    }
}