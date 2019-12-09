/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.RekapTransaksi;
import com.equinox.model.TransaksiBelanja;
import com.equinox.model.TransaksiPenjualan;
import com.equinox.model.TransaksiPesanan;
import com.equinox.model.dao.DetailTransaksiDAO;
import com.equinox.model.dao.RekapTransaksiDAO;
import com.equinox.model.dao.TransaksiBelanjaDAO;
import com.equinox.model.dao.TransaksiPenjualanDAO;
import com.equinox.model.dao.TransaksiPesananDAO;
import com.equinox.model.implement.ImplementDetailTransaksi;
import com.equinox.model.implement.ImplementRekapTransaksi;
import com.equinox.model.implement.ImplementTransaksiBelanja;
import com.equinox.model.implement.ImplementTransaksiPenjualan;
import com.equinox.model.implement.ImplementTransaksiPesanan;
import com.equinox.model.tabel.TabelModelDetailTransaksi;
import com.equinox.model.tabel.TabelModelRekapTransaksi;
import com.equinox.view.FormAdmin;
import com.equinox.view.RekapTransaksiForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author equinox
 */
public class ControllerRekapTransaksi extends PopUp {

    private final RekapTransaksiForm rekapForm;
    private final ImplementRekapTransaksi implementRekap;
    private final ImplementDetailTransaksi implementDetailTransaksi;
    private final ImplementTransaksiPenjualan implementPenjualan;
    private final ImplementTransaksiBelanja implementBelanja;
    private final ImplementTransaksiPesanan implementPesanan;
    private List<RekapTransaksi> list;
    private List<DetailTransaksi> listDetail;

    public ControllerRekapTransaksi(RekapTransaksiForm rekapForm) {
        this.rekapForm = rekapForm;
        implementRekap = new RekapTransaksiDAO();
        implementPenjualan = new TransaksiPenjualanDAO();
        implementDetailTransaksi = new DetailTransaksiDAO();
        implementBelanja = new TransaksiBelanjaDAO();
        implementPesanan = new TransaksiPesananDAO();
        list = implementRekap.getAllTransaksi(this.rekapForm.getCbx_bulan().getSelectedIndex() + 1);
        listDetail = implementDetailTransaksi.getDetailTransaksi(this.rekapForm.getTf_idtransaksi().getText());
    }

    public void tampilanAwal() {
        rekapForm.getLb_admin().setText("Hi, " + Session.getNama_admin() + " ^_^ ");
        Session.setTanggal_transaksi();
        rekapForm.getLb_date().setText(Session.getTanggal_transaksi());
        isiTabel();
    }

    public void isiTabel() {
        list = implementRekap.getAllTransaksi(rekapForm.getCbx_bulan().getSelectedIndex() + 1);
        listDetail = implementDetailTransaksi.getDetailTransaksi(rekapForm.getTf_idtransaksi().getText());

        rekapForm.getTb_detailtransaksi().setModel(new TabelModelDetailTransaksi(listDetail));
        rekapForm.getTb_rekap().setModel(new TabelModelRekapTransaksi(list));

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(rekapForm.getTb_rekap().getModel());
        rekapForm.getTb_rekap().setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    public void getRekapTotalHarga() {
        long total_pengeluaran = 0;
        long total_pemasukan = 0;

        int rowSize = rekapForm.getTb_rekap().getRowCount();

        for (int i = 0; i < rowSize; i++) {
            total_pengeluaran += Long.valueOf((long) rekapForm.getTb_rekap().getValueAt(i, 4));
            total_pemasukan += Long.valueOf((long) rekapForm.getTb_rekap().getValueAt(i, 3));
        }

        rekapForm.getTf_pengeluaran().setText(String.valueOf(total_pengeluaran));
        rekapForm.getTf_pemasukan().setText(String.valueOf(total_pemasukan));
    }

    public void getDataField() {
        String id = "";
        int row = rekapForm.getTb_rekap().getSelectedRow();

        if (row != -1) {

            id = rekapForm.getTb_rekap().getValueAt(row, 0).toString();

            if (implementPenjualan.getTransaksiPenjualan(id) != null) {

                rekapForm.getLb_karyawan().setText("Id Kasir");
                TransaksiPenjualan penjualan = implementPenjualan.getTransaksiPenjualan(id);

                rekapForm.getTf_idtransaksi().setText(penjualan.getId_penjualan());
                rekapForm.getTf_idkasir().setText(penjualan.getId_kasir());
                rekapForm.getTf_tanggal().setText(penjualan.getTanggal_transaksi());
                rekapForm.getTf_qty().setText(String.valueOf(penjualan.getJumlah_item()));
                rekapForm.getTf_totalharga().setText(String.valueOf(penjualan.getHarga_total()));
            } else if (implementBelanja.getTransaksiBelanja(id) != null) {

                rekapForm.getLb_karyawan().setText("Id Supplier");
                TransaksiBelanja belanja = implementBelanja.getTransaksiBelanja(id);

                rekapForm.getTf_idtransaksi().setText(belanja.getId_transaksi_belanja());
                rekapForm.getTf_idkasir().setText(belanja.getId_suplier());
                rekapForm.getTf_tanggal().setText(belanja.getTanggal_transaksi());
                rekapForm.getTf_qty().setText(String.valueOf(belanja.getJumlah_item()));
                rekapForm.getTf_totalharga().setText(String.valueOf(belanja.getHarga_total()));

            } else if (implementPesanan.getTransaksiPesanan(id) != null) {
                rekapForm.getLb_karyawan().setText("Id_Kasir");
                TransaksiPesanan pesanan = implementPesanan.getTransaksiPesanan(id);
                
                rekapForm.getTf_idtransaksi().setText(pesanan.getId_transaksi());
                rekapForm.getTf_idkasir().setText(pesanan.getId_kasir());
                rekapForm.getTf_tanggal().setText(pesanan.getTanggal_transaksi());
                rekapForm.getTf_qty().setText(String.valueOf(pesanan.getJumlah_item()));
                rekapForm.getTf_totalharga().setText(String.valueOf(pesanan.getHarga_total()));
            }
        }
    }

    public void kembali() {
        rekapForm.setVisible(false);
        new FormAdmin().setVisible(true);
    }

    public void deleteTransaksi() {
        if (rekapForm.getTf_idtransaksi().getText().isEmpty() || rekapForm.getTf_idkasir().getText().isEmpty()) {
            popupWarning(rekapForm, "Data tidak boleh ada yang kosong !");
        } else {
            if (confirmHapus(rekapForm) == JOptionPane.YES_OPTION) {
                implementRekap.deleteTransaksi(rekapForm.getTf_idtransaksi().getText());
                implementDetailTransaksi.delete(rekapForm.getTf_idtransaksi().getText());
            }
        }
    }

    public void reset() {
        rekapForm.getTf_idtransaksi().setText(null);
        rekapForm.getTf_idkasir().setText(null);
        rekapForm.getTf_qty().setText(null);
        rekapForm.getTf_tanggal().setText(null);
        rekapForm.getTf_totalharga().setText(null);
    }
}
