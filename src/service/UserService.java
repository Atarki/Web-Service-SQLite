package service;

import dao.User;
import dao.UserDao;
import dao.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static UserService USERSERVICE;
    private static java.sql.Connection connection = UserDao.Connector();
    private UserRepository userRepository;
    private UserDao userDao = new UserDao();

    public static UserService getUserService() {
        if (USERSERVICE == null) {
            USERSERVICE = new UserService();
        }
        return USERSERVICE;
    }

    public List<User> getAll() {
        Connection();
        List<User> list = userDao.getUserList();
        userRepository = UserRepository.getUserRepository();
        userRepository.setList(list);
        return list;
    }

    public void save(int id, String name, int age, String dateOfBirth) {
        Connection();
        userDao.addUser(id, name, age, dateOfBirth);
    }

    public void saveToFile() {
        Connection();
        userDao.saveToFile();
    }

    public void delete(String id) {
        Connection();
        userDao.delete(id);
    }
    public void Connection() {
        try {
            if (connection.isClosed()) {
                connection = UserDao.Connector();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


