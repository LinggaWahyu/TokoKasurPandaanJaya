/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.TransaksiBelanja;

/**
 *
 * @author equinox
 */
public interface ImplementTransaksiBelanja {
    
    public void insertTransaksiBelanja(TransaksiBelanja transaksiBelanja);
    
    public String getIdTransaksiBelanja();
    
    public TransaksiBelanja getTransaksiBelanja(String id);
    
}
