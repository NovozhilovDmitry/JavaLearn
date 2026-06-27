package repository;

import bd.SqliteConnect;
import json.tkstatus.fieldsdiscription.TestCaseStatus;

public class TestCaseStatusRepository {

    public void save(SqliteConnect connection, TestCaseStatus info) {
        connection.insertTkStatuses(info);
    }
}

