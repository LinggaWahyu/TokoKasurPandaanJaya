/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.Supplier;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementSupplier {
    
    public List<Supplier> getAllSupplier();
    
    public void insertSupplier(Supplier supplier);
    
    public String getIdSupplier();
    
    public void delete(String id);
    
    public void update(Supplier supplier);
    
    public String getIdSupplier(String nama);
}
