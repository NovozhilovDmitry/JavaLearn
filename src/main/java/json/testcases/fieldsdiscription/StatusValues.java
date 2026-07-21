package json.testcases.fieldsdiscription;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StatusValues {
    private boolean isDefault;
    private String color;
    private String i18nKey;
    private boolean isArchived;
    private String name;
    private Integer index;
    private Integer id;
    private Integer projectId;

    @JsonProperty("isDefault")
    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }

    @JsonProperty("isArchived")
    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
