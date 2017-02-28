-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 28, 2017 at 10:35 PM
-- Server version: 5.7.17-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tictactoe`
--
CREATE Database tictactoe;
use tictactoe;
-- --------------------------------------------------------

--
-- Table structure for table `Game`
--

CREATE TABLE `Game` (
  `id` smallint(6) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT 'NULL',
  `mode` int(11) NOT NULL,
  `winner_id` smallint(6) NOT NULL DEFAULT '0',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Game`
--

INSERT INTO `Game` (`id`, `name`, `mode`, `winner_id`, `date`) VALUES
(1, 'test1', 0, 0, '2017-02-28 17:48:25'),
(2, 'test2', 1, 0, '2017-02-28 17:49:59'),
(3, 'test3', 0, 0, '2017-02-28 20:13:38'),
(4, 'testgame 4', 0, 0, '2017-02-28 20:16:59');

-- --------------------------------------------------------

--
-- Table structure for table `Game_Player`
--

CREATE TABLE `Game_Player` (
  `game_id` smallint(6) NOT NULL,
  `user_id` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Game_Player`
--

INSERT INTO `Game_Player` (`game_id`, `user_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8);

-- --------------------------------------------------------

--
-- Table structure for table `Step`
--

CREATE TABLE `Step` (
  `id` smallint(6) NOT NULL,
  `user_id` smallint(6) NOT NULL,
  `game_id` smallint(6) NOT NULL,
  `step_number` int(11) NOT NULL DEFAULT '0',
  `position` tinyint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Step`
--

INSERT INTO `Step` (`id`, `user_id`, `game_id`, `step_number`, `position`) VALUES
(1, 2, 1, 0, 0),
(2, 1, 1, 0, 3),
(3, 2, 1, 0, 1),
(4, 1, 1, 0, 6),
(5, 2, 1, 0, 2),
(6, 3, 2, 0, 0),
(7, 4, 2, 0, 3),
(8, 3, 2, 0, 1),
(9, 4, 2, 0, 4),
(10, 3, 2, 0, 2),
(11, 6, 3, 0, 2),
(12, 5, 3, 0, 4),
(13, 6, 3, 0, 5),
(14, 5, 3, 0, 1),
(15, 8, 4, 0, 5),
(16, 7, 4, 0, 4),
(17, 8, 4, 0, 7),
(18, 7, 4, 0, 8);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `id` smallint(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mode` tinyint(1) NOT NULL DEFAULT '0',
  `shape_type` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id`, `name`, `mode`, `shape_type`) VALUES
(1, 'Computer', 0, 'x'),
(2, 'azhary', 0, 'o'),
(3, 'anas', 1, 'x'),
(4, 'eman', 1, 'o'),
(5, 'Computer', 0, 'x'),
(6, 'aaaaaaa', 0, 'o'),
(7, 'Computer', 0, 'x'),
(8, 'test4', 0, 'o');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Game`
--
ALTER TABLE `Game`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Step`
--
ALTER TABLE `Step`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Game`
--
ALTER TABLE `Game`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Step`
--
ALTER TABLE `Step`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
