/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.Supplier;
import com.equinox.model.TokoLangganan;
import com.equinox.model.dao.TokoLanggananDAO;
import com.equinox.model.implement.ImplementTokoLangganan;
import com.equinox.model.tabel.TabelModelSupplier;
import com.equinox.model.tabel.TabelModelTokoLangganan;
import com.equinox.view.FormAdmin;
import com.equinox.view.MasterTokoLanggananForm;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class ControllerMasterTokoLangganan extends PopUp {
    
    private final MasterTokoLanggananForm tokoLangganan;
    private final ImplementTokoLangganan implementTokoLangganan;
    private List<TokoLangganan> list;
    
    public ControllerMasterTokoLangganan(MasterTokoLanggananForm tokoLanggananForm) {
        this.tokoLangganan = tokoLanggananForm;
        implementTokoLangganan = new TokoLanggananDAO();
        list = implementTokoLangganan.getAllTokoLangganan();
    }
    
    public void tampilanAwal() {
        tokoLangganan.getLb_admin().setText("Hi, " + Session.getNama_toko()+ " ^_^ ");
        Session.setTanggal_transaksi();
        tokoLangganan.getLb_date().setText(Session.getTanggal_transaksi());
        tokoLangganan.getTf_idToko().setText(implementTokoLangganan.getIdTokoLangganan());
        isiTabel();
        setKomponen("early");
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                tokoLangganan.getTf_idToko().setEnabled(false);
                tokoLangganan.getBt_hapus().setEnabled(false);
                tokoLangganan.getBt_edit().setEnabled(false);
                break;
            case "added":
                tokoLangganan.getBt_tambah().setEnabled(true);
                tokoLangganan.getBt_edit().setEnabled(false);
                tokoLangganan.getBt_hapus().setEnabled(false);
                tokoLangganan.getBt_reset().setEnabled(true);
                break;
            case "deleted":
                tokoLangganan.getBt_tambah().setEnabled(true);
                tokoLangganan.getBt_edit().setEnabled(false);
                tokoLangganan.getBt_hapus().setEnabled(false);
                tokoLangganan.getBt_reset().setEnabled(true);
                break;
            case "edited":
                tokoLangganan.getBt_tambah().setEnabled(true);
                tokoLangganan.getBt_edit().setEnabled(false);
                tokoLangganan.getBt_hapus().setEnabled(false);
                tokoLangganan.getBt_reset().setEnabled(true);
                break;
            case "clicked":
                tokoLangganan.getBt_tambah().setEnabled(false);
                tokoLangganan.getBt_edit().setEnabled(true);
                tokoLangganan.getBt_hapus().setEnabled(true);
                tokoLangganan.getBt_reset().setEnabled(true);
                break;
            case "reseted":
                tokoLangganan.getBt_tambah().setEnabled(true);
                tokoLangganan.getBt_edit().setEnabled(false);
                tokoLangganan.getBt_hapus().setEnabled(false);
                tokoLangganan.getBt_reset().setEnabled(true);
                break;
        }
    }

    public void isiTabel() {
        list = implementTokoLangganan.getAllTokoLangganan();
        tokoLangganan.getTb_toko().setModel(new TabelModelTokoLangganan(list));
    }

    public void insert() {
        if (tokoLangganan.getTf_nama().getText().isEmpty() || tokoLangganan.getTf_alamat().getText().isEmpty() || tokoLangganan.getTf_notelp().getText().isEmpty()) {
            popupWarning(tokoLangganan, "Data tidak boleh ada yang kosong !");
        } else {
            TokoLangganan tokoLangganan1 = new TokoLangganan();
            tokoLangganan1.setId_toko(tokoLangganan.getTf_idToko().getText());
            tokoLangganan1.setNama_toko(tokoLangganan.getTf_nama().getText());
            tokoLangganan1.setPassword_toko(tokoLangganan.getTf_password().getText());
            tokoLangganan1.setAlamat_toko(tokoLangganan.getTf_alamat().getText());
            tokoLangganan1.setNo_telp(tokoLangganan.getTf_notelp().getText());

            implementTokoLangganan.insertTokoLangganan(tokoLangganan1);
        }
    }

    public void reset() {
        tokoLangganan.getTf_nama().setText(null);
        tokoLangganan.getTf_alamat().setText(null);
        tokoLangganan.getTf_notelp().setText(null);
        tokoLangganan.getTf_password().setText(null);
    }

    public void getDataField() {
        int row = tokoLangganan.getTb_toko().getSelectedRow();

        if (row != -1) {

            tokoLangganan.getTf_idToko().setText(list.get(row).getId_toko());
            tokoLangganan.getTf_nama().setText(list.get(row).getNama_toko());
            tokoLangganan.getTf_password().setText(list.get(row).getPassword_toko());
            tokoLangganan.getTf_alamat().setText(list.get(row).getAlamat_toko());
            tokoLangganan.getTf_notelp().setText(list.get(row).getNo_telp());
        }
    }

    public void hapus() {

        if (tokoLangganan.getTf_nama().getText().isEmpty() || tokoLangganan.getTf_password().getText().isEmpty() || tokoLangganan.getTf_alamat().getText().isEmpty() || tokoLangganan.getTf_notelp().getText().isEmpty()) {
            popupWarning(tokoLangganan, "Data tidak boleh ada yang kosong !");
        } else {
            if (confirmHapus(tokoLangganan) == JOptionPane.YES_OPTION) {
                implementTokoLangganan.delete(tokoLangganan.getTf_idToko().getText());
            }
        }
    }

    public void update() {
        if (tokoLangganan.getTf_nama().getText().isEmpty() || tokoLangganan.getTf_alamat().getText().isEmpty() || tokoLangganan.getTf_notelp().getText().isEmpty()) {
            popupWarning(tokoLangganan, "Data tidak boleh ada yang kosong !");
        } else {
            TokoLangganan tokoLangganan1 = new TokoLangganan();
            tokoLangganan1.setId_toko(tokoLangganan.getTf_idToko().getText());
            tokoLangganan1.setNama_toko(tokoLangganan.getTf_nama().getText());
            tokoLangganan1.setPassword_toko(tokoLangganan.getTf_password().getText());
            tokoLangganan1.setAlamat_toko(tokoLangganan.getTf_alamat().getText());
            tokoLangganan1.setNo_telp(tokoLangganan.getTf_notelp().getText());

            implementTokoLangganan.update(tokoLangganan1);
        }
    }

    public void kembali() {
        tokoLangganan.setVisible(false);
        new FormAdmin().setVisible(true);
    }
}
