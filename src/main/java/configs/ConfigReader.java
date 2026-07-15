package configs;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {
    private final Properties prop = new Properties();
    private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);

    public Properties getPropertiesData() {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("configs/config.properties")) {
            prop.load(input);
            log.info("Загрузка параметров прошла успешно");
        } catch (Exception ex) {
            log.error(ex.toString());
        }
        return prop;
    }
}