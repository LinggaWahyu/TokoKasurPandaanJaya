/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.PopUp;
import com.equinox.config.Session;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.TransaksiPesanan;
import com.equinox.model.dao.BarangDAO;
import com.equinox.model.dao.DetailTransaksiDAO;
import com.equinox.model.dao.KasirDAO;
import com.equinox.model.dao.TokoLanggananDAO;
import com.equinox.model.dao.TransaksiPesananDAO;
import com.equinox.model.implement.ImplementBarang;
import com.equinox.model.implement.ImplementDetailTransaksi;
import com.equinox.model.implement.ImplementKasir;
import com.equinox.model.implement.ImplementTokoLangganan;
import com.equinox.model.implement.ImplementTransaksiPesanan;
import com.equinox.model.tabel.TabelModelDetailTransaksi;
import com.equinox.model.tabel.TabelModelTransaksiPesanan;
import com.equinox.view.FormKasir;
import com.equinox.view.FormTokoLangganan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author equinox
 */
public class ControllerTokoLangganan extends PopUp {

    private final FormTokoLangganan formTokoLangganan;
    private final ImplementTokoLangganan implementTokoLangganan;
    private final ImplementTransaksiPesanan implementPesanan;
    private final ImplementBarang implementBarang;
    private final ImplementDetailTransaksi implementDetailTransaksi;
    private final ImplementKasir implementKasir;
    private List<TransaksiPesanan> list;
    private List<DetailTransaksi> listDetail;

    public ControllerTokoLangganan(FormTokoLangganan formTokoLangganan) {
        this.formTokoLangganan = formTokoLangganan;
        implementTokoLangganan = new TokoLanggananDAO();
        implementBarang = new BarangDAO();
        implementPesanan = new TransaksiPesananDAO();
        implementKasir = new KasirDAO();
        implementDetailTransaksi = new DetailTransaksiDAO();
        list = new ArrayList<>();
        listDetail = new ArrayList<>();
        Session.setListBarang();
        isiKeranjang();
        isiTabelPesanan();
    }

    public void tampilanAwal() {
        formTokoLangganan.getLb_toko().setText("Hi, " + Session.getNama_toko() + " ^_^ ");
        Session.setTanggal_transaksi();
        formTokoLangganan.getLb_date().setText(Session.getTanggal_transaksi());
        formTokoLangganan.getTf_idPesanan().setText(implementPesanan.getIdTransaksiPesanan());
        getBarang();
        getHarga();
        setKomponen("early");
        isiTabelPesanan();
    }

    public void getBarang() {
        List<String> barang = implementBarang.getAllNamaBarang();
        String[] arraybarang = barang.toArray(new String[barang.size()]);
        DefaultComboBoxModel barang1 = new DefaultComboBoxModel(arraybarang);
        formTokoLangganan.getCbx_barang().setModel(barang1);
    }

    public void setKomponen(String komponen) {
        switch (komponen) {
            case "early":
                formTokoLangganan.getBtn_tambah().setEnabled(true);
                formTokoLangganan.getBtn_edit().setEnabled(false);
                formTokoLangganan.getBtn_hapus().setEnabled(false);
                formTokoLangganan.getBtn_reset().setEnabled(true);
                formTokoLangganan.getTf_idPesanan().setEnabled(false);
                formTokoLangganan.getTf_harga().setEnabled(false);
                formTokoLangganan.getTf_jumlahHarga().setEnabled(false);
                formTokoLangganan.getTf_totalHarga().setEnabled(false);
                break;
            case "clicked":
                formTokoLangganan.getBtn_tambah().setEnabled(false);
                formTokoLangganan.getBtn_edit().setEnabled(true);
                formTokoLangganan.getBtn_hapus().setEnabled(true);
                formTokoLangganan.getBtn_reset().setEnabled(true);
                break;
            case "deleted":
                formTokoLangganan.getBtn_tambah().setEnabled(true);
                formTokoLangganan.getBtn_edit().setEnabled(false);
                formTokoLangganan.getBtn_hapus().setEnabled(false);
                formTokoLangganan.getBtn_reset().setEnabled(true);
                break;
            case "added":
                formTokoLangganan.getBtn_tambah().setEnabled(true);
                formTokoLangganan.getBtn_edit().setEnabled(false);
                formTokoLangganan.getBtn_hapus().setEnabled(false);
                formTokoLangganan.getBtn_reset().setEnabled(true);
                break;
            case "edited":
                formTokoLangganan.getBtn_tambah().setEnabled(true);
                formTokoLangganan.getBtn_edit().setEnabled(false);
                formTokoLangganan.getBtn_hapus().setEnabled(false);
                formTokoLangganan.getBtn_reset().setEnabled(true);
                break;
            case "reseted":
                formTokoLangganan.getBtn_tambah().setEnabled(true);
                formTokoLangganan.getBtn_edit().setEnabled(false);
                formTokoLangganan.getBtn_hapus().setEnabled(false);
                formTokoLangganan.getBtn_reset().setEnabled(true);
                break;
            case "editHargaEnable":
                formTokoLangganan.getTf_harga().setEnabled(true);
                break;
            case "editHargaDisable":
                formTokoLangganan.getTf_harga().setEnabled(false);
                break;
        }
    }

    public void tambah() {
        if (formTokoLangganan.getTf_harga().getText().isEmpty() || formTokoLangganan.getTf_jumlah().getText().isEmpty()) {
            popupWarning(formTokoLangganan, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            DetailTransaksi detailTransaksi = new DetailTransaksi();
            detailTransaksi.setId_transaksi(formTokoLangganan.getTf_idPesanan().getText());
            detailTransaksi.setId_barang(implementBarang.getIdBarang(formTokoLangganan.getCbx_barang().getSelectedItem().toString()));
            detailTransaksi.setJumlah(Double.valueOf(formTokoLangganan.getTf_jumlah().getText()));
            detailTransaksi.setJumlah_harga(Long.valueOf(formTokoLangganan.getTf_jumlahHarga().getText()));

            Session.listBarang.add(detailTransaksi);
        }
    }

    public void isiKeranjang() {
        formTokoLangganan.getTb_keranjangPesanan().setModel(new TabelModelDetailTransaksi(Session.listBarang));
    }

    public void getJumlahHarga() {
        Double jumlah = 0.0;
        String getJumlah = formTokoLangganan.getTf_jumlah().getText();
        if (getJumlah.isEmpty()) {
            jumlah = jumlah;
        } else {
            jumlah = Double.valueOf(getJumlah);
        }
        double harga = Double.valueOf(formTokoLangganan.getTf_harga().getText());
        long jumlah_harga = Math.round(jumlah * harga);

        formTokoLangganan.getTf_jumlahHarga().setText(String.valueOf(jumlah_harga));
    }

    public void getHarga() {
        String barang = formTokoLangganan.getCbx_barang().getSelectedItem().toString();
        long harga = 0;
        harga = implementBarang.getHargaBarang(barang);
        if (harga != 0) {
            formTokoLangganan.getTf_harga().setText(String.valueOf(harga));
            formTokoLangganan.getBtn_tambah().setEnabled(true);
        } else {
            formTokoLangganan.getBtn_tambah().setEnabled(false);
            formTokoLangganan.getTf_harga().setText(String.valueOf(harga));
        }
    }

    public void getTotalHarga() {
        long harga_total = 0;

        int rowSize = formTokoLangganan.getTb_keranjangPesanan().getRowCount();
        for (int i = 0; i < rowSize; i++) {
            harga_total += Long.valueOf((long) formTokoLangganan.getTb_keranjangPesanan().getValueAt(i, 3));
        }
        formTokoLangganan.getTf_totalHarga().setText(String.valueOf(harga_total));
    }

    public void reset() {
        formTokoLangganan.getTf_jumlah().setText(null);
        formTokoLangganan.getCbx_barang().setSelectedIndex(0);
        getHarga();
        getJumlahHarga();
    }

    public void getDataField() {
        int row = formTokoLangganan.getTb_keranjangPesanan().getSelectedRow();

        if (row != -1) {

            formTokoLangganan.getCbx_barang().setSelectedItem(implementBarang.getNamaBarang(Session.listBarang.get(row).getId_barang()));
            formTokoLangganan.getTf_jumlah().setText(String.valueOf(Session.listBarang.get(row).getJumlah()));
            formTokoLangganan.getTf_harga().setText(String.valueOf(implementBarang.getHargaBarang(implementBarang.getNamaBarang(Session.listBarang.get(row).getId_barang()))));
            formTokoLangganan.getTf_jumlahHarga().setText(String.valueOf(Session.listBarang.get(row).getJumlah_harga()));

        }
    }

    public void edit() {
        if (formTokoLangganan.getTf_harga().getText().isEmpty() || formTokoLangganan.getTf_jumlah().getText().isEmpty()) {
            popupWarning(formTokoLangganan, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            int row = formTokoLangganan.getTb_keranjangPesanan().getSelectedRow();

            if (row != -1) {

                DetailTransaksi detailTransaksi = new DetailTransaksi();
                detailTransaksi.setId_transaksi(formTokoLangganan.getTf_idPesanan().getText());
                detailTransaksi.setId_barang(implementBarang.getIdBarang(formTokoLangganan.getCbx_barang().getSelectedItem().toString()));
                detailTransaksi.setJumlah(Double.valueOf(formTokoLangganan.getTf_jumlah().getText()));
                detailTransaksi.setJumlah_harga(Long.valueOf(formTokoLangganan.getTf_jumlahHarga().getText()));

                Session.listBarang.set(row, detailTransaksi);
            }
            reset();
        }
    }

    public void hapus() {
        if (formTokoLangganan.getTf_harga().getText().isEmpty() || formTokoLangganan.getTf_jumlah().getText().isEmpty()) {
            popupWarning(formTokoLangganan, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            if (confirmHapus(formTokoLangganan) == JOptionPane.YES_OPTION) {

                int row = formTokoLangganan.getTb_keranjangPesanan().getSelectedRow();

                if (row != -1) {
                    Session.listBarang.remove(row);
                    reset();
                }
            }
        }
    }

    public void insertDetailTransaksi() {
        if (formTokoLangganan.getTb_keranjangPesanan().getRowCount() == 0) {
            popupWarning(formTokoLangganan, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            implementDetailTransaksi.insert(Session.listBarang);
            implementDetailTransaksi.updateStokPenjualan(Session.listBarang);
            popupInformation(formTokoLangganan, "Data berhasil di simpan !");
            Session.setListBarang();
            isiKeranjang();
            tampilanAwal();
        }
    }

    public void insertTransaksiPesanan() {
        TransaksiPesanan transaksiPesanan = new TransaksiPesanan();

        transaksiPesanan.setId_transaksi(formTokoLangganan.getTf_idPesanan().getText());
        transaksiPesanan.setId_toko(implementTokoLangganan.getIdTokoLangganan(Session.getNama_toko()));
        transaksiPesanan.setJumlah_item(formTokoLangganan.getTb_keranjangPesanan().getRowCount());
        transaksiPesanan.setHarga_total(Long.valueOf(formTokoLangganan.getTf_totalHarga().getText()));
        transaksiPesanan.setStatus_pengantaran("BELUM");

        implementPesanan.insert(transaksiPesanan);
    }

    public void insert() {
        if (formTokoLangganan.getTb_keranjangPesanan().getRowCount() == 0) {
            popupWarning(formTokoLangganan, "Maaf ! Data tidak boleh ada yang kosong");
        } else {
            insertTransaksiPesanan();
            insertDetailTransaksi();
        }
    }

    public void cekStok() {
        String barang = formTokoLangganan.getCbx_barang().getSelectedItem().toString();
        String id = implementBarang.getIdBarang(barang);
        Double jumlah = 0.0;
        if (!formTokoLangganan.getTf_jumlah().getText().isEmpty()) {
            jumlah = Double.valueOf(formTokoLangganan.getTf_jumlah().getText());
        }
        if (implementDetailTransaksi.cekStok(id, jumlah) == false) {
            popupInformation(formTokoLangganan, "Maaf ! Stok Tidak Mencukupi");
            formTokoLangganan.getTf_jumlah().setText("0.0");
        } else {
            return;
        }
    }

    public void isiTabelPesanan() {
        list = implementPesanan.getAllPesanan(implementTokoLangganan.getIdTokoLangganan(Session.getNama_toko()));
        formTokoLangganan.getTb_listPesanan().setModel(new TabelModelTransaksiPesanan(list));
    }

    public void isiTabelDetailPesanan() {
        int row = formTokoLangganan.getTb_listPesanan().getSelectedRow();

        String id = "";
        id = formTokoLangganan.getTb_listPesanan().getValueAt(row, 0).toString();

        listDetail = implementDetailTransaksi.getDetailTransaksi(id);
        formTokoLangganan.getTb_detailPesanan().setModel(new TabelModelDetailTransaksi(listDetail));
    }
}
