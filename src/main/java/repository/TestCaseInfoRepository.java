package repository;

import bd.SqliteConnect;
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
    public TestCaseInfoRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(TestCaseInfoRepository.class);
//    HashMap<Integer, String> components
    public void insertIntoTkInfo(List<TestCase> testCasesInfo) {
        // TODO: выполнить SELECT и полученные данные передать сюда
//        String sqlTkInfo =
//                """
//                MERGE into TK_INFO c
//                USING ( select ? id,
//                        ? key,
//                        ? name,
//                        ? statusid,
//                        ? folderid,
//                        ? owner,
//                        ? updatedby,
//                        ? updatedon from dual) t
//                ON (c.id=t.id)
//                WHEN MATCHED THEN UPDATE SET
//                    c.key=t.key,
//                    c.name=t.name,
//                    c.statusid=t.statusid,
//                    c.folderid=t.folderid,
//                    c.owner=t.owner,
//                    c.updatedby=t.updatedby,
//                    c.updatedon=t.updatedon
//                WHEN NOT MATCHED THEN INSERT
//                    (id, key, name, statusid, folderid, owner, updatedby, updatedon)
//                    VALUES (t.id, t.key, t.name, t.statusid, t.folderid, t.owner, t.updatedby, t.updatedon)
//                """;

        String sqlTkInfo = """
                INSERT INTO TK_INFO (
                    id,
                    key,
                    name,
                    statusid,
                    folderid,
                    owner,
                    updatedby,
                    updatedon
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";

//        String sqlTkCommonFields =
//                """
//                MERGE into tkcommonfields c
//                USING ( select ? tk_id,
//                               ? approvedby,
//                               ? releases,
//                               ? testingtypes,
//                               ? servers,
//                               ? userroles,
//                               ? functionalities,
//                               ? apm from dual) t
//                ON (c.tk_id=t.tk_id)
//                WHEN MATCHED THEN UPDATE SET
//                    c.approvedby=t.approvedby,
//                    c.releases=t.releases,
//                    c.testingtypes=t.testingtypes,
//                    c.servers=t.servers,
//                    c.userroles=t.userroles,
//                    c.functionalities=t.functionalities,
//                    c.apm=t.apm
//                WHEN NOT MATCHED THEN INSERT
//                    (tk_id, approvedby, releases, testingtypes, servers, userroles, functionalities, apm)
//                    VALUES (t.tk_id, t.approvedby, t.releases, t.testingtypes, t.servers, t.userroles, t.functionalities, t.apm)
//                """;

//        try (PreparedStatement ps = conn.prepareStatement(sqlTkInfo);
//             PreparedStatement comPs = conn.prepareStatement(sqlTkCommonFields))

        try (PreparedStatement ps = conn.prepareStatement(sqlTkInfo)) {
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
                ps.addBatch();
//                List<CustomFieldValue> customField = info.getCustomFieldValues();
//                for (CustomFieldValue value: customField) {
//                    switch (value.getCustomFieldId()) {
//
//                    }
//                    System.out.println();
//                }
            }
            ps.executeBatch();
            conn.commit();
            log.info("Внесены данные в таблицу TK_INFO");
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage());
        }
    }
}
