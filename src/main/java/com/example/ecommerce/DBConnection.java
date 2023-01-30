package com.example.ecommerce;

import java.sql.*;

public class DBConnection {
    Connection con = null;
    String sqlurl="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String userName="root";
    String password ="umesh12493@#";

    DBConnection() throws SQLException {
 con= DriverManager.getConnection(sqlurl,userName,password);
if(con!=null){
    System.out.println("connected");
}
    }
    ResultSet executeQuery(String query) throws SQLException {
        ResultSet result = null;
        try {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            return result;
         }
        catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }
    public int  executeUpdate(String query)  {
        int row=0;
        try {
            Statement statement = con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
}
