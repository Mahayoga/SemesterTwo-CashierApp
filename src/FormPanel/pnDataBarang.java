/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package FormPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author Mahayoga
 */
public class pnDataBarang extends javax.swing.JPanel {
    private TambahData tambahData;
    private EditData editData;
    private AmbilData ambilData;
    private HapusData hapusData;
    DefaultTableModel model = new DefaultTableModel();
    koneksi db = new koneksi();

    /**
     * Creates new form pnDataBarang
     */
    public pnDataBarang() {
        initComponents();
        setColumn();
        setRow();
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventTambah();
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventEdit();
            }
        });
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventHapus();
            }
        }); 
        tblData.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String a = String.valueOf(tblData.getValueAt(tblData.getSelectedRow(), 0));
                if(!a.equals("")) {
                    btnEdit.setEnabled(true);
                    btnHapus.setEnabled(true);
                }
                theEventAmbil(a);
            }

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
            
        });
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    public interface TambahData {
        void tambahData();
    }
    public interface EditData {
        void editData();
    }
    public interface AmbilData {
        void ambilData(String id);
    }
    public interface HapusData {
        void hapusData();
    }
    
    public void setVariableTambah(TambahData td) {
        this.tambahData = td;
    }
    public void setVariableEdit(EditData ed) {
        this.editData = ed;
    }
    public void setVariableAmbil(AmbilData ad) {
        this.ambilData = ad;
    }
    public void setVariableHapus(HapusData hd) {
        this.hapusData = hd;
    }
    
    public void theEventTambah() {
        if(tambahData != null) {
            tambahData.tambahData();
        }
    }
    public void theEventEdit() {
        if(editData != null) {
            editData.editData();
        }
    }
    public void theEventAmbil(String id) {
        if(ambilData != null) {
            ambilData.ambilData(id);
        }
    }
    public void theEventHapus() {
        if(hapusData != null) {
            hapusData.hapusData();
        }
    }
    
    public void setColumn() {
        model.setColumnCount(0);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Kategori Barang");
        model.addColumn("Stok Tersedia");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        tblData.setModel(model);
    }
    
    public void setRow() {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), rs.getString("harga_beli"), rs.getString("harga_jual")});
            }
            tblData.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRow(String kategori) {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE k.nama_kategori = '" + kategori + "'");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), rs.getString("harga_beli"), rs.getString("harga_jual")});
            }
            tblData.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRow(String search, JComboBox kat) {
        model.setRowCount(0);
        String katt = String.valueOf(kat.getSelectedItem());
        try {
            ResultSet rs;
            if(katt.equals("--Tidak dipilih--")) {
                rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE id_barang LIKE '%" + search + "%'");
                if(!rs.next()) {
                    rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE nama_barang LIKE '%" + search + "%'");
                }
                while(rs.next()) {
                    model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), rs.getString("harga_beli"), rs.getString("harga_jual")});
                }
            } else {
                rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE sb.id_barang LIKE '%" + search + "%' AND k.nama_kategori = '" + katt + "'");
                if(!rs.next()) {
                    rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE nama_barang LIKE '%" + search + "%' AND k.nama_kategori = '" + katt + "'");
                }
                while(rs.next()) {
                    model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), rs.getString("harga_beli"), rs.getString("harga_jual")});
                }
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
                db.aksi("DELETE FROM stok_barang WHERE id_barang = '" + id + "'");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new CustomComponent.CustomTable();
        tfCari = new javax.swing.JTextField();
        btnHapus = new CustomComponent.CustomButton();
        btnEdit = new CustomComponent.CustomButton();
        btnTambah = new CustomComponent.CustomButton();
        customButton1 = new CustomComponent.CustomButton();
        cbKategori = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Data Barang");
        add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 370, 33));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 1060, 350));

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
        add(tfCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 190, 30));

        btnHapus.setText("customButton6");
        btnHapus.setBackgroundColor(new java.awt.Color(247, 64, 64));
        btnHapus.setFontSize(14);
        btnHapus.setTextBold(1);
        btnHapus.setTextColor(java.awt.Color.white);
        btnHapus.setTheText("Hapus");
        add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 490, 90, 40));

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
        add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 90, 40));

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
        add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 490, 90, 40));

        customButton1.setText("CARI");
        customButton1.setBackgroundColor(new java.awt.Color(204, 204, 204));
        customButton1.setFontSize(14);
        customButton1.setTheText("Cari");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });
        add(customButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, 70, 30));

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--", "Makanan", "Minuman", "Kebutuhan Pokok", "ATK", "Obat" }));
        cbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriActionPerformed(evt);
            }
        });
        add(cbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 160, 30));

        jLabel1.setText("*Urutkan berdasarkan Kategori*");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        boolean edit = tblData.isEditing();
        if(!edit) {
            JOptionPane.showMessageDialog(this, "Tidak bisa mengedit data ini! Silahkan edit dari tombol \"Edit\" dibawah", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void tfCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariActionPerformed
        // TODO add your handling code here:
        setRow(tfCari.getText(), cbKategori);
    }//GEN-LAST:event_tfCariActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTambahActionPerformed

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        // TODO add your handling code here:
        setRow(tfCari.getText(), cbKategori);
    }//GEN-LAST:event_customButton1ActionPerformed

    private void cbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriActionPerformed
        // TODO add your handling code here:
        if(!cbKategori.getSelectedItem().equals("--Tidak dipilih--")) {
            setRow(tfCari.getText(), cbKategori);
        } else {
            setRow(tfCari.getText(), cbKategori);
        }
    }//GEN-LAST:event_cbKategoriActionPerformed

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
    private javax.swing.JComboBox<String> cbKategori;
    private CustomComponent.CustomButton customButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTitle;
    private CustomComponent.CustomTable tblData;
    private javax.swing.JTextField tfCari;
    // End of variables declaration//GEN-END:variables
}
