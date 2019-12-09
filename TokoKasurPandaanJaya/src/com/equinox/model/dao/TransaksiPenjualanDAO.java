/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.DetailTransaksi;
import com.equinox.model.TransaksiPenjualan;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementTransaksiPenjualan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * '
 *
 * @author equinox
 */
public class TransaksiPenjualanDAO implements ImplementTransaksiPenjualan {

    @Override
    public void insertTransaksiPenjualan(TransaksiPenjualan transaksiPenjualan) {

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "INSERT INTO transaksi_penjualan VALUES (?, ?, sysdate(), ?, ?, ?)");

            statement.setString(1, transaksiPenjualan.getId_penjualan());
            statement.setString(2, transaksiPenjualan.getId_kasir());
            statement.setInt(3, transaksiPenjualan.getJumlah_item());
            statement.setString(4, transaksiPenjualan.getAlamat_pengantaran());
            statement.setLong(5, transaksiPenjualan.getHarga_total());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String getIdTransaksiPenjualan() {
        String Id_transaksi_penjualan = "0";

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_transaksi_penjualan FROM transaksi_penjualan ORDER BY id_transaksi_penjualan DESC LIMIT 1;");

            String hasil = "";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_transaksi_penjualan");
            }

            char[] hasil_sementara = hasil.toLowerCase().toCharArray();

            for (int i = 2; i < hasil_sementara.length; i++) {
                Id_transaksi_penjualan += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id_transaksi_penjualan);
            id_angka++;

            Id_transaksi_penjualan = "JL" + id_angka;

        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
        }
        return Id_transaksi_penjualan;
    }

    @Override
    public TransaksiPenjualan getTransaksiPenjualan(String id) {
        TransaksiPenjualan penjualan = new TransaksiPenjualan();

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT * FROM transaksi_penjualan WHERE id_transaksi_penjualan = ?");

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.previous();
                while (resultSet.next()) {
                    penjualan.setId_penjualan(resultSet.getString("id_transaksi_penjualan"));
                    penjualan.setId_kasir(resultSet.getString("id_kasir"));
                    penjualan.setTanggal_transaksi(resultSet.getString("tanggal_transaksi"));
                    penjualan.setJumlah_item(resultSet.getInt("jumlah_item(qty)"));
                    penjualan.setHarga_total(resultSet.getLong("harga_total"));
                }
                return penjualan;
            } else {
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
