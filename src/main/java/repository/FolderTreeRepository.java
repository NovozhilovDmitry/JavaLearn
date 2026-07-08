package repository;

import bd.SqliteConnect;
import json.folders.FolderTreeExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.List;

public class FolderTreeRepository {
    private final Connection conn;
    public FolderTreeRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(FolderTreeRepository.class);

    public void insertIntoTableTkFolders(List<FolderTreeExtractor.Result> fields) {
        String sql =
                """
                MERGE into tkfolders c
                USING ( select ? id, ? name from dual) t
                ON (c.id=t.id)
                WHEN MATCHED THEN
                    UPDATE SET c.name=t.name
                WHEN NOT MATCHED THEN
                    INSERT (id, name) values (t.id, t.name)
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
            log.error("Ошибка добавления данных: " + e.getMessage());
        }
    }

}
