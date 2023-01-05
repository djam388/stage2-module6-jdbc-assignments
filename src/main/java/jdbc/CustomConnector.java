package jdbc;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnector {
    public Connection getConnection(String url) throws SQLException, NamingException {
        Connection conn = null;

            conn = DriverManager.getConnection(url);
            System.out.println("Connected to the PostgreSQL server successfully.");


        return conn;
    }

    public Connection getConnection(String url, String user, String password) throws SQLException, NamingException {
        Connection conn = null;

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");


        return conn;
    }
}
