/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.Kasir;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementKasir {
    
    public boolean loginKasir(String username, String password);
    
    public String getIdKasir(String nama);
    
    public List<Kasir> getAllKasir();
    
    public String getIdKasir();
    
    public void insertKasir(Kasir kasir);
    
    public void delete(String id);
    
    public void update(Kasir kasir);
    
}
