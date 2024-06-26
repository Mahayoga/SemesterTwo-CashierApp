/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PanelFormTambah;

import com.barcodelib.barcode.Linear;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import koneksi.koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
        addItemToComboBox();
        checkCategori();
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
    
    public boolean checkAllForm() {
        if(tfIdBarang.getText().equals("Pilih kategori dahulu!") || tfKodeBarang.getText().equals("") || tfNamaBarang.getText().equals("") || cbNamaSupplier.getSelectedItem().toString().equals("--Tidak dipili--") || tfNamaPerushaan.getText().equals("") || tfStok.getText().equals("") || cbKategori.getSelectedItem().toString().equals("--Tidak dipili--") || tfHargaBeli.getText().equals("") || tfHargaJual.getText().equals("")) {
            return true;
        }
        return false;
    }
    
    public boolean checkIfIsAlreadyAvailable() {
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang");
        try {
            while(rs.next()) {
                if(tfNamaBarang.getText().toLowerCase().equals(rs.getString("nama_barang").toLowerCase()) || tfKodeBarang.getText().toLowerCase().equals(rs.getString("kode_barang").toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean checkTheInputIsValid() {
        try {
            Integer.parseInt(tfStok.getText());
            Integer.parseInt(tfHargaBeli.getText());
            Integer.parseInt(tfHargaJual.getText());
            return false;
        } catch(Exception e) {
            return true;
        }
    }
    
    public void resetAll() {
        addItemToComboBox();
        checkCategori();
        tfKodeBarang.setText("");
        tfNamaBarang.setText("");
        tfNamaPerushaan.setText("");
        tfStok.setText("");
        tfHargaBeli.setText("");
        tfHargaJual.setText("");
    }
    
    public void addItemToComboBox() {
        cbKategori.removeAllItems();
        cbNamaSupplier.removeAllItems();
        cbKategori.addItem("--Tidak dipilih--");
        cbNamaSupplier.addItem("--Tidak dipilih--");
        
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
    }
    
    public void setBarcodeImage(int width, int height, JLabel label, String path) {        
        try {
            File file = new File("src/BarcodeImg/" + path);
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            label.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void checkCategori() {
        if(String.valueOf(cbKategori.getSelectedItem()).equals("--Tidak dipilih--")) {
            tfIdBarang.setText("Pilih kategori dahulu!");
        } else {
            ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE k.nama_kategori = '" + String.valueOf(cbKategori.getSelectedItem()) + "' ORDER BY sb.id_barang DESC");
            try {
                if(rs.next()) {
                    int id = Integer.parseInt(rs.getString("id_barang").substring(2, 6)); //KP0001 
                    id++;
                    if(id <= 9) {
                        tfIdBarang.setText(rs.getString("id_barang").substring(0, 2) + "000" + id);
                    } else if(id <= 99) {
                        tfIdBarang.setText(rs.getString("id_barang").substring(0, 2) + "00" + id);
                    } else if(id <= 999) {
                        tfIdBarang.setText(rs.getString("id_barang").substring(0, 2) + "0" + id);
                    } else if(id <= 9999) {
                        tfIdBarang.setText(rs.getString("id_barang").substring(0, 2) + "" + id);
                    }
                } else {
                    switch(String.valueOf(cbKategori.getSelectedItem())) {
                        case "Makanan" -> {
                            tfIdBarang.setText("MK0001");
                        }
                        case "Minuman" -> {
                            tfIdBarang.setText("MN0001");
                        }
                        case "Kebutuhan Pokok" -> {
                            tfIdBarang.setText("KP0001");
                        }
                        case "ATK" -> {
                            tfIdBarang.setText("AK0001");
                        }
                        case "Obat" -> {
                            tfIdBarang.setText("OB0001");
                        }
                        case "Aksesoris" -> {
                            tfIdBarang.setText("AS0001");
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
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
        HashMap<String, String> supplierMap = new HashMap<>();
        ResultSet rs1 = db.ambilData("SELECT * FROM kategori");
        ResultSet rs2 = db.ambilData("SELECT * FROM suppliers");
        try {
            for(int i = 1; rs1.next(); i++) {
                kategoriMap.put(rs1.getString("nama_kategori"), i);
            }
            for(int i = 1; rs2.next(); i++) {
                supplierMap.put(rs2.getString("nama_supplier"), rs2.getString("id_supplier"));
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
            if(rs.next()) {
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
                    case "AK" -> {
                        id = "AK";
                        insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                    }
                    case "OB" -> {
                        id = "OB";
                        insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                    }
                    case "AS" -> {
                        id = "AS";
                        insertDataToTable(addIdPlusOne(rs, id), kategoriMap, supplierMap);
                    }
                }
            } else {
                String id;
                switch(cbKategori.getSelectedItem().toString()) {
                    case "Makanan" -> {
                        id = "MK";
                        insertDataToTable(id + "0001", kategoriMap, supplierMap);
                    }
                    case "Minuman" -> {
                        id = "MN";
                        insertDataToTable(id + "0001", kategoriMap, supplierMap);
                    }
                    case "Kebutuhan Pokok" -> {
                        id = "KP";
                        insertDataToTable(id + "0001", kategoriMap, supplierMap);
                    }
                    case "ATK" -> {
                        id = "AK";
                        insertDataToTable(id + "0001", kategoriMap, supplierMap);
                    }
                    case "Obat" -> {
                        id = "OB";
                        insertDataToTable(id + "0001", kategoriMap, supplierMap);
                    }
                    case "Aksesoris" -> {
                        id = "AS";
                        insertDataToTable(id + "0001", kategoriMap, supplierMap);
                    }
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
            if(idNum <= 9) {
                return id += "000" + idNum;
            } else if(idNum <= 99) {
                return id += "00" + idNum;
            } else if(idNum <= 999) {
                return id += "0" + idNum;
            } else if(idNum <= 9999) {
                return id += idNum;
            }
            return id + "0001";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void insertDataToTable(String id, HashMap<String, Integer> kat, HashMap<String, String> sup) {
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
        jLabel1 = new javax.swing.JLabel();
        tfIdBarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfNamaPerushaan = new javax.swing.JTextField();
        lbBarcode = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("ID Barang ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });
        add(tfKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 320, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Nama Barang ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });
        add(tfNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 310, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Stok Tersedia");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 243, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Harga Beli");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        tfStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStokActionPerformed(evt);
            }
        });
        add(tfStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 110, 30));

        tfHargaBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaBeliActionPerformed(evt);
            }
        });
        add(tfHargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 150, 30));

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Tambah Data Barang");
        add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 32, 370, 33));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Kategori");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 243, -1, -1));

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--" }));
        cbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriActionPerformed(evt);
            }
        });
        add(cbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 180, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Harga Jual");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, -1));

        tfHargaJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaJualActionPerformed(evt);
            }
        });
        add(tfHargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 150, 30));

        btnBatal.setText("customButton1");
        btnBatal.setBackgroundColor(new java.awt.Color(255, 151, 51));
        btnBatal.setTextColor(java.awt.Color.white);
        btnBatal.setTheText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 90, 40));

        btnSimpan.setText("customButton1");
        btnSimpan.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnSimpan.setTextColor(java.awt.Color.white);
        btnSimpan.setTheText("Simpan");
        add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 90, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Nama Supplier");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        cbNamaSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--" }));
        cbNamaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamaSupplierActionPerformed(evt);
            }
        });
        add(cbNamaSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 130, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Barcode");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 118, 34));

        tfIdBarang.setEnabled(false);
        add(tfIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 140, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Kode Barang ");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Nama Perusahaan");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, -1, 20));

        tfNamaPerushaan.setEnabled(false);
        tfNamaPerushaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaPerushaanActionPerformed(evt);
            }
        });
        add(tfNamaPerushaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 190, 30));
        add(lbBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 180, 60));
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128);
            barcode.setData(tfKodeBarang.getText());
            barcode.setI(1.0f);
            String path = tfNamaBarang.getText();
            
            File file = new File("src/BarcodeImg");
            barcode.renderBarcode(file.getAbsolutePath() + "\\" + path + ".png");
            setBarcodeImage(180, 60, lbBarcode, path + ".png");
        } catch(Exception e) {
            e.printStackTrace();
        }
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

    private void cbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriActionPerformed
        // TODO add your handling code here:
        checkCategori();
    }//GEN-LAST:event_cbKategoriActionPerformed

    private void cbNamaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamaSupplierActionPerformed
        // TODO add your handling code here:
        if(cbNamaSupplier.getSelectedItem() != null && !cbNamaSupplier.getSelectedItem().toString().equals("--Tidak dipilih--")) {
            ResultSet rs = db.ambilData("SELECT * FROM suppliers WHERE nama_supplier = '" + cbNamaSupplier.getSelectedItem().toString() + "'");
            try {
                if(rs.next()) {
                    tfNamaPerushaan.setText(rs.getString("asal_perusahaan"));
                } else {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan data supplier dengan nama '" + cbNamaSupplier.getSelectedItem().toString() + "'!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_cbNamaSupplierActionPerformed

    private void tfNamaPerushaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaPerushaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaPerushaanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnBatal;
    private CustomComponent.CustomButton btnSimpan;
    private javax.swing.JComboBox<String> cbKategori;
    private javax.swing.JComboBox<String> cbNamaSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField tfHargaBeli;
    private javax.swing.JTextField tfHargaJual;
    private javax.swing.JTextField tfIdBarang;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfNamaBarang;
    private javax.swing.JTextField tfNamaPerushaan;
    private javax.swing.JTextField tfStok;
    // End of variables declaration//GEN-END:variables
}
