/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.Supplier;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementSupplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author equinox
 */
public class SupplierDAO implements ImplementSupplier {

    private List<Supplier> list;

    @Override
    public List<Supplier> getAllSupplier() {
        list = new ArrayList<Supplier>();

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM suplier");

            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId_supplier(resultSet.getString("id_suplier"));
                supplier.setNama(resultSet.getString("nama_suplier"));
                supplier.setAlamat(resultSet.getString("alamat_suplier"));
                supplier.setNo_telp(resultSet.getString("no_telp"));
                list.add(supplier);
            }

            statement.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void insertSupplier(Supplier supplier) {

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "INSERT INTO suplier VALUES (?, ?, ?, ?)");

            statement.setString(1, supplier.getId_supplier());
            statement.setString(2, supplier.getNama());
            statement.setString(3, supplier.getAlamat());
            statement.setString(4, supplier.getNo_telp());

            statement.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String getIdSupplier() {
        String Id = "0";
        
        try {
        
            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_suplier FROM suplier ORDER BY id_suplier DESC LIMIT 1");
            
            String hasil = "";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_suplier");
            }
            
            char[] hasil_sementara = hasil.toUpperCase().toCharArray();

            for (int i = 2; i < hasil_sementara.length; i++) {
                Id += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id);
            id_angka++;
            
            Id = "SP" + id_angka;
            return Id;
            
        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void delete(String id) {
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "DELETE FROM suplier WHERE id_suplier = ?");
            
            statement.setString(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Supplier supplier) {
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "UPDATE suplier SET nama_suplier = ?, alamat_suplier = ?, no_telp = ? WHERE id_suplier = ?");
            
            statement.setString(1, supplier.getNama());
            statement.setString(2, supplier.getAlamat());
            statement.setString(3, supplier.getNo_telp());
            statement.setString(4, supplier.getId_supplier());
            
            statement.executeUpdate();
            
            statement.close();
        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String getIdSupplier(String nama) {
        String Id = "";
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT id_suplier FROM suplier WHERE nama_suplier = ?");
            
            statement.setString(1, nama);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                Id = resultSet.getString("id_suplier");
            }
            
            statement.close();
            resultSet.close();
            
            return Id;
        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
            return Id;
        }
    }
}
