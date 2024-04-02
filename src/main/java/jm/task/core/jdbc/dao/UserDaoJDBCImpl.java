package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(45), LASTNAME VARCHAR(45), AGE INT(3))";

        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.prepareStatement(sql);

            statement.executeUpdate(sql);
            connection.commit();
            connection.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE USERS";

        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.prepareStatement(sql);

            statement.executeUpdate(sql);
            connection.commit();
            connection.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();

            System.out.printf("User с именем %s добавлен в базу данных \n", name);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM USERS WHERE ID = ?";

        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();


        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);

                connection.commit();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE TEST.USERS";

        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);
            connection.commit();
            connection.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

