/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.implement.ImplementDetailTransaksi;
import com.equinox.model.implement.ImplementBarang;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.database.ConnectionDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class DetailTransaksiDAO implements ImplementDetailTransaksi {
    
    List<DetailTransaksi> list;

    @Override
    public void insert(List<DetailTransaksi> list) {

        for (int i = 0; i < list.size(); i++) {
            try {

                PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                        + "INSERT INTO detail_transaksi VALUES (null, ?, ?, ?, ?)");

                statement.setString(1, list.get(i).getId_transaksi());
                statement.setString(2, list.get(i).getId_barang());
                statement.setDouble(3, list.get(i).getJumlah());
                statement.setLong(4, list.get(i).getJumlah_harga());

                statement.executeUpdate();
                statement.close();

            } catch (Exception e) {
                Logger.getLogger(DetailTransaksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void updateStokPenjualan(List<DetailTransaksi> list) {
        
        for (int i = 0; i < list.size(); i++) {
            try {
                
                PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                        "UPDATE barang SET stok = stok - ? WHERE id_barang = ?");
                
                statement.setDouble(1, list.get(i).getJumlah());
                statement.setString(2, list.get(i).getId_barang());
                
                statement.executeUpdate();
                statement.close();
                
            } catch (Exception e) {
                Logger.getLogger(DetailTransaksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public boolean cekStok(String id, Double jumlah) {
        Double stok = null;

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT cekStok(?)");

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                stok = resultSet.getDouble("cekStok('" + id + "')") - jumlah;
            }
            
            statement.close();
            resultSet.close();

            if (stok >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(DetailTransaksi.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public void updateStokBelanja(List<DetailTransaksi> list) {
        
        for (int i = 0; i < list.size(); i++) {
            try {
                
                PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                        "UPDATE barang SET stok = stok + ? WHERE id_barang = ?");
                
                statement.setDouble(1, list.get(i).getJumlah());
                statement.setString(2, list.get(i).getId_barang());
                
                statement.executeUpdate();
                statement.close();
                
            } catch (Exception e) {
                Logger.getLogger(DetailTransaksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public List<DetailTransaksi> getDetailTransaksi(String id) {
        list = new ArrayList<>();
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT id_transaksi, id_barang, jumlah_barang, jumlah_harga FROM detail_transaksi WHERE id_transaksi = ?");
            
            statement.setString(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {                
                DetailTransaksi detailTransaksi = new DetailTransaksi();
                detailTransaksi.setId_transaksi(resultSet.getString("id_transaksi"));
                detailTransaksi.setId_barang(resultSet.getString("id_barang"));
                detailTransaksi.setJumlah(resultSet.getInt("jumlah_barang"));
                detailTransaksi.setJumlah_harga(resultSet.getLong("jumlah_harga"));
                list.add(detailTransaksi);
            }
            
            statement.close();
            return list;
        } catch (Exception e) {
            Logger.getLogger(DetailTransaksi.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void delete(String id_transaksi) {
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "DELETE FROM detail_transaksi WHERE id_transaksi = ?");
            
            statement.setString(1, id_transaksi);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(DetailTransaksi.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
