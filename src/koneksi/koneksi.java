/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

/**
 *
 * @author user
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Asus
 */
public class koneksi {
    Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/toko_putra";
    private final String user = "root";
    private final String pwd = "";
    
    public void koneksi() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Koneksi Berhasil");
        } catch (Exception e) {
            System.out.println("Error:\nKoneksi Data Gagal\n");
            e.printStackTrace();
        }
    }
    
    public ResultSet ambilData(String SQL) {
        try {
            con = DriverManager.getConnection(url, user, pwd);
            Statement st = con.createStatement();
            return st.executeQuery(SQL);
        } catch (Exception e) {
            System.out.println("Error:\nPengecekan Data Gagal Diakses!");
            e.printStackTrace();
            return null;
        }
    }
    
    public void aksi(String SQL) {
        try {
            con = DriverManager.getConnection(url, user, pwd);
            Statement st = con.createStatement();
            st.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error:\nAksi Gagal Diakses!");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        koneksi db = new koneksi();
        db.koneksi();
//        ResultSet rs = db.ambilData("SELECT * FROM data_diri_pasien");
//        try {
//            while(rs.next()) {
//                System.out.println(rs.getString("nomor_rekam_medis"));
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    }
}