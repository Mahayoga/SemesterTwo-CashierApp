/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package FormPanel;
import Form.MenuUtama;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mahayoga
 */
public class pnKasir extends javax.swing.JPanel {
    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();
    HashMap<String, String> dateMap = new HashMap<>();
    int jumlahHarusDipesan;
    int totalItem;
    String notr;

    /**
     * Creates new form pnKasir
     */
    public pnKasir() {
        initComponents();
        autoNumber();
        autoTgl();
        setColumn();
        btnCetak.setEnabled(false);
        btnSimpan.setEnabled(false);
    }
    
    public void resetAllForm() {
        autoNumber();
        autoTgl();
        tfKodeBarang.setText("");
        tfNamaBarang.setText("");
        tfStok.setText("");
        tfHarga.setText("");
        cbTanggalKadaluarsa.setSelectedIndex(0);
        tfJumlahBeli.setText("");
        tfJumlahHarga.setText("");
        tfTotal.setText("");
        tfBayar.setText("");
        tfKembali.setText("");
        checkItemInTable();
        btnCetak.setEnabled(false);
    }
    
    public String changeToNum(String num) {
        String result = "";
        for(int i = 0; i < num.replace('.', 'a').split("a").length; i++) {
            result += num.replace('.', 'a').split("a")[i];
        }
        System.out.println(result);
        return result.split("Rp ")[1];
    }
    
    public String changeToRp(String num) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp. " + nf.format(Double.parseDouble(num));
    }
    
    public void resetAll() {
        model.setRowCount(0);
        tblData.setModel(model);
        tfTotal.setText("");
        tfBayar.setText("");
        tfKembali.setText("");
        checkItemInTable();
        btnCetak.setEnabled(false);
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
    
    public void checkItemInTable() {
        if(tblData.getRowCount() > 0) {
            btnSimpan.setEnabled(true);
        } else {
            btnSimpan.setEnabled(false);
        }
    }
    
    public void countTotal() {
        int sumTotal = 0;
        int row = tblData.getRowCount();
        for(int i = 0; i < row; i++) {
            sumTotal += Integer.parseInt(changeToNum(String.valueOf(tblData.getValueAt(i, 6))));
        }
        tfTotal.setText(changeToRp(String.valueOf(sumTotal)));
    }
    
    public void setColumn() {
        model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("ID Barang");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Tgl Kadaluarsa");
        model.addColumn("Jumlah (beli)");
        model.addColumn("Harga (awal)");
        model.addColumn("Harga (total)");
        tblData.setModel(model);
    }
    
    public void autoTgl() {
        tfTanggal.setText(String.valueOf(LocalDate.now()));
    }
    
    public void autoNumber() {
        ResultSet rs = db.ambilData("SELECT * FROM transaksi ORDER BY id_transaksi DESC");
        try {
            if(rs.next()) {
                int id = Integer.parseInt(rs.getString("id_transaksi").substring(2, 6)); //KP0001 
                id++;
                if(id <= 9) {
                    tfNoTransaksi.setText(rs.getString("id_transaksi").substring(0, 2) + "000" + id);
                } else if(id <= 99) {
                    tfNoTransaksi.setText(rs.getString("id_transaksi").substring(0, 2) + "00" + id);
                } else if(id <= 999) {
                    tfNoTransaksi.setText(rs.getString("id_transaksi").substring(0, 2) + "0" + id);
                } else if(id <= 9999) {
                    tfNoTransaksi.setText(rs.getString("id_transaksi").substring(0, 2) + "" + id);
                }
            } else {
                tfNoTransaksi.setText("TR0001");
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

        jLabel6 = new javax.swing.JLabel();
        tfNoTransaksi = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfKodeBarang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        tfNamaBarang = new javax.swing.JTextField();
        tfStok = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfJumlahHarga = new javax.swing.JTextField();
        tfJumlahBeli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfTanggal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new CustomComponent.CustomTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfTotal = new javax.swing.JTextField();
        tfBayar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfKembali = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbJumlah = new javax.swing.JLabel();
        cbTanggalKadaluarsa = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnBatal = new CustomComponent.CustomButton();
        btnTambah = new CustomComponent.CustomButton();
        btnCetak = new CustomComponent.CustomButton();
        btnSimpan = new CustomComponent.CustomButton();
        lbBarcode = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("No Transaksi");

        tfNoTransaksi.setBackground(new java.awt.Color(242, 242, 242));
        tfNoTransaksi.setEnabled(false);
        tfNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoTransaksiActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Nama Barang");

        tfKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKodeBarangActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Barcode");

        tfNamaBarang.setEnabled(false);
        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });

        tfStok.setEnabled(false);
        tfStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStokActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Stok");

        tfHarga.setEnabled(false);
        tfHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHargaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Harga");

        tfJumlahHarga.setEnabled(false);
        tfJumlahHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahHargaActionPerformed(evt);
            }
        });

        tfJumlahBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahBeliActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Jumlah Harga");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tanggal");

        tfTanggal.setBackground(new java.awt.Color(242, 242, 242));
        tfTanggal.setEnabled(false);
        tfTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTanggalActionPerformed(evt);
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("*Pilih item barang ini dibawah ini apabila hendak membatalkan transaksi pembelian barang");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Total");

        tfTotal.setEnabled(false);
        tfTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalActionPerformed(evt);
            }
        });

        tfBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBayarActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Bayar");

        tfKembali.setEnabled(false);
        tfKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKembaliActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Kembali");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Kode Barang");

        lbJumlah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbJumlah.setText("Jumlah");

        cbTanggalKadaluarsa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tidak dipilih--" }));
        cbTanggalKadaluarsa.setEnabled(false);
        cbTanggalKadaluarsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTanggalKadaluarsaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Tgl Kadaluarsa");

        btnBatal.setText("customButton1");
        btnBatal.setBackgroundColor(new java.awt.Color(255, 151, 51));
        btnBatal.setFontSize(14);
        btnBatal.setTextBold(1);
        btnBatal.setTextColor(java.awt.Color.white);
        btnBatal.setTheText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnTambah.setText("customButton1");
        btnTambah.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnTambah.setFontSize(14);
        btnTambah.setTextBold(1);
        btnTambah.setTextColor(java.awt.Color.white);
        btnTambah.setTheText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnCetak.setText("customButton1");
        btnCetak.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnCetak.setFontSize(14);
        btnCetak.setTextBold(1);
        btnCetak.setTextColor(java.awt.Color.white);
        btnCetak.setTheText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnSimpan.setText("customButton1");
        btnSimpan.setBackgroundColor(new java.awt.Color(78, 115, 223));
        btnSimpan.setFontSize(14);
        btnSimpan.setTextBold(1);
        btnSimpan.setTextColor(java.awt.Color.white);
        btnSimpan.setTheText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(tfNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfKodeBarang)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfStok, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfHarga)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(46, 46, 46)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTanggalKadaluarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfJumlahBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfTotal)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfBayar)
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(27, 27, 27)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfKembali))
                                        .addGap(169, 169, 169))
                                    .addComponent(jLabel20)
                                    .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(167, 167, 167)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfJumlahHarga)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTambah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addComponent(jLabel14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(lbJumlah)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbTanggalKadaluarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfJumlahBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel20))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfJumlahHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNoTransaksiActionPerformed

    private void tfKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKodeBarangActionPerformed
        // TODO add your handling code here:
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE kode_barang = '" + tfKodeBarang.getText() + "'");
        try {
            if(rs.next()) {
                tfNamaBarang.setText(rs.getString("nama_barang"));
                tfStok.setText(rs.getString("stok_tersedia"));
                tfHarga.setText(changeToRp(rs.getString("harga_jual")));
                rs = db.ambilData("SELECT * FROM detail_barang WHERE kode_barang = '" + rs.getString("id_barang") + "' AND status = 'Belum Terbuang' AND tgl_kadaluarsa > '" + LocalDate.now() + "' GROUP BY tgl_kadaluarsa");
                cbTanggalKadaluarsa.removeAllItems();
                cbTanggalKadaluarsa.addItem("--Tidak dipilih--");
                cbTanggalKadaluarsa.setSelectedItem("--Tidak dipilih--");
                while(rs.next()) {
                    System.out.println(rs.getString("status"));
                    cbTanggalKadaluarsa.addItem(rs.getString("tgl_kadaluarsa"));
                }
                setBarcodeImage(180, 60, lbBarcode, tfNamaBarang.getText() + ".png");
                cbTanggalKadaluarsa.setEnabled(true);
                cbTanggalKadaluarsa.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Data denga kode barang '" + tfKodeBarang.getText() + "' tidak tersedia dalam database!\nSilahkan periksa kembali kode barang!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tfKodeBarangActionPerformed

    private void tfNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangActionPerformed

    private void tfStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfStokActionPerformed

    private void tfHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHargaActionPerformed

    private void tfJumlahHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahHargaActionPerformed

    private void tfJumlahBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahBeliActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(tfJumlahBeli.getText()) > jumlahHarusDipesan) {
            JOptionPane.showMessageDialog(this, "Jumlah melebihi stok kadaluarsa!\nMasukkan jumlah yang sesuai!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            tfJumlahBeli.setText("");
        } else {
            tfJumlahHarga.setText(changeToRp(String.valueOf(Integer.parseInt(changeToNum(tfHarga.getText())) * Integer.parseInt(tfJumlahBeli.getText()))));
        }
    }//GEN-LAST:event_tfJumlahBeliActionPerformed

    private void tfTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTanggalActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        boolean edit = tblData.isEditing();
        int row = tblData.getSelectedRow();
        if(!edit) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda ingin membatalkan barang ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE id_barang = '" + String.valueOf(tblData.getValueAt(row, 0) + "'"));
                try {
                    rs.next();
                    int stokKembali = Integer.parseInt(String.valueOf(tblData.getValueAt(row, 4))) + Integer.parseInt(String.valueOf(rs.getString("stok_tersedia")));
                    db.aksi("UPDATE stok_barang SET stok_tersedia = " + stokKembali + " WHERE id_barang = '" + rs.getString("id_barang") + "'");
                    db.aksi("UPDATE detail_barang SET status = 'Belum Terbuang' WHERE tgl_kadaluarsa = '" + String.valueOf(tblData.getValueAt(row, 3)) + "' AND kode_barang = '" + String.valueOf(tblData.getValueAt(row, 0) + "' LIMIT " + String.valueOf(tblData.getValueAt(row, 4))));
                    JOptionPane.showMessageDialog(this, "Hapus barang berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    model.removeRow(row);
                    tblData.setModel(model);
                    countTotal();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        checkItemInTable();
    }//GEN-LAST:event_tblDataMouseClicked

    private void tfTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalActionPerformed

    private void tfBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBayarActionPerformed
        // TODO add your handling code here:
        int result = Integer.parseInt(tfBayar.getText()) - Integer.parseInt(changeToNum(tfTotal.getText()));
        if(result < 0) {
            JOptionPane.showMessageDialog(this, "Uang anda tidak cukup!\nSilahkan masukkan jumlah yang tepat", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            tfBayar.setText(changeToRp(tfBayar.getText()));
            tfKembali.setText(String.valueOf(changeToRp(String.valueOf(result))));
        }
    }//GEN-LAST:event_tfBayarActionPerformed

    private void tfKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKembaliActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        try {
            db.koneksi();
            File f = new File("src/Struk/report1.jasper");
            
            HashMap<String, Object> param = new HashMap<>();
            param.put("nama_kasir", new String(MenuUtama.kasir));
            param.put("no_transaksi", new String(notr));
            JasperPrint print = JasperFillManager.fillReport(f.getAbsolutePath(), param, db.con);
            
            JasperViewer.viewReport(print, false);
            
            resetAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        /*
            * Stok Barang (stok)
            * Kadaluarsa (semua)
        */
        if(tblData.getRowCount() > 0) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda mau membatalkan transaksi dan kembali ke Menu Utama?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                for(int i = 0; i < tblData.getRowCount(); i++) {
                    try {
                        String idBr = String.valueOf(tblData.getValueAt(i, 0));
                        ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE id_barang = '" + idBr + "'");
                        rs.next();
                        String jumlahBr = String.valueOf(tblData.getValueAt(i, 4));
                        String tgl_kadaluarsa = String.valueOf(tblData.getValueAt(i, 3));
                        int jumlahBrInt = Integer.parseInt(jumlahBr) + Integer.parseInt(rs.getString("stok_tersedia"));
                        db.aksi("UPDATE stok_barang SET stok_tersedia = '" + jumlahBrInt + "' WHERE id_barang = '" + idBr + "'");
                        db.aksi("UPDATE detail_barang SET status = 'Belum Terbuang' WHERE kode_barang = '" + idBr + "' AND tgl_kadaluarsa = '" + tgl_kadaluarsa + "' AND status = 'Sudah Terjual' LIMIT " + jumlahBr);
                        model.removeRow(i);
                        tblData.setModel(model);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
            totalItem = tblData.getRowCount();
            
            /*
                * Transaksi
                - id_transaksi	
                - tanggal_transaksi
                - total_item
                - harga_total
                - bayar
                - kembali
            */
            String idTr = tfNoTransaksi.getText();
            String tgl = tfTanggal.getText();
            String hargaTotal = changeToNum(tfTotal.getText());
            String bayar = changeToNum(tfBayar.getText());
            String kembali = changeToNum(tfKembali.getText());
            db.aksi("INSERT INTO transaksi VALUES ('" + idTr + "', '" + tgl + "', '" + totalItem + "', '" + hargaTotal + "', '" + bayar + "', '" + kembali + "')");
            System.out.println("Transaksi Berhasil!");

            /*
                * Detail Transaksi
                - id_transaksi x
                - kode_barang
                - tanggal x
                - jumlah_barang
                - harga_barang
            */
            for(int i = 0; i < tblData.getRowCount(); i++) {
                totalItem = i + 1;
                String idBr = String.valueOf(tblData.getValueAt(i, 0));
                String jumlahBr = String.valueOf(tblData.getValueAt(i, 4));
                String hargaBr = changeToNum(String.valueOf(tblData.getValueAt(i, 5)));
                String hargaTotalTabel = changeToNum(String.valueOf(tblData.getValueAt(i, 6)));
                db.aksi("INSERT INTO detail_transaksi VALUES ('" + idTr + "', '" + idBr + "', '" + tgl + "', '" + jumlahBr + "', '" + hargaBr + "', '" + hargaTotalTabel + "')");
            }
            JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            this.notr = idTr;
            autoTgl();
            autoNumber();
            btnSimpan.setEnabled(false);
            btnCetak.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        ResultSet rs = db.ambilData("SELECT * FROM stok_barang WHERE kode_barang = '" + tfKodeBarang.getText() + "'");
        boolean found = false;
        try {
            if(rs.next()) {
                for(int i = 0; i < tblData.getRowCount(); i++) {
                    if(tblData.getValueAt(i, 0).equals(rs.getString("id_barang")) && tblData.getValueAt(i, 3).equals(cbTanggalKadaluarsa.getSelectedItem())) {
                        int resultJumlahBeli = Integer.parseInt(tfJumlahBeli.getText()) + Integer.parseInt(String.valueOf(tblData.getValueAt(i, 4)));
                        int resultJumlahHarga = Integer.parseInt(changeToNum(tfJumlahHarga.getText())) + Integer.parseInt(changeToNum(String.valueOf(tblData.getValueAt(i, 6))));
                        tblData.setValueAt(resultJumlahBeli, i, 4);
                        tblData.setValueAt(changeToRp(String.valueOf(resultJumlahHarga)), i, 6);
                        i = tblData.getRowCount();
                        found = true;
                    } else {
                        found = false;
                    }
                }
                if(!found) {
                    model.addRow(new Object[]{rs.getString("id_barang"), tfKodeBarang.getText(), tfNamaBarang.getText(), cbTanggalKadaluarsa.getSelectedItem(), tfJumlahBeli.getText(), changeToRp(changeToNum(tfHarga.getText())), changeToRp(changeToNum(tfJumlahHarga.getText()))});
                }
                
                int result = Integer.parseInt(tfStok.getText()) - Integer.parseInt(tfJumlahBeli.getText());
                db.aksi("UPDATE stok_barang SET stok_tersedia = " + result + " WHERE id_barang = '" + rs.getString("id_barang") + "'");
                
                //dateMap.put(String.valueOf(cbTanggalKadaluarsa.getSelectedItem()), rs.getString("id_barang")); // ??
                db.aksi("UPDATE detail_barang SET status = 'Sudah Terjual' WHERE kode_barang = '" + rs.getString("id_barang") + "' AND tgl_kadaluarsa = '" + String.valueOf(cbTanggalKadaluarsa.getSelectedItem()) + "' AND status = 'Belum Terbuang' LIMIT " + tfJumlahBeli.getText());
                
                tfKodeBarang.setText("");
                tfNamaBarang.setText("");
                tfStok.setText("");
                tfHarga.setText("");
                tfJumlahBeli.setText("");
                
                cbTanggalKadaluarsa.removeAllItems();
                cbTanggalKadaluarsa.addItem("--Tidak dipilih--");
                cbTanggalKadaluarsa.setSelectedItem("--Tidak dipilih--");
                
                tfJumlahHarga.setText("");
                lbJumlah.setText("Jumlah");
                countTotal();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tblData.setModel(model);
        checkItemInTable();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void cbTanggalKadaluarsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTanggalKadaluarsaActionPerformed
        // TODO add your handling code here:
        ResultSet rs = db.ambilData("SELECT *, COUNT(tgl_kadaluarsa) AS 'hehe' FROM stok_barang sb INNER JOIN detail_barang dt ON sb.id_barang = dt.kode_barang WHERE sb.kode_barang = '" + tfKodeBarang.getText() + "' AND dt.tgl_kadaluarsa = '" + cbTanggalKadaluarsa.getSelectedItem() + "' AND dt.status = 'Belum Terbuang'");
        try {
            rs.next();
            lbJumlah.setText("Jumlah");
            lbJumlah.setText(lbJumlah.getText() + "(" + rs.getString("hehe") + ")");
            this.jumlahHarusDipesan = Integer.parseInt(rs.getString("hehe"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnTambah.requestFocus();
    }//GEN-LAST:event_cbTanggalKadaluarsaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomButton btnBatal;
    private CustomComponent.CustomButton btnCetak;
    private CustomComponent.CustomButton btnSimpan;
    private CustomComponent.CustomButton btnTambah;
    private javax.swing.JComboBox<String> cbTanggalKadaluarsa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbBarcode;
    private javax.swing.JLabel lbJumlah;
    private CustomComponent.CustomTable tblData;
    private javax.swing.JTextField tfBayar;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfJumlahBeli;
    private javax.swing.JTextField tfJumlahHarga;
    private javax.swing.JTextField tfKembali;
    private javax.swing.JTextField tfKodeBarang;
    private javax.swing.JTextField tfNamaBarang;
    private javax.swing.JTextField tfNoTransaksi;
    private javax.swing.JTextField tfStok;
    private javax.swing.JTextField tfTanggal;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables
}
