package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {
//    private User user = null;
    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "INSERT INTO myusers (id, firstname, lastname, age) VALUES (?, ?, ?, ?);";
    private static final String updateUserSQL = "UPDATE myusers SET firstname = ?, lastname = ?, age = ? WHERE id = ?";
    private static final String deleteUser = "DELETE FROM myusers WHERE id = ?";
    private static final String findUserByIdSQL = "SELECT * FROM myusers WHERE id = ?";
    private static final String findUserByNameSQL = "SELECT * FROM myusers WHERE firstname = ?";
    private static final String findAllUserSQL = "SELECT * FROM myusers";

//    public SimpleJDBCRepository(User user) {
//        this.user = user;
//    }

    public Long createUser(User user) throws NamingException, SQLException {
        connection = CustomDataSource.getInstance().getConnection();
        ps = connection.prepareStatement(createUserSQL);
        ps.setLong(1, user.getId());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setInt(4, user.getAge());
        long val = ps.executeUpdate();
        connection.close();
        return val;

    }

    public User findUserById(Long userId) throws NamingException, SQLException {
        connection = CustomDataSource.getInstance().getConnection();
        ps = connection.prepareStatement(findUserByIdSQL);
        ps.setLong(1, userId);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setAge(rs.getInt("age"));
            user.setFirstName(rs.getString("firstname") );
            user.setLastName(rs.getString("lastname"));
        }
        connection.close();
        return user;
    }

    public User findUserByName(String userName) throws NamingException, SQLException {
        connection = CustomDataSource.getInstance().getConnection();
        ps = connection.prepareStatement(findUserByNameSQL);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setAge(rs.getInt("age"));
            user.setFirstName(rs.getString("firstname") );
            user.setLastName(rs.getString("lastname"));
        }
        connection.close();
        return user;
    }

    public List<User> findAllUser() throws NamingException, SQLException  {
        List<User> userList = new ArrayList<>();
        connection = CustomDataSource.getInstance().getConnection();
        st = connection.createStatement();
        ResultSet rs = st.executeQuery(findAllUserSQL);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setAge(rs.getInt("age"));
            user.setFirstName(rs.getString("firstname") );
            user.setLastName(rs.getString("lastname"));
            userList.add(user);
        }
        connection.close();
        return userList;
    }

    public User updateUser(User user) throws NamingException, SQLException, NullPointerException  {
        connection = CustomDataSource.getInstance().getConnection();
        ps = connection.prepareStatement(updateUserSQL);
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setInt(3, user.getAge());
        ps.setLong(4, user.getId());
        ps.executeUpdate();
        User changedUser = findUserById(user.getId());
        connection.close();
        return changedUser;
    }

    public void deleteUser(Long userId) throws NamingException, SQLException  {
        connection = CustomDataSource.getInstance().getConnection();
        ps = connection.prepareStatement(deleteUser);
        ps.setLong(1, userId);
        ps.executeUpdate();
        connection.close();
    }
}
