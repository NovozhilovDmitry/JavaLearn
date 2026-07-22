package repository;

import json.testcases.fieldsdiscription.StepsValues;
import json.testcases.fieldsdiscription.TestCaseMainInformation;
import repository.testcasesteps.TestCaseParametersRepository;
import repository.testcasesteps.TestCasePreconditionRepository;
import repository.testcasesteps.TestCaseStepsRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestCaseServiceRepository {
    private final TestCasePreconditionRepository precondition = new TestCasePreconditionRepository();
    private final TestCaseStepsRepository tkSteps = new TestCaseStepsRepository();
    private final TestCaseParametersRepository tkParams = new TestCaseParametersRepository();

    public void insertSteps(Connection connection, TestCaseMainInformation mainInformation) throws SQLException {
        try {
            List<StepsValues> stepByStepScript = mainInformation.getTestScript();
            Integer tk_id = mainInformation.getId();
            precondition.insertIntoTkPreconditions(connection, tk_id, mainInformation);
            tkSteps.insertIntoTkSteps(connection, tk_id, stepByStepScript);
            tkParams.insertIntoTkParameters(connection, tk_id, mainInformation.getTestData());
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}