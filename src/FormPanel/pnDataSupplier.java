/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package FormPanel;

import Form.MenuUtama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.sql.ResultSet;

/**
 *
 * @author Mahayoga
 */
public class pnDataSupplier extends javax.swing.JPanel {
    private TambahData tambahData;
    private EditData editData;
    private AmbilDataPadaTabel ambilDataPadaTabel;
    private HapusData hapusData;
    DefaultTableModel model = new DefaultTableModel();
    koneksi db = new koneksi();

    /**
     * Creates new form pnDataSupplier
     */
    public pnDataSupplier() {
        initComponents();
        setColumn();
        setRow();
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theTambahEvent();
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEditEvent();
            }
        });
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theHapusEvent();
            }
        });
        tblData.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                String a = String.valueOf(tblData.getValueAt(tblData.getSelectedRow(), 0));
                if(!a.equals("")) {
                    btnEdit.setEnabled(true);
                    btnHapus.setEnabled(true);
                }
                theAmbilEvent(a);
            }
        });
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    public interface TambahData {
        void supplierTambahData();
    }
    public interface EditData {
        void supplierEditData();
    }
    public interface AmbilDataPadaTabel {
        void ambilDataSupplier(String id);
    }
    public interface HapusData {
        void supplierHapusData();
    }
    
    public void setVariableTambah(TambahData td) {
        this.tambahData = td;
    }
    public void setVariableEdit(EditData ed) {
        this.editData = ed;
    }
    public void setVariableAmbil(AmbilDataPadaTabel adpt) {
        this.ambilDataPadaTabel = adpt;
    }
    public void setVariableHapus(HapusData hd) {
        this.hapusData = hd;
    }
    
    public void theTambahEvent() {
        if(tambahData != null) {
            tambahData.supplierTambahData();
        }
    }
    public void theEditEvent() {
        if(editData != null) {
            editData.supplierEditData();
        }
    }
    public void theAmbilEvent(String id) {
        if(ambilDataPadaTabel != null) {
            ambilDataPadaTabel.ambilDataSupplier(id);
        }
    }
    public void theHapusEvent() {
        if(hapusData != null) {
            hapusData.supplierHapusData();
        }
    }
    
    public void resetAllForm() {
        tfCari.setText("");
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    public void setColumn() {
        model.setColumnCount(0);
        model.addColumn("ID Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Alamat");
        model.addColumn("No Telp");
        model.addColumn("Email");
        model.addColumn("Asal Perusahaan");
        tblData.setModel(model);
    }
    
    public void setRow() {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM suppliers");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_supplier"), rs.getString("nama_supplier"), rs.getString("alamat_supplier"), rs.getString("no_telp"), rs.getString("email_supplier"), rs.getString("asal_perusahaan")});
            }
            tblData.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRow(String search) {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM suppliers WHERE nama_supplier LIKE '%" + search + "%' OR alamat_supplier LIKE '%" + search + "%' OR no_telp LIKE '%" + search + "%' OR email_supplier LIKE '%" + search + "%' OR asal_perusahaan LIKE '%" + search + "%';");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_supplier"), rs.getString("nama_supplier"), rs.getString("alamat_supplier"), rs.getString("no_telp"), rs.getString("email_supplier"), rs.getString("asal_perusahaan")});
            }
            tblData.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hapusData(String id) {
        try {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin menghapus data barang dengan ID: '" + id + "'?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(asn == JOptionPane.YES_NO_OPTION) {
                db.aksi("DELETE FROM suppliers WHERE id_supplier = '" + id + "'");
                JOptionPane.showMessageDialog(this, "Hapus data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
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

        lbTitle = new javax.swing.JLabel();
        tfCari = new javax.swing.JTextField();
        customButton1 = new CustomComponent.CustomButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new CustomComponent.CustomTable();
        btnHapus = new CustomComponent.CustomButton();
        btnEdit = new CustomComponent.CustomButton();
        btnTambah = new CustomComponent.CustomButton();

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Data Supplier");

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

        customButton1.setText("CARI");
        customButton1.setBackgroundColor(new java.awt.Color(204, 204, 204));
        customButton1.setFontSize(14);
        customButton1.setTheText("Cari");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });

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

        btnHapus.setText("customButton6");
        btnHapus.setBackgroundColor(new java.awt.Color(247, 64, 64));
        btnHapus.setFontSize(14);
        btnHapus.setTextBold(1);
        btnHapus.setTextColor(java.awt.Color.white);
        btnHapus.setTheText("Hapus");

        btnEdit.setText("customButton4");
        btnEdit.setBackgroundColor(new java.awt.Color(0, 51, 255));
        btnEdit.setFontSize(14);
        btnEdit.setTextBold(1);
        btnEdit.setTextColor(java.awt.Color.white);
        btnEdit.setTheText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnTambah.setText("customButton4");
        btnTambah.setBackgroundColor(new java.awt.Color(0, 51, 255));
        btnTambah.setFontSize(14);
        btnTambah.setTextBold(1);
        btnTambah.setTextColor(java.awt.Color.white);
        btnTambah.setTheText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariActionPerformed
        // TODO add your handling code here:
        setRow(tfCari.getText());
    }//GEN-LAST:event_tfCariActionPerformed

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        // TODO add your handling code here:
        setRow(tfCari.getText());
    }//GEN-LAST:event_customButton1ActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        boolean edit = tblData.isEditing();
        if(!edit) {
            JOptionPane.showMessageDialog(this, "Tidak bisa mengedit data ini!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambahActionPerformed

    private void tfCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyReleased
        // TODO add your handling code here:
        if(tfCari.getText().equals("")) {
            setRow();
        }
    }//GEN-LAST:event_tfCariKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnEdit;
    private CustomComponent.CustomButton btnHapus;
    private CustomComponent.CustomButton btnTambah;
    private CustomComponent.CustomButton customButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTitle;
    private CustomComponent.CustomTable tblData;
    private javax.swing.JTextField tfCari;
    // End of variables declaration//GEN-END:variables
}
