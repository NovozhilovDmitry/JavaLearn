package json.testcasesinfo.fieldsdiscription;

import java.util.List;

public class TestCaseResponse {
    private List<TestCase> results;
    private Integer total;
    private Byte startAt;
    private Integer maxResults;
    private Boolean last;

    public List<TestCase> getResults() {
        return results;
    }

    public void setResults(List<TestCase> results) {
        this.results = results;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Byte getStartAt() {
        return startAt;
    }

    public void setStartAt(Byte startAt) {
        this.startAt = startAt;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

}
