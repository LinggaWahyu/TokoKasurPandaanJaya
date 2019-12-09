/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.implement.ImplementBarang;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.Barang;
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
public class BarangDAO implements ImplementBarang {

    private List<String> listNamaBarang;
    private List<Barang> listBarang;

    @Override
    public List<String> getAllNamaBarang() {
        listNamaBarang = new ArrayList<>();
        
        try {
            
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nama_barang FROM barang");
            
            while (resultSet.next()) {
                listNamaBarang.add(resultSet.getString("nama_barang"));
            }
            
            statement.close();
            resultSet.close();
            return listNamaBarang;
        } catch (Exception e) {
            Logger.getLogger(BarangDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public long getHargaBarang(String barang) {
        long harga = 0;
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT harga FROM barang WHERE nama_barang = ?");
            
            statement.setString(1, barang);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                harga = resultSet.getLong("harga");
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            Logger.getLogger(BarangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return harga;
    }

    @Override
    public String getIdBarang(String nama_barang) {
        String id = "";
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT id_barang FROM barang WHERE nama_barang = ?");
            
            statement.setString(1, nama_barang);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getString("id_barang");
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            Logger.getLogger(BarangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }

    @Override
    public String getNamaBarang(String id) {
        String nama = "";
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT nama_barang FROM barang WHERE id_barang = ?");
            
            statement.setString(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nama = resultSet.getString("nama_barang");
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            Logger.getLogger(BarangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return nama;
    }

    @Override
    public List<Barang> getAllBarang() {
        listBarang = new ArrayList<>();
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT * FROM barang");
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {                
                Barang barang = new Barang();
                barang.setId_barang(resultSet.getString("id_barang"));
                barang.setNama_barang(resultSet.getString("nama_barang"));
                barang.setStok(resultSet.getDouble("stok"));
                barang.setHarga(resultSet.getInt("harga"));
                listBarang.add(barang);
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            Logger.getLogger(BarangDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listBarang;
    }

    @Override
    public String getIdBarang() {
        String Id = "0";
        
        try {
        
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_barang FROM barang ORDER BY id_barang DESC LIMIT 1");
            
            String hasil = "";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_barang");
            }
            
            char[] hasil_sementara = hasil.toUpperCase().toCharArray();

            for (int i = 3; i < hasil_sementara.length; i++) {
                Id += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id);
            id_angka++;
            
            Id = "BRG" + id_angka;
            return Id;
            
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void insertBarang(Barang barang) {
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "INSERT INTO barang VALUES (?, ?, ?, ?)");
            
            statement.setString(1, barang.getId_barang());
            statement.setString(2, barang.getNama_barang());
            statement.setDouble(3, barang.getStok());
            statement.setLong(4, barang.getHarga());
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void deleteBarang(String id) {
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "DELETE FROM barang WHERE id_barang = ?");
            
            statement.setString(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void updateBarang(Barang barang) {
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "UPDATE barang SET nama_barang = ?, stok = ?, harga = ? WHERE id_barang = ?");
            
            statement.setString(1, barang.getNama_barang());
            statement.setDouble(2, barang.getStok());
            statement.setLong(3, barang.getHarga());
            statement.setString(4, barang.getId_barang());
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(KasirDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
