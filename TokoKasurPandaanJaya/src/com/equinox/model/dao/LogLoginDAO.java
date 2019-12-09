/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.LogLogin;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementLogLogin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author equinox
 */
public class LogLoginDAO implements ImplementLogLogin {
    
    private List<LogLogin> list;
    
    @Override
    public void insert(String nama_user) {
        
        try {
             
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "INSERT INTO Log_Login(namaUser) VALUES (?)");
            
            statement.setString(1, nama_user);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LogLogin> getAllLogin() {
        list = new ArrayList<>();
        
        try {
            
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT * FROM Log_Login");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {                
                LogLogin login = new LogLogin();
                login.setId(resultSet.getString("id"));
                login.setNama_user(resultSet.getString("namaUser"));
                login.setWaktu_login(resultSet.getString("waktu_login"));
                list.add(login);
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
