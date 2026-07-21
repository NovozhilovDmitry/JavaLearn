package json.testcases.fieldsdiscription;

import org.jsoup.Jsoup;
import java.util.List;

public class StepsValues {
    private String testData;
    private String expectedResult;
    private Integer index;
    private String description;
    private Integer id;
    private List<StepParameterValues> stepParameters;
    private TestCaseValues testCase;

    public String getTestDataText() {
        return testData == null
                ? null
                : Jsoup.parse(testData).text();
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getExpectedResultText() {
        return expectedResult == null
                ? null
                : Jsoup.parse(expectedResult).text();
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDescriptionText() {
        return description == null
                ? null
                : Jsoup.parse(description).text();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<StepParameterValues> getStepParameters() {
        return stepParameters;
    }

    public void setStepParameters(List<StepParameterValues> stepParameters) {
        this.stepParameters = stepParameters;
    }

    public TestCaseValues getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseValues testCase) {
        this.testCase = testCase;
    }
}
