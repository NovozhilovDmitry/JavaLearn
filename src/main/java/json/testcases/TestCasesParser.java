package json.testcases;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.testcases.fieldsdiscription.TestCaseMainInformation;
import java.io.*;

public class TestCasesParser {
    private final ObjectMapper mapper;
    public TestCasesParser() {
        mapper = new ObjectMapper();
    }

    public TestCaseMainInformation parseResults(String path) throws IOException {
        return mapper.readValue(
                new File(path),
                TestCaseMainInformation.class
        );
    }
}