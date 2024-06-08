/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PanelFormEdit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Mahayoga
 */
public class pnEditKadaluarsa extends javax.swing.JPanel {
    private BatalKadaluarsa batalBarang;
    private EditKadaluarsa editBarang;
    koneksi db = new koneksi();

    /**
     * Creates new form pnTambahKadaluarsa
     */
    public pnEditKadaluarsa(String id, String date) {
        initComponents();
        String[] bln = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        // ComboBox set
        try {
            ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE id_barang = '" + id + "'");
            if(rs.next()) {
                cbNamaBarang.addItem(rs.getString("nama_barang"));
                cbNamaBarang.setSelectedItem(rs.getString("nama_barang"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        setCbBulan();
        setCbTahun();
        String[] dt = date.split("-");
        cbTahun.setSelectedItem(dt[0]);
        cbBulan.setSelectedItem(bln[Integer.parseInt(dt[1]) + 1]);
        cbTanggal.setSelectedItem(dt[2]);
        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventBatal();
            }
        });
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theEventEdit();
            }
        });
    }
    
    public interface BatalKadaluarsa {
        void batalKadaluarsa();
    }
    public interface EditKadaluarsa {
        void editKadaluarsa();
    }
    
    public void setVariableBatal(BatalKadaluarsa bk) {
        this.batalBarang = bk;
    }
    public void setVariableEdit(EditKadaluarsa ek) {
        this.editBarang = ek;
    }
    
    public void theEventBatal() {
        if(batalBarang != null) {
            batalBarang.batalKadaluarsa();
        }
    }
    public void theEventEdit() {
        if(editBarang != null) {
            editBarang.editKadaluarsa();
        }
    }
    
    public boolean checkAllForm() {
        if(cbNamaBarang.getSelectedItem().toString().equals("Pilih") || tfKodeBarang.getText().equals("") || cbTahun.getSelectedItem().toString().equals("--Tidak dipilih--") || cbBulan.getSelectedItem().toString().equals("--Tidak dipilih--") || cbTanggal.getSelectedItem().toString().equals("--Tidak dipilih--") || tfJumlah.getText().equals("")) {
            return true;
        }
        return false;
    }
    
    public boolean checkTheInputIsValid() {
        try {
            Integer.parseInt(tfJumlah.getText());
            return false;
        } catch(Exception e) {
            return true;
        }
    }
    
    public void resetAll() {
        cbNamaBarang.removeAllItems();
        cbNamaBarang.addItem("Tidak ada data yang dipilih!");
        cbNamaBarang.setSelectedItem("Tidak ada data yang dipilih!");
        tfKodeBarang.setText("");
        cbTahun.setSelectedItem("--Tidak dipilih--");
        cbBulan.setSelectedItem("--Tidak dipilih--");
        cbTanggal.setSelectedItem("--Tidak dipilih--");
        tfJumlah.setText("");
    }
    
    public void setCbTahun() {
        for(int i = 2024; i < 2050; i++) {
            cbTahun.addItem(String.valueOf(i));
        }
    }
    
    public void setCbBulan() {
        String[] bln = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        for (String bln1 : bln) {
            cbBulan.addItem(bln1);
        }
    }
    
    public void setCbTgl() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Januari", 31);
        map.put("Februari", 28);
        map.put("Maret", 31);
        map.put("April", 30);
        map.put("Mei", 31);
        map.put("Juni", 30);
        map.put("Juli", 31);
        map.put("Agustus", 31);
        map.put("September", 30);
        map.put("Oktober", 31);
        map.put("November", 30);
        map.put("Desember", 31);
        cbTanggal.removeAllItems();
        cbTanggal.addItem("--Tidak dipilih--");
        try {
            if(!cbTahun.getSelectedItem().equals("--Tidak dipilih--") || !cbBulan.getSelectedItem().equals("--Tidak dipilih--")) {
                int kabisat = Integer.parseInt(String.valueOf(cbTahun.getSelectedItem())) % 4;
                if(kabisat == 0 && cbBulan.getSelectedItem().equals("Februari")) {
                    for(int i = 0; i < map.get("Februari") + 1; i++) {
                        cbTanggal.addItem(String.valueOf(i + 1));
                    }
                } else {
                    for(int i = 0; i < map.get(String.valueOf(cbBulan.getSelectedItem())); i++) {
                        cbTanggal.addItem(String.valueOf(i + 1));
                    }
                }
            } else {
                // Hehe
            }
        } catch(Exception e) {
            System.err.println("Masukin dulu bulan atau tahun nya!");
        }
    }
    
    public void simpanData(String id, String date) {
        /*
        * kode_barang	
        * tgl_kadaluarsa	
        * status
        */
        HashMap<String, Integer> map = new HashMap<>();
        String[] blnArr = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        for(int i = 0; i < blnArr.length; i++) {
            map.put(blnArr[i], i + 1);
        }
        String kd = String.valueOf(tfKodeBarang.getText());
        String tgl = String.valueOf(cbTanggal.getSelectedItem());
        String bln = String.valueOf(cbBulan.getSelectedItem());
        String thn = String.valueOf(cbTahun.getSelectedItem());
        String tglJadi = thn + "-" + map.get(bln) + "-" + tgl;
        int jumlah = Integer.parseInt(tfJumlah.getText());
        
        try {
            db.aksi("UPDATE detail_barang SET tgl_kadaluarsa = '" + tglJadi + "' WHERE kode_barang = '" + kd + "' AND tgl_kadaluarsa = '" + date + "' LIMIT " + jumlah);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ambilDataKodeBarang() {
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE nama_barang = '" + String.valueOf(cbNamaBarang.getSelectedItem()) + "'");
        try {
            if(rs.next()) {
                tfKodeBarang.setText(rs.getString("id_barang"));
            }
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
        cbNamaBarang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tfJumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbBulan = new javax.swing.JComboBox<>();
        cbTanggal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbTahun = new javax.swing.JComboBox<>();
        lbTitle = new javax.swing.JLabel();
        btnBatal = new CustomComponent.CustomButton();
        btnSimpan = new CustomComponent.CustomButton();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nama Barang");

        cbNamaBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cbNamaBarang.setEnabled(false);
        cbNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamaBarangActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Kode Barang ");

        tfKodeBarang.setEnabled(false);
        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Jumlah");

        tfJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Bulan");

        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--" }));
        cbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBulanActionPerformed(evt);
            }
        });

        cbTanggal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tanggal");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Tahun");

        cbTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--" }));
        cbTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTahunActionPerformed(evt);
            }
        });

        lbTitle.setBackground(new java.awt.Color(133, 135, 150));
        lbTitle.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(133, 135, 150));
        lbTitle.setText("Edit Data Kadaluarsa");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(tfKodeBarang)))
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(755, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKodeBarangActionPerformed

    private void tfJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed

    private void cbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTahunActionPerformed
        // TODO add your handling code here:
        setCbTgl();
    }//GEN-LAST:event_cbTahunActionPerformed

    private void cbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBulanActionPerformed
        // TODO add your handling code here:
        setCbTgl();
    }//GEN-LAST:event_cbBulanActionPerformed

    private void cbNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamaBarangActionPerformed
        // TODO add your handling code here:
        ambilDataKodeBarang();
    }//GEN-LAST:event_cbNamaBarangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnBatal;
    private CustomComponent.CustomButton btnSimpan;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbNamaBarang;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JComboBox<String> cbTanggal;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField tfJumlah;
    private javax.swing.JTextField tfKodeBarang;
    // End of variables declaration//GEN-END:variables
}
