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
import java.text.NumberFormat;
import java.util.Locale;
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
public class pnDataKeuangan extends javax.swing.JPanel {
    private TambahData tambahData;
    private EditData editData;
    private AmbilData ambilData;
    private HapusData hapusData;
    private Keuangan keuangan;
    DefaultTableModel model = new DefaultTableModel();
    koneksi db = new koneksi();

    /**
     * Creates new form pnDataBarang
     */
    public pnDataKeuangan() {
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
        btnKeuangan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventKeuangan();
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
    public interface Keuangan {
        void cekData();
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
    public void setVariableKeuangan(Keuangan k) {
        this.keuangan = k;
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
    public void theEventKeuangan() {
        if(keuangan != null) {
            keuangan.cekData();
        }
    }
    
    public void resetAllForm() {
        cbKategori.setSelectedItem("--Tidak dipilih--");
        tfCari.setText("");
        btnHapus.setEnabled(false);
        btnEdit.setEnabled(false);
    }
    
    public String changeToNum(String num) {
        String result = "";
        for(int i = 0; i < num.replace('.', 'a').split("a").length; i++) {
            result += num.replace('.', 'a').split("a")[i];
        }
        return result.split("Rp ")[1];
    }
    
    public String changeToRp(String num) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp. " + nf.format(Double.parseDouble(num));
    }
    
    public void setColumn() {
        model.setColumnCount(0);
        model.addColumn("Kode Pengeluaran");
        model.addColumn("Tanggal Pengeluaran");
        model.addColumn("Jenis Pengeluaran");
        model.addColumn("Harga");
        model.addColumn("Keterangan");
        tblData.setModel(model);
    }
    
    public void setRow() {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM pengeluaran");
//      kode_pengeluaran
//	tanggal_pengeluara
//	jenis_pengeluaran
//	harga
//	keterangan
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
            }
            tblData.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRow(String kategori) {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM pengeluaran WHERE jenis_pengeluaran = '" + kategori + "'");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
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
                rs = db.ambilData("SELECT * FROM pengeluaran WHERE kode_pengeluaran LIKE '%" + search + "%'");
                if(rs.next()) {
                    model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
                    while(rs.next()) {
                        model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
                    }
                } else {
                    rs = db.ambilData("SELECT * FROM pengeluaran WHERE keterangan LIKE '%" + search + "%'");
                    while(rs.next()) {
                        model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
                    }
                }
            } else {
                rs = db.ambilData("SELECT * FROM pengeluaran WHERE kode_pengeluaran LIKE '%" + search + "%' AND jenis_pengeluaran LIKE '%" + katt + "%'"); // Kode, Jenis
                if(rs.next()) {
                    model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
                    while(rs.next()) {
                        model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
                    }
                } else {
                    rs = db.ambilData("SELECT * FROM pengeluaran WHERE keterangan LIKE '%" + search + "%' AND jenis_pengeluaran LIKE '%" + katt + "%'"); // Keterangan, Jenis
                    while(rs.next()) {
                        model.addRow(new Object[]{rs.getString("kode_pengeluaran"), rs.getString("tanggal_pengeluaran"), rs.getString("jenis_pengeluaran"), changeToRp(rs.getString("harga")), rs.getString("keterangan")});
                    }
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
                db.aksi("DELETE FROM pengeluaran WHERE kode_pengeluaran = '" + id + "'");
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
        btnKeuangan = new CustomComponent.CustomButton();

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Data Keuangan");

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
        tblData.setPreferredSize(new java.awt.Dimension(60, 160));
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

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

        customButton1.setText("CARI");
        customButton1.setBackgroundColor(new java.awt.Color(204, 204, 204));
        customButton1.setFontSize(14);
        customButton1.setTheText("Cari");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--", "Operasional", "Beli Barang", "Gaji Karyawan", "Lainnya" }));
        cbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriActionPerformed(evt);
            }
        });

        jLabel1.setText("*Pilih berdasarkan Jenis Pengeluaran*");

        btnKeuangan.setText("customButton6");
        btnKeuangan.setBackgroundColor(new java.awt.Color(247, 64, 64));
        btnKeuangan.setFontSize(14);
        btnKeuangan.setTextBold(1);
        btnKeuangan.setTextColor(java.awt.Color.white);
        btnKeuangan.setTheText("Cek Keuangan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKeuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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

    private void tfCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariKeyReleased
        // TODO add your handling code here:
        if(tfCari.getText().equals("")) {
            setRow();
        }
    }//GEN-LAST:event_tfCariKeyReleased

    private void cbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriActionPerformed
        // TODO add your handling code here:
        if(!cbKategori.getSelectedItem().equals("--Tidak dipilih--")) {
            setRow(tfCari.getText(), cbKategori);
        } else {
            setRow(tfCari.getText(), cbKategori);
        }
    }//GEN-LAST:event_cbKategoriActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnEdit;
    private CustomComponent.CustomButton btnHapus;
    private CustomComponent.CustomButton btnKeuangan;
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
