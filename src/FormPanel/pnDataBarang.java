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
public class pnDataBarang extends javax.swing.JPanel {
    private TambahData tambahData;
    private EditData editData;
    private AmbilData ambilData;
    private HapusData hapusData;
    private DataKadaluarsa dataKadaluarsa;
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
        btnKadaluarsa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventKadaluarsa();
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
    public interface DataKadaluarsa {
        void dataKadaluarsa();
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
    public void setVariableKadaluarsa(DataKadaluarsa dk) {
        this.dataKadaluarsa = dk;
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
    public void theEventKadaluarsa() {
        if(dataKadaluarsa != null) {
            dataKadaluarsa.dataKadaluarsa();
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
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Kategori Barang");
        model.addColumn("Stok Tersedia");
        model.addColumn("Harga Jual");
        model.addColumn("Nama Supplier");
        tblData.setModel(model);
    }
    
    public void setRow() {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), changeToRp(rs.getString("harga_jual")), rs.getString("nama_supplier")});
            }
            tblData.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRow(String kategori) {
        model.setRowCount(0);
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier WHERE k.nama_kategori = '" + kategori + "'");
        try {
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), changeToRp(rs.getString("harga_jual")), rs.getString("nama_supplier")});
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
                rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier WHERE id_barang LIKE '%" + search + "%'");
                if(!rs.next()) {
                    rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier WHERE nama_barang LIKE '%" + search + "%'");
                }
                do {
                    model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), changeToRp(rs.getString("harga_jual")), rs.getString("nama_supplier")});
                } while(rs.next());
            } else {
                rs = db.ambilData("SELECT  * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier WHERE sb.id_barang LIKE '%" + search + "%' AND k.nama_kategori = '" + katt + "'");
                if(!rs.next()) {
                    rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier WHERE sb.nama_barang LIKE '%" + search + "%' AND k.nama_kategori = '" + katt + "'");
                }
                do {
                    model.addRow(new Object[]{rs.getString("id_barang"), rs.getString("nama_barang"), rs.getString("nama_kategori"), rs.getString("stok_tersedia"), changeToRp(rs.getString("harga_jual")), rs.getString("nama_supplier")});
                } while(rs.next());
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
        btnKadaluarsa = new CustomComponent.CustomButton();

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Data Barang");

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

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--", "Makanan", "Minuman", "Kebutuhan Pokok", "ATK", "Obat" }));
        cbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriActionPerformed(evt);
            }
        });

        jLabel1.setText("*Urutkan berdasarkan Kategori*");

        btnKadaluarsa.setText("customButton4");
        btnKadaluarsa.setBackgroundColor(new java.awt.Color(0, 51, 255));
        btnKadaluarsa.setFontSize(14);
        btnKadaluarsa.setTextBold(1);
        btnKadaluarsa.setTextColor(java.awt.Color.white);
        btnKadaluarsa.setTheText("Cek Kadaluarsa");
        btnKadaluarsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKadaluarsaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnKadaluarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKadaluarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btnKadaluarsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKadaluarsaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKadaluarsaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnEdit;
    private CustomComponent.CustomButton btnHapus;
    private CustomComponent.CustomButton btnKadaluarsa;
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
