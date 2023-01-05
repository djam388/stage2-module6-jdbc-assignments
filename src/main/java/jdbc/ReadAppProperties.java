package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadAppProperties {
    private Properties properties = new Properties();
    public ReadAppProperties() {
    }

    public Properties getProperties() {
        try (InputStream input = ReadAppProperties.class.getClassLoader().getResourceAsStream("app.properties")) {

            if (input == null) {
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

            return properties;
    }
}
