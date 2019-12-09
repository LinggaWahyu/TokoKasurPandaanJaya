/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.config;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.equinox.*;
import java.net.URL;

/**
 *
 * @author equinox
 */
public class PopUp {
    
    protected void popupWarning(Component component, String warning) {
        URL url = getClass().getResource("/com/equinox/view/icon/icons8-warning-shield-32.png");
        ImageIcon icon = new ImageIcon(url);
        JOptionPane.showMessageDialog(component, warning, "Warning", 0, icon);
    }
    
    protected int confirmHapus(Component component) {
        URL url = getClass().getResource("/com/equinox/view/icon/icons8-delete-32.png");
        ImageIcon icon = new ImageIcon(url);
        int jOptionPane = JOptionPane.showConfirmDialog(component, "Yakin mau menghapus data ini ?", "WARNING", 2, 0, icon);
        return jOptionPane;
    }
    
    protected void popupInformation(Component component, String info) {
        URL url = getClass().getResource("/com/equinox/view/icon/icons8-information-32.png");
        ImageIcon icon = new ImageIcon(url);
        JOptionPane.showMessageDialog(component, info, "Information", 0, icon);
    }
}
