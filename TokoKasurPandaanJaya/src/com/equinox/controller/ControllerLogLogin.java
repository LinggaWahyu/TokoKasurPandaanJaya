/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.model.LogLogin;
import com.equinox.model.dao.LogLoginDAO;
import com.equinox.model.implement.ImplementLogLogin;
import com.equinox.model.tabel.TabelModelLogLogin;
import com.equinox.view.FormAdmin;
import com.equinox.view.FormKasir;
import com.equinox.view.FormLogLogin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author equinox
 */
public class ControllerLogLogin {
    
    private final FormLogLogin formLogLogin;
    private final ImplementLogLogin implementLogLogin;
    private List<LogLogin> list;
    
    public ControllerLogLogin(FormLogLogin formLogLogin) {
        this.formLogLogin = formLogLogin;
        implementLogLogin = new LogLoginDAO();
        list = new ArrayList<>();
        isiTabel();
    }
    
    public void isiTabel() {
        list = implementLogLogin.getAllLogin();
        formLogLogin.getTb_logLogin().setModel(new TabelModelLogLogin(list));
    }
    
    public void kembali() {
        formLogLogin.setVisible(false);
        new FormAdmin().setVisible(true);
    }
}
