import api.JiraApi;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Orchestrator orc = new Orchestrator();
        JiraApi jira = new JiraApi();
        String USERNAME = orc.getJiraUser();
        String PASSWORD = orc.getJiraPassword();
        String ENDPOINT = orc.getJiraApiAuthorizationUrl();

        try {
            System.out.println("Шаг 1: Авторизация и открытие сессии в Jira...");
            boolean loginSuccess = jira.createJiraSession(USERNAME, PASSWORD, ENDPOINT);

            if (loginSuccess) {
                System.out.println("Успешно! Сессия создана. Пароль больше не потребуется.\n");
                System.out.println("Получение данных о каталогах (используется кука сессии)...");
                String issueData = jira.fetchJiraData("/rest/api/2/issue/TEST-101");
                System.out.println("Данные задачи:\n" + issueData);

            } else {
                System.err.println("Не удалось авторизоваться. Проверьте логин и пароль.");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Произошла ошибка сетевого взаимодействия: " + e.getMessage());
        }

     }
}