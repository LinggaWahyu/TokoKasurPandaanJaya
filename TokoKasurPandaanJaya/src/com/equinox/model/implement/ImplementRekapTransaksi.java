/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.RekapTransaksi;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementRekapTransaksi {
    
    public List<RekapTransaksi> getAllTransaksi(int bulan);
    
    public boolean deleteTransaksi(String id);
    
}
