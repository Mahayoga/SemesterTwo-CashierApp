/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PanelFormTambah;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import koneksi.koneksi;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Mahayoga
 */
public class pnTambahBarang extends javax.swing.JPanel {
    koneksi db = new koneksi();
    private BatalBarang batalBarang;
    private SimpanBarang simpanBarang;

    /**
     * Creates new form pnTambahBarang
     */
    public pnTambahBarang() {
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
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventSimpan();
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
    public interface SimpanBarang {
        void simpanBarang();
    }
    
    public void setVariableBatal(BatalBarang bs) {
        this.batalBarang = bs;
    }
    public void setVariableSimpan(SimpanBarang es) {
        this.simpanBarang = es;
    }
    
    public void theEventBatal() {
        if(batalBarang != null) {
            batalBarang.batalBarang();
        }
    }
    public void theEventSimpan() {
        if(simpanBarang != null) {
            simpanBarang.simpanBarang();
        }
    }
    
    public void simpanData() {
        /*
        id_barang	
        kode_barang	
        id_supplier	
        nama_barang	
        stok_tersedia	
        harga_beli	
        harga_jual	
        kategori_barang
        
        1 Makanan
        2 Minuman
        3 Kebutuhan Pokok
        4 ATK
        5 Obat
        6 Aksesoris
        */
        HashMap<String, Integer> kategoriMap = new HashMap<>();
        HashMap<String, Integer> supplierMap = new HashMap<>();
        ResultSet rs1 = db.ambilData("SELECT * FROM kategori");
        ResultSet rs2 = db.ambilData("SELECT * FROM suppliers");
        try {
            for(int i = 1; rs1.next(); i++) {
                kategoriMap.put(rs1.getString("nama_kategori"), i);
            }
            for(int i = 1; rs2.next(); i++) {
                supplierMap.put(rs2.getString("nama_supplier"), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        map.put("Makanan", 1); // MK
//        map.put("Minuman", 2); // MN
//        map.put("Kebutuhan Pokok", 3); // KP
//        map.put("ATK", 4); // ATK
//        map.put("Obat", 5); // OB
//        map.put("Aksesoris", 6); // AKS

        String a = String.valueOf(cbKategori.getSelectedItem());
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE kategori_barang = '" + kategoriMap.get(a) + "' ORDER BY id_barang DESC");
        try {
            rs.next();
            String id;
            System.out.println(rs.getString("id_barang").substring(0, 2));
            switch(rs.getString("id_barang").substring(0, 2)) {
                case "MK" -> {
                    id = "MK";
                    insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                }
                case "MN" -> {
                    id = "MN";
                    insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                }
                case "KP" -> {
                    id = "KP";
                    insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                }
                case "ATK" -> {
                    id = "ATK";
                    insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                }
                case "OB" -> {
                    id = "OB";
                    insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                }
                case "AKS" -> {
                    id = "AKS";
                    insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String addIdPlusOne(ResultSet rs, String id) {
        try {
            int idNum = Integer.parseInt(rs.getString("id_barang").substring(2, 6)); //BR0001
            idNum++;
            if(idNum < 9) {
                return id += "000" + idNum;
            } else if(idNum < 99) {
                return id += "00" + idNum;
            } else if(idNum < 999) {
                return id += "0" + idNum;
            } else if(idNum < 9999) {
                return id += idNum;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void insertDataToTable(String id, HashMap<String, Integer> kat, HashMap<String, Integer> sup) {
        /*id_barang	
        kode_barang	
        id_supplier	
        nama_barang	
        stok_tersedia	
        harga_beli	
        harga_jual	
        kategori_barang*/
        String kd = tfKodeBarang.getText();
        String supp = String.valueOf(sup.get(String.valueOf(cbNamaSupplier.getSelectedItem())));
        String nama = tfNamaBarang.getText();
        String stok = tfStok.getText();
        String hargaBeli = tfHargaBeli.getText();
        String hargaJual = tfHargaJual.getText();
        String kate = String.valueOf(kat.get(String.valueOf(cbKategori.getSelectedItem())));
        
        try {
            db.aksi("INSERT INTO stok_barang VALUES ('" + id + "', '" + kd + "', '" + supp + "', '" + nama + "', '" + stok + "', '" + hargaBeli + "', '" + hargaJual + "', '" + kate + "')");
        } catch (Exception e) {
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

        jLabel6 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfNamaBarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfStok = new javax.swing.JTextField();
        tfHargaBeli = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbKategori = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        tfHargaJual = new javax.swing.JTextField();
        btnBatal = new CustomComponent.CustomButton();
        btnSimpan = new CustomComponent.CustomButton();
        jLabel11 = new javax.swing.JLabel();
        cbNamaSupplier = new javax.swing.JComboBox<>();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Kode Barang ");

        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Nama Barang ");

        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Stok Tersedia");

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

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Tambah Data Barang");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Kategori");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Harga Jual");

        tfHargaJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaJualActionPerformed(evt);
            }
        });

        btnBatal.setText("customButton1");
        btnBatal.setBackgroundColor(new java.awt.Color(255, 151, 51));
        btnBatal.setTextColor(java.awt.Color.white);
        btnBatal.setTheText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnSimpan.setText("customButton1");
        btnSimpan.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnSimpan.setTextColor(java.awt.Color.white);
        btnSimpan.setTheText("Simpan");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Nama Supplier");

        cbNamaSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(cbNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeBarangActionPerformed

    private void tfNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangActionPerformed

    private void tfStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfStokActionPerformed

    private void tfHargaBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaBeliActionPerformed

    private void tfHargaJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaJualActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnBatal;
    private CustomComponent.CustomButton btnSimpan;
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
