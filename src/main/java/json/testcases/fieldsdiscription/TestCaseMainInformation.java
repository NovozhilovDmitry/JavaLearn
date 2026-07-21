package json.testcases.fieldsdiscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jsoup.Jsoup;
import java.util.List;

@JsonIgnoreProperties(value = { "customFieldValues", "estimatedTime" })
public class TestCaseMainInformation {
    private String owner;
    private List<TestData> testData;
    private Integer componentId;
    private String precondition;
    private PriorityValues priority;
    private Integer majorVersion;
    private List<String> labels;
    private String objective;
    private boolean archived;
    private FolderValues folder;
    private boolean latestVersion;
    private TestScriptValues testScript;
    private String name;
    private Integer id;
    private List<String> parameters;
    private Integer projectId;
    private String key;
    private StatusValues status;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<TestData> getTestData() {
        return testData;
    }

    public void setTestData(List<TestData> testData) {
        this.testData = testData;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public String getPreconditionText() {
        return Jsoup.parse(precondition).text();
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition;
    }

    public PriorityValues getPriority() {
        return priority;
    }

    public void setPriority(PriorityValues priority) {
        this.priority = priority;
    }

    public Integer getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getObjectiveText() {
        return Jsoup.parse(objective).text();
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public FolderValues getFolder() {
        return folder;
    }

    public void setFolder(FolderValues folder) {
        this.folder = folder;
    }

    public boolean isLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(boolean latestVersion) {
        this.latestVersion = latestVersion;
    }

    public List <StepsValues> getTestScript() {
        return testScript.getStepByStepScript().getSteps();
    }

    public void setTestScript(TestScriptValues testScript) {
        this.testScript = testScript;
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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public StatusValues getStatus() {
        return status;
    }

    public void setStatus(StatusValues status) {
        this.status = status;
    }
}