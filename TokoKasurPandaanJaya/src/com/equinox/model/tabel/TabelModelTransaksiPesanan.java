/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.tabel;

import com.equinox.model.TransaksiPesanan;
import com.equinox.model.dao.TokoLanggananDAO;
import com.equinox.model.implement.ImplementTokoLangganan;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author equinox
 */
public class TabelModelTransaksiPesanan extends AbstractTableModel {

    List<TransaksiPesanan> listPesanan;
    ImplementTokoLangganan implementTokoLangganan;

    public TabelModelTransaksiPesanan(List<TransaksiPesanan> listPesanan) {
        this.listPesanan = listPesanan;
        implementTokoLangganan = new TokoLanggananDAO();
    }

    @Override
    public int getRowCount() {
        return listPesanan.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return listPesanan.get(rowIndex).getId_transaksi();
            case 1 : return implementTokoLangganan.getNamaTokoLangganan(listPesanan.get(rowIndex).getId_toko());
            case 2 : return listPesanan.get(rowIndex).getTanggal_transaksi();
            case 3 : return listPesanan.get(rowIndex).getJumlah_item();
            case 4 : return listPesanan.get(rowIndex).getHarga_total();
            case 5 : return listPesanan.get(rowIndex).getStatus_pengantaran();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Id Transaksi";
            case 1 : return "Nama Toko";
            case 2 : return "Tanggal Transaksi";
            case 3 : return "Qty";
            case 4 : return "Harga Total";
            case 5 : return "Status Pengantaran";
            default: return null;
        }
    }
}
