/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.TransaksiPenjualan;

/**
 *
 * @author equinox
 */
public interface ImplementTransaksiPenjualan {
    
    public void insertTransaksiPenjualan(TransaksiPenjualan transaksiPenjualan);
    
    public String getIdTransaksiPenjualan();
    
    public TransaksiPenjualan getTransaksiPenjualan(String id);
    
}
