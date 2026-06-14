import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties prop = new Properties();

    public Properties getProp() {
        return prop;
    }

    public Properties getPropertiesData() {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            // записать в лог что параметры считаны
        } catch (Exception ex) {
            ex.printStackTrace();
            // записать исключение в лог
        }
        return prop;
    }
}