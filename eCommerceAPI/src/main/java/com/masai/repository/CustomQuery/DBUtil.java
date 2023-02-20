package com.masai.repository.CustomQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public Connection provideConnection(){
        Connection connection=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url="jdbc:mysql://localhost:3306/db1";

        try {
            connection= DriverManager.getConnection(url,"root","shubhrakanti");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}