-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 04, 2019 at 02:12 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `TokoKasur`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `cekStok` (`id` VARCHAR(5)) RETURNS INT(4) BEGIN
DECLARE hasil int(4);
SELECT stok INTO hasil FROM barang WHERE id_barang = id;
RETURN hasil;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getIdKasir` (`nama` VARCHAR(20)) RETURNS VARCHAR(20) CHARSET latin1 BEGIN
DECLARE hasil varchar(20);
SELECT id_kasir INTO hasil FROM kasir WHERE nama_kasir = nama;
RETURN hasil;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(5) NOT NULL,
  `username_admin` varchar(20) NOT NULL,
  `pass_admin` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username_admin`, `pass_admin`) VALUES
('ADM1', 'Lingga', 'pass123');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(5) NOT NULL,
  `nama_barang` varchar(30) NOT NULL,
  `stok` int(4) NOT NULL,
  `harga` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `stok`, `harga`) VALUES
('BRG10', 'Karpet Gulung Bludru', 7, 35000),
('BRG11', 'Karpet Permadani', 19, 100000),
('BRG12', 'Kursi Sandaran', 8, 80000),
('BRG2', 'Kursi', 12, 45000),
('BRG3', 'Seprei', 131, 50000),
('BRG4', 'Bantal', 103, 33000),
('BRG5', 'Kasur Spons ', 160, 490000),
('BRG6', 'Kasur Kapuk', 35, 450000),
('BRG7', 'Spring Bed', 42, 1200000),
('BRG8', 'Kasur Lipat', 5, 140000),
('BRG9', 'Kasur Palembang', 37, 100000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail_transaksi` int(11) NOT NULL,
  `id_transaksi` varchar(5) NOT NULL,
  `id_barang` varchar(5) NOT NULL,
  `jumlah_barang` int(3) NOT NULL,
  `jumlah_harga` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail_transaksi`, `id_transaksi`, `id_barang`, `jumlah_barang`, `jumlah_harga`) VALUES
(36, 'BL1', 'BRG4', 122, 1464000),
(37, 'PS1', 'BRG4', 12, 396000),
(38, 'PS1', 'BRG3', 1, 50000),
(39, 'JL2', 'BRG4', 1, 33000),
(40, 'JL3', 'BRG4', 12, 396000),
(41, 'JL3', 'BRG2', 1, 12000),
(42, 'PS2', 'BRG4', 12, 396000),
(43, 'PS2', 'BRG3', 1, 50000),
(44, 'BL2', 'BRG12', 1, 12000),
(45, 'BL3', 'BRG4', 1, 12000),
(46, 'PS3', 'BRG11', 2, 200000),
(47, 'PS3', 'BRG8', 23, 3220000),
(48, 'PS4', 'BRG9', 12, 1200000);

-- --------------------------------------------------------

--
-- Table structure for table `kasir`
--

CREATE TABLE `kasir` (
  `id_kasir` varchar(5) NOT NULL,
  `nama_kasir` varchar(30) NOT NULL,
  `pass_kasir` varchar(12) NOT NULL,
  `no_telp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kasir`
--

INSERT INTO `kasir` (`id_kasir`, `nama_kasir`, `pass_kasir`, `no_telp`) VALUES
('KSR1', 'Qoheng', 'pass456', '081249558675');

-- --------------------------------------------------------

--
-- Table structure for table `Log_Login`
--

CREATE TABLE `Log_Login` (
  `id` int(11) NOT NULL,
  `namaUser` varchar(20) NOT NULL,
  `waktu_login` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Log_Login`
--

INSERT INTO `Log_Login` (`id`, `namaUser`, `waktu_login`) VALUES
(3, 'Qoheng', '2019-12-02 22:03:53'),
(4, 'Lingga', '2019-12-02 22:55:11'),
(5, 'Lingga', '2019-12-02 22:56:55'),
(6, 'Lingga', '2019-12-02 22:59:40'),
(7, 'Barokah', '2019-12-02 23:00:15'),
(8, 'Qoheng', '2019-12-02 23:01:02'),
(9, 'Qoheng', '2019-12-02 23:02:26'),
(10, 'Lingga', '2019-12-02 23:03:41'),
(11, 'Lingga', '2019-12-02 23:04:28'),
(12, 'Lingga', '2019-12-02 23:06:14'),
(13, 'Qoheng', '2019-12-02 23:08:58'),
(14, 'Qoheng', '2019-12-02 23:10:28'),
(15, 'Lingga', '2019-12-02 23:12:25'),
(16, 'Lingga', '2019-12-03 09:35:11'),
(17, 'Qoheng', '2019-12-03 09:36:04'),
(18, 'Barokah', '2019-12-03 09:37:00'),
(19, 'Barokah', '2019-12-03 09:40:04'),
(20, 'Lingga', '2019-12-03 10:32:04'),
(21, 'Qoheng', '2019-12-03 10:51:04'),
(22, 'Barokah', '2019-12-03 10:55:11'),
(23, 'qoheng', '2019-12-03 10:58:12'),
(24, 'Qoheng', '2019-12-03 11:09:37'),
(25, 'Lingga', '2019-12-03 11:18:10'),
(26, 'Barokah', '2019-12-03 11:19:31'),
(27, 'Qoheng', '2019-12-03 11:20:24'),
(28, 'Lingga', '2019-12-04 18:37:09'),
(29, 'Qoheng', '2019-12-04 18:41:26'),
(30, 'Barokah', '2019-12-04 18:43:46');

--
-- Triggers `Log_Login`
--
DELIMITER $$
CREATE TRIGGER `waktuLogin` BEFORE INSERT ON `Log_Login` FOR EACH ROW BEGIN SET NEW.waktu_login = sysdate(); END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `suplier`
--

CREATE TABLE `suplier` (
  `id_suplier` varchar(5) NOT NULL,
  `nama_suplier` varchar(20) NOT NULL,
  `alamat_suplier` varchar(40) NOT NULL,
  `no_telp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suplier`
--

INSERT INTO `suplier` (`id_suplier`, `nama_suplier`, `alamat_suplier`, `no_telp`) VALUES
('SP1', 'UD. Semangat Jaya', 'Jl. Jend. S.Parman V No.21a', '08113111105'),
('SP2', 'CV.Makmur Abadi', 'Jl. MT Hariyono No. 12 Kota Malang', '0342 282734');

-- --------------------------------------------------------

--
-- Table structure for table `toko_langganan`
--

CREATE TABLE `toko_langganan` (
  `id_toko` varchar(5) NOT NULL,
  `nama_toko` varchar(20) NOT NULL,
  `password_toko` varchar(12) NOT NULL,
  `alamat_toko` varchar(40) NOT NULL,
  `no_telp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `toko_langganan`
--

INSERT INTO `toko_langganan` (`id_toko`, `nama_toko`, `password_toko`, `alamat_toko`, `no_telp`) VALUES
('TK1', 'Barokah', 'pass789', 'Jl. Joyo Raharjo Gg. IV No.214', '085217453887');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_belanja`
--

CREATE TABLE `transaksi_belanja` (
  `id_belanja` varchar(5) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_suplier` varchar(5) NOT NULL,
  `id_admin` varchar(5) NOT NULL,
  `jumlah_item(qty)` int(4) NOT NULL,
  `total_harga` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_belanja`
--

INSERT INTO `transaksi_belanja` (`id_belanja`, `tanggal_transaksi`, `id_suplier`, `id_admin`, `jumlah_item(qty)`, `total_harga`) VALUES
('BL1', '2019-12-02', 'SP1', 'ADM1', 1, 1464000),
('BL2', '2019-12-03', 'SP2', 'ADM1', 1, 12000),
('BL3', '2019-12-03', 'SP1', 'ADM1', 1, 12000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `id_transaksi_penjualan` varchar(12) NOT NULL,
  `id_kasir` varchar(5) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `jumlah_item(qty)` int(4) NOT NULL,
  `alamat_pengantaran` varchar(40) DEFAULT NULL,
  `harga_total` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_penjualan`
--

INSERT INTO `transaksi_penjualan` (`id_transaksi_penjualan`, `id_kasir`, `tanggal_transaksi`, `jumlah_item(qty)`, `alamat_pengantaran`, `harga_total`) VALUES
('JL1', 'KSR1', '2019-12-02', 2, 'Sawojajar, Malang', 153000),
('JL2', 'KSR1', '2019-12-02', 1, '', 33000),
('JL3', 'KSR1', '2019-12-03', 2, 'Jl. MT Hariyono No. 15', 428000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_suplai`
--

CREATE TABLE `transaksi_suplai` (
  `id_transaksi_suplai` varchar(12) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_kasir` varchar(5) DEFAULT NULL,
  `id_toko` varchar(5) NOT NULL,
  `jumlah_item(qty)` int(4) NOT NULL,
  `harga_total` int(9) NOT NULL,
  `status_pengantaran` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_suplai`
--

INSERT INTO `transaksi_suplai` (`id_transaksi_suplai`, `tanggal_transaksi`, `id_kasir`, `id_toko`, `jumlah_item(qty)`, `harga_total`, `status_pengantaran`) VALUES
('PS1', '2019-12-02', 'KSR1', 'TK1', 2, 446000, 'SUDAH'),
('PS2', '2019-12-03', 'KSR1', 'TK1', 2, 446000, 'SUDAH'),
('PS3', '2019-12-03', 'KSR1', 'TK1', 2, 3420000, 'SUDAH'),
('PS4', '2019-12-03', NULL, 'TK1', 1, 1200000, 'BELUM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`) USING BTREE;

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD UNIQUE KEY `NamaBarang` (`nama_barang`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail_transaksi`),
  ADD KEY `ConsBarangDtl` (`id_barang`),
  ADD KEY `ConsSuplaiDtl` (`id_transaksi`);

--
-- Indexes for table `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indexes for table `Log_Login`
--
ALTER TABLE `Log_Login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`id_suplier`);

--
-- Indexes for table `toko_langganan`
--
ALTER TABLE `toko_langganan`
  ADD PRIMARY KEY (`id_toko`);

--
-- Indexes for table `transaksi_belanja`
--
ALTER TABLE `transaksi_belanja`
  ADD PRIMARY KEY (`id_belanja`),
  ADD KEY `ConsSuplier` (`id_suplier`),
  ADD KEY `ConsAdminBlj` (`id_admin`);

--
-- Indexes for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`id_transaksi_penjualan`),
  ADD KEY `ConsKasir` (`id_kasir`);

--
-- Indexes for table `transaksi_suplai`
--
ALTER TABLE `transaksi_suplai`
  ADD PRIMARY KEY (`id_transaksi_suplai`),
  ADD KEY `ConsTokoLgn` (`id_toko`),
  ADD KEY `ConsKasirSupl` (`id_kasir`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `Log_Login`
--
ALTER TABLE `Log_Login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `ConsBarangDtl` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi_belanja`
--
ALTER TABLE `transaksi_belanja`
  ADD CONSTRAINT `ConsAdminBlj` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`),
  ADD CONSTRAINT `ConsSuplier` FOREIGN KEY (`id_suplier`) REFERENCES `suplier` (`id_suplier`);

--
-- Constraints for table `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `ConsKasir` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`);

--
-- Constraints for table `transaksi_suplai`
--
ALTER TABLE `transaksi_suplai`
  ADD CONSTRAINT `ConsKasirSupl` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`),
  ADD CONSTRAINT `ConsTokoLgn` FOREIGN KEY (`id_toko`) REFERENCES `toko_langganan` (`id_toko`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
