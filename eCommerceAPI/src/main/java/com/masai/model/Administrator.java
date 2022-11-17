package com.masai.model;

public class Administrator {

    private String userName;
    private String userPassword;


    public Administrator(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
