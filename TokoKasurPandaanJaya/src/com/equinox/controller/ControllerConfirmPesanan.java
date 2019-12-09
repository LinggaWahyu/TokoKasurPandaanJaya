/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.controller;

import com.equinox.config.Session;
import com.equinox.model.DetailTransaksi;
import com.equinox.model.TransaksiPesanan;
import com.equinox.model.dao.DetailTransaksiDAO;
import com.equinox.model.dao.KasirDAO;
import com.equinox.model.dao.TokoLanggananDAO;
import com.equinox.model.dao.TransaksiPesananDAO;
import com.equinox.model.implement.ImplementDetailTransaksi;
import com.equinox.model.implement.ImplementKasir;
import com.equinox.model.implement.ImplementTokoLangganan;
import com.equinox.model.implement.ImplementTransaksiPesanan;
import com.equinox.model.tabel.TabelModelDetailTransaksi;
import com.equinox.model.tabel.TabelModelTransaksiPesanan;
import com.equinox.view.FormConfirmPesanan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author equinox
 */
public class ControllerConfirmPesanan {

    private final FormConfirmPesanan confirmPesanan;
    private final ImplementKasir implementKasir;
    private final ImplementTransaksiPesanan implementPesanan;
    private final ImplementDetailTransaksi implementDetailTransaksi;
    private final ImplementTokoLangganan implementTokoLangganan;
    List<TransaksiPesanan> listPesanan;
    List<DetailTransaksi> listDetail;

    public ControllerConfirmPesanan(FormConfirmPesanan confirmPesanan) {
        this.confirmPesanan = confirmPesanan;
        implementKasir = new KasirDAO();
        implementDetailTransaksi = new DetailTransaksiDAO();
        implementPesanan = new TransaksiPesananDAO();
        implementTokoLangganan = new TokoLanggananDAO();
        listPesanan = new ArrayList<>();
        listDetail = new ArrayList<>();
        isiTabelPesanan();
        tampilanAwal();
    }

    public void tampilanAwal() {
        confirmPesanan.getLb_kasir1().setText("Hi, " + Session.getNama_kasir()+ " ^_^ ");
        Session.setTanggal_transaksi();
        confirmPesanan.getLb_date().setText(Session.getTanggal_transaksi());
    }

    public void isiTabelPesanan() {
        listPesanan = implementPesanan.getAllPesanan();
        confirmPesanan.getTb_listPesanan().setModel(new TabelModelTransaksiPesanan(listPesanan));
    }

    public void getDataField() {
        int row = confirmPesanan.getTb_listPesanan().getSelectedRow();

        if (row != -1) {
            listDetail = implementDetailTransaksi.getDetailTransaksi(listPesanan.get(row).getId_transaksi());
            confirmPesanan.getTb_detailPesanan().setModel(new TabelModelDetailTransaksi(listDetail));

            confirmPesanan.getTf_idPesanan().setText(listPesanan.get(row).getId_transaksi());
            confirmPesanan.getTf_namaToko().setText(implementTokoLangganan.getNamaTokoLangganan(listPesanan.get(row).getId_toko()));
            confirmPesanan.getTf_tanggal().setText(listPesanan.get(row).getTanggal_transaksi());
            confirmPesanan.getTf_jumlahItem().setText(String.valueOf(listPesanan.get(row).getJumlah_item()));
            confirmPesanan.getTf_totalHarga().setText(String.valueOf(listPesanan.get(row).getHarga_total()));

        }
    }

    public void confirmPesanan() {
        String id_kasir = implementKasir.getIdKasir(Session.getNama_kasir());
        implementPesanan.confirmPesanan(confirmPesanan.getTf_idPesanan().getText(), id_kasir);
    }
}
