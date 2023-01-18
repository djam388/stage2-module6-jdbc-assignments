package jdbc;

import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Logger;


@Getter
@Setter


public class CustomDataSource implements DataSource {
    private static volatile CustomDataSource instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;
    private static final Object object = new Object();

    private Properties properties;

    {
        properties = new ReadAppProperties().getProperties();
    }

    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.name = name;

    }

    private CustomDataSource() {
        this.driver = properties.getProperty("postgres.driver");
        this.url = properties.getProperty("postgres.url");
        this.password = properties.getProperty("postgres.password");
        this.name = properties.getProperty("postgres.name");

    }
    public Connection getConnection() {
        String url = getUrl() + "?user=" + getName() + "&password=" + getPassword();
        Connection connection = new CustomConnector().getConnection(url);
        return connection;
    }
    @Override
    public Connection getConnection(String username, String password) {
        Connection connection = new CustomConnector().getConnection(getUrl(), username, password);
        return connection;
    }

    public static CustomDataSource getInstance() {

        if (instance != null) {
            return instance;
        }

        synchronized (object) {
            if (instance == null) {
                instance = new CustomDataSource();
            }

            return instance;
        }
    }

    public static CustomDataSource getInstance(String driver, String url, String password, String name) {
        if (instance == null)
            instance = new CustomDataSource(driver, url, password, name);
        return instance;
    }

    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {
    }

    @Override
    public void setLoginTimeout(int seconds) {
    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }
}
