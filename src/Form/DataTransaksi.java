/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Login.login;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pingki sukmawati
 */
public class DataTransaksi extends javax.swing.JFrame {
    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();
    String role;
    /**
     * Creates new form DataTransaksi
     */
    public DataTransaksi(String role) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.role = role;
        resizeImage(30, 30, iconMenuUtama, "dashboard.png");
        resizeImage(30, 30, iconDataBarang, "items.png");
        resizeImage(30, 30, iconDataSupplier, "supplier.png");
        resizeImage(30, 30, iconKasir, "kasir.png");
        resizeImage(30, 30, iconDataTransaksi, "transaction.png");
        resizeImage(30, 30, iconLogOut, "logout.png");
        iconToko();
        setColumn();
        setRow();
        btnDetail.setEnabled(false);
    }
    
    public void setRow() {
        model.setRowCount(0);
        try {
            ResultSet rs = db.ambilData("SELECT * FROM transaksi");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_transaksi"), rs.getString("tanggal_transaksi"), rs.getString("harga_total")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setColumn() {
        model.setColumnCount(0);
        model.addColumn("Nomor Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Harga Total");
    }
    
    public void iconToko() {
        try {
            File file = new File("src/img/Toko Ku (5).png");
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            jLabel1.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void resizeImage(int width, int height, JLabel label, String path) {        
        try {
            File file = new File("src/img/" + path);
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            label.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        customTimeLabel1 = new CustomComponent.CustomTimeLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        secAdmin = new javax.swing.JPanel();
        lbMenuUtama = new javax.swing.JLabel();
        lbDataBarang = new javax.swing.JLabel();
        lbDataSupplier = new javax.swing.JLabel();
        iconMenuUtama = new javax.swing.JLabel();
        iconDataBarang = new javax.swing.JLabel();
        iconDataSupplier = new javax.swing.JLabel();
        secUser = new javax.swing.JPanel();
        lbKasir = new javax.swing.JLabel();
        lbDataTransaksi = new javax.swing.JLabel();
        iconKasir = new javax.swing.JLabel();
        iconDataTransaksi = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbLogin = new javax.swing.JLabel();
        iconLogOut = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        tfCari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new CustomComponent.CustomTable();
        customButton1 = new CustomComponent.CustomButton();
        btnDetail = new CustomComponent.CustomButton();
        customButton4 = new CustomComponent.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(78, 115, 223));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -10, 280, 130));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 80));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(204, 204, 255));

        jLabel2.setBackground(new java.awt.Color(133, 135, 150));
        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(133, 135, 150));
        jLabel2.setText("Toko Kelontong “Putra”");

        customTimeLabel1.setForeground(new java.awt.Color(102, 102, 102));
        customTimeLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 725, Short.MAX_VALUE)
                .addComponent(customTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1190, 80));

        jPanel4.setBackground(new java.awt.Color(78, 115, 223));

        jPanel17.setBackground(new java.awt.Color(78, 115, 223));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        secAdmin.setBackground(new java.awt.Color(78, 115, 223));
        secAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMenuUtama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMenuUtama.setForeground(new java.awt.Color(255, 255, 255));
        lbMenuUtama.setText("Menu Utama");
        secAdmin.add(lbMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 30));

        lbDataBarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataBarang.setForeground(new java.awt.Color(255, 255, 255));
        lbDataBarang.setText("Data Barang");
        lbDataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDataBarangMouseClicked(evt);
            }
        });
        secAdmin.add(lbDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 30));

        lbDataSupplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataSupplier.setForeground(new java.awt.Color(255, 255, 255));
        lbDataSupplier.setText("Data Supplier");
        secAdmin.add(lbDataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 30));
        secAdmin.add(iconMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));
        secAdmin.add(iconDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 30, 30));
        secAdmin.add(iconDataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 30, 30));

        secUser.setBackground(new java.awt.Color(78, 115, 223));
        secUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbKasir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbKasir.setForeground(new java.awt.Color(255, 255, 255));
        lbKasir.setText("Kasir");
        secUser.add(lbKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        lbDataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lbDataTransaksi.setText("Data Transaksi");
        secUser.add(lbDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 30));
        secUser.add(iconKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));
        secUser.add(iconDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 30, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Keluar");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        lbLogin.setBackground(new java.awt.Color(133, 135, 150));
        lbLogin.setForeground(new java.awt.Color(133, 135, 150));
        lbLogin.setText("Masuk Sebagai Admin ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lbLogin)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(secUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(iconLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(secAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secUser, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 180, 740));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(133, 135, 150));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(133, 135, 150));
        jLabel14.setText("2024 - Sistem Penjualan Pada Toko Kelontong | Kelompok 5");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 670, 1240, 90));

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Data Transaksi");
        jPanel1.add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 370, 33));

        tfCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariActionPerformed(evt);
            }
        });
        tfCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariKeyReleased(evt);
            }
        });
        jPanel1.add(tfCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 190, 30));

        tblData.setModel(new javax.swing.table.DefaultTableModel(
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
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 1060, 350));

        customButton1.setText("Cari");
        customButton1.setBackgroundColor(new java.awt.Color(204, 204, 204));
        customButton1.setFontSize(14);
        customButton1.setTextBold(0);
        customButton1.setTheText("Cari");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(customButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 160, 60, 30));

        btnDetail.setText("customButton2");
        btnDetail.setBackgroundColor(new java.awt.Color(51, 204, 255));
        btnDetail.setTextBold(0);
        btnDetail.setTheText("Detail");
        jPanel1.add(btnDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 580, 100, 40));

        customButton4.setText("customButton2");
        customButton4.setBackgroundColor(new java.awt.Color(102, 255, 51));
        customButton4.setTextBold(0);
        customButton4.setTheText("Cetak");
        jPanel1.add(customButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 580, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDataBarangMouseClicked
        // TODO add your handling code here:
        new DataBarang(role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbDataBarangMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void tfCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        boolean edit = tblData.isEditing();
        if(!edit) {
            JOptionPane.showMessageDialog(this, "Tidak bisa mengedit data ini!", "Error", JOptionPane.ERROR_MESSAGE);
            jPanel1.requestFocus();
        }
        if(tblData.getSelectedRow() > -1) {
            btnDetail.setEnabled(true);
        } else {
            btnDetail.setEnabled(false);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        // TODO add your handling code here:
        ResultSet rs = db.ambilData("SELECT * FROM transaksi WHERE tanggal_transaksi LIKE '%" + tfCari.getText() + "%'");
        try {
            model.setRowCount(0);
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_transaksi"), rs.getString("tanggal_transaksi"), rs.getString("harga_total")});
            }
            tblData.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_customButton1ActionPerformed

    private void tfCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyReleased
        // TODO add your handling code here:
        if(tfCari.getText().equals("")) {
            setRow();
        }
    }//GEN-LAST:event_tfCariKeyReleased

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
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataTransaksi("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnDetail;
    private CustomComponent.CustomButton customButton1;
    private CustomComponent.CustomButton customButton4;
    private CustomComponent.CustomTimeLabel customTimeLabel1;
    private javax.swing.JLabel iconDataBarang;
    private javax.swing.JLabel iconDataSupplier;
    private javax.swing.JLabel iconDataTransaksi;
    private javax.swing.JLabel iconKasir;
    private javax.swing.JLabel iconLogOut;
    private javax.swing.JLabel iconMenuUtama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDataBarang;
    private javax.swing.JLabel lbDataSupplier;
    private javax.swing.JLabel lbDataTransaksi;
    private javax.swing.JLabel lbKasir;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMenuUtama;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel secAdmin;
    private javax.swing.JPanel secUser;
    private CustomComponent.CustomTable tblData;
    private javax.swing.JTextField tfCari;
    // End of variables declaration//GEN-END:variables
}
