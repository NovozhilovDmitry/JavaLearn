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

    public void insertIntoTableSyncInfo(String entity_name) {
        String sql =
                """
                MERGE into SYNC_INFO c
                USING ( select ? entity_name from dual) t
                ON (c.entity_name=t.entity_name)
                WHEN MATCHED THEN
                    UPDATE SET c.last_sync=sysdate
                WHEN NOT MATCHED THEN
                    INSERT (entity_name, last_sync) values (t.entity_name, sysdate)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity_name);
            ps.execute();
            log.info("Внесены данные в таблицу SYNC_INFO по сущности {}", entity_name);
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
        }
    }

}
