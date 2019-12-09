/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.TokoLangganan;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementTokoLangganan;
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
public class TokoLanggananDAO implements ImplementTokoLangganan {

    private boolean status = false;
    private List<TokoLangganan> list;

    @Override
    public boolean loginTokoLangganan(String username, String password) {

        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT * FROM toko_langganan WHERE nama_toko = ? AND password_toko = ?");

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
    public List<TokoLangganan> getAllTokoLangganan() {
        list = new ArrayList<>();

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM toko_langganan");

            while (resultSet.next()) {
                TokoLangganan tokoLangganan = new TokoLangganan();
                tokoLangganan.setId_toko(resultSet.getString("id_toko"));
                tokoLangganan.setNama_toko(resultSet.getString("nama_toko"));
                tokoLangganan.setPassword_toko(resultSet.getString("password_toko"));
                tokoLangganan.setAlamat_toko(resultSet.getString("alamat_toko"));
                tokoLangganan.setNo_telp(resultSet.getString("no_telp"));

                list.add(tokoLangganan);
            }

            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String getIdTokoLangganan() {
        String Id = "0";

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_toko FROM toko_langganan ORDER BY id_toko DESC LIMIT 1");

            String hasil = "";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_toko");
            }

            char[] hasil_sementara = hasil.toUpperCase().toCharArray();

            for (int i = 2; i < hasil_sementara.length; i++) {
                Id += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id);
            id_angka++;

            Id = "TK" + id_angka;
            return Id;

        } catch (Exception e) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public void insertTokoLangganan(TokoLangganan tokoLangganan) {
        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "INSERT INTO toko_langganan VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, tokoLangganan.getId_toko());
            statement.setString(2, tokoLangganan.getNama_toko());
            statement.setString(3, tokoLangganan.getPassword_toko());
            statement.setString(4, tokoLangganan.getAlamat_toko());
            statement.setString(5, tokoLangganan.getNo_telp());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "DELETE FROM toko_langganan WHERE id_toko = ?");
            
            statement.setString(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TokoLangganan tokoLangganan1) {
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "UPDATE toko_langganan SET nama_toko = ?, password_toko = ?, alamat_toko = ?, no_telp = ? WHERE id_toko = ?");
            
            statement.setString(1, tokoLangganan1.getNama_toko());
            statement.setString(2, tokoLangganan1.getPassword_toko());
            statement.setString(3, tokoLangganan1.getAlamat_toko());
            statement.setString(4, tokoLangganan1.getNo_telp());
            statement.setString(5, tokoLangganan1.getId_toko());
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getIdTokoLangganan(String nama) {
        String id = "";
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT id_toko FROM toko_langganan WHERE nama_toko = ?");
            
            statement.setString(1, nama);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getString("id_toko");
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getNamaTokoLangganan(String id) {
        String nama = "";
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT nama_toko FROM toko_langganan WHERE id_toko = ?");
            
            statement.setString(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nama = resultSet.getString("nama_toko");
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nama;
    }
}
