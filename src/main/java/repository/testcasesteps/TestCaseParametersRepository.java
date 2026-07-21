package repository.testcasesteps;

import json.testcases.fieldsdiscription.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestCaseParametersRepository {
    private static final Logger log = LoggerFactory.getLogger(TestCaseParametersRepository.class);

    public void insertIntoTkParameters(Connection conn, Integer tk_id, List<TestData> dataValues) throws SQLException {
        String delete = "delete from TK_PARAMETERS where tk_id = ?";

        String SQL =
                """
                INSERT INTO TK_PARAMETERS (tk_id, rownum_id, param_index, param_name, param_value)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(SQL);
             PreparedStatement delPs = conn.prepareStatement(delete)) {
            delPs.setInt(1, tk_id);
            delPs.executeUpdate();

            for (int idx = 0; idx < dataValues.size(); idx++) {
                TestData testData = dataValues.get(idx);
                for (Map.Entry<String, TestData.Param> entry : testData.getParameters().entrySet()) {
                    String name = entry.getKey();
                    TestData.Param param = entry.getValue();
                    ps.setInt(1, tk_id);
                    ps.setInt(2, idx + 1);
                    ps.setInt(3, param.getIndex() + 1);
                    ps.setString(4, name);
                    ps.setString(5, param.getValue());
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            log.info("Внесены данные в таблицу TK_PARAMETERS");
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
            conn.rollback();
        }
    }
}