package repository.testcasesteps;

import json.testcases.fieldsdiscription.StepParameterValues;
import json.testcases.fieldsdiscription.StepParametersValues;
import json.testcases.fieldsdiscription.StepsValues;
import json.testcases.fieldsdiscription.TestCaseValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCaseStepsRepository {
    private static final Logger log = LoggerFactory.getLogger(TestCaseStepsRepository.class);

    public void insertIntoTkSteps(Connection conn, Integer tk_id, List<StepsValues> stepsInfo) throws SQLException {
        String deleteSteps = "delete from TK_STEPS where tk_id = ?";
        String deleteCommonSteps = "delete from TK_WITH_COMMON_STEPS where tk_id = ?";
        String sql =
                """
                INSERT INTO TK_STEPS (tk_id, step, expectedresult, step_description, step_testdata)
                VALUES (?, ?, ?, ?, ?)
                """;
        String tkWithCommonStepsSql =
                """
                INSERT INTO TK_WITH_COMMON_STEPS (tk_id, step, tk_key, idcommonstep, namecommonstep, parameterscommonstep)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             PreparedStatement delSteps = conn.prepareStatement(deleteSteps);
             PreparedStatement delCommonSteps = conn.prepareStatement(deleteCommonSteps);
             PreparedStatement commonStepsPS = conn.prepareStatement(tkWithCommonStepsSql)) {
            delSteps.setInt(1, tk_id);
            delCommonSteps.setInt(1, tk_id);
            delSteps.executeUpdate();
            delCommonSteps.executeUpdate();

            for (StepsValues step: stepsInfo) {
                String expectedResult = step.getExpectedResultText();
                int index = step.getIndex() + 1;
                String description = step.getDescriptionText();
                String testData = step.getTestDataText();
                List<StepParameterValues> stepParameters = step.getStepParameters();
                TestCaseValues testCase = step.getTestCase();

                ps.setInt(1, tk_id);
                ps.setInt(2, index);

                if (testCase == null) {
                    ps.setString(3, expectedResult);
                    ps.setString(4, description);
                    ps.setString(5, testData);
                } else {
                    ps.setString(3, "Информация об общем шаге добавлена в таблицу TK_WITH_COMMON_STEPS");
                    ps.setNull(4, Types.VARCHAR);
                    ps.setNull(5, Types.VARCHAR);
                    addCommonStep(commonStepsPS, tk_id, index, stepParameters, testCase);
                }
                ps.addBatch();
            }
            ps.executeBatch();
            commonStepsPS.executeBatch();
            log.info("Внесены данные в таблицы TK_STEPS, TK_WITH_COMMON_STEPS");
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
            conn.rollback();
        }
    }

    private void addCommonStep(PreparedStatement ps,
                               Integer tk_id,
                               Integer index,
                               List<StepParameterValues> stepParameters,
                               TestCaseValues testCase) throws SQLException {
        Map<Integer, String> parameters = new HashMap<>();
        List<String> paramsList = new ArrayList<>();
        if (stepParameters != null) {
            for (StepParameterValues param: stepParameters) {
                parameters.put(
                        param.getTestCaseParameterId(),
                        param.getValueText()
                );
            }
        }

        List<StepParametersValues> parametersCommonStep = testCase.getParameters();
        if (parametersCommonStep != null && !parametersCommonStep.isEmpty()) {
            for (StepParametersValues p : parametersCommonStep) {
                String value = parameters.get(p.getId());
                if (value != null) {
                    paramsList.add("PARAMETER: " + p.getName() + ", VALUE: " + value);
                }
            }
        }

        ps.setInt(1, tk_id);
        ps.setInt(2, index);
        ps.setString(3, testCase.getKey());
        ps.setInt(4, testCase.getId());
        ps.setString(5, testCase.getName());
        if (paramsList.isEmpty()) {
            ps.setNull(6, Types.VARCHAR);
        } else {
            ps.setString(6, String.join("; ", paramsList));
        }
        ps.addBatch();
    }
}