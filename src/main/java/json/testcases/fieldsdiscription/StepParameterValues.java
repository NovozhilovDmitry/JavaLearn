package json.testcases.fieldsdiscription;

import org.jsoup.Jsoup;

public class StepParameterValues {
    private Integer id;
    private String value;
    private Integer testCaseParameterId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public String getValueText() {
        return Jsoup.parse(value).text();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getTestCaseParameterId() {
        return testCaseParameterId;
    }

    public void setTestCaseParameterId(Integer testCaseParameterId) {
        this.testCaseParameterId = testCaseParameterId;
    }
}
