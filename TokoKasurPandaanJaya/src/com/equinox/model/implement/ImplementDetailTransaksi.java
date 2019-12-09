/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.DetailTransaksi;
import java.util.List;
import javax.xml.soap.Detail;
/**
 *
 * @author equinox
 */
public interface ImplementDetailTransaksi {
  
    public void insert(List<DetailTransaksi> list);
    
    public void updateStokPenjualan(List<DetailTransaksi> list);
    
    public boolean cekStok(String id, Double jumlah);
    
    public void updateStokBelanja(List<DetailTransaksi> list);
    
    public List<DetailTransaksi> getDetailTransaksi(String id);
    
    public void delete(String id_transaksi);
}
