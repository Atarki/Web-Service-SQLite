package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserRepository {
    private static UserRepository userRepository;
    private static List<User> usersFromDB;
    private static List<User> usersNew;
    private static List<User> finalList;

    public void setList(List<User> list) {
        UserRepository.usersFromDB = list;
        if (finalList.isEmpty()) {
            finalList.addAll(usersFromDB);
        }
    }

    public List<User> getAllUsers() {
        if (usersNew.isEmpty()) {
            return usersFromDB;
        } else {
            return finalList;
        }
    }

    public List<User> getNewUserList() {
        return usersNew;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
            usersFromDB = new ArrayList<>();
            usersNew = new ArrayList<>();
            finalList = new ArrayList<>();

        }
        return userRepository;
    }

    public void addNewUsers(User user) {
        for (User u : finalList) {
            if (u.getId() == user.getId()) {
                throw new RuntimeException("User with this [id] is exist");
            }
        }
        usersNew.add(user);
        updateList();
    }

    public void clear() {
        usersFromDB.clear();
        usersNew.clear();
        finalList.clear();
    }

    public static void updateList() {
        finalList.clear();
        finalList.addAll(usersFromDB);
        finalList.addAll(usersNew);
    }

    public boolean deleteUser(String id) {
        boolean inDB = false;
        Iterator<User> iteratorFromDB = usersFromDB.iterator();
        Iterator<User> iteratorNewUsers = usersNew.iterator();

        while (iteratorFromDB.hasNext()) {
            User user = iteratorFromDB.next();
            if (Integer.valueOf(id) == user.getId()) {
                iteratorFromDB.remove();
                inDB = true;
            }
        }
        while (iteratorNewUsers.hasNext()) {
            User user = iteratorNewUsers.next();
            if (Integer.valueOf(id) == user.getId()) {
                iteratorNewUsers.remove();
            }
        }
/*
        for (User user : usersFromDB) {
            if (Integer.valueOf(id) == user.getId()) {
                usersFromDB.remove(user);
                inDB = true;
            }
        }
        for (User user : usersNew) {
            if (Integer.valueOf(id) == user.getId()) {
                usersNew.remove(user);
            }
        }*/
        updateList();
        return inDB;
    }
}
