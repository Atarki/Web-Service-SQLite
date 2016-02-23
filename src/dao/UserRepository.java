package dao;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository userRepository;
    private static List<User> list;

    public void setList(List<User> list) {
        UserRepository.list = list;
    }

    public List<User> getList() {
        return list;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
            list = new ArrayList<>();
        }
        return userRepository;
    }
}
