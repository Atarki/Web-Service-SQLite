package dao;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository userRepository;
    private static List<User> userList;
    private static List<User> newUserList;

    public void setList(List<User> list) {
        UserRepository.userList = list;
    }

    public List<User> getAllUsers() {
        // TODO: 01.03.2016  
        userList.addAll(newUserList);
        return userList;
    }

    public List<User> getNewUserList() {
        return newUserList;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
            userList = new ArrayList<>();
            newUserList = new ArrayList<>();
        }
        return userRepository;
    }

    public void addNewUsers(User user) {
        newUserList.add(user);
    }

    public void clear() {
        userList.clear();
    }

    public void deleteUser(String id) {
        for (int i = 0; i < newUserList.size(); i++) {
            User user = newUserList.get(i);
            if (Integer.valueOf(id) == user.getId()) {
                newUserList.remove(i);
            }
        }
    }

    public boolean contains(String id) {
        for (User user : newUserList) {
            if (Integer.valueOf(id) == user.getId()) {
                return true;
            }
        }
        return false;
    }
}
