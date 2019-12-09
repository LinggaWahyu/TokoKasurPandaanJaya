/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.TransaksiPesanan;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementTransaksiPesanan {
    
    public void insert(TransaksiPesanan transaksiPesanan);

    public String getIdTransaksiPesanan();
    
    public List<TransaksiPesanan> getAllPesanan(String id);

    public List<TransaksiPesanan> getAllPesanan();
    
    public void confirmPesanan(String id, String kasir);
    
    public TransaksiPesanan getTransaksiPesanan(String id);
    
}
