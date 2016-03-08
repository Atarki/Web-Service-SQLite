package com.study.userStore.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

public class FileExporter {

    private String fileName;
    private static final Logger LOG = Logger.getLogger(DataSource.class.getName());

    public void saveToFile(List<User> users) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));) {
            for (User user : users) {
                pw.println(user.toString());
            }
            LOG.info("File was successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
            LOG.info("Error. There was some problems with writing the file.");
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
