import api.JiraApi;
import orchestrator.Orchestrator;

public class Main {
    public static void main(String[] args) {
        String dataFilePath = "src/main/resources/data/";
        Orchestrator orc = new Orchestrator();
        JiraApi jira = new JiraApi();
        String userName = orc.getJiraUser();
        String password = orc.getJiraPassword();
        String projectId = orc.getJiraProjectId();
        String maxResult = orc.getJiraMaxResults();
        String authEndpoint = orc.getJiraApiAuthorizationUrl();
        String customFieldsEndpoint = orc.getJiraApiCustomFieldsUrl();
        String folderTreeEndpoint = orc.getJiraApiFolderTreeUrl();
        String testCasesEndpoint = orc.getJiraApiTestCasesUrl();
        String maxWorkers = orc.getMaxWorkers();

        try {
            System.out.println("Шаг 1: Авторизация и открытие сессии в Jira...");
            boolean loginSuccess = jira.createJiraSession(userName, password, authEndpoint);

            if (loginSuccess) {
                System.out.println("Успешно! Сессия создана. Пароль больше не потребуется.\n");
                System.out.println("Получение данных о каталогах (используется кука сессии)...");
                String issueData = jira.fetchJiraDataNA(customFieldsEndpoint);
                System.out.println("Данные: \n" + issueData);

            } else {
                System.err.println("Не удалось авторизоваться. Проверьте логин и пароль.");
            }

        } catch (Exception e) {
            System.err.println("Произошла ошибка сетевого взаимодействия: " + e.getMessage());
        }

    }
}
