/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author equinox
 */
public class TransaksiPenjualan {
    
    private String id_penjualan, tanggal_transaksi, id_kasir, alamat_pengantaran;
    private int jumlah_item;
    private long harga_total;

    public String getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(String id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public int getJumlah_item() {
        return jumlah_item;
    }

    public void setJumlah_item(int jumlah_item) {
        this.jumlah_item = jumlah_item;
    }

    public long getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(long harga_total) {
        this.harga_total = harga_total;
    }

    public String getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(String id_kasir) {
        this.id_kasir = id_kasir;
    }

    public String getAlamat_pengantaran() {
        return alamat_pengantaran;
    }

    public void setAlamat_pengantaran(String alamat_pengantaran) {
        this.alamat_pengantaran = alamat_pengantaran;
    }
}
