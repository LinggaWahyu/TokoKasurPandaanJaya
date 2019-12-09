/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.TransaksiPenjualan;
import com.equinox.model.dao.BarangDAO;
import com.equinox.model.dao.DetailTransaksiDAO;
import com.equinox.model.dao.KasirDAO;
import com.equinox.model.dao.TransaksiPenjualanDAO;
import com.equinox.model.implement.ImplementKasir;
import com.equinox.model.implement.ImplementBarang;
import com.equinox.model.implement.ImplementDetailTransaksi;
import com.equinox.model.implement.ImplementTransaksiPenjualan;
import com.equinox.model.tabel.TabelModelDetailTransaksi;
import com.equinox.view.Form;
import com.equinox.view.FormKasir;
import com.equinox.view.LoginForm;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class ControllerKasir extends PopUp {

    private final FormKasir kasir;
    private final ImplementKasir implementKasir;
    private final ImplementBarang implementBarang;
    private final ImplementDetailTransaksi implementDetailTransaksi;
    private final ImplementTransaksiPenjualan implementTransaksiPenjualan;

    public ControllerKasir(FormKasir kasir) {
        this.kasir = kasir;
        implementBarang = new BarangDAO();
        implementKasir = new KasirDAO();
        implementDetailTransaksi = new DetailTransaksiDAO();
        implementTransaksiPenjualan = new TransaksiPenjualanDAO();
        Session.setListBarang();
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                kasir.getBtn_tambah().setEnabled(true);
                kasir.getBtn_edit().setEnabled(false);
                kasir.getBtn_hapus().setEnabled(false);
                kasir.getBtn_reset().setEnabled(true);
                kasir.getTf_transaksi().setEnabled(false);
                kasir.getTf_harga().setEnabled(false);
                kasir.getTf_jumlahharga().setEnabled(false);
                kasir.getTf_totalharga().setEnabled(false);
                break;
            case "clicked":
                kasir.getBtn_tambah().setEnabled(false);
                kasir.getBtn_edit().setEnabled(true);
                kasir.getBtn_hapus().setEnabled(true);
                kasir.getBtn_reset().setEnabled(true);
                break;
            case "deleted":
                kasir.getBtn_tambah().setEnabled(true);
                kasir.getBtn_edit().setEnabled(false);
                kasir.getBtn_hapus().setEnabled(false);
                kasir.getBtn_reset().setEnabled(true);
                break;
            case "added":
                kasir.getBtn_tambah().setEnabled(true);
                kasir.getBtn_edit().setEnabled(false);
                kasir.getBtn_hapus().setEnabled(false);
                kasir.getBtn_reset().setEnabled(true);
                break;
            case "edited":
                kasir.getBtn_tambah().setEnabled(true);
                kasir.getBtn_edit().setEnabled(false);
                kasir.getBtn_hapus().setEnabled(false);
                kasir.getBtn_reset().setEnabled(true);
                break;
            case "reseted":
                kasir.getBtn_tambah().setEnabled(true);
                kasir.getBtn_edit().setEnabled(false);
                kasir.getBtn_hapus().setEnabled(false);
                kasir.getBtn_reset().setEnabled(true);
                break;
            case "editHargaEnable":
                kasir.getTf_harga().setEnabled(true);
                break;
            case "editHargaDisable":
                kasir.getTf_harga().setEnabled(false);
                break;
        }
    }

    public void Logout() {
        kasir.setVisible(false);
        Session.setStatusLoginKasir("TIDAK AKTIF");
        new Form().setVisible(true);
    }

    public void tampilanKasir() {
        kasir.getLb_kasir().setText("Hi, " + Session.getNama_kasir() + " ^_^ ");
        Session.setTanggal_transaksi();
        kasir.getLb_date().setText(Session.getTanggal_transaksi());
        kasir.getTf_transaksi().setText(implementTransaksiPenjualan.getIdTransaksiPenjualan());
        getBarang();
        getHarga();
        isiTabel();
        setKomponen("early");
    }

    public void getBarang() {
        List<String> barang = implementBarang.getAllNamaBarang();
        String[] arraybarang = barang.toArray(new String[barang.size()]);
        DefaultComboBoxModel barang1 = new DefaultComboBoxModel(arraybarang);
        kasir.getCbx_barang().setModel(barang1);
    }

    public void getHarga() {
        String barang = kasir.getCbx_barang().getSelectedItem().toString();
        long harga = 0;
        harga = implementBarang.getHargaBarang(barang);
        if (harga != 0) {
            kasir.getTf_harga().setText(String.valueOf(harga));
            kasir.getBtn_tambah().setEnabled(true);
        } else {
            kasir.getBtn_tambah().setEnabled(false);
            kasir.getTf_harga().setText(String.valueOf(harga));
        }
    }

    public void getJumlahHarga() {
        Double jumlah = 0.0;
        String getJumlah = kasir.getTf_jumlah().getText();
        if (getJumlah.isEmpty()) {
            jumlah = jumlah;
        } else {
            jumlah = Double.valueOf(getJumlah);
        }
        double harga = Double.valueOf(kasir.getTf_harga().getText());
        long jumlah_harga = Math.round(jumlah * harga);

        kasir.getTf_jumlahharga().setText(String.valueOf(jumlah_harga));
    }

    public void tambah() {
        if (kasir.getTf_harga().getText().isEmpty() || kasir.getTf_jumlah().getText().isEmpty()) {
            popupWarning(kasir, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            DetailTransaksi detailTransaksi = new DetailTransaksi();
            detailTransaksi.setId_transaksi(kasir.getTf_transaksi().getText());
            detailTransaksi.setId_barang(implementBarang.getIdBarang(kasir.getCbx_barang().getSelectedItem().toString()));
            detailTransaksi.setJumlah(Double.valueOf(kasir.getTf_jumlah().getText()));
            detailTransaksi.setJumlah_harga(Long.valueOf(kasir.getTf_jumlahharga().getText()));

            Session.listBarang.add(detailTransaksi);
        }
    }

    public void isiTabel() {
        kasir.getTb_penjualan().setModel(new TabelModelDetailTransaksi(Session.listBarang));
    }

    public void getDataField() {
        int row = kasir.getTb_penjualan().getSelectedRow();

        if (row != -1) {
            
            kasir.getCbx_barang().setSelectedItem(implementBarang.getNamaBarang(Session.listBarang.get(row).getId_barang()));
            kasir.getTf_jumlah().setText(String.valueOf(Session.listBarang.get(row).getJumlah()));
            kasir.getTf_harga().setText(String.valueOf(implementBarang.getHargaBarang(implementBarang.getNamaBarang(Session.listBarang.get(row).getId_barang()))));
            kasir.getTf_jumlahharga().setText(String.valueOf(Session.listBarang.get(row).getJumlah_harga()));

        }
    }

    public void getTotalHarga() {
        long harga_total = 0;
        
        if (!kasir.getTf_alamat().getText().isEmpty()) {
            harga_total = 20000;
        }
        int rowSize = kasir.getTb_penjualan().getRowCount();
        for (int i = 0; i < rowSize; i++) {
            harga_total += Long.valueOf((long) kasir.getTb_penjualan().getValueAt(i, 3));
        }
        kasir.getTf_totalharga().setText(String.valueOf(harga_total));
    }

    public void reset() {
        kasir.getTf_jumlah().setText(null);
        kasir.getTf_jumlahharga().setText(null);
    }

    public void hapus() {
        if (kasir.getTf_harga().getText().isEmpty() || kasir.getTf_jumlah().getText().isEmpty()) {
            popupWarning(kasir, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            if (confirmHapus(kasir) == JOptionPane.YES_OPTION) {

                int row = kasir.getTb_penjualan().getSelectedRow();

                if (row != -1) {
                    Session.listBarang.remove(row);
                    reset();
                }
            }
        }
    }

    public void edit() {
        if (kasir.getTf_harga().getText().isEmpty() || kasir.getTf_jumlah().getText().isEmpty()) {
            popupWarning(kasir, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            int row = kasir.getTb_penjualan().getSelectedRow();

            if (row != -1) {

                DetailTransaksi detailTransaksi = new DetailTransaksi();
                detailTransaksi.setId_transaksi(kasir.getTf_transaksi().getText());
                detailTransaksi.setId_barang(implementBarang.getIdBarang(kasir.getCbx_barang().getSelectedItem().toString()));
                detailTransaksi.setJumlah(Double.valueOf(kasir.getTf_jumlah().getText()));
                detailTransaksi.setJumlah_harga(Long.valueOf(kasir.getTf_jumlahharga().getText()));

                Session.listBarang.set(row, detailTransaksi);
            }
            reset();
        }
    }

    public void insertDetailTransaksi() {
        if (kasir.getTb_penjualan().getRowCount() == 0) {
            popupWarning(kasir, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            implementDetailTransaksi.insert(Session.listBarang);
            implementDetailTransaksi.updateStokPenjualan(Session.listBarang);
            popupInformation(kasir, "Data berhasil di simpan !");
            new FormKasir().setVisible(true);
        }
    }

    public void insertTransaksiPenjualan() {
        TransaksiPenjualan transaksiPenjualan = new TransaksiPenjualan();
        transaksiPenjualan.setTanggal_transaksi(kasir.getLb_date().getText());
        transaksiPenjualan.setId_penjualan(kasir.getTf_transaksi().getText());
        transaksiPenjualan.setJumlah_item(kasir.getTb_penjualan().getRowCount());
        transaksiPenjualan.setHarga_total(Long.valueOf(kasir.getTf_totalharga().getText()));
        transaksiPenjualan.setAlamat_pengantaran(kasir.getTf_alamat().getText());
        transaksiPenjualan.setId_kasir(implementKasir.getIdKasir(Session.getNama_kasir()));

        implementTransaksiPenjualan.insertTransaksiPenjualan(transaksiPenjualan);
    }

    public void cekStok() {
        String barang = kasir.getCbx_barang().getSelectedItem().toString();
        String id = implementBarang.getIdBarang(barang);
        Double jumlah = 0.0;
        if (!kasir.getTf_jumlah().getText().isEmpty()) {
            jumlah = Double.valueOf(kasir.getTf_jumlah().getText());
        }
        if (implementDetailTransaksi.cekStok(id, jumlah) == false) {
            popupInformation(kasir, "Maaf ! Stok Tidak Mencukupi");
            kasir.getTf_jumlah().setText("0.0");
        } else {
            return;
        }
    }

    public void insert() {
        if (kasir.getTb_penjualan().getRowCount() == 0) {
            popupWarning(kasir, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            insertTransaksiPenjualan();
            insertDetailTransaksi();
        }
    }
}
