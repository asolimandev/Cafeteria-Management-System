-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2019 at 05:29 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kandilcafe`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryId` int(16) NOT NULL,
  `categoryName` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryId`, `categoryName`) VALUES
(1, 'Appetisers '),
(2, 'Hot Drinks'),
(3, 'Cold Drinks'),
(4, 'Salads'),
(5, 'Sandwiches');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackId` int(16) NOT NULL,
  `userId` int(16) NOT NULL,
  `feedback` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedbackId`, `userId`, `feedback`) VALUES
(1, 6, 'hello');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `itemId` int(16) NOT NULL,
  `categoryId` int(16) NOT NULL,
  `ItemName` varchar(64) NOT NULL,
  `itemPrice` int(16) NOT NULL,
  `itemProfit` int(16) NOT NULL,
  `itemQuantity` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`itemId`, `categoryId`, `ItemName`, `itemPrice`, `itemProfit`, `itemQuantity`) VALUES
(1, 1, 'Fried Eggs', 12, 2, 120),
(2, 3, 'Coca Cola', 5, 1, 60),
(3, 2, 'Coffee', 17, 3, 30);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(16) NOT NULL,
  `userId` int(16) NOT NULL,
  `itemId` int(16) NOT NULL,
  `quantity` int(16) NOT NULL,
  `payment` int(1) NOT NULL,
  `isConfirmed` int(1) NOT NULL,
  `date` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `userId`, `itemId`, `quantity`, `payment`, `isConfirmed`, `date`) VALUES
(1, 6, 3, 30, 0, 0, '2019-05-12 04:35:07.296589'),
(2, 6, 1, 120, 0, 0, '2019-05-12 04:35:07.302573'),
(3, 6, 2, 60, 0, 0, '2019-05-12 04:35:07.309554'),
(4, 6, 3, 30, 0, 0, '2019-05-12 05:25:27.071990'),
(5, 6, 2, 60, 0, 0, '2019-05-12 11:15:27.190410'),
(6, 6, 3, 30, 0, 0, '2019-05-13 10:46:06.944313'),
(7, 6, 2, 60, 0, 0, '2019-05-13 10:46:06.969113'),
(8, 6, 1, 120, 0, 0, '2019-05-13 10:46:06.974572');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(16) NOT NULL,
  `userName` varchar(64) NOT NULL,
  `userPass` varchar(64) NOT NULL,
  `authority` int(16) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userName`, `userPass`, `authority`) VALUES
(6, 'kan', '123', 0),
(7, 'ttt', '123', 0),
(8, 'yassenSeller', '123', 1),
(9, 'kandil', '123', 2),
(10, 'shierf', '123', 2),
(11, 'yousefSeller', '123', 1),
(12, 'abdoSeller', '123', 1),
(13, 'ali', '123', 2),
(14, 'ahmedManager', '123', 2),
(15, 'yassen', '123', 2),
(16, 'eldaly', '123', 0),
(17, 'eldalySeller', '123', 1),
(18, 'eldalyMM', '123', 2),
(20, 'saadfadsfas', 'asa', 0),
(21, 'saadfadsfas1', 'asa', 0),
(23, ' kx', 'gghggg', 0),
(24, 'ggo', 'ooo', 0),
(26, 'oho', 'ooo', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackId`),
  ADD KEY `userId_ForeignKey` (`userId`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`itemId`),
  ADD KEY `catId_ForeignKey` (`categoryId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `userId_FKey` (`userId`),
  ADD KEY `itemId_ForeignKey` (`itemId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `userName` (`userName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryId` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackId` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `itemId` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `userId_ForeignKey` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `catId_ForeignKey` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryId`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `itemId_ForeignKey` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`),
  ADD CONSTRAINT `userId_FKey` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
