package utils;
import javax.net.ssl.*;
import java.io.InputStream;
import java.security.KeyStore;

public class SslHelper {

    public static void setupTrustStore() throws Exception {
        try (InputStream trustStoreStream = SslHelper.class.getResourceAsStream("/configs/myTrustStore.jks")) {
            if (trustStoreStream == null) {
                throw new RuntimeException("TrustStore not found in classpath: /configs/myTrustStore.jks");
            }

            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(trustStoreStream, "changeit".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }
    }
}