package repository;

import bd.SqliteConnect;
import bd.sqliteselect.SelectResultsTkStatus;
import json.tkstatus.fieldsdiscription.TestCaseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestCaseStatusRepository {
    private final Connection conn;
    public TestCaseStatusRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }

    public void insertIntoTable(List<TestCaseStatus> fields) {
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
                System.out.println("Ошибка добавления: " + e.getMessage());
            }
        }

    public List<SelectResultsTkStatus> getTkStatuses() {
        List<SelectResultsTkStatus> statuses = new ArrayList<>();
        String sql = "SELECT * FROM statuses";
        if (conn == null) {
            System.out.println("Нет соединения!");
            return statuses;
        }
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                statuses.add(new SelectResultsTkStatus(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return statuses;
    }
}

