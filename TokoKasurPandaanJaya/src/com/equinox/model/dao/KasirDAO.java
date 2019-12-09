/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.implement.ImplementKasir;
import com.equinox.model.Kasir;
import com.equinox.model.database.ConnectionDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author equinox
 */
public class KasirDAO implements ImplementKasir {

    private boolean status = false;
    private List<Kasir> list;
    
    @Override
    public boolean loginKasir(String username, String password) {
        
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT * FROM kasir WHERE nama_kasir = ? AND pass_kasir = ?");

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
    public String getIdKasir(String nama) {
        String Id_kasir = null;
        
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" +
                    "SELECT getIdKasir(?)");
            
            statement.setString(1, nama);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Id_kasir = resultSet.getString("getIdKasir('" + nama + "')");
            } else {
                Id_kasir = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Id_kasir;
    } 

    @Override
    public List<Kasir> getAllKasir() {
        list = new ArrayList<>();
        
        try {
            
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM kasir");
            
            while (resultSet.next()) {                
                Kasir kasir = new Kasir();
                kasir.setId(resultSet.getString("id_kasir"));
                kasir.setUsername(resultSet.getString("nama_kasir"));
                kasir.setPass(resultSet.getString("pass_kasir"));
                kasir.setNo_telp(resultSet.getString("no_telp"));
                list.add(kasir);
            }
            
            statement.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public String getIdKasir() {
        String Id = "0";
        
        try {
        
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_kasir FROM kasir ORDER BY id_kasir DESC LIMIT 1");
            
            String hasil = "";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_kasir");
            }
            
            char[] hasil_sementara = hasil.toUpperCase().toCharArray();

            for (int i = 3; i < hasil_sementara.length; i++) {
                Id += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id);
            id_angka++;
            
            Id = "KSR" + id_angka;
            return Id;
            
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void insertKasir(Kasir kasir) {
    
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "INSERT INTO kasir VALUES(?, ?, ?, ?)");
             
            statement.setString(1, kasir.getId());
            statement.setString(2, kasir.getUsername());
            statement.setString(3, kasir.getPass());
            statement.setString(4, kasir.getNo_telp());
            
            statement.executeUpdate();
            
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    @Override
    public void delete(String id) {
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "DELETE FROM kasir WHERE id_kasir = ?");
            
            statement.setString(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Kasir kasir) {
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "UPDATE kasir SET nama_kasir = ?, pass_kasir = ?, no_telp = ? WHERE id_kasir = ?");
            
            statement.setString(1, kasir.getUsername());
            statement.setString(2, kasir.getPass());
            statement.setString(3, kasir.getNo_telp());
            statement.setString(4, kasir.getId());
            
            statement.executeUpdate();
            
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
