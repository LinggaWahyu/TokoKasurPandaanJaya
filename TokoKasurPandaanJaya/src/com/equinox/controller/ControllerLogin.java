/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.dao.AdminDAO;
import com.equinox.model.dao.KasirDAO;
import com.equinox.model.dao.LogLoginDAO;
import com.equinox.model.dao.TokoLanggananDAO;
import com.equinox.model.implement.ImplementAdmin;
import com.equinox.model.implement.ImplementKasir;
import com.equinox.model.implement.ImplementLogLogin;
import com.equinox.model.implement.ImplementTokoLangganan;
import com.equinox.view.FormAdmin;
import com.equinox.view.FormKasir;
import com.equinox.view.FormTokoLangganan;
import com.equinox.view.LoginForm;

/**
 *
 * @author equinox
 */
public class ControllerLogin extends PopUp {

    private final LoginForm login;
    private final ImplementAdmin implementAdmin;
    private final ImplementKasir implementKasir;
    private final ImplementTokoLangganan implementTokoLangganan;
    private final ImplementLogLogin implementLogLogin;

    public ControllerLogin(LoginForm loginForm) {
        this.login = loginForm;
        implementAdmin = new AdminDAO();
        implementKasir = new KasirDAO();
        implementTokoLangganan = new TokoLanggananDAO();
        implementLogLogin = new LogLoginDAO();
    }

    public void loginAdmin() {
        if (login.getCbx_level().getSelectedItem().toString().equals("Admin")) {
            Session.setNama_admin(login.getTxt_ussername().getText());

            boolean checkLogin = implementAdmin.loginAdmin(login.getTxt_ussername().getText(), login.getTxt_pass().getText());
            if (checkLogin) {
                popupInformation(login, "Login sebagai Admin Berhasil !");
                implementLogLogin.insert(Session.getNama_admin());
                Session.setStatusLoginAdmin("AKTIF");
                login.setVisible(false);
                new FormAdmin().setVisible(true);
            } else {
                popupWarning(login, "Harap Masukkan Username dan Password yang Benar");
                login.getTxt_ussername().setText(null);
                login.getTxt_pass().setText(null);
            }
        }
    }

    public void loginKasir() {
        if (login.getCbx_level().getSelectedItem().toString().equals("Kasir")) {
            Session.setNama_kasir(login.getTxt_ussername().getText());
            boolean checkLogin = implementKasir.loginKasir(login.getTxt_ussername().getText(), login.getTxt_pass().getText());
            if (checkLogin) {
                popupInformation(login, "Login sebagai Kasir Berhasil !");
                implementLogLogin.insert(Session.getNama_kasir());
                Session.setStatusLoginKasir("AKTIF");
                login.setVisible(false);
                new FormKasir().setVisible(true);
            } else {
                popupWarning(login, "Harap masukkan Username dan Password yang benar !");
                login.getTxt_ussername().setText(null);
                login.getTxt_pass().setText(null);
                login.getCbx_level().setSelectedItem("Admin");
            }
        }
    }
    
    public void loginTokoLangganan() {
        if (login.getCbx_level().getSelectedItem().toString().equals("Toko Langganan")) {
            Session.setNama_toko(login.getTxt_ussername().getText());
            boolean checkLogin = implementTokoLangganan.loginTokoLangganan(login.getTxt_ussername().getText(), login.getTxt_pass().getText());
            if (checkLogin) {
                popupInformation(login, "Login sebagai Toko Langganan Berhasil !");
                implementLogLogin.insert(Session.getNama_toko());
                Session.setStatusLoginToko("AKTIF");
                login.setVisible(false);
                new FormTokoLangganan().setVisible(true);
            } else {
                popupWarning(login, "Harap masukkan Username dan Password yang benar !");
                login.getTxt_ussername().setText(null);
                login.getTxt_pass().setText(null);
                login.getCbx_level().setSelectedItem("Admin");
            }
        }
    }
}
