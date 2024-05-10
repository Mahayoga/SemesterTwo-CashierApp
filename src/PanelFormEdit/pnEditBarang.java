/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PanelFormEdit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.HashMap;
import koneksi.koneksi;

/**
 *
 * @author Mahayoga
 */
public class pnEditBarang extends javax.swing.JPanel {
    koneksi db = new koneksi();
    private BatalBarang batalBarang;
    private EditBarang editBarang;

    /**
     * Creates new form pnEditDataBarang
     */
    public pnEditBarang(String id) {
        initComponents();
        ResultSet rs1 = db.ambilData("SELECT * FROM kategori");
        ResultSet rs2 = db.ambilData("SELECT * FROM suppliers");
        try {
            for(int i = 1; rs1.next(); i++) {
                cbKategori.addItem(rs1.getString("nama_kategori"));
            }
            for(int i = 1; rs2.next(); i++) {
                cbNamaSupplier.addItem(rs2.getString("nama_supplier"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setData(id);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventEdit();
            }
        });
        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventBatal();
            }
        });
    }
    
    public interface BatalBarang {
        void batalBarang();
    }
    public interface EditBarang {
        void editBarang();
    }
    
    public void setVariableBatal(BatalBarang bs) {
        this.batalBarang = bs;
    }
    public void setVariableEdit(EditBarang es) {
        this.editBarang = es;
    }
    
    public void theEventBatal() {
        if(batalBarang != null) {
            batalBarang.batalBarang();
        }
    }
    public void theEventEdit() {
        if(editBarang != null) {
            editBarang.editBarang();
        }
    }
    public void setData(String id) {
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE id_barang = '" + id + "'");
        try {
            if(rs.next()) {
                tfKodeBarang.setText(rs.getString("kode_barang"));
                cbNamaSupplier.setSelectedItem(rs.getString("nama_supplier"));
                tfNamaBarang.setText(rs.getString("nama_barang"));
                tfStok.setText(rs.getString("stok_tersedia"));
                tfHargaBeli.setText(rs.getString("harga_beli"));
                tfHargaJual.setText(rs.getString("harga_jual"));
                cbKategori.setSelectedItem(rs.getString("nama_kategori"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void simpanData(String id) {
        ResultSet rs1 = db.ambilData("SELECT * FROM kategori");
        ResultSet rs2 = db.ambilData("SELECT * FROM suppliers");
        try {
            for(int i = 1; rs1.next(); i++) {
                cbKategori.addItem(rs1.getString("nama_kategori"));
            }
            for(int i = 1; rs2.next(); i++) {
                cbNamaSupplier.addItem(rs2.getString("nama_supplier"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> kategoriMap = new HashMap<>();
        HashMap<String, Integer> supplierMap = new HashMap<>();
        ResultSet rs3 = db.ambilData("SELECT * FROM kategori");
        ResultSet rs4 = db.ambilData("SELECT * FROM suppliers");
        try {
            for(int i = 1; rs3.next(); i++) {
                kategoriMap.put(rs3.getString("nama_kategori"), i);
            }
            for(int i = 1; rs4.next(); i++) {
                supplierMap.put(rs4.getString("nama_supplier"), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String kd = tfKodeBarang.getText();
        String supp = String.valueOf(supplierMap.get(String.valueOf(cbNamaSupplier.getSelectedItem())));
        String nama = tfNamaBarang.getText();
        String stok = tfStok.getText();
        String hargaBeli = tfHargaBeli.getText();
        String hargaJual = tfHargaJual.getText();
        String kate = String.valueOf(kategoriMap.get(String.valueOf(cbKategori.getSelectedItem())));
        
        db.aksi("UPDATE stok_barang SET kode_barang = '" + kd + "', id_supplier = '" + supp + "', nama_barang = '" + nama + "', stok_tersedia = '" + stok + "', harga_beli = '" + hargaBeli + "', harga_jual = '" + hargaJual + "', kategori_barang = '" + kate + "' WHERE id_barang = '" + id + "'");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEdit = new CustomComponent.CustomButton();
        lbTitle = new javax.swing.JLabel();
        btnBatal = new CustomComponent.CustomButton();
        tfNamaBarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbNamaSupplier = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tfStok = new javax.swing.JTextField();
        tfHargaBeli = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        tfHargaJual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        btnEdit.setText("customButton1");
        btnEdit.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnEdit.setTextColor(java.awt.Color.white);
        btnEdit.setTheText("Simpan");

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Edit Data Barang");

        btnBatal.setText("customButton1");
        btnBatal.setBackgroundColor(new java.awt.Color(255, 151, 51));
        btnBatal.setTextColor(java.awt.Color.white);
        btnBatal.setTheText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Stok Tersedia");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Nama Supplier");

        cbNamaSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Harga Beli");

        tfStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStokActionPerformed(evt);
            }
        });

        tfHargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaBeliActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Kategori");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Kode Barang ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Harga Jual");

        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });

        tfHargaJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaJualActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Nama Barang ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tfStok, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(cbKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(cbNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(628, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(15, 15, 15)
                .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbNamaSupplier)
                    .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tfNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangActionPerformed

    private void tfStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfStokActionPerformed

    private void tfHargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaBeliActionPerformed

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeBarangActionPerformed

    private void tfHargaJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaJualActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnBatal;
    private CustomComponent.CustomButton btnEdit;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JComboBox<String> cbNamaSupplier;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField tfHargaBeli;
    private javax.swing.JTextField tfHargaJual;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfNamaBarang;
    private javax.swing.JTextField tfStok;
    // End of variables declaration//GEN-END:variables
}
