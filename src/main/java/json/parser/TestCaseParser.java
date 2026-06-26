package json.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.getterFields.TestCaseResponse;

import java.io.File;

public class TestCaseParser {
    // через данный класс будем парсить Json файл с структурой results: [] (https://jira.cbr.ru/rest/tests/1.0/testcase/)
    private final ObjectMapper mapper;

    public TestCaseParser() {
        mapper = new ObjectMapper();
    }

    public TestCaseResponse firstLevelParseJsonAttribute(String path) throws Exception {
        return mapper.readValue(
                new File(path),
                TestCaseResponse.class);
    }
}
