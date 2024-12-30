package com.jdbcHospital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static Connection con =null;
    public static Connection getConnection(){
        final String db_name ="Hospital";
        final String myURL = "jdbc:mysql://localhost:3306/"+db_name;
        final String myUser= "root";
        final String myPass ="MApw2024!!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =  DriverManager.getConnection(myURL, myUser, myPass);
//            System.out.println(con);
//            System.out.println("Connected successfully!");

        } catch (ClassNotFoundException | SQLException e) {
            // throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
        return con;
    }
}
