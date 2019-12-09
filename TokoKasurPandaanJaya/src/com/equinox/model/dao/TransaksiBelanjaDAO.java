/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.TransaksiBelanja;
import com.equinox.model.TransaksiPenjualan;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementTransaksiBelanja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author equinox
 */
public class TransaksiBelanjaDAO implements ImplementTransaksiBelanja {

    @Override
    public void insertTransaksiBelanja(TransaksiBelanja transaksiBelanja) {

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "INSERT INTO transaksi_belanja VALUES (?, sysdate(), ?, ?, ?, ?)");

            statement.setString(1, transaksiBelanja.getId_transaksi_belanja());
            statement.setString(2, transaksiBelanja.getId_suplier());
            statement.setString(3, transaksiBelanja.getId_admin());
            statement.setInt(4, transaksiBelanja.getJumlah_item());
            statement.setLong(5, transaksiBelanja.getHarga_total());
            
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            Logger.getLogger(TransaksiBelanja.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String getIdTransaksiBelanja() {
        String Id_transaksi_belanja = "0";

        try {

            Statement statement = ConnectionDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_belanja FROM transaksi_belanja ORDER BY id_belanja DESC LIMIT 1;");

            String hasil = "BL0";
            if (resultSet.next()) {
                hasil = resultSet.getString("id_belanja");
            }

            char[] hasil_sementara = hasil.toLowerCase().toCharArray();

            for (int i = 2; i < hasil_sementara.length; i++) {
                Id_transaksi_belanja += hasil_sementara[i];
            }

            int id_angka = Integer.parseInt(Id_transaksi_belanja);
            id_angka++;

            Id_transaksi_belanja = "BL" + id_angka;

        } catch (Exception e) {
            Logger.getLogger(TransaksiBelanja.class.getName()).log(Level.SEVERE, null, e);
        }
        return Id_transaksi_belanja;
    }

    @Override
    public TransaksiBelanja getTransaksiBelanja(String id) {
        TransaksiBelanja belanja = new TransaksiBelanja();

        try {

            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT * FROM transaksi_belanja WHERE id_belanja = ?");

            statement.setString(1, id);

            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultSet.previous();
                while (resultSet.next()) {
                    belanja.setId_transaksi_belanja(resultSet.getString("id_belanja"));
                    belanja.setId_suplier(resultSet.getString("id_suplier"));
                    belanja.setTanggal_transaksi(resultSet.getString("tanggal_transaksi"));
                    belanja.setJumlah_item(resultSet.getInt("jumlah_item(qty)"));
                    belanja.setHarga_total(resultSet.getLong("total_harga"));
                }
                return belanja;
            } else {
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(TransaksiPenjualan.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
