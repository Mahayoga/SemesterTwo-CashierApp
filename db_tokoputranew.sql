-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 27 Bulan Mei 2024 pada 08.45
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tokoputra`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pengguna`
--

CREATE TABLE `data_pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `kode_pengguna` char(20) NOT NULL,
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `role` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `data_pengguna`
--

INSERT INTO `data_pengguna` (`id_pengguna`, `kode_pengguna`, `username`, `password`, `role`) VALUES
(1, '1384931076', 'Mahayoga', 'mahayoga', 'A'),
(2, '1384920580', 'Rangga', 'rangga', 'U'),
(3, '1384856580', 'Pingki', 'pingki', 'A'),
(4, '1384805636', 'Nisa', 'nisa', 'U'),
(5, '1398715908', 'Nayla', 'nayla', 'U');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_barang`
--

CREATE TABLE `detail_barang` (
  `kode_barang` char(20) NOT NULL,
  `tgl_kadaluarsa` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_barang`
--

INSERT INTO `detail_barang` (`kode_barang`, `tgl_kadaluarsa`, `status`) VALUES
('KP0001', '2024-08-30', 'Belum Terbuang'),
('KP0001', '2024-01-23', 'Sudah Terjual'),
('KP0001', '2024-01-23', 'Sudah Terjual'),
('KP0001', '2024-01-23', 'Belum Terbuang'),
('KP0001', '2024-01-23', 'Belum Terbuang'),
('KP0001', '2024-01-23', 'Belum Terbuang'),
('KP0001', '2024-01-23', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('KP0001', '2024-06-06', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang'),
('MK0001', '2024-12-29', 'Belum Terbuang');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` char(10) NOT NULL,
  `kode_barang` char(20) NOT NULL,
  `tanggal` date NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `harga_barang` varchar(25) NOT NULL,
  `harga_total` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `kode_barang`, `tanggal`, `jumlah_barang`, `harga_barang`, `harga_total`) VALUES
('TR0001', 'KP0001', '2024-05-27', 2, '3000', '6000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Makanan'),
(2, 'Minuman'),
(3, 'Kebutuhan Pokok'),
(4, 'ATK'),
(5, 'Obat'),
(6, 'Aksesoris');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `kode_pengeluaran` char(7) NOT NULL,
  `tanggal_pengeluaran` date NOT NULL,
  `jenis_pengeluaran` char(30) NOT NULL,
  `harga` int(11) NOT NULL,
  `keterangan` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengeluaran`
--

INSERT INTO `pengeluaran` (`kode_pengeluaran`, `tanggal_pengeluaran`, `jenis_pengeluaran`, `harga`, `keterangan`) VALUES
('KP0001', '2024-05-13', 'Operasional', 100000, 'Heheheheheheheheheheh wwkwkkwkw sanshu uhoeipe bebej,auegu ueueywiu'),
('KP0002', '2024-05-13', 'Beli Barang', 121200, 'Heheheh bewbieuiuwhdi beuidwiebi r ygoyo87y8  gdb iayeui'),
('KP0003', '2024-05-15', 'Beli Barang', 20000, 'Beli barang sapu');

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok_barang`
--

CREATE TABLE `stok_barang` (
  `id_barang` char(20) NOT NULL,
  `kode_barang` char(20) NOT NULL,
  `id_supplier` char(6) NOT NULL,
  `nama_barang` varchar(70) NOT NULL,
  `stok_tersedia` int(11) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `kategori_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stok_barang`
--

INSERT INTO `stok_barang` (`id_barang`, `kode_barang`, `id_supplier`, `nama_barang`, `stok_tersedia`, `harga_beli`, `harga_jual`, `kategori_barang`) VALUES
('KP0001', '8996001304921', 'SP0002', 'Mie Sedap Instant', 18, 2500, 3000, 3),
('MK0001', '8997225840011', 'SP0001', 'Roti Aoka Rasa Keju', 20, 1500, 2500, 1),
('MK0002', '8996001304921', 'SP0001', 'Slai Olai Rasa Nanas', 20, 1500, 2000, 1),
('MK0003', '8991102220729', 'SP0002', 'Blastoz', 20, 1500, 2000, 1),
('MK0004', '8991102187367', 'SP0002', 'Imperial', 20, 1500, 2000, 1),
('MK0005', '8996001305119', 'SP0003', 'Arden', 20, 1500, 2000, 1),
('MK0006', '8991102280211', 'SP0003', 'Waffle', 20, 1500, 2000, 1),
('MN0001', '8996129809131', 'SP0003', 'Cleo 220ml', 20, 2500, 3000, 2),
('MN0002', '8998009040290', 'SP0004', 'Teh Kotak Rasa Blackcurrant', 20, 3500, 4000, 2),
('MN0003', '8886008101053', 'SP0003', 'Aqua 600ml', 20, 2500, 3000, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `suppliers`
--

CREATE TABLE `suppliers` (
  `id_supplier` char(6) NOT NULL,
  `nama_supplier` varchar(40) NOT NULL,
  `no_telp` char(20) NOT NULL,
  `alamat_supplier` varchar(225) NOT NULL,
  `email_supplier` varchar(50) NOT NULL,
  `asal_perusahaan` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `suppliers`
--

INSERT INTO `suppliers` (`id_supplier`, `nama_supplier`, `no_telp`, `alamat_supplier`, `email_supplier`, `asal_perusahaan`) VALUES
('SP0001', 'Mahayoga', '082336374821', 'Probolinggo', 'myoga@gmail.com', 'Perusahaan ABC'),
('SP0002', 'Rangga', '082336374821', 'Gresik', 'ranggak@gmail.com', 'Perusahaan DEF'),
('SP0003', 'Adam', '082336374821', 'Jember', 'adam@gmail.com', 'Perusahaan AH'),
('SP0004', 'Irsyad', '082336374821', 'Gersik', 'irsyad@gmail.com', 'Perusahaan II'),
('SP0005', 'Tegar', '082336374821', 'Jember', 'wahyu@gmail.com', 'Perusahaan WI');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(10) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `total_item` int(11) NOT NULL,
  `harga_total` varchar(25) NOT NULL,
  `bayar` char(10) NOT NULL,
  `kembali` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal_transaksi`, `total_item`, `harga_total`, `bayar`, `kembali`) VALUES
('TR0001', '2024-05-27', 1, '6000', '10000', '4000');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_barang_dan_supplier`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_barang_dan_supplier` (
`nama_supplier` varchar(40)
,`asal_perusahaan` varchar(225)
,`nama_barang` varchar(70)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_barang_supplier_tglkadaluarsa`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_barang_supplier_tglkadaluarsa` (
`nama_supplier` varchar(40)
,`kode_barang` char(20)
,`nama_barang` varchar(70)
,`tgl_kadaluarsa` date
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `view_transaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `view_transaksi` (
`id_transaksi` char(10)
,`tanggal_transaksi` date
,`harga_total` varchar(25)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `view_barang_dan_supplier`
--
DROP TABLE IF EXISTS `view_barang_dan_supplier`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_barang_dan_supplier`  AS  select `s`.`nama_supplier` AS `nama_supplier`,`s`.`asal_perusahaan` AS `asal_perusahaan`,`sb`.`nama_barang` AS `nama_barang` from (`suppliers` `s` join `stok_barang` `sb` on(`s`.`id_supplier` = `sb`.`id_supplier`)) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_barang_supplier_tglkadaluarsa`
--
DROP TABLE IF EXISTS `view_barang_supplier_tglkadaluarsa`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_barang_supplier_tglkadaluarsa`  AS  select `s`.`nama_supplier` AS `nama_supplier`,`sb`.`kode_barang` AS `kode_barang`,`sb`.`nama_barang` AS `nama_barang`,`db`.`tgl_kadaluarsa` AS `tgl_kadaluarsa` from ((`suppliers` `s` join `stok_barang` `sb` on(`s`.`id_supplier` = `sb`.`id_supplier`)) join `detail_barang` `db` on(`sb`.`kode_barang` = `db`.`kode_barang`)) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi`
--
DROP TABLE IF EXISTS `view_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi`  AS  select `transaksi`.`id_transaksi` AS `id_transaksi`,`transaksi`.`tanggal_transaksi` AS `tanggal_transaksi`,`transaksi`.`harga_total` AS `harga_total` from `transaksi` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data_pengguna`
--
ALTER TABLE `data_pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indeks untuk tabel `detail_barang`
--
ALTER TABLE `detail_barang`
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`kode_pengeluaran`);

--
-- Indeks untuk tabel `stok_barang`
--
ALTER TABLE `stok_barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_supplier` (`id_supplier`),
  ADD KEY `kategori_barang` (`kategori_barang`);

--
-- Indeks untuk tabel `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `data_pengguna`
--
ALTER TABLE `data_pengguna`
  MODIFY `id_pengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_barang`
--
ALTER TABLE `detail_barang`
  ADD CONSTRAINT `detail_barang_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `stok_barang` (`id_barang`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_3` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `detail_transaksi_ibfk_4` FOREIGN KEY (`kode_barang`) REFERENCES `stok_barang` (`id_barang`);

--
-- Ketidakleluasaan untuk tabel `stok_barang`
--
ALTER TABLE `stok_barang`
  ADD CONSTRAINT `stok_barang_ibfk_2` FOREIGN KEY (`kategori_barang`) REFERENCES `kategori` (`id_kategori`),
  ADD CONSTRAINT `stok_barang_ibfk_3` FOREIGN KEY (`id_supplier`) REFERENCES `suppliers` (`id_supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
