package com.study.userStore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class UserMapper {
    public static User map(ResultSet resultSet) {
        User user = new User();
        try {
            Timestamp timestamp = Timestamp.valueOf(resultSet.getString("dateOfBirth"));
            LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setDateOfBirth(localDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}

