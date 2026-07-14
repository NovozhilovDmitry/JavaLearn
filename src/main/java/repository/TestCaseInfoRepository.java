package repository;

import bd.OracleConnect;
import json.testcasesinfo.fieldsdiscription.CustomFieldValue;
import json.testcasesinfo.fieldsdiscription.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class TestCaseInfoRepository {
    private final Connection conn;
    public TestCaseInfoRepository(OracleConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(TestCaseInfoRepository.class);

    public void insertIntoTkInfo(List<TestCase> testCasesInfo,
                                 HashMap<Integer, String> optionComponents,
                                 HashMap<Integer, String> mainComponents) throws SQLException {
        String sqlTkInfo =
                """
                MERGE into TK_INFO c
                USING ( select ? id,
                        ? key,
                        ? name,
                        ? statusid,
                        ? folderid,
                        ? owner,
                        ? updatedby,
                        ? updatedon from dual) t
                ON (c.id=t.id)
                WHEN MATCHED THEN UPDATE SET
                    c.key=t.key,
                    c.name=t.name,
                    c.statusid=t.statusid,
                    c.folderid=t.folderid,
                    c.owner=t.owner,
                    c.updatedby=t.updatedby,
                    c.updatedon=t.updatedon
                WHEN NOT MATCHED THEN INSERT
                    (id, key, name, statusid, folderid, owner, updatedby, updatedon)
                    VALUES (t.id, t.key, t.name, t.statusid, t.folderid, t.owner, t.updatedby, t.updatedon)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sqlTkInfo))
        {
            conn.setAutoCommit(false);
            for (TestCase info: testCasesInfo) {
                ps.setInt(1, info.getId());
                ps.setString(2, info.getKey());
                ps.setString(3, info.getName());
                ps.setInt(4, info.getStatusId());
                ps.setInt(5, info.getFolderId());
                ps.setString(6, info.getOwner());
                ps.setString(7, info.getUpdatedBy());
                ps.setTimestamp(8, Timestamp.from(info.getUpdatedOn()));
                TestCaseCommonFieldsRepository tk = new TestCaseCommonFieldsRepository();
                tk.insertIntoTkCommonFields(conn, info.getId(), info.getCustomFieldValues(), optionComponents, mainComponents);
                ps.addBatch();
            }
            ps.executeBatch();
            log.info("Внесены данные в таблицы TK_INFO, TKCOMMONFIELDS");
            new SyncInfoRepository(conn).insertIntoTableSyncInfo("TKINFO");
            new SyncInfoRepository(conn).insertIntoTableSyncInfo("TKCOMMONFIELDS");
            conn.commit();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
            conn.rollback();
        }
    }
}
