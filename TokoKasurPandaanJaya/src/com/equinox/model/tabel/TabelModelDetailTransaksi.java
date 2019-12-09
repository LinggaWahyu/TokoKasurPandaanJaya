/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.DetailTransaksi;
import com.equinox.model.dao.BarangDAO;
import com.equinox.model.dao.DetailTransaksiDAO;
import com.equinox.model.implement.ImplementBarang;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelDetailTransaksi extends AbstractTableModel {
    
    ImplementBarang implementBarang;
    List<DetailTransaksi> list;
    
    public TabelModelDetailTransaksi(List<DetailTransaksi> list) {
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
            case 0 : return list.get(rowIndex).getId_transaksi();
            case 1 : return implementBarang.getNamaBarang(list.get(rowIndex).getId_barang());
            case 2 : return list.get(rowIndex).getJumlah();
            case 3 : return list.get(rowIndex).getJumlah_harga();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id_Transaksi";
            case 1 : return "Nama Barang";
            case 2 : return "Jumlah";
            case 3 : return "Jumlah Harga";
            default:return null;
        }
    }
}
