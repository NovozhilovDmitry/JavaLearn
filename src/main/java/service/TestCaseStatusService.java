package service;

import bd.SqliteConnect;
import json.tkstatus.fieldsdiscription.TestCaseStatus;
import repository.TestCaseStatusRepository;

import java.util.List;


public class TestCaseStatusService {
    private final TestCaseStatusRepository fieldRepository = new TestCaseStatusRepository();

    public void importFields(SqliteConnect connection, List<TestCaseStatus> fields) throws Exception {
        for (TestCaseStatus field : fields) {
            fieldRepository.save(connection, field);
        }
    }
}