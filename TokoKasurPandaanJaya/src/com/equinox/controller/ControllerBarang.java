/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.Barang;
import com.equinox.model.dao.AdminDAO;
import com.equinox.model.dao.BarangDAO;
import com.equinox.model.implement.ImplementAdmin;
import com.equinox.model.implement.ImplementBarang;
import com.equinox.model.tabel.TabelModelBarang;
import com.equinox.view.FormAdmin;
import com.equinox.view.MasterBarangForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author equinox
 */
public class ControllerBarang extends PopUp {

    private final ImplementAdmin implementAdmin;
    private final ImplementBarang implementBarang;
    private final MasterBarangForm masterBS;
    private List<Barang> listBarang;

    public ControllerBarang(MasterBarangForm masterBS) {
        this.masterBS = masterBS;
        implementAdmin = new AdminDAO();
        implementBarang = new BarangDAO();
        listBarang = implementBarang.getAllBarang();
    }

    public void tampilanAwal() {
        masterBS.getLb_admin().setText("Hi, " + Session.getNama_admin() + " ^_^ ");
        setKomponen("early");
        Session.setTanggal_transaksi();
        masterBS.getLb_date().setText(Session.getTanggal_transaksi());
        masterBS.getTf_id().setText(implementBarang.getIdBarang());
        isiTabel();
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                masterBS.getTf_id().setEnabled(false);
                masterBS.getBt_edit().setEnabled(false);
                break;
            case "added":
                masterBS.getBt_tambah().setEnabled(true);
                masterBS.getBt_edit().setEnabled(false);
                masterBS.getBt_reset().setEnabled(true);
                break;
            case "deleted":
                masterBS.getBt_tambah().setEnabled(true);
                masterBS.getBt_edit().setEnabled(false);
                masterBS.getBt_reset().setEnabled(true);
                break;
            case "edited":
                masterBS.getBt_tambah().setEnabled(true);
                masterBS.getBt_edit().setEnabled(false);
                masterBS.getBt_reset().setEnabled(true);
                break;
            case "clicked":
                masterBS.getBt_tambah().setEnabled(false);
                masterBS.getBt_edit().setEnabled(true);
                masterBS.getBt_reset().setEnabled(true);
                break;
            case "reseted":
                masterBS.getBt_tambah().setEnabled(true);
                masterBS.getBt_edit().setEnabled(false);
                masterBS.getBt_reset().setEnabled(true);
                break;
        }
    }

    public void isiTabel() {
        listBarang = implementBarang.getAllBarang();
        masterBS.getTb_barang().setModel(new TabelModelBarang(listBarang));
    }

    public void insertBarang() {
        if (masterBS.getTf_nama().getText().isEmpty() || masterBS.getTf_stok().getText().isEmpty() || masterBS.getTf_harga().getText().isEmpty()) {
            popupWarning(masterBS, "Data tidak boleh ada yang kosong !");
        } else {
            Barang barang = new Barang();
            barang.setId_barang(masterBS.getTf_id().getText());
            barang.setNama_barang(masterBS.getTf_nama().getText());
            barang.setStok(Double.valueOf(masterBS.getTf_stok().getText()));
            barang.setHarga(Integer.parseInt(masterBS.getTf_harga().getText()));
            implementBarang.insertBarang(barang);
        }
    }

    public void reset() {
        masterBS.getTf_nama().setText(null);
        masterBS.getTf_stok().setText(null);
        masterBS.getTf_harga().setText(null);
    }

    public void getDataField() {
        int row = masterBS.getTb_barang().getSelectedRow();

        if (row != -1) {

            masterBS.getTf_id().setText(listBarang.get(row).getId_barang());
            masterBS.getTf_nama().setText(listBarang.get(row).getNama_barang());
            masterBS.getTf_stok().setText(String.valueOf(listBarang.get(row).getStok()));
            masterBS.getTf_harga().setText(String.valueOf(listBarang.get(row).getHarga()));
        }
    }

    public void hapus() {
        if (masterBS.getTf_nama().getText().isEmpty() || masterBS.getTf_harga().getText().isEmpty() || masterBS.getTf_stok().getText().isEmpty()) {
            popupWarning(masterBS, "Data tidak boleh ada yang kosong !");
        } else {
            if (confirmHapus(masterBS) == JOptionPane.YES_OPTION) {
                implementBarang.deleteBarang(masterBS.getTf_id().getText());
            }
        }
    }

    public void update() {
        if (masterBS.getTf_nama().getText().isEmpty() || masterBS.getTf_harga().getText().isEmpty() || masterBS.getTf_stok().getText().isEmpty()) {
            popupWarning(masterBS, "Data tidak boleh ada yang kosong !");
        } else {
            Barang barang = new Barang();
            barang.setId_barang(masterBS.getTf_id().getText());
            barang.setNama_barang(masterBS.getTf_nama().getText());
            barang.setStok(Double.valueOf(masterBS.getTf_stok().getText()));
            barang.setHarga(Integer.parseInt(masterBS.getTf_harga().getText()));

            implementBarang.updateBarang(barang);
        }
    }

    public void kembali() {
        masterBS.setVisible(false);
        new FormAdmin().setVisible(true);
    }
}
