package json.testcases.fieldsdiscription;

import java.util.List;

public class TestCaseValues {
    private boolean archived;
    private boolean latestVersion;
    private String name;
    private Integer id;
    private Integer majorVersion;
    private List<StepParametersValues> parameters;
    private String key;

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(boolean latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    public List<StepParametersValues> getParameters() {
        return parameters;
    }

    public void setParameters(List<StepParametersValues> parameters) {
        this.parameters = parameters;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
