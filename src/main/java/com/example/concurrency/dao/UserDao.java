package com.example.concurrency.dao;

import com.example.concurrency.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public int saveUser(User user){
        int rows=0;
        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement("insert into user (id,username,email,password) values (?,?,?,?)");
            statement.setInt(1,user.getId());
            statement.setString(2,user.getName());
            statement.setString(3,user.getEmailAddress());
            statement.setString(4,"$12$Bxdy417maBSkE.A8GcXm9ODXowfZW9Mb5OxR2J41iSK9MRhgVPR8S");
            rows=statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception");
        }
        return rows;
    }
}
