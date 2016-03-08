package com.study.userStore.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserRepository {
    private static UserRepository USER_REPOSITORY;
    private List<User> userList;

    public static UserRepository getInstance() {
        if (USER_REPOSITORY == null) {
            USER_REPOSITORY = new UserRepository();
            USER_REPOSITORY.setList(new ArrayList<>());
        }
        return USER_REPOSITORY;
    }

    public void setList(List<User> list) {
        userList = list;
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
