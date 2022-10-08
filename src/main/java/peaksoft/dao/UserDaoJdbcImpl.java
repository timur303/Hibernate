package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public  void createUsersTable() throws SQLException {
        try (Connection connect = Util.connection();
             Statement statement = connect.createStatement()) {
            String SQL_CREAT = "CREATE TABLE user1" +
                    "(id SERIAL PRIMARY KEY NOT NULL," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age SMALLINT NOT NULL )";
            statement.executeUpdate(SQL_CREAT);
            System.out.println("User1 table created");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void dropUsersTable() {
        String SQL_DROP= "DROP TABLE user1";
        try(
            Connection connect =Util.connection();
            Statement statement = connect.createStatement()){
            statement.executeUpdate(SQL_DROP);
            System.out.println("User1 table drop");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String  SQL_SAVE = "INSERT INTO user1(name,lastName,age) VALUES (?, ?, ?)";
try(
        Connection connect =Util.connection();
        PreparedStatement statement = connect.prepareStatement(SQL_SAVE)){
        statement.setString(1,name);
        statement.setString(2,lastName);
        statement.setByte(3,age);
            statement.executeUpdate();
    System.out.println("Add base " + name);
        }catch (SQLException e){
    System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String SQL_REMOVE= "DELETE FROM user1 WHERE id = ? ";
        try(Connection connect =Util.connection();
            PreparedStatement statement = connect.prepareStatement(SQL_REMOVE)){
            statement.setInt(1,(int) id);
            statement.executeUpdate();
            System.out.println("Removing with " + id + " id ");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public List<User> getAllUsers() {
        String SQL_GETALL = "SELECT * FROM user1";
        List<User> users = new ArrayList<>();
        try(    Connection connect = Util.connection();
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_GETALL)){
                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                           users.add(user);
                }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } return users;

    }

    public void cleanUsersTable() {
        String SQL_CLEAN = "TRUNCATE TABLE user1";
        try(    Connection connect =Util.connection();
                Statement statement = connect.createStatement()){
                statement.executeUpdate(SQL_CLEAN);
            System.out.println("Cleaning table user1");
    }catch (SQLException e){
            System.out.println(e.getMessage());
        }
}
}