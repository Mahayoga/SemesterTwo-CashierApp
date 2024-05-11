-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 11 Bulan Mei 2024 pada 14.28
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tokoputranew`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pengguna`
--

CREATE TABLE `data_pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `role` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_pengguna`
--

INSERT INTO `data_pengguna` (`id_pengguna`, `username`, `password`, `role`) VALUES
(1, 'Mahayoga', 'mahayoga', 'A'),
(2, 'Rangga', 'rangga', 'U'),
(3, 'Pingki', 'pingki', 'A'),
(4, 'Nisa', 'nisa', 'U'),
(5, 'Nayla', 'nayla', 'U');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_barang`
--

CREATE TABLE `detail_barang` (
  `kode_barang` char(20) NOT NULL,
  `tgl_kadaluarsa` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `detail_barang`
--

INSERT INTO `detail_barang` (`kode_barang`, `tgl_kadaluarsa`, `status`) VALUES
('MN0006', '2024-05-10', 'Belum Terbuang'),
('MN0006', '2024-05-12', 'Belum Terbuang'),
('MN0006', '2024-05-10', 'Belum Terbuang'),
('KP0001', '2024-05-12', 'Belum Terbuang'),
('KP0001', '2024-05-12', 'Belum Terbuang'),
('KP0002', '2024-05-14', 'Belum Terbuang'),
('KP0002', '2024-05-14', 'Belum Terbuang');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` char(10) NOT NULL,
  `kode_barang` char(20) NOT NULL,
  `tanggal` date NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `harga_barang` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Struktur dari tabel `stok_barang`
--

CREATE TABLE `stok_barang` (
  `id_barang` char(20) NOT NULL,
  `kode_barang` char(20) NOT NULL,
  `id_supplier` int(11) NOT NULL,
  `nama_barang` varchar(70) NOT NULL,
  `stok_tersedia` int(11) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `kategori_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `stok_barang`
--

INSERT INTO `stok_barang` (`id_barang`, `kode_barang`, `id_supplier`, `nama_barang`, `stok_tersedia`, `harga_beli`, `harga_jual`, `kategori_barang`) VALUES
('KP0001', '', 1, 'Indomie Goreng', 100, 2500, 3000, 3),
('KP0002', '', 1, 'Mie Sedap', 100, 2500, 3000, 3),
('KP0003', '', 2, 'Beras Kepompong 3KG', 10, 40000, 45000, 3),
('KP0004', '', 2, 'Beras Kepompong 5KG', 15, 65000, 70000, 3),
('KP0005', '', 2, 'Beras Apel Merah 5KG', 7, 70000, 72000, 3),
('MK0009', '', 4, 'Wafer Nabati Keju 39g', 11, 1500, 2000, 1),
('MK0010', '', 4, 'Wafer Nabati Coklat 39g', 23, 1500, 2000, 1),
('MK0011', '', 4, 'Wafer SIIP', 26, 500, 1000, 1),
('MN0006', '', 3, 'Aqua Botol 1.5L', 30, 4500, 5000, 2),
('MN0007', '', 3, 'Aqua Botol 600ml', 14, 2500, 3500, 2),
('MN0008', '', 3, 'Teh Pucuk 350ml', 31, 2500, 3000, 2),
('OB0012', '909090909', 5, 'Promag Tablet', 29, 500, 1000, 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `suppliers`
--

CREATE TABLE `suppliers` (
  `id_supplier` int(11) NOT NULL,
  `nama_supplier` varchar(40) NOT NULL,
  `no_telp` char(20) NOT NULL,
  `alamat_supplier` varchar(225) NOT NULL,
  `email_supplier` varchar(50) NOT NULL,
  `asal_perusahaan` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `suppliers`
--

INSERT INTO `suppliers` (`id_supplier`, `nama_supplier`, `no_telp`, `alamat_supplier`, `email_supplier`, `asal_perusahaan`) VALUES
(1, 'Mahayoga', '082336374821', 'Probolinggo', 'myoga@gmail.com', 'Perusahaan ABC'),
(2, 'Rangga', '082336374821', 'Gersik', 'ranggak@gmail.com', 'Perusahaan DEF'),
(3, 'Adam', '082336374821', 'Jember', 'adam@gmail.com', 'Perusahaan AH'),
(4, 'Irsyad', '082336374821', 'Gersik', 'irsyad@gmail.com', 'Perusahaan II'),
(5, 'Tegar', '082336374821', 'Jember', 'wahyu@gmail.com', 'Perusahaan WI'),
(6, 'Ibad', '08222', 'Gresik', 'ibad@gmail.com', 'Semen Gresik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(10) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `harga_total` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal_transaksi`, `harga_total`) VALUES
('TR0001', '2024-05-06', '20000'),
('TR0002', '2024-05-06', '32000'),
('TR0003', '2024-05-07', '45000'),
('TR0004', '2024-05-08', '50000'),
('TR0005', '2024-05-08', '24000');

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

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_barang_dan_supplier`  AS SELECT `s`.`nama_supplier` AS `nama_supplier`, `s`.`asal_perusahaan` AS `asal_perusahaan`, `sb`.`nama_barang` AS `nama_barang` FROM (`suppliers` `s` join `stok_barang` `sb` on(`s`.`id_supplier` = `sb`.`id_supplier`)) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_barang_supplier_tglkadaluarsa`
--
DROP TABLE IF EXISTS `view_barang_supplier_tglkadaluarsa`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_barang_supplier_tglkadaluarsa`  AS SELECT `s`.`nama_supplier` AS `nama_supplier`, `sb`.`kode_barang` AS `kode_barang`, `sb`.`nama_barang` AS `nama_barang`, `db`.`tgl_kadaluarsa` AS `tgl_kadaluarsa` FROM ((`suppliers` `s` join `stok_barang` `sb` on(`s`.`id_supplier` = `sb`.`id_supplier`)) join `detail_barang` `db` on(`sb`.`kode_barang` = `db`.`kode_barang`)) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `view_transaksi`
--
DROP TABLE IF EXISTS `view_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_transaksi`  AS SELECT `transaksi`.`id_transaksi` AS `id_transaksi`, `transaksi`.`tanggal_transaksi` AS `tanggal_transaksi`, `transaksi`.`harga_total` AS `harga_total` FROM `transaksi` ;

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
  ADD CONSTRAINT `stok_barang_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `suppliers` (`id_supplier`),
  ADD CONSTRAINT `stok_barang_ibfk_2` FOREIGN KEY (`kategori_barang`) REFERENCES `kategori` (`id_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
