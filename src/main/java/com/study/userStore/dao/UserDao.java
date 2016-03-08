package com.study.userStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {
    private static final Logger LOG = Logger.getLogger(UserDao.class.getName());
    private static final String SAVE_USER_QUERY = "INSERT INTO users(name, dateOfBirth) VALUES(?,?)";
    private static final String GET_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String DELETE_USER_QUERY = "delete from users WHERE id =";
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                list.add(UserMapper.map(resultSet));
            }
            LOG.info("Connection closed." + connection);
            return list;
        } catch (SQLException e) {
            LOG.warning("Connection closed.\n Warning : " + e);
        }
        return list;
    }

    public void saveToDB(List<User> userList) {
        try (Connection connection = dataSource.getConnection();) {
            for (User user : userList) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_QUERY);) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getDateOfBirth().toString());
                    preparedStatement.executeUpdate();
                }
            }
            LOG.info("Connection closed.\n Data Base was updated.User was added successfully: ");
        } catch (SQLException e) {
            LOG.warning("Connection closed.\n Warning : " + e);
        }
    }

    public void deleteFromDB(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY + id);) {
            preparedStatement.executeUpdate();

            LOG.info("Connection closed.");
        } catch (SQLException e) {
            LOG.warning("Connection closed.\n Warning : " + e);
        }
    }
}


