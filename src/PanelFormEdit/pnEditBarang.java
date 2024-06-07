/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PanelFormEdit;

import com.barcodelib.barcode.Linear;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        setData(id);
        setBarcodeImage(180, 60, lbBarcode, tfNamaBarang.getText() + ".png");
        addItemToComboBox();
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
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
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
    
    public void setData(String id) {
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang sb INNER JOIN suppliers s ON sb.id_supplier = s.id_supplier INNER JOIN kategori k ON sb.kategori_barang = k.id_kategori WHERE id_barang = '" + id + "'");
        try {
            if(rs.next()) {
                tfIdBarang.setText(id);
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
        HashMap<String, String> supplierMap = new HashMap<>();
        ResultSet rs3 = db.ambilData("SELECT * FROM kategori");
        ResultSet rs4 = db.ambilData("SELECT * FROM suppliers");
        try {
            for(int i = 1; rs3.next(); i++) {
                kategoriMap.put(rs3.getString("nama_kategori"), i);
            }
            for(int i = 1; rs4.next(); i++) {
                supplierMap.put(rs4.getString("nama_supplier"), rs4.getString("id_supplier"));
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
        
        db.aksi("UPDATE stok_barang SET kode_barang = '" + kd + "', id_supplier = '" + supp + "', nama_barang = '" + nama + "', stok_tersedia = '" + stok + "', harga_beli = '" + hargaBeli + "', harga_jual = '" + hargaJual + "', kategori_barang = '" + kate + "', id_barang = '" + tfIdBarang.getText() + "' WHERE id_barang = '" + id + "'");
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
        jLabel10 = new javax.swing.JLabel();
        tfHargaJual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfIdBarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        tfNamaPerushaan = new javax.swing.JTextField();
        lbBarcode = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEdit.setText("customButton1");
        btnEdit.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnEdit.setTextColor(java.awt.Color.white);
        btnEdit.setTheText("Simpan");
        add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 90, 40));

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Edit Data Barang");
        add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 32, 370, 33));

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

        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });
        add(tfNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 203, 310, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Stok Tersedia");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 243, -1, -1));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Nama Barang ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 163, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Barcode");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 118, 34));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("ID Barang ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        tfIdBarang.setEnabled(false);
        add(tfIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 140, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Kode Barang ");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });
        add(tfKodeBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 140, 30));

        tfNamaPerushaan.setEnabled(false);
        tfNamaPerushaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaPerushaanActionPerformed(evt);
            }
        });
        add(tfNamaPerushaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 190, 30));
        add(lbBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 180, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Nama Perusahaan");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, -1, 20));
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

    private void tfHargaJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaJualActionPerformed

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

    private void tfNamaPerushaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaPerushaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaPerushaanActionPerformed

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

    private void cbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriActionPerformed
        // TODO add your handling code here:
        checkCategori();
    }//GEN-LAST:event_cbKategoriActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnBatal;
    private CustomComponent.CustomButton btnEdit;
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
