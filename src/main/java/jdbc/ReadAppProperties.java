package jdbc;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadAppProperties {
    private Properties properties = new Properties();
    public ReadAppProperties() {
    }

    public Properties getProperties() throws IOException, NamingException {
        InputStream input = ReadAppProperties.class.getClassLoader().getResourceAsStream("app.properties");

            if (input == null) {
                return null;
            }
            properties.load(input);


            return properties;
    }
}
