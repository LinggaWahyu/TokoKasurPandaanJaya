/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.TransaksiBelanja;
import com.equinox.model.dao.AdminDAO;
import com.equinox.model.dao.BarangDAO;
import com.equinox.model.dao.DetailTransaksiDAO;
import com.equinox.model.dao.SupplierDAO;
import com.equinox.model.dao.TransaksiBelanjaDAO;
import com.equinox.model.implement.ImplementAdmin;
import com.equinox.model.implement.ImplementBarang;
import com.equinox.model.implement.ImplementDetailTransaksi;
import com.equinox.model.implement.ImplementSupplier;
import com.equinox.model.implement.ImplementTransaksiBelanja;
import com.equinox.model.tabel.TabelModelDetailTransaksi;
import com.equinox.view.BelanjaForm;
import com.equinox.view.FormAdmin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class ControllerBelanja extends PopUp {

    private final BelanjaForm belanja;
    private final ImplementBarang implementBarang;
    private final ImplementDetailTransaksi implementDetailTransaksi;
    private final ImplementTransaksiBelanja implementTransaksiBelanja;
    private final ImplementSupplier implementSupplier;
    private final ImplementAdmin implementAdmin;

    public ControllerBelanja(BelanjaForm belanjaForm) {
        this.belanja = belanjaForm;
        implementBarang = new BarangDAO();
        implementDetailTransaksi = new DetailTransaksiDAO();
        implementTransaksiBelanja = new TransaksiBelanjaDAO();
        implementSupplier = new SupplierDAO();
        implementAdmin = new AdminDAO();
        Session.setListBarang();
    }

    public void tampilanAwal() {
        belanja.getLb_kasir().setText("Hi, " + Session.getNama_admin() + " ^_^ ");
        Session.setTanggal_transaksi();
        belanja.getLb_date().setText(Session.getTanggal_transaksi());
        belanja.getTf_transaksi().setText(implementTransaksiBelanja.getIdTransaksiBelanja());
        setKomponen("early");
        getSupplier();
        getBarang();
        isiTabel();
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                belanja.getBtn_tambah().setEnabled(true);
                belanja.getBtn_edit().setEnabled(false);
                belanja.getBtn_hapus().setEnabled(false);
                belanja.getBtn_reset().setEnabled(true);
                belanja.getTf_transaksi().setEnabled(false);
                belanja.getTf_jumlahharga().setEnabled(false);
                belanja.getTf_totalharga().setEnabled(false);
                break;
            case "clicked":
                belanja.getBtn_tambah().setEnabled(false);
                belanja.getBtn_edit().setEnabled(true);
                belanja.getBtn_hapus().setEnabled(true);
                belanja.getBtn_reset().setEnabled(true);
                break;
            case "deleted":
                belanja.getBtn_tambah().setEnabled(true);
                belanja.getBtn_edit().setEnabled(false);
                belanja.getBtn_hapus().setEnabled(false);
                belanja.getBtn_reset().setEnabled(true);
                break;
            case "added":
                belanja.getBtn_tambah().setEnabled(true);
                belanja.getBtn_edit().setEnabled(false);
                belanja.getBtn_hapus().setEnabled(false);
                belanja.getBtn_reset().setEnabled(true);
                break;
            case "edited":
                belanja.getBtn_tambah().setEnabled(true);
                belanja.getBtn_edit().setEnabled(false);
                belanja.getBtn_hapus().setEnabled(false);
                belanja.getBtn_reset().setEnabled(true);
                break;
            case "reseted":
                belanja.getBtn_tambah().setEnabled(true);
                belanja.getBtn_edit().setEnabled(false);
                belanja.getBtn_hapus().setEnabled(false);
                belanja.getBtn_reset().setEnabled(true);
                break;
        }
    }

    public void getSupplier() {
        List<String> listNamaSupplier = new ArrayList<String>();

        for (int i = 0; i < implementSupplier.getAllSupplier().size(); i++) {
            listNamaSupplier.add(implementSupplier.getAllSupplier().get(i).getNama());
        }

        String[] arraySupplier = listNamaSupplier.toArray(new String[listNamaSupplier.size()]);
        DefaultComboBoxModel namaSupplier = new DefaultComboBoxModel(arraySupplier);
        belanja.getCbx_supplier().setModel(namaSupplier);
    }

    public void tambah() {
        if (belanja.getTf_harga().getText().isEmpty() || belanja.getTf_jumlah().getText().isEmpty()) {
            popupWarning(belanja, "Data tidak boleh ada yang kosong !");
        } else {
            DetailTransaksi detailTransaksi = new DetailTransaksi();
            detailTransaksi.setId_transaksi(belanja.getTf_transaksi().getText());
            detailTransaksi.setId_barang(implementBarang.getIdBarang(belanja.getCbx_barang().getSelectedItem().toString()));
            detailTransaksi.setJumlah(Double.valueOf(belanja.getTf_jumlah().getText()));
            detailTransaksi.setJumlah_harga(Long.valueOf(belanja.getTf_jumlahharga().getText()));

            Session.listBarang.add(detailTransaksi);
        }
    }
    
    public void getBarang() {
        List<String> barang = implementBarang.getAllNamaBarang();
        String[] arraybarang = barang.toArray(new String[barang.size()]);
        DefaultComboBoxModel barang1 = new DefaultComboBoxModel(arraybarang);
        belanja.getCbx_barang().setModel(barang1);
    }

    public void insertTransaksiBelanja() {

        TransaksiBelanja transaksiBelanja = new TransaksiBelanja();
        transaksiBelanja.setId_transaksi_belanja(belanja.getTf_transaksi().getText());
        transaksiBelanja.setId_suplier(implementSupplier.getIdSupplier(belanja.getCbx_supplier().getSelectedItem().toString()));
        transaksiBelanja.setJumlah_item(belanja.getTb_belanja().getRowCount());
        transaksiBelanja.setId_admin(implementAdmin.getIdAdmin(Session.getNama_admin()));
        transaksiBelanja.setHarga_total(Long.valueOf(belanja.getTf_totalharga().getText()));

        implementTransaksiBelanja.insertTransaksiBelanja(transaksiBelanja);
    }

    public void insertDetailTransaksi() {
        implementDetailTransaksi.insert(Session.listBarang);
        implementDetailTransaksi.updateStokBelanja(Session.listBarang);
        JOptionPane.showMessageDialog(null, "Data Transaksi Penjualan Berhasil di Simpan !");
    }

    public void isiTabel() {
        belanja.getTb_belanja().setModel(new TabelModelDetailTransaksi(Session.listBarang));
    }

    public void getJumlahHarga() {
        Double getJumlah = 0.0;
        int getHarga = 0;

        if (!belanja.getTf_jumlah().getText().isEmpty()) {
            getJumlah = Double.valueOf(belanja.getTf_jumlah().getText());
        }

        if (!belanja.getTf_harga().getText().isEmpty()) {
            getHarga = Integer.valueOf(belanja.getTf_harga().getText());
        }

        long jumlah_harga = Math.round(getJumlah * getHarga);

        belanja.getTf_jumlahharga().setText(String.valueOf(jumlah_harga));
    }

    public void reset() {
        belanja.getTf_jumlah().setText(null);
        belanja.getTf_harga().setText(null);
        belanja.getTf_jumlahharga().setText(null);
    }

    public void getTotalHarga() {
        long harga_total = 0;

        int rowSize = belanja.getTb_belanja().getRowCount();
        for (int i = 0; i < rowSize; i++) {
            harga_total += Long.valueOf((long) belanja.getTb_belanja().getValueAt(i, 3));
        }
        belanja.getTf_totalharga().setText(String.valueOf(harga_total));
    }

    public void getDataField() {
        int row = belanja.getTb_belanja().getSelectedRow();

        if (row != -1) {

            belanja.getCbx_barang().setSelectedItem(implementBarang.getNamaBarang(Session.listBarang.get(row).getId_barang()));
            belanja.getTf_jumlah().setText(String.valueOf(Session.listBarang.get(row).getJumlah()));
            belanja.getTf_harga().setText(String.valueOf(implementBarang.getHargaBarang(implementBarang.getNamaBarang(Session.listBarang.get(row).getId_barang()))));
            belanja.getTf_jumlahharga().setText(String.valueOf(Session.listBarang.get(row).getJumlah_harga()));

        }
    }

    public void hapus() {
        if (belanja.getTf_harga().getText().isEmpty() || belanja.getTf_jumlah().getText().isEmpty()) {
            popupWarning(belanja, "Data tidak boleh ada yang kosong !");
        } else {
            if (confirmHapus(belanja) == JOptionPane.YES_OPTION) {
                int row = belanja.getTb_belanja().getSelectedRow();

                if (row != -1) {
                    Session.listBarang.remove(row);
                }
            }
        }
    }

    public void edit() {
        if (belanja.getTf_harga().getText().isEmpty() || belanja.getTf_jumlah().getText().isEmpty()) {
            popupWarning(belanja, "Data tidak boleh ada yang kosong !");
        } else {
            int row = belanja.getTb_belanja().getSelectedRow();

            if (row != -1) {

                DetailTransaksi detailTransaksi = new DetailTransaksi();
                detailTransaksi.setId_transaksi(belanja.getTf_transaksi().getText());
                detailTransaksi.setId_barang(implementBarang.getIdBarang(belanja.getCbx_barang().getSelectedItem().toString()));
                detailTransaksi.setJumlah(Double.valueOf(belanja.getTf_jumlah().getText()));
                detailTransaksi.setJumlah_harga(Long.valueOf(belanja.getTf_jumlahharga().getText()));

                Session.listBarang.set(row, detailTransaksi);
            }
            reset();
        }
    }

    public void kembali() {
        belanja.setVisible(false);
        new FormAdmin().setVisible(true);
    }

    public void simpan() {
        if (belanja.getTb_belanja().getRowCount() == 0) {
            popupWarning(belanja, "Keranjang tidak boleh kosong !");
        } else {
            insertTransaksiBelanja();
            insertDetailTransaksi();
        }
    }
}
