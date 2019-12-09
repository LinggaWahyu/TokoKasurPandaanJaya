/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.Barang;
import com.equinox.model.dao.BarangDAO;
import com.equinox.model.implement.ImplementBarang;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelBarang extends AbstractTableModel {

    ImplementBarang implementBarang;
    List<Barang> list;
    
    public TabelModelBarang(List<Barang> list) {
        this.list = list;
        implementBarang = new BarangDAO();
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
            case 0 : return list.get(rowIndex).getId_barang();
            case 1 : return list.get(rowIndex).getNama_barang();
            case 2 : return list.get(rowIndex).getStok();
            case 3 : return list.get(rowIndex).getHarga();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id Barang";
            case 1 : return "Nama Barang";
            case 2 : return "Stok";
            case 3 : return "Harga";
            default:return null;
        }
    }
}
