# SemesterTwo-CashierApp

## <a name="bagian-form"></a>Bagian Form Yang Sudah / Belum Jadi
1. Login <a name="login"></a>
    - `Login` (Selesai)
2. Form <a name="form"></a>
    - `Menu Utama` (Belum) (Sidebar)
    - `Data Barang` (Belum) (Sidebar)
    - `Data Kadaluarsa` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah)
    - `Data Kategori Barang` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah)
    - `Data Supplier` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah)
    - `Kasir` (Belum) (Semua)
    - `Data Transaksi` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah, Detail, Cetak, Cek Keuangan)
    - `Data Keuangan` (Belum) (Semua)
    - `Menu Kategori Barang` (Belum) (Sidebar, Cari)
3. Form Edit <a name="form-edit"></a>
    - `Edit Data Barang` (Belum) (Sidebar, CRUD)
    - `Edit Data Kadaluarsa` (Belum) (Form, Sidebar, CRUD)
    - `Edit Data Supplier` (Belum) (Form, Sidebar, CRUD)
4. Form Tambah <a name="form-tambah"></a>
    - `Tambah Data Barang` (Belum) (Form, Sidebar, CRUD)
    - `Tambah Data Supplier` (Belum) (Form, Sidebar, CRUD)
    - `Tambah Data Kadaluarsa` (Belum) (Form, Sidebar, CRUD)
5. Struk <a name="struk"></a>
    - `Struk.jrxml` (Belum) (Desain, Tampilan Data)

## Daftar Isi
<details>
    <summary>Buka untuk melihat daftar isi</summary>

- [Daftar Isi](#daftar-isi)

- [Bagian Form](#bagian-form)
    - [Login](#login)
    - [Form](#form)
    - [Form-Edit](#form-edit)
    - [Form-Tambah](#form-tambah)
    - [Struk](#struk)

- [Tentang](#tentang)

- [Koneksi](#koneksi)
    - [Bagian Import](#koneksi-import)
    - [Variabel Koneksi](#koneksi-var)
    - [Method Koneksi](#koneksi-koneksi)
    - [Method Ambil Data](#koneksi-ambilData)
    - [Method Aksi](#koneksi-aksi)

- [Custom Component](#custom-component)
    - [Custom Tombol](#custom-btn)
    - [Custom Panel](#custom-panel)
    - [Custom Table](#custom-table)
    - [Custom Time Label](#custom-time-label)

</details>

## Tentang
Projek ini adalah tugas semester yang diberikan untuk memenuhi nilai semester nantinya. Dekripsi dari aplikasi ini adalah membuat aplikasi kasir berbasis dekstop (Java) dengan menggunakan IDE Netbeans. Dibawah ini adalah full dokumentasi dari projek kami. Semua kode / file disini adalah bebas digunakan oleh **SIAPA SAJA** (*tapi setelah projek selesai*).

Projek ini menggunakan database dari *MySQL* untuk menyimpan datanya. Jadi yang pertama kita buat adalah [*Koneksi*](#koneksi)

## Koneksi
- Pertama kita membuat package koneksi terlebih dahulu, yang nantinya di dalam nya memuat class `koneksi.java`

  <img src="">

- <a name="koneksi-import"></a>Jika sudah kita akan mengisi class `koneksi.java` tersebut, selanjutnya kita mengisi import terlebih dahulu.
  ```
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.ResultSet;
  import java.sql.Statement;
  ```

  <img src="">
  
  Gada penjelasan tentang import sih, tapi kalo kalian mau tau bisa cek dokumentasi di bawah ini (*klik aja*):
  * [`java.sql.Connection`](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)
  * [`java.sql.DriverManager`](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html)
  * <a name="result-set"></a>[`java.sql.ResultSet`](https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html)
  * <a name="statement"></a>[`java.sql.Statement`](https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html)

- <a name="koneksi-var"></a>Okey aku anggap kalian udah ketik import tadi yakann, sekarang lanjut ke kode di dalam class.
  ```
  public Connection con;
  private final String driver = "com.mysql.cj.jdbc.Driver";
  private final String url = "jdbc:mysql://localhost:3306/db_tokoputranew";
  private final String user = "root";
  private final String pwd = "";
  ```

  <img src="">

- <a name="koneksi-koneksi"></a>Nahh kalo udah kita buat method `koneksi()` yang fungsinya buat koneksikan ke dalam database.
  ```
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
  ```

  <img src="">

  Penjelasan kode di atas cuma sedikit yang aku tau, tapi aku berusaha buat jelasin dah.
  * Pertama yang `public void koneksi()`
  Ini adalah kode buat method koneksi agar public, atau bisa di akses di class lain. Ini memudahkan kita nanti membuat koneksi di dalam *Form* lain.\
  * Yang kedua adalah `try`. Harusnya kode ini adalah ditulis `try-catch` yang fungsi nya adalah menangkap error dari kode yang kita tulis agar tidak menghentikan program yang kita jalankan. Biasanya kan kalo kita buat kode trus kalo ada error kan biasanya berhenti tuh kodenya, nah kalo kita taruh `try-catch`, meskipun kode tersebut error, kode yang lainnya gak akan gagal dijalankan. Buat referensi nya klik <a name="try-catch"></a>[*disini*](https://www.w3schools.com/java/java_try_catch.asp).
  * Yang ketiga adalah kode ini: `Class.forName(driver)`. Nah kalo buat kode ini kurang tau ya, tapi kalo mau referensi nya [*disini*](https://www.geeksforgeeks.org/class-forname-method-in-java-with-examples/)
  * <a name="con-driver-manager"></a>Yang keempat `con = DriverManager.getConnection(url, user, pwd);`. Kode ini kalo penjelasan nya kurang tau, tapi intinya kode ini menginisialisasi variabel `con` agar memiliki atribut yang akan digunakan untuk koneksi dalam database. Kode tersebut bisa kalian cek referensi nya [*disini*](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-java.lang.String-java.lang.String-)
  * Selebihnya adalah kode *opsional* untuk di tulis.
  
- <a name="koneksi-ambilData"></a>Selanjutnya adalah membuat method `ambilData()`. Fungsi method ini adalah untuk menjalankan kode `SQL` atau `Data Definition Language` seperti `SELECT`. Selengkapnya bisa kalian lihat di bawah ini:
  ```
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
  ```
  <img src="">

  Method diatas digunakan untuk mengambil data dari dalam database. Selengkapnya lihat penjelasan di bawah ini:
  * Pada kode awal `public ResultSet ambilData(String SQL)` adalah membuat sebuah method `ambilData()` dengan dibutuhkan parameter `String` bernama `SQL`. Untuk referensi parameter, kalian bisa lihat [*disini*](https://www.w3schools.com/java/java_methods_param.asp). Method ini akan mengembalikan sebuah nilai `ResultSet`, kalian bisa cek untuk `ResultSet` [*disini*](#result-set).
  * Yang kedua adalah kode `con = DriverManager.getConnection(url, user, pwd)`, penjelasan kode diatas sudah tersedia [*disini*](#con-driver-manager).
  * Yang ketiga, adalah `Statement st = con.createStatement();`. Kode tersebut adalah menyimpan kode query `SQL` ke dalam objek `Statement`. Untuk referensi nya ada [*disini*](#statement).
  Yang keempat adalah kode `return st.executeQuery(SQL)`, yang berarti kita akan mengembalikan nilai data yang diambil dari database hasil dari query `SQL` tadi. Untuk referensi `executeQuery(SQL)` bisa kalian lihat [*disini*](https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html#executeQuery-java.lang.String-).
  * Selebihnya adalah kode *opsional* untuk di tulis.

- <a name="koneksi-aksi"></a>Selanjutnya adalah membuat method `aksi()`. Fungsi method ini adalah untuk menjalankan query `SQL` bagian `Data Manipulation Language` (DML) seperti `INSERT`, `UPDATE`, `DELETE`.
  ```
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
  ```

  <img src="">

  * Untuk kode diatas bagian `con = DriverManager.getConnection(url, user, pwd);`, `Statement st = con.createStatement();` bisa kalian lihat [*disini*](#con-driver-manager) dan [*disini*](#statement).
  * Sekilas kode dalam method `aksi()` ini sama dengan method `ambilData()` diatas, bedanya adalah pada kode `st.executeUpdate(SQL)` sedangkan pada method `ambilData()` menggunakan `executeQuery(SQL)` dan method `aksi()` ini tidak mengembalikan nilai return apapun. Referensi tentang kode `st.executeUpdate(SQL)`, bisa dilihat [*disini*](https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html#executeUpdate-java.lang.String-).

Untuk penjelasan mengenai kode di dalam class `koneksi.java` hanya seperti itu dulu. Memang penjelasan saya mungkin kurang lengkap, tetapi saya akan mencantumkan link-link referensi dan hal hal yang harus kalian pelajari dahulu sebelum masuk ke dalam pembuatan projek ini. Selanjutnya kita akan memasuki package `CustomComponent`.

## Custom Component
Di dalam package `CustomComponent` ini, terdapat beberapa class diantaranya:
1. Class [`CustomButton`](#custom-btn).
2. Class [`CustomPanel`](#custom-panel)
3. Class [`CustomTable`](#custom-table)
4. Class [`CustomTimeLabel`](#custom-time-label)

Yang berfungsi sebagai custom komponen dalam java agar bisa dimodifikasi lebih jauh, seperti memberikan sebuah warna background, font, border, dan lainnya.

1. Class `CustomButton` <a name="custom-btn"></a>
  Di dalam class ini, nantinya akan berfungsi sebagai tombol yang bisa di modifikasi lebih dalam. Dalam kasus projek ini, saya memodifikasi tombol seperti warna, background, font, border, rounded border, dan lainnya.
  Nih buat referensi nya (klik aja):
    - <a name="color"></a>[`Color`](https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html).
      - Fungsi `Color` disini adalah untuk membuat warna menggunakan API yang sudah disediakan oleh Java.
   - <a name="dimension"></a>[`Dimension`](https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html).
     - Fungsi `Dimension` disini adalah untuk membuat ukuran baru dari sebuah komponen dengan dibutuhkan dua [*argument*]() yaitu *width* dan *height*.
   - <a name="font"></a>[`Font`](https://docs.oracle.com/javase/8/docs/api/java/awt/Font.html).
     - Fungsi `Font` disini adalah untuk mens-pesifikasikan font kedalam sebuah tulisan di dalam komponen seperti ukuran font, tipe font, atau juga bold.
   - <a name="font-metrics"></a>[`FontMetrics`](https://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.html).
     - Fungsi `FontMetrics` fungsi utama saya menggunakan API ini adalah untuk mendapatkan lebar dan tinggi dari sebuah font.
   - <a name="graphics"></a>[`Graphics`](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html).
     - Fungsi `Graphics` disini adalah untuk menggambar sesuatu seperti warna, bentuk dan lain lain di dalam sebuah komponen.
   - <a name="graphics2d"></a>[`Graphics2D`](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html).
     - Fungsi `Graphics2D` adalah untuk menggambar sesuatu di dalam sebuah komponen seperti [`Graphics`](#graphics), bedanya disini bisa ditambah seperti [`anti-aliasing`](#rendering-hints) yang bisa kalian lihat di dalam dokumentasi [`RenderingHints`](#rendering-hints).
   - <a name="rendering-hints"></a>[`RenderingHints`](https://docs.oracle.com/javase/8/docs/api/java/awt/RenderingHints.html).
     - Fungsi `RenderingHints` disini adalah untuk mengambil attribut untuk me-render sebuah komponen dengan [`Graphics`](#graphics) atau [`Graphics2D`](#graphics2d).
   - <a name="mouse-adapter"></a>[`MouseAdapter`](https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html)
   - <a name="mouse-event"></a>[`MouseEvent`](https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseEvent.html)
   - <a name="jbutton"></a>[`JButton`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html)
   - <a name="empty-border"></a>[`EmptyBorder`](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/border/EmptyBorder.html)



2. <a name="custom-panel"></a>Class `CustomPanel`
  Di dalam class ini, nantinya akan berfungsi sebagai panel yang bisa di modifikasi lebih dalam. Dalam kasus projek ini, saya memodifikasi panel seperti warna, background, font, border, rounded border, dan lainnya.
  Saya tidak akan membahas lebih jauh tentang kode ini (Soalnya udah malem jam setengah 3 pagi >_<. Mungkin nanti lah ya). Tapi, saya tetep ngasih referensi nya (klik aja):
   - <a name="panel"></a>[`JPanel`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JPanel.html)
   - [`Color`](#color)
   - [`Graphics`](#graphics)
   - [`Graphics2D`](#graphics2d)
   - [`RenderingHints`](#rendering-hints)

3. <a name="custom-table"></a>Class `CustomTable`
  Di dalam class ini, nantinya akan berfungsi sebagai tabel yang bisa di modifikasi lebih dalam. Dalam kasus projek ini, saya memodifikasi tabel seperti warna, background, font, border, rounded border, dan lainnya.
  Saya tidak akan membahas lebih jauh tentang kode ini (Soalnya udah malem jam 3 kurang 10 menit pagi >_<. Mungkin nanti lah ya). Tapi, saya tetep ngasih referensi nya (klik aja):

    - <a name="jtable"></a>[`JTable`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JTable.html)
    - <a name="component"></a>[`Component`](https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html)
    - <a name="jlabel"></a>[`JLabel`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JLabel.html)
    - <a name="line-border"></a>[`LineBorder`](https://docs.oracle.com/javase/8/docs/api/javax/swing/border/LineBorder.html)
    - <a name="default-table-cell-renderer"></a>[`DefaultTableCellRenderer`](https://docs.oracle.com/javase%2F8%2Fdocs%2Fapi%2F%2F/javax/swing/table/DefaultTableCellRenderer.html)
    - <a name="default-table-model"></a>[`DefaultTableModel`](https://docs.oracle.com/javase/8/docs/api/javax/swing/table/DefaultTableModel.html)

4. <a name="custom-time-label"></a>Class `CustomTimeLabel`
  Di dalam class ini, nantinya akan berfungsi sebagai label yang bisa menampilkan waktu (jam, menit, detik). Dalam kasus projek ini, saya menggunakan nya sebagai jam saja.
  Saya tidak akan membahas lebih jauh tentang kode ini (Soalnya udah malem jam 3 kurang 10 menit pagi >_<. Mungkin nanti lah ya). Tapi, saya tetep ngasih referensi nya (klik aja):

    - [`JLabel`](#jlabel)
    - <a name="local-time"></a>[`LocalTime`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html)
    - <a name="robot"></a>[`Robot`](https://docs.oracle.com/javase/8/docs/api/java/awt/Robot.html)

Mungkin untuk custom sementara hanya itu aja, mungkin nanti ada komponen lain yang aku custom, contohnya `JTextField`, dan lain lain.

## <a name="hehe"></a>Sebelum mulai
Kita harus buat objek class `koneksi` terlebih dahulu
Kalo di projek kita itu caranya gini:
> ```koneksi db = new koneksi();```

Kalo ada error berarti di import dulu, bisa dari klik error nya trus pencet import, atau import manual:
>```import koneksi.koneksi;```

Kalo udah bisa ke next point

## Cara ambil data dari Database (MySQL) ke Netbeans (Java)
1. Kalo mau ambil data, pertama harus import dulu:
>```import java.sql.ResultSet;```

2. Kalo udah next kita buat objek `ResultSet`
>```ResultSet rs = db.ambilData("");```

3. Kalo udah kita tentukan dulu mau ambil data dari database lewat query `SQL`. Semisal mau ambil data dari tabel `Barang`, kodenya seperti ini kan?:
>```SELECT * FROM barang```

4. Nah kalo udah kita tentukan query `SQL` nya. Baru kita input ke kode di point ke ke dua tadi.
>```ResultSet rs = db.ambilData("SELECT * FROM barang")```

5. Nahhh sipp, kalo udah kita bikin statement `while` buat ambil datanya:
>```while(rs.next()) {```
>``` ```
>```}```

6. Okey kalo udah kita tampilkan outputnya ke console. Semisal kita mau nampilin kolom nama barang di dalam database. Maka kita pake method `rs.getString("nama_barang")`
>```System.out.println(rs.getString("nama_barang"))```

7. Selamat~ udah jadi bisa ngambil data lewat database...

## Cara menampilkan data dari Netbeans (Java) ke dalam Database (MySQL)

Cara nampilin ke tabel itu kodenya itu sama seperti ambil data, tapi bedanya pada poin ke `6` di bagian "Cara ambil data dari Database (MySQL) ke Netbeans (Java)", bedanya nggak pake `System.out.println();`. Jadi kita itu bikin model buat tabel dulu. Kita mulai di poin satu yaa...

1. Membuat model database
<img src="https://github.com/Mahayoga/My-Screenshot/blob/main/image.png">

2. Kita sesuaikan dulu tabel nya, seperti kolom apa aja yang mau dibuat, misal seperti gambar di bawah ini.
<img src="https://github.com/Mahayoga/My-Screenshot/blob/main/heii.PNG">

2. Kita lanjut buat kode ambil data seperti kode pada bagian "Cara ambil data dari Database (MySQL) ke Netbeans (Java)"

# Still Work in Progress (WIP)

Start write from `2024-05-05` to `....`
Made with much effort for my team!