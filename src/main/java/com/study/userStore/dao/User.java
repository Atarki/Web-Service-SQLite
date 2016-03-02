package com.study.userStore.dao;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private String name;
    private Integer id;
    private LocalDate dateOfBirth;

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
