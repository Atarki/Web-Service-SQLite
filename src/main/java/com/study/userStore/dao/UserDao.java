package main.java.com.study.userStore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDao {
    private UserDao USERDAO;
    private java.sql.Connection connection;
    private UserRepository userRepository;
    private DataSource dataSource;

    public static UserDao getUserDao() {
        if (USERDAO == null) {
            USERDAO = new UserDao();
        }
        return USERDAO;
    }

    public List<User> getAll() {
        connection = dataSource.getConnection();
        List<User> list = dataSource.getUserList();
        userRepository = UserRepository.getUserRepository();
        userRepository.setList(list);
        return list;
    }

    public void saveToDB() {
        connection = dataSource.getConnection();
        List<User> newUserList = UserRepository.getUserRepository().getNewUserList();
        dataSource.addUser(newUserList);
    }

    public void saveToFile() {
        connection = dataSource.getConnection();
        dataSource.saveToFile();
    }

    public void deleteFromDB(String id) {
        connection = dataSource.getConnection();
        dataSource.delete(id);

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<User> getUserList() {
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()) {
                userList.add(UserMapper.map(resultSet));
            }
            System.out.println("Connection closed.");
            return userList;

        } catch (SQLException e) {
            System.out.println("Connection closed.");
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(List<User> userList) {
        String query = "INSERT INTO users(id, name, age, dateOfBirth) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            for (User user : userList) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getDateOfBirth());
                preparedStatement.executeUpdate();
            }
            System.out.println("Data Base was updated.");
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String query = "delete from users WHERE id =" + id;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.executeUpdate();

            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println("Connection closed.");
            e.getSQLState();
        }
    }

}


