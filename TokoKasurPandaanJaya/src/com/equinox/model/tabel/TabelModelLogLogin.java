/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.LogLogin;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelLogLogin extends AbstractTableModel {

    List<LogLogin> list;
    
    public TabelModelLogLogin(List<LogLogin> list) {
        this.list = list;
    }
     
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getId();
            case 1 : return list.get(rowIndex).getNama_user();
            case 2 : return list.get(rowIndex).getWaktu_login();
            default: return null;
        } 
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id";
            case 1 : return "Nama User";
            case 2 : return "Waktu Login";
            default: return null;
        }
    }
}
