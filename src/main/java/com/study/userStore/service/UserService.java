package main.java.com.study.userStore.service;

import main.java.com.study.userStore.dao.User;
import main.java.com.study.userStore.dao.UserDao;
import main.java.com.study.userStore.dao.UserRepository;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void initialize() {
        UserRepository userRepository = UserRepository.getUserRepository();
        userRepository.clear();
        userRepository.setList(userDao.getAll());
    }

    public List<User> getAll() {
        return UserRepository.getUserRepository().getAllUsers();
    }

    public void addToRepository(User user) {
        UserRepository.getUserRepository().addNewUsers(user);
    }
    public void save() {
        userDao.saveToDB();
    }
    public void saveToFile() {
        userDao.saveToFile();
    }
    public void deleteUser(String id) {
        if (UserRepository.getUserRepository().deleteUser(id)) {
            userDao.deleteFromDB(id);
        }
    }
}
