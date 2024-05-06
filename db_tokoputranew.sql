-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Bulan Mei 2024 pada 04.53
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
  `tgl_kadaluarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `detail_barang`
--

INSERT INTO `detail_barang` (`kode_barang`, `tgl_kadaluarsa`) VALUES
('BR0006', '2025-05-14'),
('BR0006', '2025-05-09'),
('BR0007', '2025-06-02'),
('BR0007', '2025-05-17'),
('BR0005', '2025-03-18'),
('BR0005', '2024-03-11'),
('BR0003', '2024-05-16'),
('BR0004', '2024-09-12'),
('BR0001', '2024-10-16'),
('BR0001', '2024-09-11'),
('BR0012', '2024-09-12'),
('BR0008', '2025-05-01'),
('BR0010', '2024-08-15'),
('BR0009', '2024-08-30'),
('BR0009', '2024-08-15'),
('BR0010', '2024-08-07'),
('BR0011', '2024-10-14'),
('BR0011', '2024-09-08'),
('BR0011', '2024-09-18'),
('BR0011', '2024-10-09');

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

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `kode_barang`, `tanggal`, `jumlah_barang`, `harga_barang`) VALUES
('TR0001', 'BR0002', '2024-05-06', 4, '12000'),
('TR0001', 'BR0007', '2024-05-06', 2, '7000'),
('TR0001', 'BR0012', '2024-05-06', 1, '1000'),
('TR0002', 'BR0006', '2024-05-06', 6, '30000'),
('TR0002', 'BR0009', '2024-05-06', 1, '2000'),
('TR0003', 'BR0003', '2024-05-07', 1, '45000'),
('TR0004', 'BR0003', '2024-05-08', 1, '45000'),
('TR0004', 'BR0006', '2024-05-08', 1, '5000'),
('TR0005', 'BR0008', '2024-05-08', 2, '6000'),
('TR0005', 'BR0010', '2024-05-08', 3, '6000'),
('TR0005', 'BR0011', '2024-05-08', 2, '2000'),
('TR0005', 'BR0006', '2024-05-08', 2, '10000');

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

INSERT INTO `stok_barang` (`kode_barang`, `id_supplier`, `nama_barang`, `stok_tersedia`, `harga_beli`, `harga_jual`, `kategori_barang`) VALUES
('BR0001', 1, 'Indomie Goreng', 100, 2500, 3000, 3),
('BR0002', 1, 'Mie Sedap', 100, 2500, 3000, 3),
('BR0003', 2, 'Beras Kepompong 3KG', 10, 40000, 45000, 3),
('BR0004', 2, 'Beras Kepompong 5KG', 15, 65000, 70000, 3),
('BR0005', 2, 'Beras Apel Merah 5KG', 7, 70000, 72000, 3),
('BR0006', 3, 'Aqua Botol 1.5L', 30, 4500, 5000, 2),
('BR0007', 3, 'Aqua Botol 600ml', 14, 2500, 3500, 2),
('BR0008', 3, 'Teh Pucuk 350ml', 31, 2500, 3000, 2),
('BR0009', 4, 'Wafer Nabati Keju 39g', 11, 1500, 2000, 1),
('BR0010', 4, 'Wafer Nabati Coklat 39g', 23, 1500, 2000, 1),
('BR0011', 4, 'Wafer SIIP', 26, 500, 1000, 1),
('BR0012', 5, 'Promag', 29, 500, 1000, 5);

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
(5, 'Tegar', '082336374821', 'Jember', 'wahyu@gmail.com', 'Perusahaan WI');

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
  ADD PRIMARY KEY (`kode_barang`),
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
  ADD CONSTRAINT `detail_barang_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `stok_barang` (`kode_barang`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_barang`) REFERENCES `stok_barang` (`kode_barang`),
  ADD CONSTRAINT `detail_transaksi_ibfk_3` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);

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
