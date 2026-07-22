package json.tkstatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.tkstatus.fieldsdiscription.TestCaseStatus;
import java.io.File;
import java.util.List;

public class TestCaseStatusParser {
    private final ObjectMapper mapper;
    public TestCaseStatusParser() {
        mapper = new ObjectMapper();
    }

    public List<TestCaseStatus> parse(String json) throws Exception {
        return mapper.readValue(json, new TypeReference<>() {});
    }
}
