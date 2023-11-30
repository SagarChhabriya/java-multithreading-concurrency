package com.example.concurrency.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root","1234");
            return connection;
        } catch (SQLException e) {
            System.out.println("Exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception");
        }
        return connection;
    }


}
