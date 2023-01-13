package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnector {
    public Connection getConnection(String url) {
        Connection conn;

        try {
            Class.forName(CustomDataSource.getInstance().getDriver());
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to the PostgreSQL server successfully.");

        return conn;
    }

    public Connection getConnection(String url, String user, String password) {
        Connection conn;

        try {

            Class.forName(CustomDataSource.getInstance().getDriver());
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected to the PostgreSQL server successfully.");

        return conn;
    }
}
