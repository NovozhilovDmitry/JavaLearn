package orchestrator;

import configs.ConfigReader;

import java.util.Properties;

public class Orchestrator {
    private static String jiraUser, jiraPassword, jiraApiAuthorizationUrl, bdUser, bdPassword, jiraApiFolderTreeUrl,
            jiraApiConfigUrl, jiraApiTestcasesUrl;

    static ConfigReader prop = new ConfigReader();
    static Properties propertiesData = prop.getPropertiesData();

    public Orchestrator() {
        bdUser = propertiesData.getProperty("bd.user");
        bdPassword = propertiesData.getProperty("bd.password");
        jiraUser = propertiesData.getProperty("jira.user");
        jiraPassword = propertiesData.getProperty("jira.password");
        jiraApiAuthorizationUrl = propertiesData.getProperty("jiraApi.AuthorizathionUrl");
        jiraApiFolderTreeUrl = propertiesData.getProperty("jiraApi.FolderTreeUrl");
        jiraApiConfigUrl = propertiesData.getProperty("jiraApi.ConfigUrl");
        jiraApiTestcasesUrl = propertiesData.getProperty("jiraApi.TestcasesUrl");

    }

    public String getJiraUser() {
        return jiraUser;
    }

    public String getJiraPassword() {
        return jiraPassword;
    }

    public String getJiraApiAuthorizationUrl() {
        return jiraApiAuthorizationUrl;
    }

    public String getBdUser() {
        return bdUser;
    }

    public String getBdPassword() {
        return bdPassword;
    }

    public String getJiraApiFolderTreeUrl() {
        return jiraApiFolderTreeUrl;
    }

    public String getJiraApiConfigUrl() {
        return jiraApiConfigUrl;
    }

    public String getJiraApiTestcasesUrl() {
        return jiraApiTestcasesUrl;
    }

}
