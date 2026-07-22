package repository;

import bd.OracleConnection;
import json.testcases.fieldsdiscription.StepsValues;
import json.testcases.fieldsdiscription.TestCaseMainInformation;
import repository.testcasesteps.TestCaseParametersRepository;
import repository.testcasesteps.TestCasePreconditionRepository;
import repository.testcasesteps.TestCaseStepsRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestCaseServiceRepository {

    public void insertSteps(OracleConnection connection, TestCaseMainInformation mainInformation) throws SQLException {

        try (Connection conn = connection.getConnection()) {
            TestCasePreconditionRepository precondition = new TestCasePreconditionRepository();
            TestCaseStepsRepository tkSteps = new TestCaseStepsRepository();
            TestCaseParametersRepository tkParams = new TestCaseParametersRepository();

            List<StepsValues> stepByStepScript = mainInformation.getTestScript();
            Integer tk_id = mainInformation.getId();

            precondition.insertIntoTkPreconditions(conn, tk_id, mainInformation);
            tkSteps.insertIntoTkSteps(conn, tk_id, stepByStepScript);
            tkParams.insertIntoTkParameters(conn, tk_id, mainInformation.getTestData());

            conn.commit();
        }
    }
}