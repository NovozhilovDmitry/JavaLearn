import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class JiraApi {
    private static final String JIRA_BASE_URL = "https://jira.cbr.ru";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";
    private static final HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> getDataFromUri(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .GET()
                .build();
             HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response;
    }

    public static boolean createJiraSession(String username, String password, String baseUrl) throws IOException, InterruptedException {
        String sessionUrl = JIRA_BASE_URL + baseUrl;
        String jsonPayload = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create(sessionUrl))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(loginRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return true;
        } else {
            System.err.println("Ошибка авторизации. Код ответа Jira: " + response.statusCode());
            System.err.println("Детали: " + response.body());
            return false;
        }
    }

    public static String fetchJiraData(String apiEndpoint) throws IOException, InterruptedException {
        String targetUrl = JIRA_BASE_URL + apiEndpoint;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Jira API вернула ошибку " + response.statusCode() + " для адреса " + targetUrl);
        }
    }
}