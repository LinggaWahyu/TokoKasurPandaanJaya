/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author equinox
 */
public class ConnectionDatabase {
    
    private static Connection connection;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/TokoKasur", "root", "");
            } catch (ClassNotFoundException | SQLException e) {
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return connection;
    }
    
    public static void main(String[] args) {
        
        ConnectionDatabase.getConnection();
        
    }
}
