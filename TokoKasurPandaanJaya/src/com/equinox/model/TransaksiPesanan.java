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
public class TransaksiPesanan {

    private String id_transaksi, tanggal_transaksi, id_kasir, id_toko, status_pengantaran;
    private int jumlah_item;
    private long harga_total;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }

    public String getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(String id_kasir) {
        this.id_kasir = id_kasir;
    }

    public String getId_toko() {
        return id_toko;
    }

    public void setId_toko(String id_toko) {
        this.id_toko = id_toko;
    }

    public String getStatus_pengantaran() {
        return status_pengantaran;
    }

    public void setStatus_pengantaran(String status_pengantaran) {
        this.status_pengantaran = status_pengantaran;
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
}
