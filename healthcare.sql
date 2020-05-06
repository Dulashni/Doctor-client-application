-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 02:56 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `docID` int(11) NOT NULL,
  `docName` varchar(300) NOT NULL,
  `docSpec` varchar(100) NOT NULL,
  `docHosp` varchar(100) NOT NULL,
  `docContact` int(20) NOT NULL,
  `docEmail` varchar(100) NOT NULL,
  `docStat` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`docID`, `docName`, `docSpec`, `docHosp`, `docContact`, `docEmail`, `docStat`) VALUES
(3, 'Samadhi Liyanage', 'Physician', 'Nawaloka Hospitals', 773425673, 'samadhi.lk@gmail.com', 'Available'),
(5, 'Nalin Rajapakshe', 'Surgeon', 'Nawaloka', 779853874, 'nalin2raj@gmail.com', 'Available'),
(7, 'Saman Ramanayake', 'Surgeon', 'Asiri Hospital Col', 779856342, 'ramanayake.s@gmail.com', 'Available'),
(23, 'Dulashni Amanda', 'Child Specialist', 'Karapitiya UPDATED', 715464234, 'dul.amanda@gmail.com', 'Available'),
(24, 'Anil Jasinghe', 'Physician', 'Colombo Hospitals', 714532378, 'anil.jasigh@yahoo.com', 'Available'),
(25, 'Ramya Ragunathan', 'Dermatologist Updated', 'Colombo Hospitals ', 715634564, 'rmya.rag@gmail.com', 'Available'),
(26, 'Ashani Nanayakkara', 'Eye', 'Nawaloka Central', 712343879, 'ashani.nanakk@gmail.com', 'Unavailable');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`docID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `docID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
