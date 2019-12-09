/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.Admin;
import com.equinox.model.dao.AdminDAO;
import com.equinox.model.implement.ImplementAdmin;
import com.equinox.view.BelanjaForm;
import com.equinox.view.Form;
import com.equinox.view.FormAdmin;
import com.equinox.view.LoginForm;
import com.equinox.view.MasterBarangForm;
import com.equinox.view.MasterKasirForm;
import com.equinox.view.MasterSuplierForm;
import com.equinox.view.RekapTransaksiForm;
import com.equinox.view.MasterTokoLanggananForm;
import javax.swing.JOptionPane;

/**
 *
 * @author equinox
 */
public class ControllerAdmin extends PopUp {

    private final FormAdmin admin;
    private final ImplementAdmin implementAdmin;

    public ControllerAdmin(FormAdmin admin) {
        this.admin = admin;
        implementAdmin = new AdminDAO();
    }

    public void Logout() {
        admin.setVisible(false);
        Session.setStatusLoginKasir("TIDAK AKTIF");
        new Form().setVisible(true);
    }

    public void tampilanAdmin() {
        admin.getLb_admin().setText("Hi, " + Session.getNama_admin() + " ^_^");
        Session.setTanggal_transaksi();
        admin.getLb_date().setText(Session.getTanggal_transaksi());
    }

    public void masterBarang() {
        admin.setVisible(false);
        new MasterBarangForm().setVisible(true);
    }

    public void masterSupplier() {
        admin.setVisible(false);
        new MasterSuplierForm().setVisible(true);
    }

    public void masterKasir() {
        admin.setVisible(false);
        new MasterKasirForm().setVisible(true);
    }

    public void transaksiBelanja() {
        admin.setVisible(false);
        new BelanjaForm().setVisible(true);
    }

    public void rekapTransaksi() {
        admin.setVisible(false);
        new RekapTransaksiForm().setVisible(true);
    }
    
    public void masterTokoPelanggan() {
        admin.setVisible(false);
        new MasterTokoLanggananForm().setVisible(true);
    }
}
