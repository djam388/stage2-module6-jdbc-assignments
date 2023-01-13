package jdbc;


import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = new ReadAppProperties().getProperties();



        System.out.println(properties.getProperty("postgres.driver"));
        System.out.println(properties.getProperty("postgres.url"));
        System.out.println(properties.getProperty("postgres.name"));
        System.out.println(properties.getProperty("postgres.password"));

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String dbURL = "jdbc:postgresql:myfirstdb" + "?user=" +properties.getProperty("postgres.name") + "&password=" + properties.getProperty("postgres.password");
        CustomDataSource customDataSource = CustomDataSource.getInstance();
        Connection connection = null;

        Connection conn = null;
        try {
            connection = customDataSource.getConnection();
            conn = DriverManager.getConnection(dbURL);
            // use connection
        } catch (SQLException e) {
            // log error
        } finally {
            if (conn != null) {
                try {
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM myusers");
                    PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM myusers");
                    ResultSet rs = ps.executeQuery();
                    ResultSet rs2 = ps2.executeQuery();
                    rs.next();
                    rs2.next();
                    System.out.println(rs.getLong("id") + " / " + rs2.getLong("id"));
                    System.out.println(rs.getString("firstname") + " / " + rs2.getString("firstname"));
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

//        User newUser = new User();
//        newUser.setId(30L);
//        newUser.setFirstName("Elon");
//        newUser.setLastName("Musk");
//        newUser.setAge(40);
//
//        new SimpleJDBCRepository(newUser).createUser();

        List<User> userList = new SimpleJDBCRepository().findAllUser();
        for (User user : userList) {
            System.out.println("id: " + user.getId());
            System.out.println("first name: " + user.getFirstName());
            System.out.println("last name: " + user.getLastName());
            System.out.println("age: " + user.getAge());

        }

        User user = new User();
        user.setId(10L);
        user.setFirstName("James");
        user.setLastName("Bond");
        user.setAge(20);
        long val = new SimpleJDBCRepository().createUser(user);
        System.out.println("Result of user creation: " + val);
        user = new SimpleJDBCRepository().findUserById(10L);

        user.setFirstName("Will");
        user.setLastName("Smith");
        user.setAge(25);

        new SimpleJDBCRepository().updateUser(user);

        user = new SimpleJDBCRepository().findUserById(10L);
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getAge());

        new SimpleJDBCRepository().deleteUser(10L);

        user = new SimpleJDBCRepository().findUserById(10L);
        System.out.println(user.getId());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getAge());

    }
}
