/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import javax.swing.table.AbstractTableModel;
import com.equinox.model.RekapTransaksi;
import java.util.List;

/**
 *
 * @author equinox
 */
public class TabelModelRekapTransaksi extends AbstractTableModel {

    List<RekapTransaksi> list;

    public TabelModelRekapTransaksi(List<RekapTransaksi> list) {
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
            case 0:
                return list.get(rowIndex).getId_transaksi();
            case 1:
                return list.get(rowIndex).getTanggal_transaksi();
            case 2:
                return list.get(rowIndex).getQty();
            case 3:
                return list.get(rowIndex).getDebit();
            case 4:
                return list.get(rowIndex).getKredit();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id Transaksi";
            case 1:
                return "Tanggal";
            case 2:
                return "Qty";
            case 3:
                return "Debit";
            case 4:
                return "Kredit";
            default:
                return null;
        }
    }
}
