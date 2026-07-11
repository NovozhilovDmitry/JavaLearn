package json.testcasesinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import json.testcasesinfo.fieldsdiscription.TestCase;
import json.testcasesinfo.fieldsdiscription.TestCaseResponse;
import java.io.File;
import java.util.List;

public class TestCaseParser {
    private final ObjectMapper mapper;
    public TestCaseParser() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public List<TestCase> parseResults(String path) throws Exception {
        return mapper.readValue(
                new File(path),
                TestCaseResponse.class).getResults();
    }
}
