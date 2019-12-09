/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.implement.ImplementAdmin;
import com.equinox.model.Admin;
import com.equinox.model.database.ConnectionDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class AdminDAO implements ImplementAdmin {

    private boolean status = false;

    @Override
    public boolean loginAdmin(String username, String password) {

        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT * FROM admin WHERE username_admin = ? AND pass_admin = ?");

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                status = true;
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public String getIdAdmin(String username) {
        String id = "";

        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT id_admin FROM admin WHERE username_admin = ?");
            
            statement.setString(1, username);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getString("id_admin");
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
