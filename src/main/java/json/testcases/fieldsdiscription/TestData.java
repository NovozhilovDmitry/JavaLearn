package json.testcases.fieldsdiscription;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties("id")
public class TestData {
    private Map<String, Param> parameters = new LinkedHashMap<>();

    public Map<String, Param> getParameters() {
        return parameters;
    }

    @JsonAnySetter
    public void addParameter(String name, Param param) {
        parameters.put(name, param);
    }

    public static class Param {
        private int index;
        private String value;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}