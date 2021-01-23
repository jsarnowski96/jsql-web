/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.wzr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsarnowski
 */
public class DatabaseConnection {
    private Connection connection;
    
    public DatabaseConnection() {
        
    }
    
    public void ConnectToDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;",
                "sa", "Zagubieni434"
            );
            System.out.println("MS SQL Connection - ESTABLISHED");
            System.out.println(connection);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void CloseConnection() {
        try {
            if(connection.isClosed() == false) {
                connection.close();
            }
            System.out.println("MS SQL Connection - CLOSED");
        } catch (Exception e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Connection getConnection() {
        try {
            if(connection.isClosed() == false) {
                return connection;
            } else {
                return null;
            }
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
