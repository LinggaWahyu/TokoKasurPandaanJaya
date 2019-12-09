/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.dao;

import com.equinox.model.RekapTransaksi;
import com.equinox.model.database.ConnectionDatabase;
import com.equinox.model.implement.ImplementRekapTransaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author equinox
 */
public class RekapTransaksiDAO implements ImplementRekapTransaksi {

    private List<RekapTransaksi> list;

    @Override
    public List<RekapTransaksi> getAllTransaksi(int bulan) {
        list = new ArrayList<>();

        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT id_transaksi_penjualan, tanggal_transaksi, `jumlah_item(qty)`, harga_total FROM transaksi_penjualan WHERE MONTH(tanggal_transaksi) = ?");
            statement.setInt(1, bulan);
            ResultSet resultSet = statement.executeQuery();

            PreparedStatement statement1 = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT id_belanja, tanggal_transaksi, `jumlah_item(qty)`, total_harga FROM transaksi_belanja WHERE MONTH(tanggal_transaksi) = ?");
            statement1.setInt(1, bulan);
            ResultSet resultSet1 = statement1.executeQuery();

            PreparedStatement statement2 = ConnectionDatabase.getConnection().prepareStatement(""
                    + "SELECT id_transaksi_suplai, tanggal_transaksi, `jumlah_item(qty)`, harga_total FROM transaksi_suplai WHERE MONTH(tanggal_transaksi) = ? AND status_pengantaran = 'SUDAH'");
            statement2.setInt(1, bulan);
            ResultSet resultSet2 = statement2.executeQuery();

            while (resultSet.next()) {
                RekapTransaksi rekapTransaksi = new RekapTransaksi();
                rekapTransaksi.setId_transaksi(resultSet.getString("id_transaksi_penjualan"));
                rekapTransaksi.setTanggal_transaksi(resultSet.getString("tanggal_transaksi"));
                rekapTransaksi.setQty(resultSet.getInt("jumlah_item(qty)"));
                rekapTransaksi.setDebit(resultSet.getLong("harga_total"));
                rekapTransaksi.setKredit(0);
                list.add(rekapTransaksi);
            }

            while (resultSet1.next()) {
                RekapTransaksi rekapTransaksi = new RekapTransaksi();
                rekapTransaksi.setId_transaksi(resultSet1.getString("id_belanja"));
                rekapTransaksi.setTanggal_transaksi(resultSet1.getString("tanggal_transaksi"));
                rekapTransaksi.setQty(resultSet1.getInt("jumlah_item(qty)"));
                rekapTransaksi.setKredit(resultSet1.getLong("total_harga"));
                rekapTransaksi.setDebit(0);
                list.add(rekapTransaksi);
            }

            while (resultSet2.next()) {
                RekapTransaksi rekapTransaksi = new RekapTransaksi();
                rekapTransaksi.setId_transaksi(resultSet2.getString("id_transaksi_suplai"));
                rekapTransaksi.setTanggal_transaksi(resultSet2.getString("tanggal_transaksi"));
                rekapTransaksi.setQty(resultSet2.getInt("jumlah_item(qty)"));
                rekapTransaksi.setDebit(resultSet2.getLong("harga_total"));
                rekapTransaksi.setKredit(0);
                list.add(rekapTransaksi);
            }

            statement.close();
            statement1.close();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteTransaksi(String id) {
        try {
            PreparedStatement statement = ConnectionDatabase.getConnection().prepareStatement(""
                    + "DELETE FROM transaksi_belanja WHERE id_belanja = ?");

            statement.setString(1, id);

            int resultSet = statement.executeUpdate();

            if (resultSet == 0) {
                PreparedStatement statement1 = ConnectionDatabase.getConnection().prepareStatement(""
                        + "DELETE FROM transaksi_penjualan WHERE id_transaksi_penjualan = ?");

                statement1.setString(1, id);

                int resultSet1 = statement1.executeUpdate();

                if (resultSet1 == 0) {
                    PreparedStatement statement2 = ConnectionDatabase.getConnection().prepareStatement("" + 
                            "DELETE FORM transaksi_suplai WHERE id_transaksi_suplai = ?");
                    
                    statement2.setString(1, id);
                    
                    int resultSet2 = statement2.executeUpdate();
                    
                    if (resultSet2 == 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
