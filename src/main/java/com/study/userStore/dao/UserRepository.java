package com.study.userStore.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserRepository {
    private static UserRepository USER_REPOSITORY;
    private static List<User> userList;

    public static UserRepository getInstance() {
        if (USER_REPOSITORY == null) {
            USER_REPOSITORY = new UserRepository();
            userList = new ArrayList<>();
        }
        return USER_REPOSITORY;
    }

    public void setList(List<User> list) {
        UserRepository.userList = list;
    }

    public void addNewUsers(User user) {
        userList.add(user);
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void clear() {
        userList.clear();
    }

    public boolean deleteUser(String id, String name) {
        boolean userInDB = false;
        Iterator<User> iteratorFromDB = userList.iterator();
        if (id == null) {
            while (iteratorFromDB.hasNext()) {
                User user = iteratorFromDB.next();
                if (user.getName().equals(name)) {
                    iteratorFromDB.remove();
                }
            }
        } else {
            while (iteratorFromDB.hasNext()) {
                User user = iteratorFromDB.next();
                if (String.valueOf(user.getId()).equals(id)) {
                    iteratorFromDB.remove();
                    userInDB = true;
                }
            }
        }
        return userInDB;
    }
}
