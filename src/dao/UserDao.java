package dao;

import java.util.List;

public class UserDao {
    private static UserDao USERDAO;
    private static java.sql.Connection connection;
    private UserRepository userRepository;
    private DataSource dataSource;

    public static UserDao getUserDao() {
        if (USERDAO == null) {
            USERDAO = new UserDao();
        }
        return USERDAO;
    }

    public List<User> getAll() {
        connection = dataSource.getConnection();
        List<User> list = dataSource.getUserList();
        userRepository = UserRepository.getUserRepository();
        userRepository.setList(list);
        return list;
    }

    public void saveToDB() {
        connection = dataSource.getConnection();
        List<User> newUserList = UserRepository.getUserRepository().getNewUserList();
        dataSource.addUser(newUserList);
    }

    public void saveToFile() {
        connection = dataSource.getConnection();
        dataSource.saveToFile();
    }

    public void deleteFromDB(String id) {
        connection = dataSource.getConnection();
        dataSource.delete(id);

    }

    public void deleteFromNewList(String id) {
        UserRepository.getUserRepository().deleteUser(id);

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


