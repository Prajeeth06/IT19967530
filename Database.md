-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2022 at 01:14 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employeedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empID` int(50) NOT NULL,
  `empName` varchar(50) NOT NULL,
  `empSalary` double(10,2) NOT NULL,
  `empGender` varchar(10) NOT NULL,
  `empContact` varchar(10) NOT NULL,
  `empDep` varchar(50) NOT NULL,
  `empJoinDate` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empID`, `empName`, `empSalary`, `empGender`, `empContact`, `empDep`, `empJoinDate`) VALUES
(10, 'Faisan MAF', 50000.00, 'male', '0768775432', 'User Service', '2019-10-04'),
(11, 'Ajanthan T', 75000.00, 'male', '0722079547', 'Power Usage Service', '2019-02-05'),
(12, 'Thahseen MHA', 80000.00, 'male', '0755080643', 'Payment Service', '2019-01-11'),
(13, 'Madushan MAC', 66000.00, 'male', '0778534390', 'Power Interrupt Service', '2019-06-17'),
(14, 'Iruthayaraj SJ', 43000.00, 'male', '0780512483', 'Complaint Service', '2019-10-17'),
(16, 'Malavika', 45000.00, 'female', '0763560980', 'Customer Service', '2020-02-05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `empID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
