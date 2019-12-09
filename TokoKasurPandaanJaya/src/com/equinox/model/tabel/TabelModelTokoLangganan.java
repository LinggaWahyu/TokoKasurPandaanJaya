/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.Supplier;
import com.equinox.model.TokoLangganan;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelTokoLangganan extends AbstractTableModel {

    List<TokoLangganan> list;

    public TabelModelTokoLangganan(List<TokoLangganan> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getId_toko();
            case 1 : return list.get(rowIndex).getNama_toko();
            case 2 : return list.get(rowIndex).getPassword_toko();
            case 3 : return list.get(rowIndex).getAlamat_toko();
            case 4 : return list.get(rowIndex).getNo_telp();
            default: return null;
        } 
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id Toko";
            case 1 : return "Nama";
            case 2 : return "Password";
            case 3 : return "Alamat";
            case 4 : return "No. telp";
            default: return null;
        }
    }
}
