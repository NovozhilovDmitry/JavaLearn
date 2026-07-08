package repository;

import bd.SqliteConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SyncInfoRepository {
    private final Connection conn;
    public SyncInfoRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(SyncInfoRepository.class);

    public void insertIntoTableSyncInfo(String sync_name) {
        // TODO: поправить скрипт
        String sql =
                """
                MERGE into SYNC_INFO c
                USING ( select ? sync_name, ? name from dual) t
                ON (c.id=t.id)
                WHEN MATCHED THEN
                    UPDATE SET c.name=t.name
                WHEN NOT MATCHED THEN
                    INSERT (id, name) values (t.id, t.name)  (sysdate)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sync_name);
            ps.execute();
            log.info("Внесены данные в таблицу SYNC_INFO по сущности {}", sync_name);
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage());
        }
    }

}
