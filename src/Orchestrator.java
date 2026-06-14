import java.util.Properties;

public class Orchestrator {
    public static String jiraUser, jiraPassword, jiraApiAuthorizathionUrl, bdUser, bdPassword;

    static ConfigReader prop = new ConfigReader();
    static Properties propertiesData = prop.getPropertiesData();

    public Orchestrator() {
        bdUser = propertiesData.getProperty("bd.user");
        bdPassword = propertiesData.getProperty("bd.password");
        jiraUser = propertiesData.getProperty("jira.user");
        jiraPassword = propertiesData.getProperty("jira.password");
        jiraApiAuthorizathionUrl = propertiesData.getProperty("jiraApi.AuthorizathionUrl");

    }

    public String getJiraUser() {
        return jiraUser;
    }

    public String getJiraPassword() {
        return jiraPassword;
    }

    public String getJiraApiAuthorizathionUrl() {
        return jiraApiAuthorizathionUrl;
    }

    public String getBdUser() {
        return bdUser;
    }

    public String getBdPassword() {
        return bdPassword;
    }

}
