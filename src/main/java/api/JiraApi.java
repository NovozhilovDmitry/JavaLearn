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
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class JiraApi {
    private final String jiraBaseUrl;
    private final String authHeader;
    private static HttpClient client;
    private static final CookieManager cookieManager = new CookieManager();
    private static final Logger log = LoggerFactory.getLogger(JiraApi.class);

    public JiraApi(String baseUrl, String userName, String password) {
        this.jiraBaseUrl = baseUrl;
        this.authHeader = "Basic " + Base64.getEncoder().encodeToString((userName +":"+ password).getBytes(StandardCharsets.UTF_8));
    }

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

    public String getDataFromUrl(String apiEndpoint) throws IOException, InterruptedException {
        String sessionUrl = jiraBaseUrl + apiEndpoint;
        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create(sessionUrl))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", authHeader)
                .GET()
                .build();
        HttpResponse<String> response = client.send(loginRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            log.error("Ошибка авторизации. Код ответа Jira: {}", response.statusCode());
            log.error("Детали: {}", response.body());
            throw new IOException("Jira API вернула ошибку " + response.statusCode() + " для адреса " + sessionUrl);
        }
    }

}