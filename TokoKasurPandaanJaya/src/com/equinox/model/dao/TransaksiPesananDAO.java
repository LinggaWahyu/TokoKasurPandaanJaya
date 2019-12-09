/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.TransaksiPenjualan;
import com.equinox.model.TransaksiPesanan;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementTransaksiPesanan;
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
public class TransaksiPesananDAO implements ImplementTransaksiPesanan {

    private List<TransaksiPesanan> list;
    
    @Override
    public void insert(TransaksiPesanan transaksiPesanan) {

        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "INSERT INTO transaksi_suplai VALUES (?, sysdate(), null, ?, ?, ?, ?)");
            
            statement.setString(1, transaksiPesanan.getId_transaksi());
            statement.setString(2, transaksiPesanan.getId_toko());
            statement.setInt(3, transaksiPesanan.getJumlah_item());
            statement.setLong(4, transaksiPesanan.getHarga_total());
            statement.setString(5, transaksiPesanan.getStatus_pengantaran());
            
            statement.executeQuery();
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getIdTransaksiPesanan() {
        String Id_transaksi_pesanan = "0";

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_transaksi_suplai FROM transaksi_suplai ORDER BY id_transaksi_suplai DESC LIMIT 1;");

            String hasil = "";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_transaksi_suplai");
            }

            char[] hasil_sementara = hasil.toLowerCase().toCharArray();

            for (int i = 2; i < hasil_sementara.length; i++) {
                Id_transaksi_pesanan += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id_transaksi_pesanan);
            id_angka++;

            Id_transaksi_pesanan = "PS" + id_angka;

        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
        }
        return Id_transaksi_pesanan;
    }

    @Override
    public List<TransaksiPesanan> getAllPesanan(String id) {
        list = new ArrayList<>();
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT * FROM transaksi_suplai WHERE id_toko = ?");
            
            statement.setString(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {                
                TransaksiPesanan pesanan = new TransaksiPesanan();
                pesanan.setId_transaksi(resultSet.getString("id_transaksi_suplai"));
                pesanan.setTanggal_transaksi(resultSet.getString("tanggal_transaksi"));
                pesanan.setJumlah_item(resultSet.getInt("jumlah_item(qty)"));
                pesanan.setHarga_total(resultSet.getLong("harga_total"));
                pesanan.setStatus_pengantaran(resultSet.getString("status_pengantaran"));
                pesanan.setId_toko(id);
                list.add(pesanan);
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TransaksiPesanan> getAllPesanan() {
        list = new ArrayList<>();
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "SELECT * FROM transaksi_suplai");
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {                
                TransaksiPesanan pesanan = new TransaksiPesanan();
                pesanan.setId_transaksi(resultSet.getString("id_transaksi_suplai"));
                pesanan.setTanggal_transaksi(resultSet.getString("tanggal_transaksi"));
                pesanan.setJumlah_item(resultSet.getInt("jumlah_item(qty)"));
                pesanan.setHarga_total(resultSet.getLong("harga_total"));
                pesanan.setId_toko(resultSet.getString("id_toko"));
                pesanan.setStatus_pengantaran(resultSet.getString("status_pengantaran"));
                list.add(pesanan);
            }
            
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void confirmPesanan(String id, String kasir) {
        
        try {
            
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement("" + 
                    "UPDATE transaksi_suplai SET id_kasir = ?, status_pengantaran = 'SUDAH' WHERE id_transaksi_suplai = ?");
            
            statement.setString(1, kasir);
            statement.setString(2, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransaksiPesanan getTransaksiPesanan(String id) {
        TransaksiPesanan pesanan = new TransaksiPesanan();

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT * FROM transaksi_suplai WHERE id_transaksi_suplai = ?");

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.previous();
                while (resultSet.next()) {
                    pesanan.setId_transaksi(resultSet.getString("id_transaksi_suplai"));
                    pesanan.setId_kasir(resultSet.getString("id_kasir"));
                    pesanan.setTanggal_transaksi(resultSet.getString("tanggal_transaksi"));
                    pesanan.setJumlah_item(resultSet.getInt("jumlah_item(qty)"));
                    pesanan.setHarga_total(resultSet.getLong("harga_total"));
                }
                return pesanan;
            } else {
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
