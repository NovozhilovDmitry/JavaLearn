package repository;

import bd.SqliteConnect;
import bd.sqliteselect.SelectResultsTkStatus;
import json.folders.FolderTreeExtractor;
import json.tkstatus.fieldsdiscription.TestCaseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FolderTreeRepository {
    private final Connection conn;
    public FolderTreeRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }

    public void insertIntoTable(List<FolderTreeExtractor.Result> fields) {
        String sql = """
                INSERT INTO tkfolders
                (
                id, name
                )
                VALUES
                (?,?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (FolderTreeExtractor.Result info: fields) {
                if (info.id != 0 && info.name != null && !info.name.isEmpty()) {
                    ps.setLong(1, info.id);
                    ps.setString(2, info.name);
                    System.out.println(info.id + ": " + info.name);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления: " + e.getMessage());
        }
    }

    public List<SelectResultsTkStatus> getTkFolders() {
        List<SelectResultsTkStatus> statuses = new ArrayList<>();
        String sql = "SELECT * FROM tkfolders";
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
