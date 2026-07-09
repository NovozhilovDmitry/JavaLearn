package json.testcasesinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.testcasesinfo.fieldsdiscription.TestCase;
import json.testcasesinfo.fieldsdiscription.TestCaseResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestCaseParser {
    // через данный класс будем парсить Json файл с структурой results: [] (https://jira.cbr.ru/rest/tests/1.0/testcase/)
    private final ObjectMapper mapper;
    private TestCaseResponse resultsValue;

    public TestCaseParser(String path) throws IOException {
        mapper = new ObjectMapper();
        this.resultsValue = mapper.readValue(new File(path),
                TestCaseResponse.class);
    }

    public List<TestCase> getResultsValue() {
        return resultsValue.getResults();
    }

    public void setResultsValue(TestCaseResponse resultsValue) {
        this.resultsValue = resultsValue;
    }

    public ObjectMapper getMapperTkInfo() {
        return this.mapper;
    }

    public TestCaseResponse firstLevelJsonAttributes(String path) throws Exception {
        return mapper.readValue(new File(path),
                TestCaseResponse.class);
    }


}
