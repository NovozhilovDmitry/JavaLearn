package bd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class SelectStatements {
    private final Connection conn;
    public SelectStatements(OracleConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(SelectStatements.class);

    public Timestamp getSyncInfo() {
        Timestamp lastSync = null;
        String sql =
                """
                SELECT s.last_sync
                FROM sync_info s
                WHERE s.entity_name = 'TKINFO'
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                    lastSync = rs.getTimestamp("last_sync");
                }
        } catch (SQLException e) {
            log.error("Ошибка выполнения запроса: {}", e.getMessage(), e);
        }
        return lastSync;
    }
}
