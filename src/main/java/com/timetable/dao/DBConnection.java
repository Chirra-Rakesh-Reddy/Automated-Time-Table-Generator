package com.timetable.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/timetable_generator";
    private static final String USER = "nani";
    private static final String PASSWORD = "nani";
    
    private static Connection connection = null;
    
   
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}