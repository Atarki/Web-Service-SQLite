package com.study.userStore.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileDao {

    public void saveToFile(List<User> users) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("UserRepository.txt"));) {
            for (User user : users) {
                pw.println(user.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
