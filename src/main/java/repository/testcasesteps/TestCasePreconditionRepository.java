package repository.testcasesteps;

import json.testcases.fieldsdiscription.TestCaseMainInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestCasePreconditionRepository {
    private static final Logger log = LoggerFactory.getLogger(TestCasePreconditionRepository.class);

    public void insertIntoTkPreconditions(Connection conn, Integer tkId, TestCaseMainInformation mainInformation) throws SQLException {
        String delete = "delete from TK_PRECONDITIONS where tk_id = ?";

        String sql =
                """
                INSERT INTO TK_PRECONDITIONS (tk_id, majorversion, objective, precondition)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement delPs = conn.prepareStatement(delete)) {
            delPs.setInt(1, tkId);
            delPs.executeUpdate();

            ps.setInt(1, tkId);
            ps.setInt(2, mainInformation.getMajorVersion());
            ps.setString(3, mainInformation.getObjectiveText());
            ps.setString(4, mainInformation.getPreconditionText());
            ps.executeUpdate();
            log.info("Внесены данные в таблицу TK_PRECONDITIONS по тест-кейсу: {}", tkId);
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
            conn.rollback();
        }
    }
}
