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
//        System.out.println("Test:-------------------------------");
//        try {
//            DriverManager.registerDriver(new org.postgresql.Driver());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String dbURL = "jdbc:postgresql:myfirstdb" + "?user=" +properties.getProperty("postgres.name") + "&password=" + properties.getProperty("postgres.password");
//        CustomDataSource customDataSource = CustomDataSource.getInstance();
//        Connection connection = null;
//
//        Connection conn = null;
//        try {
//            connection = customDataSource.getConnection();
//            conn = DriverManager.getConnection(dbURL);
//            // use connection
//        } catch (SQLException e) {
//            // log error
//        } finally {
//            if (conn != null) {
//                try {
//                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM myusers");
//                    PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM myusers");
//                    ResultSet rs = ps.executeQuery();
//                    ResultSet rs2 = ps2.executeQuery();
//                    rs.next();
//                    rs2.next();
//                    System.out.println(rs.getLong("id") + " / " + rs2.getLong("id"));
//                    System.out.println(rs.getString("firstname") + " / " + rs2.getString("firstname"));
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("Test finished:---------------------");
        SimpleJDBCRepository simpleJDBCRepository = new SimpleJDBCRepository();


        List<User> users = simpleJDBCRepository.findAllUser();

        System.out.println("Users count in DB: " + users.size());

//        simpleJDBCRepository.deleteUser(10L);

        users = simpleJDBCRepository.findAllUser();

        System.out.println("Users count in DB: " + users.size());

        User user = new User();
        user.setId(10L);
        user.setFirstName("Elon");
        user.setLastName("Musk");
        user.setAge(35);

        long val = simpleJDBCRepository.createUser(user);

        for (User foundUser : simpleJDBCRepository.findAllUser()) {
            user = foundUser;
            System.out.println("User id: " + foundUser.getId()
                    + ", User age: " + foundUser.getAge()
                    + ", User last name: " + foundUser.getLastName()
                    + ", User first name: " + foundUser.getFirstName());

        }

        user.setFirstName("Bill");
        user.setLastName("Gates");
        user.setAge(45);

        simpleJDBCRepository.updateUser(user);


        for (User foundUser : simpleJDBCRepository.findAllUser()) {
            user = foundUser;
            System.out.println("User id: " + foundUser.getId()
                    + ", User age: " + foundUser.getAge()
                    + ", User last name: " + foundUser.getLastName()
                    + ", User first name: " + foundUser.getFirstName());

        }

        user = simpleJDBCRepository.findUserById(4L);

        System.out.println("ID: " + user.getId() + " Name: " + user.getFirstName() );

        simpleJDBCRepository.deleteUser(5L);




//        simpleJDBCRepository.updateUser(user);
//
//        User user2 = simpleJDBCRepository.findUserByName("firstUserName");
//
//        user2.setFirstName(oldName);
//
//        simpleJDBCRepository.updateUser(user2);
//
//        User user3 = new User();
//
//        user3.setId(10L);
//        user3.setFirstName("Elon");
//        user3.setLastName("Musk");
//        user3.setAge(35);
//        simpleJDBCRepository.createUser(user3);

        for (User foundUser : simpleJDBCRepository.findAllUser()) {

            System.out.println("User id: " + foundUser.getId()
                + ", User age: " + foundUser.getAge()
                + ", User last name: " + foundUser.getLastName()
                + ", User first name: " + foundUser.getFirstName());

        }


////        User newUser = new User();
////        newUser.setId(30L);
////        newUser.setFirstName("Elon");
////        newUser.setLastName("Musk");
////        newUser.setAge(40);
////
////        new SimpleJDBCRepository(newUser).createUser();
//        System.out.println("List of all users:-----------------");
//
//        List<User> userList = new SimpleJDBCRepository().findAllUser();
//        for (User user : userList) {
//            System.out.println("id: " + user.getId());
//            System.out.println("first name: " + user.getFirstName());
//            System.out.println("last name: " + user.getLastName());
//            System.out.println("age: " + user.getAge());
//
//        }
//        System.out.println("Users count: " + userList.size());
//        System.out.println("----------------------------------");
//        System.out.println("Delete user with Id 10:-----------");
//
//        new SimpleJDBCRepository().deleteUser(10L);
////
//        System.out.println("User with Id 10 is deleted");
//
//        userList = new SimpleJDBCRepository().findAllUser();
//        System.out.println("----------------------------------");
//
//        System.out.println("Users count: " + userList.size());
//        System.out.println("Create user with Id 10:-----------");
//
//
//        User user = new User();
//        user.setId(10L);
//        user.setFirstName("James");
//        user.setLastName("Bond");
//        user.setAge(20);
//        System.out.println("user id: " + user.getId()
//                + " user age: " + user.getAge()
//                + " user last name: " + user.getLastName()
//                + " user first name: " + user.getFirstName());
//        long val = new SimpleJDBCRepository().createUser(user);
//        System.out.println("Result of user creation: " + val);
//        user = new SimpleJDBCRepository().findUserById(10L);
//
//        System.out.println("----------------------------------");
//        System.out.println("Rename user: ---------------------");
//
//        user.setFirstName("Will");
//        user.setLastName("Smith");
//        user.setAge(25);
//
//        new SimpleJDBCRepository().updateUser(user);
//        System.out.println("----------------------------------");
//
//        System.out.println("Renamed user: --------------------");
//
//        user = new SimpleJDBCRepository().findUserById(10L);
//
//        System.out.println(user.getId());
//        System.out.println(user.getFirstName());
//        System.out.println(user.getLastName());
//        System.out.println(user.getAge());
//        System.out.println("----------------------------------");
//
////        new SimpleJDBCRepository().deleteUser(10L);
//        System.out.println("Users count: " + userList.size());
//
//        user = new SimpleJDBCRepository().findUserById(10L);
//        System.out.println(user.getId());
//        System.out.println(user.getFirstName());
//        System.out.println(user.getLastName());
//        System.out.println(user.getAge());
//
//        user = new SimpleJDBCRepository().findUserById(null);

    }
}
