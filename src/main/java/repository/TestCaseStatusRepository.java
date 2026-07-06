package repository;

import bd.SqliteConnect;
import json.tkstatus.fieldsdiscription.TestCaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.List;

public class TestCaseStatusRepository {
    private final Connection conn;
    public TestCaseStatusRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(TestCaseStatusRepository.class);

    public void insertIntoTableStatuses(List<TestCaseStatus> fields) {
        String sql = """
                INSERT INTO statuses
                (
                id, name
                )
                VALUES
                (?,?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (TestCaseStatus info: fields) {
                ps.setInt(1, info.getId());
                ps.setString(2, info.getName());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            } catch (SQLException e) {
                log.error("Ошибка добавления данных: {}", e.getMessage());
            }
        }
}

