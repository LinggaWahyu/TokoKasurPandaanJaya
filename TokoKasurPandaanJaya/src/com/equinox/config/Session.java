/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.config;

import com.equinox.model.Barang;
import com.equinox.model.DetailTransaksi;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author equinox
 */
public class Session {
    
    public static List<DetailTransaksi> listBarang;
    private static String tanggal_transaksi, nama_kasir, nama_admin, nama_toko, statusLoginAdmin, statusLoginKasir, statusLoginToko;

    public static String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public static void setTanggal_transaksi() {
        GregorianCalendar date = new GregorianCalendar();
        
        String[] namaHari = {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum'at", "Sabtu"};
        
        int hari = date.get(Calendar.DAY_OF_WEEK);
        String tanggal = Integer.toString(date.get(Calendar.DAY_OF_MONTH));
        String bulan = Integer.toString(date.get(Calendar.MONTH) + 1);
        String tahun = Integer.toString(date.get(Calendar.YEAR));
        
        Session.tanggal_transaksi = namaHari[hari-1] + ", " + tanggal + "-" + bulan + "-" + tahun;
    }

    public static List<DetailTransaksi> getListBarang() {
        return listBarang;
    }

    public static void setListBarang() {
        Session.listBarang = new ArrayList<>();
    }

    public static String getNama_kasir() {
        return nama_kasir;
    }

    public static void setNama_kasir(String nama_kasir) {
        Session.nama_kasir = nama_kasir;
    }

    public static String getNama_admin() {
        return nama_admin;
    }

    public static void setNama_admin(String nama_admin) {
        Session.nama_admin = nama_admin;
    }

    public static String getStatusLoginAdmin() {
        return statusLoginAdmin;
    }

    public static void setStatusLoginAdmin(String statusLogin) {
        Session.statusLoginAdmin = statusLogin;
    }

    public static String getStatusLoginKasir() {
        return statusLoginKasir;
    }

    public static void setStatusLoginKasir(String statusLoginKasir) {
        Session.statusLoginKasir = statusLoginKasir;
    }

    public static String getNama_toko() {
        return nama_toko;
    }

    public static void setNama_toko(String nama_toko) {
        Session.nama_toko = nama_toko;
    }

    public static String getStatusLoginToko() {
        return statusLoginToko;
    }

    public static void setStatusLoginToko(String statusLoginToko) {
        Session.statusLoginToko = statusLoginToko;
    }
}