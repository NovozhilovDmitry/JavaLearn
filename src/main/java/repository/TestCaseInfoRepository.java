package repository;

import bd.OracleConnection;
import json.testcasesinfo.fieldsdiscription.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCaseInfoRepository {
    private static final Logger log = LoggerFactory.getLogger(TestCaseInfoRepository.class);

    public void insertIntoTkInfo(OracleConnection connection,
                                 List<TestCase> testCasesInfo,
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
        try(Connection conn = connection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sqlTkInfo))
            {
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
                new SyncInfoRepository().insertIntoTableSyncInfo(connection, "TKINFO");
                new SyncInfoRepository().insertIntoTableSyncInfo(connection, "TKCOMMONFIELDS");
                conn.commit();
            } catch (SQLException e) {
                log.error("Ошибка добавления данных: {}", e.getMessage(), e);
                conn.rollback();
            }
        }
    }

    public List<Integer> getIdFromTkInfo(OracleConnection connection) throws SQLException {
        List<Integer> tempId = new ArrayList<>();
        String sql =
                """
                SELECT id
                FROM tk_info
                WHERE trunc(UPDATEDON, 'DDD') > (SELECT trunc(LAST_SYNC, 'DDD')
                                                 FROM sync_info
                                                 WHERE ENTITY_NAME = 'TKINFO')
                """;

        try(Connection conn = connection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    tempId.add(id);
                }
            } catch (SQLException e) {
                log.error("Ошибка выполнения запроса: {}", e.getMessage(), e);
            }
        }

        return tempId;
    }

    public List<Integer> getIdFromTkInfoWithoutCondition(OracleConnection connection) throws SQLException {
        List<Integer> tempId = new ArrayList<>();
        String sql =
                """
                SELECT id
                FROM tk_info
                """;

        try(Connection conn = connection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    tempId.add(id);
                }
            } catch (SQLException e) {
                log.error("Ошибка выполнения запроса: {}", e.getMessage(), e);
            }
        }

        return tempId;
    }
}
