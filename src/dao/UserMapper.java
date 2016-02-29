package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static User map(ResultSet resultSet) {
        User user = new User();
        try {
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setDateOfBirth(resultSet.getString("dateOfBirth"));
            user.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
