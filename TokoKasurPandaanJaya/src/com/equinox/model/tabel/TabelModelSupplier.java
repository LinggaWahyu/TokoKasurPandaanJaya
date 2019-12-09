/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.Supplier;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelSupplier extends AbstractTableModel {

    List<Supplier> list;

    public TabelModelSupplier(List<Supplier> list) {
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
            case 0 : return list.get(rowIndex).getId_supplier();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getAlamat();
            case 3 : return list.get(rowIndex).getNo_telp();
            default: return null;
        } 
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id Supplier";
            case 1 : return "Nama Supplier";
            case 2 : return "Alamat";
            case 3 : return "No. telp";
            default: return null;
        }
    }
}
