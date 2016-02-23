package dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

public class UserDao {

    private static Connection connection;
    private List<User> userList = UserRepository.getUserRepository().getList();

    public UserDao() {
    }

    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:EmployeeDb.db");
            System.out.println(connection);
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

            if (!userList.isEmpty()) {
                userList.clear();
            }

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setDateOfBirth(resultSet.getString("dateOfBirth"));
                user.setId(resultSet.getInt("id"));

                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void addUser(int id, String name, int age, String dateOfBirth) {
        PreparedStatement preparedStatement = null;
//        String query = "INSERT * INTO users where name = ? and userAge = ? and dateOfBirth = ?";
//        String query = "INSERT * INTO users VALUES (name = ? and userAge = ? and dateOfBirth = ?)";
//        String query = String.format("INSERT INTO users VALUES(%s\n, %s\n,%s\n)", name, String.valueOf(age), String.valueOf(dateOfBirth));
//        String query = "INSERT INTO users VALUES ('name' ,age ,dateOfBirth)";
        String query = "INSERT INTO users(id, name, age, dateOfBirth) VALUES(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

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
