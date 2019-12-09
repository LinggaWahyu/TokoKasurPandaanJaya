/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;


import com.equinox.model.Admin;
/**
 *
 * @author equinox
 */
public interface ImplementAdmin {
    
    public boolean loginAdmin(String username, String password);
    
    public String getIdAdmin(String username);
    
}
