package json.customfield;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.customfield.fieldsdiscription.CustomField;
import java.io.File;
import java.util.List;

public class CustomFieldParser {
    private final ObjectMapper mapper;
    public CustomFieldParser() {
        mapper = new ObjectMapper();
    }

    public List<CustomField> parse(String path) throws Exception {
        return mapper.readValue(
        new File(path),
        new TypeReference<>() {});
    }
}
