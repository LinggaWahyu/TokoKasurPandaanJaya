 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model;

import java.util.List;

/**
 *
 * @author equinox
 */
public class DetailTransaksi {
    
    private String id_transaksi, id_barang;
    private double jumlah;
    private long jumlah_harga;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public long getJumlah_harga() {
        return jumlah_harga;
    }

    public void setJumlah_harga(long jumlah_harga) {
        this.jumlah_harga = jumlah_harga;
    }
}
