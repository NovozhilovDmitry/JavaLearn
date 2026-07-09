package json.testcasesinfo.fieldsdiscription;

public class CustomFieldValue {
    private Integer customFieldId;
    private String stringValue;
    private Integer intValue;
    private Boolean booleanValue;
    private String dateValue;

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public Integer getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Integer id) {
        this.customFieldId = id;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String value) {
        this.stringValue = value;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer value) {
        this.intValue = value;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean value) {
        this.booleanValue = value;
    }

}
