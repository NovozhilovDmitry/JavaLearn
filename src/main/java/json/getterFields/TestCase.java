package json.getterFields;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestCase {
    private Integer id;
    private String key;
    private String name;
    private Integer statusId;
    private List<CustomFieldValue> customFieldValues;
    private HashMap<String, String> lastTestResultStatus; //?
    private String owner;
    private Integer estimatedTime;
    private String componentId;
    private String updatedBy;
    private Date updatedOn; //?
    private Byte majorVersion;
    private Date createdOn; //?
    private Integer folderId;
    private Integer priorityId;
    private List<String> labels;
    private String createdBy;
    private Integer projectId;
    private Integer averageTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public List<CustomFieldValue> getCustomFieldValues() {
        return customFieldValues;
    }

    public void setCustomFieldValues(List<CustomFieldValue> values) {
        this.customFieldValues = values;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String a1) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Byte getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Byte majorVersion) {
        this.majorVersion = majorVersion;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public HashMap<String, String> getLastTestResultStatus() {
        return lastTestResultStatus;
    }

    public void setLastTestResultStatus(HashMap<String, String> lastTestResultStatus) {
        this.lastTestResultStatus = lastTestResultStatus;
    }

    public Integer getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(Integer averageTime) {
        this.averageTime = averageTime;
    }

}
