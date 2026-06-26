package json.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.getterFields.TestCaseStatus;

import java.io.File;
import java.util.List;

public class TestCaseStatusParser {
    // через данный класс будем парсить Json файл с структурой [{}] (https://jira.cbr.ru/rest/tests/1.0/project/28106/testcasesstatus)
    private final ObjectMapper mapper;

    public TestCaseStatusParser() {
        mapper = new ObjectMapper();
    }

    public List<TestCaseStatus> parse(String path) throws Exception {
        return mapper.readValue(
                new File(path),
                new TypeReference<List<TestCaseStatus>>() {});
    }
}
