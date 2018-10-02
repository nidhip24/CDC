-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 01, 2018 at 09:45 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mycdc`
--

-- --------------------------------------------------------

--
-- Table structure for table `mycdc`
--

CREATE TABLE `mycdc` (
  `id` int(11) NOT NULL,
  `fname` varchar(40) NOT NULL,
  `rollno` int(10) NOT NULL,
  `grno` int(10) NOT NULL,
  `phno` int(10) NOT NULL,
  `eid` varchar(30) NOT NULL,
  `clas` varchar(10) NOT NULL,
  `ssc` int(10) NOT NULL,
  `hsc` int(10) NOT NULL,
  `sem1` int(10) NOT NULL,
  `sem2` int(10) NOT NULL,
  `sem3` int(10) NOT NULL,
  `sem4` int(10) NOT NULL,
  `uid` varchar(10) NOT NULL,
  `pass` varchar(10) NOT NULL,
  `cnfpass` varchar(10) NOT NULL,
  `memno` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mycdc`
--

INSERT INTO `mycdc` (`id`, `fname`, `rollno`, `grno`, `phno`, `eid`, `clas`, `ssc`, `hsc`, `sem1`, `sem2`, `sem3`, `sem4`, `uid`, `pass`, `cnfpass`, `memno`) VALUES
(7, 'sdas', 213, 212, 1213, 'dsadads', 'sdasd', 443, 23423, 3223, 23423, 23324, 234, 'aaa', 'aaa', '4sgdc', 'fdfs'),
(8, '', 0, 0, 0, '', '', 0, 0, 0, 0, 0, 0, 'admin', 'admin', '', ''),
(9, 'nk', 9898, 8989, 2147483647, 'xbbd@didj.com', 'fj', 59, 29, 89, 68, 98, 89, 'nk', 'nk', 'nk', 'hxhsdh');

--
-- Triggers `mycdc`
--
DELIMITER $$
CREATE TRIGGER `add_user_privilege` BEFORE INSERT ON `mycdc` FOR EACH ROW INSERT INTO user_privilege VALUES(new.uid,"user")
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `placementdata`
--

CREATE TABLE `placementdata` (
  `id` int(11) NOT NULL,
  `cname` varchar(30) NOT NULL,
  `info` text NOT NULL,
  `link` varchar(200) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'open',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `placementdata`
--

INSERT INTO `placementdata` (`id`, `cname`, `info`, `link`, `status`, `date`) VALUES
(1, 'LNT', 'Greetings to all, \r\n\r\nCareer Development Cell is here with big opportunity for our Current TY IT CS students.  \r\n\r\nHello friends. Pool campus by L & T Infotech is arranged on 19 and 20 July 2018 at Thanks College for current TYBSC cs and it students. It is only for colleges between Andheri and Bhayandar. We shall not entertain students coming from outside of this range. Please ask your eligible students to enroll at following link by tomorrow before 7 pm. \r\n\r\nStudents are requested to not to pass the link to NON CDC STUDENTS. \r\n\r\nAlso please both the Google forms before tomorrow 7 pm.\r\n\r\n\r\nSSC and HSC 50% \r\n\r\nSem 1-4 60%\r\n\r\n\r\nThank you \r\n\r\n- Mr Ashish Modi.', 'https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application', 'open', '2018-09-25 16:16:13'),
(8, 'dffffdas', 'asdadasd\r\nsasd\r\nasd\r\nass\r\ndas\r\nda\r\ndas\r\ndas', 'https://www.w3schools.com/html/tryit.asp?filename=tryhtml_elem_textarea', 'open', '2018-09-30 10:18:37'),
(10, 'kwhfwlhfbkwf', 'aojfwpkfwfev\nsf9snfosmclwc\nec\nepcmsmfwc', 'https://goo.gl/forms/mdq6KxVlOoBeQEZA3', 'close', '2018-09-30 10:22:30');

-- --------------------------------------------------------

--
-- Table structure for table `updateplacement`
--

CREATE TABLE `updateplacement` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `package` bigint(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `updateplacement`
--

INSERT INTO `updateplacement` (`id`, `pid`, `uid`, `designation`, `package`) VALUES
(8, 10, 9, 'nfne', 89495);

-- --------------------------------------------------------

--
-- Table structure for table `user_privilege`
--

CREATE TABLE `user_privilege` (
  `username` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_privilege`
--

INSERT INTO `user_privilege` (`username`, `type`) VALUES
('aaa', 'user'),
('admin', 'admin'),
('nk', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mycdc`
--
ALTER TABLE `mycdc`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uid` (`uid`);

--
-- Indexes for table `placementdata`
--
ALTER TABLE `placementdata`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `updateplacement`
--
ALTER TABLE `updateplacement`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pid_2` (`pid`),
  ADD KEY `cid` (`uid`),
  ADD KEY `pid` (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mycdc`
--
ALTER TABLE `mycdc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `placementdata`
--
ALTER TABLE `placementdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `updateplacement`
--
ALTER TABLE `updateplacement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `updateplacement`
--
ALTER TABLE `updateplacement`
  ADD CONSTRAINT `updateplacement_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `mycdc` (`id`),
  ADD CONSTRAINT `updateplacement_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `placementdata` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
