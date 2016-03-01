package main.java.com.study.userStore.dao;

import java.util.UUID;

public class User {
    private String name;
    private int id;
    private int age;
    private String dateOfBirth;
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
