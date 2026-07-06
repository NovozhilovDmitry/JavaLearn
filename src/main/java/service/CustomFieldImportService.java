package service;

import bd.SqliteConnect;
import json.customfield.fieldsdiscription.CustomField;
import repository.CustomFieldRepository;
import java.sql.Connection;
import java.util.List;

public class CustomFieldImportService {
    private final Connection conn;
    public CustomFieldImportService(SqliteConnect connection) {
        this.conn = connection.getConnection();
    }
    private final CustomFieldRepository fieldRepository = new CustomFieldRepository();

    public void importFields(List<CustomField> fields) {
        fieldRepository.inserIntoTables(conn, fields);
    }
}