import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse<String> response;
        Orchestrator orc = new Orchestrator();
        JiraApi jira = new JiraApi();
        CookieManager cookieManager = new CookieManager();
        HttpClient client = HttpClient.newHttpClient();
        String USERNAME = orc.getJiraUser();
        String PASSWORD = orc.getJiraPassword();
        String ENDPOINT = orc.getJiraApiAuthorizathionUrl();

        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client = HttpClient.newBuilder()
                .cookieHandler(cookieManager) // Связываем менеджер кук с клиентом
                .connectTimeout(Duration.ofSeconds(10))
                .build();

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