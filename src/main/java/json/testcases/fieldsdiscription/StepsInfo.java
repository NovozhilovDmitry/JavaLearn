package json.testcases.fieldsdiscription;

import java.util.List;

public class StepsInfo {
    private Integer id;
    private List<StepsValues> steps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<StepsValues> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsValues> steps) {
        this.steps = steps;
    }
}
