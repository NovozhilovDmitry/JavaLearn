package repository;

import bd.OracleConnect;
import json.testcases.fieldsdiscription.StepsValues;
import json.testcases.fieldsdiscription.TestCaseMainInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.testcasesteps.TestCaseParametersRepository;
import repository.testcasesteps.TestCasePreconditionRepository;
import repository.testcasesteps.TestCaseStepsRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestCaseServiceRepository {
    private final Connection conn;
    public TestCaseServiceRepository(OracleConnect connection) {
        this.conn = connection.getConnection();
    }
    private static final Logger log = LoggerFactory.getLogger(TestCaseServiceRepository.class);

    public void insertSteps(TestCaseMainInformation mainInformation) throws SQLException {
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