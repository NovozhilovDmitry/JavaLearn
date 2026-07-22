package orchestrator;

import configs.ConfigReader;
import java.util.Properties;

public class Orchestrator {
    static Properties propertiesData = new ConfigReader().getPropertiesData();
    private final String maxResult = propertiesData.getProperty("jiraApi.maxResults");
    private final String projectId = propertiesData.getProperty("jiraApi.projectId");

    public String getBdUser() {
        return propertiesData.getProperty("bd.user");
    }

    public String getBdPassword() {
        return propertiesData.getProperty("bd.password");
    }

    public String getBdConnectLine() {
        return propertiesData.getProperty("bd.connectLine");
    }

    public String getMaxPoolSize() {
        return propertiesData.getProperty("maxPoolSize");
    }

    public String getJiraUser() {
        return propertiesData.getProperty("jiraApi.user");
    }

    public String getJiraPassword() {
        return propertiesData.getProperty("jiraApi.password");
    }

    public String getJiraApiTestCaseStatusUrl() {
        return propertiesData.getProperty("jiraApi.TestCaseStatus");
    }

    public String getJiraApiFolderTreeUrl() {
        return propertiesData.getProperty("jiraApi.FolderTreeUrl");
    }

    public String getJiraApiCustomFieldsUrl() {
        return propertiesData.getProperty("jiraApi.CustomFieldsUrl");
    }

    public String getJiraApiTestCaseInfoUrl() {
        return propertiesData.getProperty("jiraApi.TestCaseInfoUrl").replace("{maxResults}", maxResult).replace("{projectID}", projectId);
    }

    public String getJiraApiTestCaseUrl() {
        return propertiesData.getProperty("jiraApi.TestCaseUrl");
    }

}