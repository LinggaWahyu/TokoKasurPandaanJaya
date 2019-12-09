/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.Supplier;
import com.equinox.model.dao.SupplierDAO;
import com.equinox.model.implement.ImplementSupplier;
import com.equinox.model.tabel.TabelModelSupplier;
import com.equinox.view.FormAdmin;
import com.equinox.view.MasterSuplierForm;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class ControllerSupplier extends PopUp {

    private final MasterSuplierForm supplier;
    private final ImplementSupplier implementSupplier;
    private List<Supplier> listSupplier;

    public ControllerSupplier(MasterSuplierForm supplier) {
        this.supplier = supplier;
        implementSupplier = new SupplierDAO();
        listSupplier = implementSupplier.getAllSupplier();
    }

    public void tampilanAwal() {
        supplier.getLb_admin().setText("Hi, " + Session.getNama_admin() + " ^_^ ");
        Session.setTanggal_transaksi();
        supplier.getLb_date().setText(Session.getTanggal_transaksi());
        supplier.getTf_idSupplier().setText(implementSupplier.getIdSupplier());
        isiTabel();
        setKomponen("early");
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                supplier.getTf_idSupplier().setEnabled(false);
                supplier.getBt_hapus().setEnabled(false);
                supplier.getBt_edit().setEnabled(false);
                break;
            case "added":
                supplier.getBt_tambah().setEnabled(true);
                supplier.getBt_edit().setEnabled(false);
                supplier.getBt_hapus().setEnabled(false);
                supplier.getBt_reset().setEnabled(true);
                break;
            case "deleted":
                supplier.getBt_tambah().setEnabled(true);
                supplier.getBt_edit().setEnabled(false);
                supplier.getBt_hapus().setEnabled(false);
                supplier.getBt_reset().setEnabled(true);
                break;
            case "edited":
                supplier.getBt_tambah().setEnabled(true);
                supplier.getBt_edit().setEnabled(false);
                supplier.getBt_hapus().setEnabled(false);
                supplier.getBt_reset().setEnabled(true);
                break;
            case "clicked":
                supplier.getBt_tambah().setEnabled(false);
                supplier.getBt_edit().setEnabled(true);
                supplier.getBt_hapus().setEnabled(true);
                supplier.getBt_reset().setEnabled(true);
                break;
            case "reseted":
                supplier.getBt_tambah().setEnabled(true);
                supplier.getBt_edit().setEnabled(false);
                supplier.getBt_hapus().setEnabled(false);
                supplier.getBt_reset().setEnabled(true);
                break;
        }
    }

    public void isiTabel() {
        listSupplier = implementSupplier.getAllSupplier();
        supplier.getTb_supplier().setModel(new TabelModelSupplier(listSupplier));
    }

    public void insert() {
        if (supplier.getTf_nama().getText().isEmpty() || supplier.getTf_alamat().getText().isEmpty() || supplier.getTf_notelp().getText().isEmpty()) {
            popupWarning(supplier, "Data tidak boleh ada yang kosong !");
        } else {
            Supplier supplier1 = new Supplier();
            supplier1.setId_supplier(supplier.getTf_idSupplier().getText());
            supplier1.setNama(supplier.getTf_nama().getText());
            supplier1.setAlamat(supplier.getTf_alamat().getText());
            supplier1.setNo_telp(supplier.getTf_notelp().getText());

            implementSupplier.insertSupplier(supplier1);
        }
    }

    public void reset() {
        supplier.getTf_nama().setText(null);
        supplier.getTf_alamat().setText(null);
        supplier.getTf_notelp().setText(null);
    }

    public void getDataField() {
        int row = supplier.getTb_supplier().getSelectedRow();

        if (row != -1) {

            supplier.getTf_idSupplier().setText(listSupplier.get(row).getId_supplier());
            supplier.getTf_nama().setText(listSupplier.get(row).getNama());
            supplier.getTf_alamat().setText(listSupplier.get(row).getAlamat());
            supplier.getTf_notelp().setText(listSupplier.get(row).getNo_telp());
        }
    }

    public void hapus() {

        if (supplier.getTf_nama().getText().isEmpty() || supplier.getTf_alamat().getText().isEmpty() || supplier.getTf_notelp().getText().isEmpty()) {
            popupWarning(supplier, "Data tidak boleh ada yang kosong !");
        } else {
            if (confirmHapus(supplier) == JOptionPane.YES_OPTION) {
                implementSupplier.delete(supplier.getTf_idSupplier().getText());
            }
        }
    }

    public void update() {
        if (supplier.getTf_nama().getText().isEmpty() || supplier.getTf_alamat().getText().isEmpty() || supplier.getTf_notelp().getText().isEmpty()) {
            popupWarning(supplier, "Data tidak boleh ada yang kosong !");
        } else {
            Supplier supplier1 = new Supplier();

            supplier1.setId_supplier(supplier.getTf_idSupplier().getText());
            supplier1.setNama(supplier.getTf_nama().getText());
            supplier1.setAlamat(supplier.getTf_alamat().getText());
            supplier1.setNo_telp(supplier.getTf_notelp().getText());

            implementSupplier.update(supplier1);
        }
    }

    public void kembali() {
        supplier.setVisible(false);
        new FormAdmin().setVisible(true);
    }
}
