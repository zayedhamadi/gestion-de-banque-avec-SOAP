-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2023 at 03:48 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banque`
--

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE `compte` (
  `codeCin` int(11) NOT NULL,
  `solde` float NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`codeCin`, `solde`, `pass`) VALUES
(1, 99999400, 'chedi'),
(2, 550, 'chedi'),
(3, 0, 'ala');

-- --------------------------------------------------------

--
-- Table structure for table `historique`
--

CREATE TABLE `historique` (
  `codecin` int(11) NOT NULL,
  `idActe` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `historique`
--

INSERT INTO `historique` (`codecin`, `idActe`, `date`) VALUES
(1, 1, '2023-10-27 10:24:59'),
(1, 1, '2023-10-27 10:34:22'),
(1, 1, '2023-10-27 10:44:33'),
(1, 1, '2023-10-29 20:04:19'),
(1, 1, '2023-10-31 12:23:10'),
(1, 1, '2023-11-20 11:48:27'),
(1, 2, '2023-10-27 10:25:12'),
(1, 2, '2023-10-27 10:36:10'),
(1, 2, '2023-11-20 11:48:51'),
(1, 3, '2023-10-27 10:31:16'),
(1, 3, '2023-10-27 10:38:44'),
(1, 3, '2023-10-27 10:40:30'),
(1, 3, '2023-10-27 10:44:48'),
(1, 3, '2023-10-27 10:45:24'),
(1, 3, '2023-10-27 10:45:59'),
(1, 3, '2023-11-20 11:49:05');

-- --------------------------------------------------------

--
-- Table structure for table `lesactions`
--

CREATE TABLE `lesactions` (
  `id` int(11) NOT NULL,
  `action` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lesactions`
--

INSERT INTO `lesactions` (`id`, `action`) VALUES
(1, 'versement'),
(2, 'retrait'),
(3, 'transfert');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`codeCin`);

--
-- Indexes for table `historique`
--
ALTER TABLE `historique`
  ADD PRIMARY KEY (`codecin`,`date`),
  ADD KEY `od` (`idActe`);

--
-- Indexes for table `lesactions`
--
ALTER TABLE `lesactions`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `historique`
--
ALTER TABLE `historique`
  ADD CONSTRAINT `code` FOREIGN KEY (`codecin`) REFERENCES `compte` (`codeCin`),
  ADD CONSTRAINT `od` FOREIGN KEY (`idActe`) REFERENCES `lesactions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
