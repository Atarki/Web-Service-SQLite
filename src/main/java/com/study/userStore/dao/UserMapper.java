package com.study.userStore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

public class UserMapper {
    public static User map(ResultSet resultSet) {
        User user = new User();
        try {
            Timestamp timestamp = Timestamp.valueOf(resultSet.getString("dateOfBirth"));
            int year = timestamp.toLocalDateTime().getYear();
            Month month = timestamp.toLocalDateTime().getMonth();
            int dayOfMonth = timestamp.toLocalDateTime().getDayOfMonth();
            LocalDate dateOfBirth = LocalDate.of(year, month, dayOfMonth);

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setDateOfBirth(dateOfBirth);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}

