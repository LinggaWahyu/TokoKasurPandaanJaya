/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model;

/**
 *
 * @author equinox
 */
public class TransaksiBelanja {
    
    private String id_transaksi_belanja, id_suplier, tanggal_transaksi, id_admin;
    private int jumlah_item;
    private long harga_total;

    public String getId_transaksi_belanja() {
        return id_transaksi_belanja;
    }

    public void setId_transaksi_belanja(String id_transaksi_belanja) {
        this.id_transaksi_belanja = id_transaksi_belanja;
    }

    public String getId_suplier() {
        return id_suplier;
    }

    public void setId_suplier(String id_suplier) {
        this.id_suplier = id_suplier;
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

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }
}
