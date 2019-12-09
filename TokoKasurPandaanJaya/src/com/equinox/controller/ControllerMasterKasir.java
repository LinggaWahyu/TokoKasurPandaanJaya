/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.Kasir;
import com.equinox.model.dao.KasirDAO;
import com.equinox.model.dao.SupplierDAO;
import com.equinox.model.implement.ImplementKasir;
import com.equinox.model.implement.ImplementSupplier;
import com.equinox.model.tabel.TabelModelKasir;
import com.equinox.model.tabel.TabelModelSupplier;
import com.equinox.view.FormAdmin;
import com.equinox.view.MasterKasirForm;
import com.equinox.view.MasterSuplierForm;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class ControllerMasterKasir extends PopUp {

    private final MasterKasirForm masterKasir;
    private final ImplementKasir implementMasterKasir;
    private List<Kasir> listKasir;

    public ControllerMasterKasir(MasterKasirForm masterKasir) {
        this.masterKasir = masterKasir;
        implementMasterKasir = new KasirDAO();
        listKasir = implementMasterKasir.getAllKasir();
    }

    public void tampilanAwal() {
        masterKasir.getLb_admin().setText("Hi, " + Session.getNama_admin() + " ^_^ ");
        Session.setTanggal_transaksi();
        masterKasir.getLb_date().setText(Session.getTanggal_transaksi());
        masterKasir.getTf_idKasir().setText(implementMasterKasir.getIdKasir());
        isiTabel();
        setKomponen("early");
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                masterKasir.getTf_idKasir().setEnabled(false);
                masterKasir.getBt_hapus().setEnabled(false);
                masterKasir.getBt_edit().setEnabled(false);
                break;
            case "added":
                masterKasir.getBt_tambah().setEnabled(true);
                masterKasir.getBt_edit().setEnabled(false);
                masterKasir.getBt_hapus().setEnabled(false);
                masterKasir.getBt_reset().setEnabled(true);
                break;
            case "deleted":
                masterKasir.getBt_tambah().setEnabled(true);
                masterKasir.getBt_edit().setEnabled(false);
                masterKasir.getBt_hapus().setEnabled(false);
                masterKasir.getBt_reset().setEnabled(true);
                break;
            case "edited":
                masterKasir.getBt_tambah().setEnabled(true);
                masterKasir.getBt_edit().setEnabled(false);
                masterKasir.getBt_hapus().setEnabled(false);
                masterKasir.getBt_reset().setEnabled(true);
                break;
            case "clicked":
                masterKasir.getBt_tambah().setEnabled(false);
                masterKasir.getBt_edit().setEnabled(true);
                masterKasir.getBt_hapus().setEnabled(true);
                masterKasir.getBt_reset().setEnabled(true);
                break;
            case "reseted":
                masterKasir.getBt_tambah().setEnabled(true);
                masterKasir.getBt_edit().setEnabled(false);
                masterKasir.getBt_hapus().setEnabled(false);
                masterKasir.getBt_reset().setEnabled(true);
                break;
        }
    }

    public void isiTabel() {
        listKasir = implementMasterKasir.getAllKasir();
        masterKasir.getTb_kasir().setModel(new TabelModelKasir(listKasir));
    }

    public void insert() {
        if (masterKasir.getTf_username().getText().isEmpty() || masterKasir.getTf_pass().getText().isEmpty() || masterKasir.getTf_notelp().getText().isEmpty()) {
            popupWarning(masterKasir, "Data tidak boleh ada yang kosong !");
        } else {
            Kasir kasir = new Kasir();
            kasir.setId(masterKasir.getTf_idKasir().getText());
            kasir.setUsername(masterKasir.getTf_username().getText());
            kasir.setPass(masterKasir.getTf_pass().getText());
            kasir.setNo_telp(masterKasir.getTf_notelp().getText());

            implementMasterKasir.insertKasir(kasir);
        }
    }

    public void reset() {
        masterKasir.getTf_username().setText(null);
        masterKasir.getTf_pass().setText(null);
        masterKasir.getTf_notelp().setText(null);
    }

    public void getDataField() {
        int row = masterKasir.getTb_kasir().getSelectedRow();

        if (row != -1) {

            masterKasir.getTf_idKasir().setText(listKasir.get(row).getId());
            masterKasir.getTf_username().setText(listKasir.get(row).getUsername());
            masterKasir.getTf_pass().setText(listKasir.get(row).getPass());
            masterKasir.getTf_notelp().setText(listKasir.get(row).getNo_telp());
        }
    }

    public void hapus() {
        if (masterKasir.getTf_username().getText().isEmpty() || masterKasir.getTf_pass().getText().isEmpty() || masterKasir.getTf_notelp().getText().isEmpty()) {
            popupWarning(masterKasir, "Data tidak boleh ada yang kosong !");
        } else {
            if (confirmHapus(masterKasir) == JOptionPane.YES_OPTION) {
                implementMasterKasir.delete(masterKasir.getTf_idKasir().getText());
            }
        }
    }

    public void update() {
        if (masterKasir.getTf_username().getText().isEmpty() || masterKasir.getTf_pass().getText().isEmpty() || masterKasir.getTf_notelp().getText().isEmpty()) {
            popupWarning(masterKasir, "Data tidak boleh ada yang kosong !");
        } else {
            Kasir kasir = new Kasir();
            kasir.setId(masterKasir.getTf_idKasir().getText());
            kasir.setUsername(masterKasir.getTf_username().getText());
            kasir.setPass(masterKasir.getTf_pass().getText());
            kasir.setNo_telp(masterKasir.getTf_notelp().getText());

            implementMasterKasir.update(kasir);
        }
    }

    public void kembali() {
        masterKasir.setVisible(false);
        new FormAdmin().setVisible(true);
    }
}
