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

    public DataSource() {
        getConnection();
    }

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
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userList.add(UserMapper.map(resultSet));
            }
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed.");
    }


//    public void addUser(int id, String name, int age, String dateOfBirth) {
//        PreparedStatement preparedStatement = null;
//        String query = "INSERT INTO users(id, name, age, dateOfBirth) VALUES(?,?,?,?)";
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            preparedStatement.setString(2, name);
//            preparedStatement.setInt(3, age);
//            preparedStatement.setString(4, dateOfBirth);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.close();
//                System.out.println("Connection closed.");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void delete(String id) {
        PreparedStatement preparedStatement = null;
        String query = "delete from users WHERE id =" + id;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveToFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("UserRepository.txt"));

            for (User user : userList) {
                pw.println(user.toString());
            }
            pw.close();
            System.out.println("saved to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
