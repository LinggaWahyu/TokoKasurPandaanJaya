/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.Kasir;
import com.equinox.model.Supplier;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelKasir extends AbstractTableModel {

    List<Kasir> list;

    public TabelModelKasir(List<Kasir> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getId();
            case 1 : return list.get(rowIndex).getUsername();
            case 2 : return list.get(rowIndex).getPass();
            case 3 : return list.get(rowIndex).getNo_telp();
            default: return null;
        } 
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id Kasir";
            case 1 : return "Username";
            case 2 : return "Password";
            case 3 : return "No. telp";
            default: return null;
        }
    }
}
