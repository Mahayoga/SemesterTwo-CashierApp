/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package FormPanel;

import Login.login;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahayoga
 */
public class pnMenuUtama extends javax.swing.JPanel {
    DefaultTableModel model = new DefaultTableModel();
    koneksi db = new koneksi();
    String role = "";

    /**
     * Creates new form pnMenuUtama
     */
    public pnMenuUtama() {
        initComponents();
        tblData.setModel(model);
        tblData.setRowHeight(40);
        //countTotal();
    }
    
    public void countTotal() {
        ResultSet rs1 = db.ambilData("SELECT COUNT(kode_barang) AS 'hehe' FROM stok_barang ORDER BY kode_barang DESC;");
        ResultSet rs2 = db.ambilData("SELECT COUNT(tanggal) AS 'hehe' FROM detail_transaksi GROUP BY tanggal DESC");
        ResultSet rs3 = db.ambilData("SELECT dt.id_transaksi, dt.tanggal, dt.kode_barang AS \"kode_barang\", COUNT(dt.kode_barang) AS \"jumlah_barang\", SUM(dt.harga_barang * dt.jumlah_barang) AS \"total_harga\" FROM detail_transaksi dt JOIN stok_barang sb ON dt.kode_barang = sb.kode_barang GROUP BY dt.tanggal DESC");
        try {
            rs1.next();
            rs2.next();
            rs3.next();
            if(Integer.parseInt(rs1.getString("hehe")) < 9) {
                lbCountDataBarang.setText("00" + rs1.getString("hehe"));
            } else if(Integer.parseInt(rs1.getString("hehe")) < 99) {
                lbCountDataBarang.setText("0" + rs1.getString("hehe"));
            } else if(Integer.parseInt(rs1.getString("hehe")) < 999) {
                lbCountDataBarang.setText(rs1.getString("hehe"));
            }
            
            if(Integer.parseInt(rs2.getString("hehe")) < 9) {
                lbCountDataTransaksi.setText("00" + rs2.getString("hehe"));
            } else if(Integer.parseInt(rs2.getString("hehe")) < 99) {
                lbCountDataTransaksi.setText("0" + rs2.getString("hehe"));
            } else if(Integer.parseInt(rs2.getString("hehe")) < 999) {
                lbCountDataTransaksi.setText(rs2.getString("hehe"));
            }
            
            if(Integer.parseInt(rs3.getString("kode_barang")) < 9) {
                lbCountDataKeuangan.setText("00" + rs3.getString("kode_barang"));
            } else if(Integer.parseInt(rs3.getString("kode_barang")) < 99) {
                lbCountDataKeuangan.setText("0" + rs3.getString("kode_barang"));
            } else if(Integer.parseInt(rs3.getString("kode_barang")) < 999) {
                lbCountDataKeuangan.setText(rs3.getString("kode_barang"));
            }
        } catch(Exception e) {
            e.printStackTrace();
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

        jLabel15 = new javax.swing.JLabel();
        customPanel1 = new CustomComponent.CustomPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lbCountDataBarang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        customPanel2 = new CustomComponent.CustomPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        lbCountDataTransaksi = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        customPanel3 = new CustomComponent.CustomPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        lbCountDataKeuangan = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnNoData = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new CustomComponent.CustomTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(133, 135, 149));
        jLabel15.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(133, 135, 149));
        jLabel15.setText("Dashboard");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, 33));

        customPanel1.setBackground(new java.awt.Color(78, 115, 223));
        customPanel1.setBackgroundColor(new java.awt.Color(78, 115, 223));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tabel Barang");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel24.setForeground(new java.awt.Color(255, 255, 255));

        lbCountDataBarang.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCountDataBarang.setText("009");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Total Barang");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbCountDataBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCountDataBarang)
                    .addComponent(jLabel8))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cek Tabel Barang");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customPanel1Layout = new javax.swing.GroupLayout(customPanel1);
        customPanel1.setLayout(customPanel1Layout);
        customPanel1Layout.setHorizontalGroup(
            customPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(71, 71, 71))))
        );
        customPanel1Layout.setVerticalGroup(
            customPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        add(customPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        customPanel2.setBackground(new java.awt.Color(78, 115, 223));
        customPanel2.setBackgroundColor(new java.awt.Color(78, 115, 223));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Tabel Transaksi");

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));

        lbCountDataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCountDataTransaksi.setText("009");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Total Transaksi");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbCountDataTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCountDataTransaksi)
                    .addComponent(jLabel10))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cek Data Transaksi");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customPanel2Layout = new javax.swing.GroupLayout(customPanel2);
        customPanel2.setLayout(customPanel2Layout);
        customPanel2Layout.setHorizontalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(66, 66, 66))))
        );
        customPanel2Layout.setVerticalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        add(customPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, -1));

        customPanel3.setBackground(new java.awt.Color(78, 115, 223));
        customPanel3.setBackgroundColor(new java.awt.Color(78, 115, 223));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tabel Keuangan");

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel27.setForeground(new java.awt.Color(255, 255, 255));

        lbCountDataKeuangan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCountDataKeuangan.setText("009");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Total Barang");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Terjual");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbCountDataKeuangan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12))
                    .addComponent(lbCountDataKeuangan))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cek Data Keuangan");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customPanel3Layout = new javax.swing.GroupLayout(customPanel3);
        customPanel3.setLayout(customPanel3Layout);
        customPanel3Layout.setHorizontalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(64, 64, 64))))
        );
        customPanel3Layout.setVerticalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        add(customPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Tidak ada data yang akan ditampilkan");

        javax.swing.GroupLayout pnNoDataLayout = new javax.swing.GroupLayout(pnNoData);
        pnNoData.setLayout(pnNoDataLayout);
        pnNoDataLayout.setHorizontalGroup(
            pnNoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNoDataLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pnNoDataLayout.setVerticalGroup(
            pnNoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNoDataLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        add(pnNoData, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 390, -1));

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
        jScrollPane1.setViewportView(tblData);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 1150, 290));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Nomor Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Harga Total");
        try {
            ResultSet rs = db.ambilData("SELECT * FROM transaksi");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_transaksi"), rs.getString("tanggal_transaksi"), rs.getString("harga_total")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
        pnNoData.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Tanggal");
        model.addColumn("Total barang yang terjual");
        model.addColumn("Total pendapatan");
        try {
            ResultSet rs = db.ambilData("SELECT dt.id_transaksi, dt.tanggal, dt.kode_barang AS \"kode_barang\", COUNT(dt.kode_barang) AS \"jumlah_barang\", SUM(dt.harga_barang * dt.jumlah_barang) AS \"total_harga\" FROM detail_transaksi dt JOIN stok_barang sb ON dt.kode_barang = sb.kode_barang GROUP BY dt.tanggal;");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("tanggal"), rs.getString("jumlah_barang"), rs.getString("total_harga")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        boolean edit = tblData.isEditing();
        if(!edit) {
            JOptionPane.showMessageDialog(this, "Tidak bisa mengedit data ini!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok Tersedia");
        model.addColumn("Harga Barang");
        try {
            ResultSet rs = db.ambilData("SELECT * FROM stok_barang");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("kode_barang"), rs.getString("nama_barang"), rs.getString("stok_tersedia"), rs.getString("harga_jual")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
        pnNoData.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomPanel customPanel1;
    private CustomComponent.CustomPanel customPanel2;
    private CustomComponent.CustomPanel customPanel3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCountDataBarang;
    private javax.swing.JLabel lbCountDataKeuangan;
    private javax.swing.JLabel lbCountDataTransaksi;
    private javax.swing.JPanel pnNoData;
    private CustomComponent.CustomTable tblData;
    // End of variables declaration//GEN-END:variables
}
