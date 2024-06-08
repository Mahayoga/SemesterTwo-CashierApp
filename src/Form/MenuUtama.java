package Form;


import FormPanel.pnDataBarang;
import FormPanel.pnDataKadaluarsa;
import FormPanel.pnDataKeuangan;
import FormPanel.pnDataSupplier;
import FormPanel.pnKeuangan;
import Login.login;
import PanelFormEdit.pnEditBarang;
import PanelFormEdit.pnEditKadaluarsa;
import PanelFormEdit.pnEditKeuangan;
import PanelFormEdit.pnEditSupplier;
import PanelFormTambah.pnTambahBarang;
import PanelFormTambah.pnTambahKadaluarsa;
import PanelFormTambah.pnTambahKeuangan;
import PanelFormTambah.pnTambahSupplier;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
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
import javax.swing.JPanel;

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
    pnTambahSupplier pTDS = new pnTambahSupplier();
    pnTambahBarang pTDB = new pnTambahBarang();
    pnTambahKadaluarsa pTDK = new pnTambahKadaluarsa();
    pnTambahKeuangan pTDKu = new pnTambahKeuangan();
    pnKeuangan pK = new pnKeuangan();
    pnEditSupplier pEDS;
    pnEditBarang pEDB;
    pnEditKadaluarsa pEDK;
    pnEditKeuangan pEDKu;
    boolean inForm = false;
    String idData;
    String date;
    public static String kasir;
    /**
     * Creates new form MenuUtama
     * Panel Width: 1190
     * Panel Height: 590
     */
    public MenuUtama(String role, String kasir) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println(this.getWidth() + " " + this.getHeight());
        //db.koneksi();
        this.kasir = kasir;
        this.role = role;
        if(role.equals("U")) {
            secAdmin.setVisible(false);
            lbLogin.setText("Masuk Sebagai Karyawan");
            lbNamaKasir.setText(kasir);
        } else {
            lbLogin.setText("Masuk Sebagai Admin");
            lbNamaKasir.setText(kasir);
        }
        db.koneksi();
        model.setRowCount(0);
        model.setColumnCount(0);
        //model.
        resizeImage(30, 30, iconMenuUtama, "dashboard.png");
        resizeImage(30, 30, iconDataBarang, "items.png");
        resizeImage(30, 30, iconDataSupplier, "supplier.png");
        resizeImage(33, 33, iconDataKeuangan, "transaction.png");
        resizeImage(30, 30, iconKasir, "kasir.png");
        resizeImage(35, 35, iconDataTransaksi, "iconTransaksi.png");
        resizeImage(30, 30, iconLogOut, "logout.png");
        resizeImage(33, 33, iconTime, "time.png");
        iconToko();
        container.removeAll();
        container.repaint();
        pnMenuUtama1.setBounds(0, 0, 1190, 590);
        container.add(pnMenuUtama1);
        
        // ------------------------------------------ Data Barang Interface Set ------------------------------------------
        pnDataBarang1.setVariableTambah(new pnDataBarang.TambahData() {
            @Override
            public void tambahData() {
                tambahDataBarang();
            }
        });
        pnDataBarang1.setVariableEdit(new pnDataBarang.EditData() {
            @Override
            public void editData() {
                editDataBarang(idData);
            }
        });
        pnDataBarang1.setVariableAmbil(new pnDataBarang.AmbilData() {
            @Override
            public void ambilData(String id) {
                setIdData(id);
            }
        });
        pnDataBarang1.setVariableHapus(new pnDataBarang.HapusData() {
            @Override
            public void hapusData() {
                btnHapusBarang(idData);
            }
        });
        pnDataBarang1.setVariableKadaluarsa(new pnDataBarang.DataKadaluarsa() {
            @Override
            public void dataKadaluarsa() {
                clickBtn(10);
            }
        });
        
        // ------------------------------------------ Data Kadaluarsa Interface Set ------------------------------------------
        pnDataKadaluarsa1.setVariableKembali(new pnDataKadaluarsa.Kembali() {
            @Override
            public void kembali() {
                btnKembaliBarang();
            }
        });
        pnDataKadaluarsa1.setVariableTambah(new pnDataKadaluarsa.TambahData() {
            @Override
            public void tambahData() {
                tambahDataKadaluarsa();
            }
        });
        pnDataKadaluarsa1.setVariableEdit(new pnDataKadaluarsa.EditData() {
            @Override
            public void editData() {
                editDataKadaluarsa(idData, date);
            }
        });
        pnDataKadaluarsa1.setVariableAmbil(new pnDataKadaluarsa.AmbilData() {
            @Override
            public void ambilData(String id, String date) {
                setIdData(id);
                setDateData(date);
            }
        });
        pnDataKadaluarsa1.setVariableHapus(new pnDataKadaluarsa.HapusData() {
            @Override
            public void hapusData() {
                hapusDataKadaluarsa();
            }
        });
        
        // ------------------------------------------ Tambah Data Kadaluarsa Interface Set ------------------------------------------
        pTDK.setVariableBatal(new pnTambahKadaluarsa.BatalKadaluarsa() {
            @Override
            public void batalBarang() {
                btnBatalKadaluarsa();
            }
        });
        pTDK.setVariableSimpan(new pnTambahKadaluarsa.SimpanKadaluarsa() {
            @Override
            public void simpanBarang() {
                btnSimpanKadaluarsa();
            }
        });
        
        // ------------------------------------------ Tambah Data Barang Interface Set ------------------------------------------
        pTDB.setVariableBatal(new pnTambahBarang.BatalBarang() {
            @Override
            public void batalBarang() {
                btnBatalBarang();
            }
        });
        pTDB.setVariableSimpan(new pnTambahBarang.SimpanBarang() {
            @Override
            public void simpanBarang() {
                btnSimpanBarang();
            }
        });
        
        // ------------------------------------------ Data Supplier Interface Set ------------------------------------------
        pnDataSupplier1.setVariableTambah(new pnDataSupplier.TambahData() {
            @Override
            public void supplierTambahData() {
                tambahDataSupplier();
            }
        });
        pnDataSupplier1.setVariableEdit(new pnDataSupplier.EditData() {
            @Override
            public void supplierEditData() {
                editDataSupplier(idData);
            }
        });
        pnDataSupplier1.setVariableAmbil(new pnDataSupplier.AmbilDataPadaTabel() {
            @Override
            public void ambilDataSupplier(String id) {
                setIdData(id);
            }
        });
        pnDataSupplier1.setVariableHapus(new pnDataSupplier.HapusData() {
            @Override
            public void supplierHapusData() {
                hapusDataSupplier(idData);
            }
        });
        
        // ------------------------------------------ Tambah Data Supplier Interface Set ------------------------------------------
        pTDS.setVariableBatal(new pnTambahSupplier.BatalSupplier() {
            @Override
            public void batalSupplier() {
                btnBatalSupplier();
            }
        });
        pTDS.setVariableSimpan(new pnTambahSupplier.SimpanSupplier() {
            @Override
            public void simpanSupplier() {
                btnSimpanSupplier();
            }
        });
        
        // ------------------------------------------ Keuangan Interface Set ------------------------------------------
        pnDataKeuangan1.setVariableTambah(new pnDataKeuangan.TambahData() {
            @Override
            public void tambahData() {
                btnTambahKeuangan();
            }
        });
        pnDataKeuangan1.setVariableEdit(new pnDataKeuangan.EditData() {
            @Override
            public void editData() {
                editDataKeuangan(idData);
            }
        });
        pnDataKeuangan1.setVariableAmbil(new pnDataKeuangan.AmbilData() {
            @Override
            public void ambilData(String id) {
                setIdData(id);
            }
        });
        pnDataKeuangan1.setVariableHapus(new pnDataKeuangan.HapusData() {
            @Override
            public void hapusData() {
                btnHapusKeuangan(idData);
            }
        });
        pnDataKeuangan1.setVariableKeuangan(new pnDataKeuangan.Keuangan() {
            @Override
            public void cekData() {
                btnCekKeuangan();
            }
        });
        
        // ------------------------------------------ Tambah Data Keuangan Interface Set ------------------------------------------
        pTDKu.setVariableBatal(new pnTambahKeuangan.BatalKeuangan() {
            @Override
            public void batalKeuangan() {
                btnBatalKeuangan();
            }
        });
        pTDKu.setVariableSimpan(new pnTambahKeuangan.SimpanKeuangan() {
            @Override
            public void simpanKeuangan() {
                btnSimpanKeuangan();
            }
        });
    }
    
    // ------------------------------------------ Method Data Keuangan ------------------------------------------
    public void btnTambahKeuangan() {
        container.removeAll();
        container.repaint();
        pTDKu.setBounds(0, 0, 1190, 590);
        container.add(pTDKu);
        inForm = true;
        resetFooter();
    }
    public void editDataKeuangan(String id) {
        pEDKu = new pnEditKeuangan(id);
        pEDKu.setVariableBatal(new pnEditKeuangan.BatalBarang() {
            @Override
            public void batalBarang() {
                btnBatalKeuangan();
            }
        });
        pEDKu.setVariableSimpan(new pnEditKeuangan.SimpanBarang() {
            @Override
            public void simpanBarang() {
                btnEditKeuangan();
            }
        });
        container.removeAll();
        container.repaint();
        pEDKu.setBounds(0, 0, 1190, 590);
        container.add(pEDKu);
        resetFooter();
        inForm = true;
    }
    public void btnSimpanKeuangan() {
        if(!inForm) {
            return;
        }
        if(pTDKu.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pTDKu.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pTDKu.simpanData();
        inForm = false;
        JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        pnDataKeuangan1.setRow();
        clickBtn(5);
    }
    public void btnEditKeuangan() {
        if(!inForm) {
            return;
        }
        if(pEDKu.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pEDKu.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan mengedit data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            pEDKu.simpanData(idData);
            inForm = false;
            JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            pnDataKeuangan1.setRow();
            clickBtn(5);
        }
    }
    public void btnHapusKeuangan(String id) {
        pnDataKeuangan1.hapusData(id);
        pnDataKeuangan1.setRow();
    }
    public void btnBatalKeuangan() {
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input dan kembali ke Menu Data Keuangan?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            clickBtn(5);
            inForm = false;
        }
    }
    public void btnCekKeuangan() {
        container.removeAll();
        container.repaint();
        pK.setBounds(0, 0, 1190, 590);
        container.add(pK);
        resetFooter();
    }
    
    // ------------------------------------------ Method Data Barang ------------------------------------------
    public void tambahDataBarang() {
        container.removeAll();
        container.repaint();
        pTDB.setBounds(0, 0, 1190, 590);
        container.add(pTDB);
        inForm = true;
        resetFooter();
    }
    public void editDataBarang(String id) {
        pEDB = new pnEditBarang(id);
        pEDB.setVariableBatal(new pnEditBarang.BatalBarang() {
            @Override
            public void batalBarang() {
                btnBatalBarang();
            }
        });
        pEDB.setVariableEdit(new pnEditBarang.EditBarang() {
            @Override
            public void editBarang() {
                btnEditBarang();
            }
        });
        container.removeAll();
        container.repaint();
        pEDB.setBounds(0, 0, 1190, 590);
        container.add(pEDB);
        resetFooter();
        inForm = true;
    }
    public void btnHapusBarang(String id) {
        pnDataBarang1.hapusData(id);
        pnDataBarang1.setRow();
    }
    public void btnSimpanBarang() {
        if(!inForm) {
            return;
        }
        if(pTDB.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pTDB.checkIfIsAlreadyAvailable()) {
            JOptionPane.showMessageDialog(this, "Data sudah terdaftar dalam database!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pTDB.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pTDB.simpanData();
        inForm = false;
        JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        pnDataBarang1.setRow();
        clickBtn(1);
    }
    public void btnEditBarang() {
        if(!inForm) {
            return;
        }
        if(pEDB.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pEDB.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan mengedit data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            pEDB.simpanData(idData);
            inForm = false;
            JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            pnDataBarang1.setRow();
            clickBtn(1);
        }
    }
    public void btnBatalBarang() {
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input dan kembali ke Menu Data Barang?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            clickBtn(1);
            inForm = false;
        }
    }
    public void dataKadaluarsa() {
        container.removeAll();
        container.repaint();
        pTDB.setBounds(0, 0, 1190, 590);
        container.add(pTDB);
        resetFooter();
    }
    
    // ------------------------------------------ Method Kadaluarsa ------------------------------------------
    public void btnKembaliBarang() {
        clickBtn(1);
    }
    public void btnBatalKadaluarsa() {
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input dan kembali ke Menu Data Kadaluarsa?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            clickBtn(10);
            inForm = false;
        }
    }
    public void btnSimpanKadaluarsa() {
        if(!inForm) {
            return;
        }
        if(pTDK.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pTDK.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!pTDK.simpanData()) {
            inForm = false;
            pnDataKadaluarsa1.setRow();
            clickBtn(10);
        }
    }
    public void btnEditKadaluarsa() {
        if(!inForm) {
            return;
        }
        if(pEDK.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pEDK.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan mengedit data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            pEDK.simpanData(idData, date);
            inForm = false;
            JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            pnDataKadaluarsa1.setRow();
            clickBtn(10);
        }
    }
    public void editDataKadaluarsa(String id, String date) {
        pEDK = new pnEditKadaluarsa(id, date);
        pEDK.setVariableBatal(new pnEditKadaluarsa.BatalKadaluarsa() {
            @Override
            public void batalKadaluarsa() {
                btnBatalKadaluarsa();
            }
        });
        pEDK.setVariableEdit(new pnEditKadaluarsa.EditKadaluarsa() {
            @Override
            public void editKadaluarsa() {
                btnEditKadaluarsa();
            }
        });
        container.removeAll();
        container.repaint();
        pEDK.setBounds(0, 0, 1190, 590);
        container.add(pEDK);
        resetFooter();
        inForm = true;
    }
    public void tambahDataKadaluarsa() {
        container.removeAll();
        container.repaint();
        pTDK.setBounds(0, 0, 1190, 590);
        container.add(pTDK);
        inForm = true;
        resetFooter();
    }
    public void hapusDataKadaluarsa() {
        pnDataKadaluarsa1.hapusData(idData, date);
    }
    
    // ------------------------------------------ Method Supplier ------------------------------------------
    public void btnEditSupplier() {
        if(!inForm) {
            return;
        }
        if(pEDS.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pEDS.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pEDS.checkIfIsAlreadyAvailable()) {
            JOptionPane.showMessageDialog(this, "Data sudah terdaftar dalam database!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan mengedit data ini?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            pEDS.simpanData();
            inForm = false;
            JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            pnDataSupplier1.setRow();
            clickBtn(2);
        }
    }
    public void btnSimpanSupplier() {
        if(!inForm) {
            return;
        }
        if(pTDS.checkAllForm()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi data terlebih dahulu!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pTDS.checkTheInputIsValid()) {
            JOptionPane.showMessageDialog(this, "Masukkan data yang valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(pTDS.checkIfIsAlreadyAvailable()) {
            JOptionPane.showMessageDialog(this, "Data sudah terdaftar dalam database!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pTDS.simpanData();
        inForm = false;
        JOptionPane.showMessageDialog(this, "Simpan data berhasil!", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
        pnDataSupplier1.setRow();
        clickBtn(2);
    }
    public void btnBatalSupplier() {
        int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input dan kembali ke Menu Data Supplier?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if(asn == JOptionPane.YES_OPTION) {
            clickBtn(2);
            inForm = false;
        }
    }
    public void tambahDataSupplier() {
        container.removeAll();
        container.repaint();
        pTDS.setBounds(0, 0, 1190, 590);
        container.add(pTDS);
        resetFooter();
        inForm = true;
    }
    public void editDataSupplier(String id) {
        pEDS = new pnEditSupplier(id);
        pEDS.setVariableBatal(new pnEditSupplier.BatalSupplier() {
            @Override
            public void batalSupplier() {
                btnBatalSupplier();
            }
        });
        pEDS.setVariableEdit(new pnEditSupplier.EditSupplier() {
            @Override
            public void editSupplier() {
                btnEditSupplier();
            }
        });
        container.removeAll();
        container.repaint();
        pEDS.setBounds(0, 0, 1190, 590);
        container.add(pEDS);
        resetFooter();
        inForm = true;
    }
    public void hapusDataSupplier(String id) {
        pnDataSupplier1.hapusData(id);
        pnDataSupplier1.setRow();
    }
    
    // ------------------------------------------ Setter Method ------------------------------------------
    public void setIdData(String id) {
        this.idData = id;
    }
    public void setDateData(String date) {
        this.date = date;
    }
    
    
    // Logo Method
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
    
    public void refreshAllTheDatabase() {
        // Form
        pnMenuUtama1.countTotal();
        pnDataBarang1.setRow();
        pnDataKadaluarsa1.setRow();
        pnDataKeuangan1.setRow();
        pnDataSupplier1.setRow();
        pnDataTransaksi1.setRow();
        
        pnDataKadaluarsa1.resetAllForm();
        pnDataBarang1.resetAllForm();
        pnDataKeuangan1.resetAllForm();
        pK.resetAllForm();
        pnDataSupplier1.resetAllForm();
        pnDataTransaksi1.resetAllForm();
        pnKasir1.resetAllForm();
        
        //Input Tambah
        pTDB.resetAll();
        pTDK.resetAll();
        pTDKu.resetAll();
        pTDS.resetAll();
        
        // Input Edit
        try {
            pEDB.resetAll();
        } catch (Exception e) {
            System.err.println("Masih belum kebuka yang Edit Barang!");
        }
        try {
            pEDK.resetAll();
        } catch (Exception e) {
            System.err.println("Masih belum kebuka yang Edit Kadaluarsa!");
        }
        try {
            pEDKu.resetAll();
        } catch (Exception e) {
            System.err.println("Masih belum kebuka yang Edit Keuangan!");
        }
        try {
            pEDS.resetAll();
        } catch (Exception e) {
            System.err.println("Masih belum kebuka yang Edit Supplier!");
        }
    }
    
    
    // Button / Label Method
    public void clickBtn(int pn) {
        switch(pn) {
            case 0 -> {
                container.removeAll();
                container.repaint();
                pnMenuUtama1.setBounds(0, 0, 1190, 590);
                container.add(pnMenuUtama1);
                resetFooter();
            }
            case 1 -> {
                container.removeAll();
                container.repaint();
                pnDataBarang1.setBounds(0, 0, 1190, 590);
                container.add(pnDataBarang1);
                resetFooter();
            }
            case 2 -> {
                container.removeAll();
                container.repaint();
                pnDataSupplier1.setBounds(0, 0, 1190, 590);
                container.add(pnDataSupplier1);
                resetFooter();
            }
            case 3 -> {
                container.removeAll();
                container.repaint();
                pnKasir1.setBounds(0, 0, 1190, 670);
                container.add(pnKasir1);
                pnFooter.setVisible(false);
            }
            case 4 -> {
                container.removeAll();
                container.repaint();
                pnDataTransaksi1.setBounds(0, 0, 1190, 590);
                container.add(pnDataTransaksi1);
                resetFooter();
            }
            case 5 -> {
                container.removeAll();
                container.repaint();
                pnDataKeuangan1.setBounds(0, 0, 1190, 590);
                container.add(pnDataKeuangan1);
                resetFooter();
            }
            case 10 -> {
                container.removeAll();
                container.repaint();
                pnDataKadaluarsa1.setBounds(0, 0, 1190, 590);
                container.add(pnDataKadaluarsa1);
                resetFooter();
            }
        }
    }
    
    public void resetFooter() {
        pnFooter.setVisible(false);
        pnFooter.setVisible(true);
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
        sidebar = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        secAdmin = new javax.swing.JPanel();
        lbMenuUtama = new javax.swing.JLabel();
        lbDataBarang = new javax.swing.JLabel();
        lbDataSupplier = new javax.swing.JLabel();
        iconMenuUtama = new javax.swing.JLabel();
        iconDataBarang = new javax.swing.JLabel();
        iconDataKeuangan = new javax.swing.JLabel();
        lbDataSupplier1 = new javax.swing.JLabel();
        iconDataSupplier = new javax.swing.JLabel();
        iconDataSupplierm1 = new javax.swing.JLabel();
        secUser = new javax.swing.JPanel();
        lbKasir = new javax.swing.JLabel();
        lbDataTransaksi = new javax.swing.JLabel();
        iconKasir = new javax.swing.JLabel();
        iconDataTransaksi = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbLogin = new javax.swing.JLabel();
        lbNamaKasir = new javax.swing.JLabel();
        iconLogOut = new javax.swing.JLabel();
        pnFooter = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        container = new javax.swing.JPanel();
        pnDataSupplier1 = new FormPanel.pnDataSupplier();
        pnMenuUtama1 = new FormPanel.pnMenuUtama();
        pnDataBarang1 = new FormPanel.pnDataBarang();
        pnKasir1 = new FormPanel.pnKasir();
        pnDataTransaksi1 = new FormPanel.pnDataTransaksi();
        pnDataKadaluarsa1 = new FormPanel.pnDataKadaluarsa();
        pnDataKeuangan1 = new FormPanel.pnDataKeuangan();
        logo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        navbar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        customTimeLabel1 = new CustomComponent.CustomTimeLabel();
        iconTime = new javax.swing.JLabel();
        customButton1 = new CustomComponent.CustomButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebar.setBackground(new java.awt.Color(78, 115, 223));

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
        lbMenuUtama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMenuUtama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMenuUtamaMouseClicked(evt);
            }
        });
        secAdmin.add(lbMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 90, 30));

        lbDataBarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataBarang.setForeground(new java.awt.Color(255, 255, 255));
        lbDataBarang.setText("Data Barang");
        lbDataBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDataBarangMouseClicked(evt);
            }
        });
        secAdmin.add(lbDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 30));

        lbDataSupplier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataSupplier.setForeground(new java.awt.Color(255, 255, 255));
        lbDataSupplier.setText("Data Supplier");
        lbDataSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDataSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDataSupplierMouseClicked(evt);
            }
        });
        secAdmin.add(lbDataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 30));
        secAdmin.add(iconMenuUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));
        secAdmin.add(iconDataBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 30, 30));
        secAdmin.add(iconDataKeuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 30, 30));

        lbDataSupplier1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataSupplier1.setForeground(new java.awt.Color(255, 255, 255));
        lbDataSupplier1.setText("Data Keuangan");
        lbDataSupplier1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDataSupplier1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDataSupplier1MouseClicked(evt);
            }
        });
        secAdmin.add(lbDataSupplier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, 30));
        secAdmin.add(iconDataSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 30, 30));
        secAdmin.add(iconDataSupplierm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 30, 30));

        secUser.setBackground(new java.awt.Color(78, 115, 223));
        secUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbKasir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbKasir.setForeground(new java.awt.Color(255, 255, 255));
        lbKasir.setText("Kasir");
        lbKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbKasirMouseClicked(evt);
            }
        });
        secUser.add(lbKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 30));

        lbDataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDataTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lbDataTransaksi.setText("Data Transaksi");
        lbDataTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDataTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDataTransaksiMouseClicked(evt);
            }
        });
        secUser.add(lbDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 100, 30));
        secUser.add(iconKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));
        secUser.add(iconDataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 30, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Keluar");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        lbLogin.setBackground(new java.awt.Color(133, 135, 150));
        lbLogin.setForeground(new java.awt.Color(133, 135, 150));
        lbLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLogin.setText("Masuk Sebagai Admin ");

        lbNamaKasir.setForeground(new java.awt.Color(133, 135, 150));
        lbNamaKasir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNamaKasir.setText("jLabel3");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNamaKasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNamaKasir)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(secUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(secAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                        .addComponent(iconLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(secAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secUser, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jPanel1.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 180, 740));

        pnFooter.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setBackground(new java.awt.Color(133, 135, 150));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(133, 135, 150));
        jLabel14.setText("2024 - Sistem Penjualan Pada Toko Kelontong | Kelompok 5");

        javax.swing.GroupLayout pnFooterLayout = new javax.swing.GroupLayout(pnFooter);
        pnFooter.setLayout(pnFooterLayout);
        pnFooterLayout.setHorizontalGroup(
            pnFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFooterLayout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnFooterLayout.setVerticalGroup(
            pnFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFooterLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(pnFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 670, 1240, 80));

        container.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(pnMenuUtama1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnDataKeuangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnDataBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1202, 1202, 1202)
                .addComponent(pnDataKadaluarsa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(0, 2069, Short.MAX_VALUE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnDataTransaksi1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnKasir1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 2070, Short.MAX_VALUE)))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(1749, 1749, 1749)
                    .addComponent(pnDataSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(2390, Short.MAX_VALUE)))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnDataKeuangan1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnMenuUtama1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnDataKadaluarsa1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                            .addComponent(pnDataBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 91, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnDataTransaksi1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnKasir1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(containerLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(pnDataSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );

        jPanel1.add(container, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 1190, 670));

        logo.setBackground(new java.awt.Color(78, 115, 223));
        logo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        logo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -10, 280, 130));

        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 80));

        navbar.setBackground(new java.awt.Color(255, 255, 255));
        navbar.setForeground(new java.awt.Color(204, 204, 255));

        jLabel2.setBackground(new java.awt.Color(133, 135, 150));
        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(133, 135, 150));
        jLabel2.setText("Toko Kelontong “Putra”");

        customTimeLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        customButton1.setText("customButton1");
        customButton1.setBackgroundColor(new java.awt.Color(0, 153, 255));
        customButton1.setFontSize(12);
        customButton1.setTextBold(1);
        customButton1.setTextColor(java.awt.Color.white);
        customButton1.setTheText("REFRESH");
        customButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(620, 620, 620)
                .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconTime, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(customTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarLayout.createSequentialGroup()
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(iconTime, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(customTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );

        jPanel1.add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1190, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input dan akan Log Out?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                login log = new login();
                log.setVisible(true);
                this.dispose();
                inForm = false;
            }
        } else {
            login log = new login();
            log.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void lbDataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDataBarangMouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                clickBtn(1);
                inForm = false;
            }
        } else {
            clickBtn(1);
        }
    }//GEN-LAST:event_lbDataBarangMouseClicked

    private void lbMenuUtamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMenuUtamaMouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                clickBtn(0);
                inForm = false;
            }
        } else {
            clickBtn(0);
        }
    }//GEN-LAST:event_lbMenuUtamaMouseClicked

    private void lbDataSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDataSupplierMouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                clickBtn(2);
                inForm = false;
            }
        } else {
            clickBtn(2);
        }
    }//GEN-LAST:event_lbDataSupplierMouseClicked

    private void lbKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbKasirMouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                clickBtn(3);
                inForm = false;
            }
        } else {
            clickBtn(3);
        }
    }//GEN-LAST:event_lbKasirMouseClicked

    private void lbDataTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDataTransaksiMouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                clickBtn(4);
                inForm = false;
            }
        } else {
            clickBtn(4);
        }
    }//GEN-LAST:event_lbDataTransaksiMouseClicked

    private void lbDataSupplier1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDataSupplier1MouseClicked
        // TODO add your handling code here:
        if(inForm) {
            int asn = JOptionPane.showConfirmDialog(this, "Apakah anda akan membatalkan input?", "Peringatan", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(asn == JOptionPane.YES_OPTION) {
                clickBtn(5);
                inForm = false;
            }
        } else {
            clickBtn(5);
        }
    }//GEN-LAST:event_lbDataSupplier1MouseClicked

    private void customButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton1ActionPerformed
        // TODO add your handling code here:
        refreshAllTheDatabase();
    }//GEN-LAST:event_customButton1ActionPerformed

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
                new MenuUtama("A", "Mahayoga").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private CustomComponent.CustomButton customButton1;
    private CustomComponent.CustomTimeLabel customTimeLabel1;
    private javax.swing.JLabel iconDataBarang;
    private javax.swing.JLabel iconDataKeuangan;
    private javax.swing.JLabel iconDataSupplier;
    private javax.swing.JLabel iconDataSupplierm1;
    private javax.swing.JLabel iconDataTransaksi;
    private javax.swing.JLabel iconKasir;
    private javax.swing.JLabel iconLogOut;
    private javax.swing.JLabel iconMenuUtama;
    private javax.swing.JLabel iconTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JLabel lbDataBarang;
    private javax.swing.JLabel lbDataSupplier;
    private javax.swing.JLabel lbDataSupplier1;
    private javax.swing.JLabel lbDataTransaksi;
    private javax.swing.JLabel lbKasir;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMenuUtama;
    private javax.swing.JLabel lbNamaKasir;
    private javax.swing.JPanel logo;
    private javax.swing.JPanel navbar;
    private FormPanel.pnDataBarang pnDataBarang1;
    private FormPanel.pnDataKadaluarsa pnDataKadaluarsa1;
    private FormPanel.pnDataKeuangan pnDataKeuangan1;
    private FormPanel.pnDataSupplier pnDataSupplier1;
    private FormPanel.pnDataTransaksi pnDataTransaksi1;
    private javax.swing.JPanel pnFooter;
    private FormPanel.pnKasir pnKasir1;
    private FormPanel.pnMenuUtama pnMenuUtama1;
    private javax.swing.JPanel secAdmin;
    private javax.swing.JPanel secUser;
    private javax.swing.JPanel sidebar;
    // End of variables declaration//GEN-END:variables
}
