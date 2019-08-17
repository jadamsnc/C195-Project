/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jeremy
 */
public class DBConnection {
    private static Connection dbconn;
    
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://52.206.157.109/U05lYc";
    final static String DBUSER = "U05lYc";
    final static String DBPASS = "53688540815";
    
    public DBConnection(){}
    
    public static Connection getDBConn() {
        return dbconn;
    }
    
    public static void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            dbconn = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);
            System.out.println("Connection successful");
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL connection failed");
            e.printStackTrace();
        }
    }
    
    public static void closeConn(){
        try{
            dbconn.close(); 
            System.out.println("Connection Closed");
        } catch (SQLException e) {
            System.out.println("failed to close SQL connection");
            e.printStackTrace();
        } 
    }
    
    public static ResultSet query(String selectValue, String fromValue, String whereValue) throws SQLException{
        DBConnection.connect();
        Statement stmt;
        stmt = dbconn.createStatement();
        String query = "SELECT " + selectValue + " FROM " + fromValue + 
                " WHERE " + whereValue + ";";
        ResultSet rs = stmt.executeQuery(query);
        DBConnection.closeConn();
        return rs;
    }
    
    public static ResultSet query(String selectValue, String fromValue) throws SQLException{
        DBConnection.connect();
        Statement stmt;
        stmt = dbconn.createStatement();
        String query = "SELECT " + selectValue + " FROM " + fromValue + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}
