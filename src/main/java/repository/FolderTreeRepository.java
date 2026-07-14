package repository;

import bd.OracleConnect;
import json.folders.FolderTreeExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.List;

public class FolderTreeRepository {
    private final Connection conn;
    public FolderTreeRepository(OracleConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(FolderTreeRepository.class);

    public void insertIntoTableTkFolders(List<FolderTreeExtractor.Result> fields) throws SQLException {
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
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            log.info("Внесены данные в таблицу TKFOLDERS");
            new SyncInfoRepository(conn).insertIntoTableSyncInfo("TKFOLDERS");
            conn.commit();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
            conn.rollback();
        }
    }
}