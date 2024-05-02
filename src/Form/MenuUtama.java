package Form;


import Login.login;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rangg
 */
public class MenuUtama extends javax.swing.JFrame {
    DefaultTableModel model = new DefaultTableModel();
    koneksi db = new koneksi();
    String role = "";

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama(String role) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println(this.getWidth() + " " + this.getHeight());
        //db.koneksi();
        this.role = role;
        if(role.equals("U")) {
            secAdmin.setVisible(false);
            lbLogin.setText("Masuk Sebagai Karyawan");
        }
        db.koneksi();
        model.setRowCount(0);
        model.setColumnCount(0);
        //model.
        tblData.setModel(model);
        tblData.setRowHeight(40);
        resizeImage(30, 30, iconMenuUtama, "dashboard.png");
        resizeImage(30, 30, iconDataBarang, "items.png");
        resizeImage(30, 30, iconDataSupplier, "supplier.png");
        resizeImage(30, 30, iconKasir, "kasir.png");
        resizeImage(30, 30, iconDataTransaksi, "transaction.png");
        resizeImage(30, 30, iconLogOut, "logout.png");
        resizeImage(33, 33, iconTime, "time.png");
        countTotal();
        iconToko();
    }
    
    public void iconToko() {
        try {
            File file = new File("src/img/Toko Ku (5).png");
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            jLabel1.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void countTotal() {
        ResultSet rs1 = db.ambilData("SELECT COUNT(kode_barang) AS 'hehe' FROM stok_barang ORDER BY kode_barang DESC;");
        ResultSet rs2 = db.ambilData("SELECT COUNT(tanggal) AS 'hehe' FROM detail_transaksi GROUP BY tanggal DESC");
        ResultSet rs3 = db.ambilData("SELECT dt.id_transaksi, dt.tanggal, dt.kode_barang AS \"kode_barang\", COUNT(dt.kode_barang) AS \"jumlah_barang\", SUM(dt.harga_barang * dt.jumlah_barang) AS \"total_harga\" FROM detail_transaksi dt JOIN stok_barang sb ON dt.kode_barang = sb.kode_barang GROUP BY dt.tanggal DESC");
        try {
            rs1.next();
            rs2.next();
            rs3.next();
            if(Integer.parseInt(rs1.getString("hehe")) < 9) {
                lbCountDataBarang.setText("00" + rs1.getString("hehe"));
            } else if(Integer.parseInt(rs1.getString("hehe")) < 99) {
                lbCountDataBarang.setText("0" + rs1.getString("hehe"));
            } else if(Integer.parseInt(rs1.getString("hehe")) < 999) {
                lbCountDataBarang.setText(rs1.getString("hehe"));
            }
            
            if(Integer.parseInt(rs2.getString("hehe")) < 9) {
                lbCountDataTransaksi.setText("00" + rs2.getString("hehe"));
            } else if(Integer.parseInt(rs2.getString("hehe")) < 99) {
                lbCountDataTransaksi.setText("0" + rs2.getString("hehe"));
            } else if(Integer.parseInt(rs2.getString("hehe")) < 999) {
                lbCountDataTransaksi.setText(rs2.getString("hehe"));
            }
            
            if(Integer.parseInt(rs3.getString("kode_barang")) < 9) {
                lbCountDataKeuangan.setText("00" + rs3.getString("kode_barang"));
            } else if(Integer.parseInt(rs3.getString("kode_barang")) < 99) {
                lbCountDataKeuangan.setText("0" + rs3.getString("kode_barang"));
            } else if(Integer.parseInt(rs3.getString("kode_barang")) < 999) {
                lbCountDataKeuangan.setText(rs3.getString("kode_barang"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void resizeImage(int width, int height, JLabel label, String path) {        
        try {
            File file = new File("src/img/" + path);
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            label.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setTableBarang() {
        model.setRowCount(0);
        model.setColumnCount(0);
        tblData.setModel(model);
        
        model.addColumn(new Object[]{"Kode Barang", "Nama Barang", "Stok", "Harga"});
        ResultSet rs = db.ambilData("");
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        customTimeLabel1 = new CustomComponent.CustomTimeLabel();
        iconTime = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        secAdmin = new javax.swing.JPanel();
        lbMenuUtama = new javax.swing.JLabel();
        lbDataBarang = new javax.swing.JLabel();
        lbDataSupplier = new javax.swing.JLabel();
        iconMenuUtama = new javax.swing.JLabel();
        iconDataBarang = new javax.swing.JLabel();
        iconDataSupplier = new javax.swing.JLabel();
        secUser = new javax.swing.JPanel();
        lbKasir = new javax.swing.JLabel();
        lbDataTransaksi = new javax.swing.JLabel();
        iconKasir = new javax.swing.JLabel();
        iconDataTransaksi = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbLogin = new javax.swing.JLabel();
        iconLogOut = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnNoData = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new CustomComponent.CustomTable();
        customPanel1 = new CustomComponent.CustomPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lbCountDataBarang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        customPanel2 = new CustomComponent.CustomPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        lbCountDataTransaksi = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        customPanel3 = new CustomComponent.CustomPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        lbCountDataKeuangan = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(78, 115, 223));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -10, 280, 130));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 80));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(204, 204, 255));

        jLabel2.setBackground(new java.awt.Color(133, 135, 150));
        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(133, 135, 150));
        jLabel2.setText("Toko Kelontong “Putra”");

        customTimeLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 702, Short.MAX_VALUE)
                .addComponent(iconTime, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconTime, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1190, 80));

        jPanel4.setBackground(new java.awt.Color(78, 115, 223));

        jPanel17.setBackground(new java.awt.Color(78, 115, 223));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        secAdmin.setBackground(new java.awt.Color(78, 115, 223));
        secAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMenuUtama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMenuUtama.setForeground(new java.awt.Color(255, 255, 255));
        lbMenuUtama.setText("Menu Utama");
        secAdmin.add(lbMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 30));

        lbDataBarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataBarang.setForeground(new java.awt.Color(255, 255, 255));
        lbDataBarang.setText("Data Barang");
        lbDataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDataBarangMouseClicked(evt);
            }
        });
        secAdmin.add(lbDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 30));

        lbDataSupplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataSupplier.setForeground(new java.awt.Color(255, 255, 255));
        lbDataSupplier.setText("Data Supplier");
        secAdmin.add(lbDataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 30));
        secAdmin.add(iconMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));
        secAdmin.add(iconDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 30, 30));
        secAdmin.add(iconDataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 30, 30));

        secUser.setBackground(new java.awt.Color(78, 115, 223));
        secUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbKasir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbKasir.setForeground(new java.awt.Color(255, 255, 255));
        lbKasir.setText("Kasir");
        secUser.add(lbKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        lbDataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lbDataTransaksi.setText("Data Transaksi");
        secUser.add(lbDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 30));
        secUser.add(iconKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));
        secUser.add(iconDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 30, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Keluar");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        lbLogin.setBackground(new java.awt.Color(133, 135, 150));
        lbLogin.setForeground(new java.awt.Color(133, 135, 150));
        lbLogin.setText("Masuk Sebagai Admin ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lbLogin)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(secUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(iconLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(secAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secUser, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 180, 740));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(133, 135, 150));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(133, 135, 150));
        jLabel14.setText("2024 - Sistem Penjualan Pada Toko Kelontong | Kelompok 5");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 670, 1240, 80));

        jLabel15.setBackground(new java.awt.Color(133, 135, 150));
        jLabel15.setFont(new java.awt.Font("Lucida Bright", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(133, 135, 150));
        jLabel15.setText("Dashboard");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 140, 33));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Tidak ada data yang akan ditampilkan");

        javax.swing.GroupLayout pnNoDataLayout = new javax.swing.GroupLayout(pnNoData);
        pnNoData.setLayout(pnNoDataLayout);
        pnNoDataLayout.setHorizontalGroup(
            pnNoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNoDataLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        pnNoDataLayout.setVerticalGroup(
            pnNoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNoDataLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel1.add(pnNoData, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 390, -1));

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
        jScrollPane1.setViewportView(tblData);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 1130, 290));

        customPanel1.setBackground(new java.awt.Color(78, 115, 223));
        customPanel1.setBackgroundColor(new java.awt.Color(78, 115, 223));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tabel Barang");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel24.setForeground(new java.awt.Color(255, 255, 255));

        lbCountDataBarang.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCountDataBarang.setText("009");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Total Barang");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbCountDataBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCountDataBarang)
                    .addComponent(jLabel8))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cek Tabel Barang");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customPanel1Layout = new javax.swing.GroupLayout(customPanel1);
        customPanel1.setLayout(customPanel1Layout);
        customPanel1Layout.setHorizontalGroup(
            customPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(71, 71, 71))))
        );
        customPanel1Layout.setVerticalGroup(
            customPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(customPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        customPanel2.setBackground(new java.awt.Color(78, 115, 223));
        customPanel2.setBackgroundColor(new java.awt.Color(78, 115, 223));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Tabel Transaksi");

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel26.setForeground(new java.awt.Color(255, 255, 255));

        lbCountDataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCountDataTransaksi.setText("009");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Total Transaksi");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbCountDataTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCountDataTransaksi)
                    .addComponent(jLabel10))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cek Data Transaksi");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customPanel2Layout = new javax.swing.GroupLayout(customPanel2);
        customPanel2.setLayout(customPanel2Layout);
        customPanel2Layout.setHorizontalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(66, 66, 66))))
        );
        customPanel2Layout.setVerticalGroup(
            customPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(customPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, -1, -1));

        customPanel3.setBackground(new java.awt.Color(78, 115, 223));
        customPanel3.setBackgroundColor(new java.awt.Color(78, 115, 223));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tabel Keuangan");

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel27.setForeground(new java.awt.Color(255, 255, 255));

        lbCountDataKeuangan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbCountDataKeuangan.setText("009");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Total Barang");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Terjual");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbCountDataKeuangan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12))
                    .addComponent(lbCountDataKeuangan))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cek Data Keuangan");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout customPanel3Layout = new javax.swing.GroupLayout(customPanel3);
        customPanel3.setLayout(customPanel3Layout);
        customPanel3Layout.setHorizontalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(64, 64, 64))))
        );
        customPanel3Layout.setVerticalGroup(
            customPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(customPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void lbDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDataBarangMouseClicked
        // TODO add your handling code here:
        new DataBarang(role).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lbDataBarangMouseClicked

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        boolean edit = tblData.isEditing();
        if(!edit) {
            JOptionPane.showMessageDialog(this, "Tidak bisa mengedit data ini!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok Tersedia");
        model.addColumn("Harga Barang");
        try {
            ResultSet rs = db.ambilData("SELECT * FROM stok_barang");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("kode_barang"), rs.getString("nama_barang"), rs.getString("stok_tersedia"), rs.getString("harga_barang")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
        pnNoData.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Nomor Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Harga Total");
        try {
            ResultSet rs = db.ambilData("SELECT * FROM transaksi");
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("id_transaksi"), rs.getString("tanggal_transaksi"), rs.getString("harga_total")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
        pnNoData.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Tanggal");
        model.addColumn("Total barang yang terjual");
        model.addColumn("Total pendapatan");
        try {
            ResultSet rs = db.ambilData("SELECT dt.id_transaksi, dt.tanggal, dt.kode_barang AS \"kode_barang\", COUNT(dt.kode_barang) AS \"jumlah_barang\", SUM(dt.harga_barang * dt.jumlah_barang) AS \"total_harga\" FROM detail_transaksi dt JOIN stok_barang sb ON dt.kode_barang = sb.kode_barang GROUP BY dt.tanggal;");            
            while(rs.next()) {
                model.addRow(new Object[]{rs.getString("tanggal"), rs.getString("jumlah_barang"), rs.getString("total_harga")});
            }
            tblData.setModel(model);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CustomComponent.CustomPanel customPanel1;
    private CustomComponent.CustomPanel customPanel2;
    private CustomComponent.CustomPanel customPanel3;
    private CustomComponent.CustomTimeLabel customTimeLabel1;
    private javax.swing.JLabel iconDataBarang;
    private javax.swing.JLabel iconDataSupplier;
    private javax.swing.JLabel iconDataTransaksi;
    private javax.swing.JLabel iconKasir;
    private javax.swing.JLabel iconLogOut;
    private javax.swing.JLabel iconMenuUtama;
    private javax.swing.JLabel iconTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCountDataBarang;
    private javax.swing.JLabel lbCountDataKeuangan;
    private javax.swing.JLabel lbCountDataTransaksi;
    private javax.swing.JLabel lbDataBarang;
    private javax.swing.JLabel lbDataSupplier;
    private javax.swing.JLabel lbDataTransaksi;
    private javax.swing.JLabel lbKasir;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMenuUtama;
    private javax.swing.JPanel pnNoData;
    private javax.swing.JPanel secAdmin;
    private javax.swing.JPanel secUser;
    private CustomComponent.CustomTable tblData;
    // End of variables declaration//GEN-END:variables
}
