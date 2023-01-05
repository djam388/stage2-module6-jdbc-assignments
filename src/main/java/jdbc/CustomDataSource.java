package jdbc;

import javax.naming.NamingException;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
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

    private Properties properties = new ReadAppProperties().getProperties();

    private CustomDataSource(String driver, String url, String password, String name) throws NamingException, IOException {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.name = name;

    }

    private CustomDataSource() throws NamingException, IOException {
        this.driver = properties.getProperty("postgres.driver");
        this.url = properties.getProperty("postgres.url");
        this.password = properties.getProperty("postgres.password");
        this.name = properties.getProperty("postgres.name");

    }
    public Connection getConnection() throws SQLException {
        String url = getUrl() + "?user=" + getName() + "&password=" + getPassword();
        Connection connection = new CustomConnector().getConnection(url);
        return connection;
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection connection = new CustomConnector().getConnection(getUrl(), username, password);
        return connection;
    }

    public static CustomDataSource getInstance() throws NamingException, IOException {
        if (instance == null)
            instance = new CustomDataSource();

        return instance;
    }

    public static CustomDataSource getInstance(String driver, String url, String password, String name) throws NamingException, IOException {
        if (instance == null)
            instance = new CustomDataSource(driver, url, password, name);

        return instance;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
