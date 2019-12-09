/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.model.implement;

import com.equinox.model.TokoLangganan;
import java.util.List;

/**
 *
 * @author equinox
 */
public interface ImplementTokoLangganan {
    
    public boolean loginTokoLangganan(String username, String password);
    
    public List<TokoLangganan> getAllTokoLangganan();

    public String getIdTokoLangganan();

    public void insertTokoLangganan(TokoLangganan tokoLangganan);

    public void delete(String id);

    public void update(TokoLangganan tokoLangganan1);
    
    public String getIdTokoLangganan(String nama);
    
    public String getNamaTokoLangganan(String id);
    
}