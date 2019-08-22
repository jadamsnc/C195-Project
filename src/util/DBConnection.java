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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jeremy
 */
public class DBConnection {
    private static DBConnection dbconn;
    
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://52.206.157.109/U05lYc";
    final static String DBUSER = "U05lYc";
    final static String DBPASS = "53688540815";
    private static Connection conn;
    
    private DBConnection(){}
    
    public static DBConnection getDBConn() {
        return dbconn;
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public static void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);
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
            conn.close(); 
            System.out.println("Connection Closed");
        } catch (SQLException e) {
            System.out.println("failed to close SQL connection");
            e.printStackTrace();
        } 
    }
    
    public static ResultSet query(String selectValue, String fromValue, String whereValue) throws SQLException{
        Statement stmt;
        stmt = conn.createStatement();
        String query = "SELECT " + selectValue + " FROM " + fromValue + 
                " WHERE " + whereValue + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    
    public static ResultSet query(String selectValue, String fromValue) throws SQLException{
        Statement stmt;
        stmt = conn.createStatement();
        String query = "SELECT " + selectValue + " FROM " + fromValue + ";";
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    
    public static void insert(String TableName, String Values) throws SQLException{
        Statement stmt;
        stmt = conn.createStatement();
        String insert = "INSERT INTO " + TableName + " VALUES " + Values + ";";
        stmt.executeUpdate(insert);
    }
    
    public static void delete(String TableName, String Value) throws SQLException {
        Statement stmt;
        stmt = conn.createStatement();
        String delete = "DELETE FROM " + TableName + " WHERE " + Value + ";";
        stmt.executeUpdate(delete);
    }
    
    public static String dateConvert(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    
    public static void update(String TableName, String Values, String Where) throws SQLException {
        Statement stmt;
        stmt = conn.createStatement();
        String update = "UPDATE " + TableName + " SET " + Values + " WHERE " + Where + ";";
        stmt.executeUpdate(update);
    }
}
