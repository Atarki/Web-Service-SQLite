package com.study.userStore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private String url = "jdbc:mysql://localhost:3306/userStore" +
            "?verifyServerCertificate=false" +
            "&useSSL=true" +
            "&requireSSL=false";
    private String driverClass = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "root";

    public Connection getConnection() {
        try {
            Class.forName(driverClass);
            System.out.println("Connection opened: ");
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
