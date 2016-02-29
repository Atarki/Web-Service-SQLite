package service;

import dao.User;
import dao.UserDao;
import dao.UserRepository;

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
    public void deleteUser(String id) {
        if (UserRepository.getUserRepository().contains(id)) {
            userDao.deleteFromNewList(id);
        } else {
            userDao.deleteFromDB(id);
            initialize();
        }
    }
}
