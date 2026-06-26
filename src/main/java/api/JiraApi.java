package api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;


public class JiraApi {
    private static final String JIRA_BASE_URL = "https://jira.cbr.ru";
    private static HttpClient client;
    private static final CookieManager cookieManager = new CookieManager();
    private static final Logger log = LoggerFactory.getLogger(JiraApi.class);
    static {
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                    }
            };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());

        client = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .sslContext(sc)
                .build();
    } catch (Exception e) {
          log.error("Не удалось настроить SSL-контекст, используется клиент по умолчанию");
          client = HttpClient.newBuilder()
                  .cookieHandler(cookieManager)
                  .build();
        }
    }

    public boolean createJiraSession(String username, String password, String baseUrl) throws IOException, InterruptedException {
        String sessionUrl = JIRA_BASE_URL + baseUrl;
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeaderValue = "Basic " + new String(encodedAuth);

        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create(sessionUrl))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", authHeaderValue)
                .GET()
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

    public String fetchJiraDataNA(String apiEndpoint) throws IOException, InterruptedException {
        String targetUrl = JIRA_BASE_URL + apiEndpoint;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("Content-Type", "application/json")
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