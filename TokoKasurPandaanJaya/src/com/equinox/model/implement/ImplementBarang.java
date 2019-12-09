/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.Barang;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementBarang {
    
    public List<String> getAllNamaBarang();
    
    public long getHargaBarang(String barang);
    
    public String getIdBarang(String nama_barang);
    
    public String getNamaBarang(String id);
    
    public List<Barang> getAllBarang();
    
    public String getIdBarang();
    
    public void insertBarang(Barang barang);
    
    public void deleteBarang(String id);
    
    public void updateBarang(Barang barang);
    
}
