/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.LogLogin;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementLogLogin {
    
    public void insert(String nama_user);
    
    public List<LogLogin> getAllLogin();
    
}
