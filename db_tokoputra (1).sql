-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Apr 2024 pada 16.05
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 8.0.12

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
  `username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `role` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `kode_barang` int(11) NOT NULL,
  `tgl_kadaluarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_barang`
--

INSERT INTO `detail_barang` (`kode_barang`, `tgl_kadaluarsa`) VALUES
(1, '2024-03-12'),
(2, '2024-03-10'),
(3, '2024-03-29'),
(4, '2024-03-29'),
(5, '2024-03-21');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `kode_barang` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `harga_barang` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `kode_barang`, `tanggal`, `jumlah_barang`, `harga_barang`) VALUES
(1, 1, '2024-03-14', 1, '100000'),
(2, 1, '2024-03-11', 2, '2000'),
(3, 1, '2024-03-13', 4, ''),
(4, 1, '2024-03-23', 5, '2000'),
(5, 1, '2024-03-18', 22, '18800'),
(6, 2, '2024-04-27', 2, '2000'),
(6, 5, '2024-04-27', 3, '2000'),
(6, 1, '2024-04-28', 2, '2000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok_barang`
--

CREATE TABLE `stok_barang` (
  `kode_barang` int(11) NOT NULL,
  `id_supplier` int(11) NOT NULL,
  `nama_barang` varchar(70) NOT NULL,
  `stok_tersedia` int(11) NOT NULL,
  `harga_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stok_barang`
--

INSERT INTO `stok_barang` (`kode_barang`, `id_supplier`, `nama_barang`, `stok_tersedia`, `harga_barang`) VALUES
(1, 2, 'Semen Gersik', 30, 100000),
(2, 1, 'Nabati', 100, 2000),
(3, 3, 'Wafer', 17, 2000),
(4, 4, 'Nabati Keju', 32, 2000),
(5, 5, 'Chocolatos', 45, 1000);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `id_transaksi` int(11) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `harga_total` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal_transaksi`, `harga_total`) VALUES
(1, '2024-03-12', '12000'),
(2, '2024-03-13', '32000'),
(3, '2024-03-15', '45000'),
(4, '2024-03-16', '43000'),
(5, '2024-03-17', '60000'),
(6, '2024-04-27', '10000');

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
-- Indeks untuk tabel `stok_barang`
--
ALTER TABLE `stok_barang`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `id_supplier` (`id_supplier`);

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
  ADD CONSTRAINT `Detail_Barang_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `stok_barang` (`kode_barang`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_barang`) REFERENCES `stok_barang` (`kode_barang`);

--
-- Ketidakleluasaan untuk tabel `stok_barang`
--
ALTER TABLE `stok_barang`
  ADD CONSTRAINT `Stok_Barang_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `suppliers` (`id_supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
