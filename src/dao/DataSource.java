package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static java.sql.Connection connection;
    private List<User> userList = new ArrayList<>();

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:EmployeeDb.db");
            System.out.println("Connection opened: " + connection);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("UserRepository.txt"));) {
            for (User user : userList) {
                pw.println(user.toString());
            }
            System.out.println("saved to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
