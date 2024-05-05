# SemesterTwo-CashierApp

## Bagian Form Yang Sudah / Belum Jadi
1. Login
- `Login` (Selesai)
2. Form
- `Menu Utama` (Belum) (Sidebar)
- `Data Barang` (Belum) (Sidebar)
- `Data Kadaluarsa` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah)
- `Data Kategori Barang` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah)
- `Data Supplier` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah)
- `Kasir` (Belum) (Semua)
- `Data Transaksi` (Belum) (Sidebar, Tabel, Cari, Hapus, Edit, Tambah, Detail, Cetak, Cek Keuangan)
- `Data Keuangan` (Belum) (Semua)
- `Menu Kategori Barang` (Belum) (Sidebar, Cari)
3. Form Edit
- `Edit Data Barang` (Belum) (Sidebar, CRUD)
- `Edit Data Kadaluarsa` (Belum) (Form, Sidebar, CRUD)
- `Edit Data Supplier` (Belum) (Form, Sidebar, CRUD)
4. Form Tambah
- `Tambah Data Barang` (Belum) (Form, Sidebar, CRUD)
- `Tambah Data Supplier` (Belum) (Form, Sidebar, CRUD)
- `Tambah Data Kadaluarsa` (Belum) (Form, Sidebar, CRUD)
5. Struk
`Struk.jrxml` (Belum) (Desain, Tampilan Data)

## Daftar Isi
<details>
    <summary>Buka untuk melihat daftar isi</summary>
    [Koneksi](#testing)
</details>

## 

## <a name="testing"></a>Sebelum mulai
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

