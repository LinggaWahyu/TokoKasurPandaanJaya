/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equinox.view;

import com.equinox.config.Session;
import com.equinox.controller.ControllerMasterKasir;
import com.equinox.controller.ControllerSupplier;
import java.awt.font.TextAttribute;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author equinox
 */
public class MasterKasirForm extends javax.swing.JFrame {
    
    ControllerMasterKasir controllerMasterKasir;
    
    public MasterKasirForm() {
        if (Session.getStatusLoginAdmin() == "AKTIF") {
            initComponents();
            setSize(482, 557);
            setLocationRelativeTo(null);
            controllerMasterKasir = new ControllerMasterKasir(this);
            controllerMasterKasir.tampilanAwal();
        } else {
            dispose();
            new LoginForm().setVisible(true);
        }
    }
    
    public JLabel getLb_admin() {
        return lb_admin;
    }
    
    public JLabel getLb_date() {
        return lb_date;
    }
    
    public JTable getTb_kasir() {
        return tb_kasir;
    }
    
    public JTextField getTf_idKasir() {
        return tf_idKasir;
    }
    
    public JTextField getTf_notelp() {
        return tf_notelp;
    }
    
    public JTextField getTf_pass() {
        return tf_pass;
    }
    
    public JTextField getTf_username() {
        return tf_username;
    }
    
    public JLabel getBt_edit() {
        return bt_edit;
    }
    
    public JLabel getBt_hapus() {
        return bt_hapus;
    }
    
    public JLabel getBt_kembali() {
        return bt_kembali;
    }
    
    public JLabel getBt_reset() {
        return bt_reset;
    }
    
    public JLabel getBt_tambah() {
        return bt_tambah;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_admin = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_idKasir = new javax.swing.JTextField();
        tf_username = new javax.swing.JTextField();
        tf_pass = new javax.swing.JTextField();
        tf_notelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_kasir = new javax.swing.JTable();
        bt_tambah = new javax.swing.JLabel();
        bt_hapus = new javax.swing.JLabel();
        bt_edit = new javax.swing.JLabel();
        bt_reset = new javax.swing.JLabel();
        bt_kembali = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb_admin.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lb_admin.setForeground(new java.awt.Color(68, 168, 95));
        lb_admin.setText("Hi, Admin ^_^");
        getContentPane().add(lb_admin);
        lb_admin.setBounds(20, 10, 110, 18);

        lb_date.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lb_date.setForeground(new java.awt.Color(68, 168, 95));
        lb_date.setText("Tanggal-Bulan-Tahun");
        getContentPane().add(lb_date);
        lb_date.setBounds(330, 10, 150, 18);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Id Kasir");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 130, 52, 18);

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Username");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 180, 71, 18);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Password");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 230, 67, 18);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("No. telp");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 280, 58, 18);
        getContentPane().add(tf_idKasir);
        tf_idKasir.setBounds(130, 120, 184, 37);
        getContentPane().add(tf_username);
        tf_username.setBounds(130, 170, 184, 37);

        tf_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_passActionPerformed(evt);
            }
        });
        getContentPane().add(tf_pass);
        tf_pass.setBounds(130, 220, 184, 37);
        getContentPane().add(tf_notelp);
        tf_notelp.setBounds(130, 270, 184, 37);

        tb_kasir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_kasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_kasirMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_kasir);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 323, 429, 130);

        bt_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/equinox/view/image/button_tambah.png"))); // NOI18N
        bt_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_tambahMouseClicked(evt);
            }
        });
        getContentPane().add(bt_tambah);
        bt_tambah.setBounds(330, 140, 110, 40);

        bt_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/equinox/view/image/button_hapus.png"))); // NOI18N
        bt_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_hapusMouseClicked(evt);
            }
        });
        getContentPane().add(bt_hapus);
        bt_hapus.setBounds(330, 180, 110, 40);

        bt_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/equinox/view/image/button_edit.png"))); // NOI18N
        bt_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_editMouseClicked(evt);
            }
        });
        getContentPane().add(bt_edit);
        bt_edit.setBounds(330, 220, 110, 40);

        bt_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/equinox/view/image/button_reset.png"))); // NOI18N
        bt_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_resetMouseClicked(evt);
            }
        });
        getContentPane().add(bt_reset);
        bt_reset.setBounds(330, 260, 110, 40);

        bt_kembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/equinox/view/image/9.button kembali.png"))); // NOI18N
        bt_kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_kembaliMouseClicked(evt);
            }
        });
        getContentPane().add(bt_kembali);
        bt_kembali.setBounds(350, 470, 110, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/equinox/view/image/6 asli.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-1, -3, 490, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_passActionPerformed

    private void tb_kasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_kasirMouseClicked
        controllerMasterKasir.getDataField();
        controllerMasterKasir.setKomponen("clicked");
    }//GEN-LAST:event_tb_kasirMouseClicked

    private void bt_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_tambahMouseClicked
        controllerMasterKasir.insert();
        controllerMasterKasir.isiTabel();
        controllerMasterKasir.tampilanAwal();
        controllerMasterKasir.reset();
        controllerMasterKasir.setKomponen("added");
    }//GEN-LAST:event_bt_tambahMouseClicked

    private void bt_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_hapusMouseClicked
        controllerMasterKasir.hapus();
        controllerMasterKasir.isiTabel();
        controllerMasterKasir.reset();
        controllerMasterKasir.setKomponen("deleted");
    }//GEN-LAST:event_bt_hapusMouseClicked

    private void bt_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_editMouseClicked
        controllerMasterKasir.update();
        controllerMasterKasir.isiTabel();
        controllerMasterKasir.reset();
        controllerMasterKasir.setKomponen("edited");
    }//GEN-LAST:event_bt_editMouseClicked

    private void bt_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_resetMouseClicked
        controllerMasterKasir.reset();
        controllerMasterKasir.tampilanAwal();
        controllerMasterKasir.setKomponen("reseted");
    }//GEN-LAST:event_bt_resetMouseClicked

    private void bt_kembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_kembaliMouseClicked
        controllerMasterKasir.kembali();
    }//GEN-LAST:event_bt_kembaliMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MasterKasirForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MasterKasirForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MasterKasirForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MasterKasirForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MasterKasirForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bt_edit;
    private javax.swing.JLabel bt_hapus;
    private javax.swing.JLabel bt_kembali;
    private javax.swing.JLabel bt_reset;
    private javax.swing.JLabel bt_tambah;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_admin;
    private javax.swing.JLabel lb_date;
    private javax.swing.JTable tb_kasir;
    private javax.swing.JTextField tf_idKasir;
    private javax.swing.JTextField tf_notelp;
    private javax.swing.JTextField tf_pass;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
