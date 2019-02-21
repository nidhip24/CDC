-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2019 at 08:42 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

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
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `docid` varchar(20) NOT NULL,
  `s1` varchar(20) NOT NULL,
  `s2` varchar(20) NOT NULL,
  `s3` varchar(20) NOT NULL,
  `s4` varchar(20) NOT NULL,
  `s5` varchar(20) NOT NULL,
  `ssc` varchar(20) NOT NULL,
  `hsc` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`docid`, `s1`, `s2`, `s3`, `s4`, `s5`, `ssc`, `hsc`) VALUES
('111', 'asd', 'asd', 'dd', 'dds', '', 'sds', 'dsd'),
('112', 'sd', 'asd', 'asdd', 'sdsd', '', 'ass', 'ds'),
('1905AE4203', '1905AE4203sem1.pdf', '1905AE4203sem2.pdf', '1905AE4203sem3.pdf', '1905AE4203sem4.pdf', '1905AE4203sem5.pdf', '1905AE4203ssc.pdf', '1905AE4203hsc.pdf'),
('4BEB29D790', '4BEB29D790sem1.pdf', '', '', '', '', '4BEB29D790ssc.pdf', '4BEB29D790hsc.pdf'),
('B13F8D57F3', '', '', '', '', '', 'B13F8D57F3ssc.pdf', ''),
('CFF0618B23', '', '', '', '', '', 'CFF0618B23ssc.pdf', ''),
('DC566F29DB', 'DC566F29DBsem1.pdf', '', '', '', '', 'DC566F29DBssc.pdf', 'DC566F29DBhsc.pdf'),
('EC42770E9A', '', '', '', '', '', 'EC42770E9Assc.pdf', ''),
('EF9E2B12B9', 'EF9E2B12B9sem1.pdf', 'EF9E2B12B9sem2.pdf', 'EF9E2B12B9sem3.pdf', 'EF9E2B12B9sem4.pdf', 'EF9E2B12B9sem5.pdf', 'EF9E2B12B9ssc.pdf', 'EF9E2B12B9hsc.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `internship`
--

DROP TABLE IF EXISTS `internship`;
CREATE TABLE `internship` (
  `id` int(11) NOT NULL,
  `cname` varchar(30) NOT NULL,
  `info` text NOT NULL,
  `link` varchar(200) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'open',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `internship`
--

INSERT INTO `internship` (`id`, `cname`, `info`, `link`, `status`, `date`) VALUES
(1, 'ssss', 'ssssssssssss', 'sssssss', 'open', '2019-02-21 05:21:11'),
(2, 'demo', 'dkasdka', 'lsdlakda', 'close', '2019-02-21 15:08:03');

-- --------------------------------------------------------

--
-- Table structure for table `mycdc`
--

DROP TABLE IF EXISTS `mycdc`;
CREATE TABLE `mycdc` (
  `id` int(11) NOT NULL,
  `docid` varchar(20) NOT NULL,
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
  `sem5` int(10) NOT NULL,
  `uid` varchar(20) NOT NULL,
  `pass` varchar(10) NOT NULL,
  `cnfpass` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mycdc`
--

INSERT INTO `mycdc` (`id`, `docid`, `fname`, `rollno`, `grno`, `phno`, `eid`, `clas`, `ssc`, `hsc`, `sem1`, `sem2`, `sem3`, `sem4`, `sem5`, `uid`, `pass`, `cnfpass`) VALUES
(8, '111', '', 0, 0, 0, '', '', 0, 0, 0, 0, 0, 0, 0, 'admin', 'admin', ''),
(9, '112', 'nk', 9898, 8989, 2147483647, 'xbbd@didj.com', 'fj', 59, 29, 89, 68, 98, 89, 0, 'nk', 'nk', 'nk'),
(16, 'CFF0618B23', 'ayc', 1234, 124597, 1122334455, 'hchsh@hs.zz', 'te', 1, 7, 8, 8, 8, 8, 0, 'abc', 'abc', 'abc'),
(17, 'EC42770E9A', 'hfhd', 6564, 846, 2147483647, 'chsh@dha.jd', 'hf', 85, 8, 88, 8, 8, 8, 0, 'aaz', 'aaz', 'aaz'),
(18, 'DC566F29DB', 'coco', 1234, 12345, 1234567890, 'hchs@ucs.c', 'gu', 69, 99, 99, -1, -1, -1, -1, 'Knidhip_24', 'Knidhip_24', 'Knidhip_24'),
(19, '4BEB29D790', 'lambu', 6656, 65656, 2147483647, 'nidhipkathiriya@gmail.com', 'BE6', 80, 89, 89, -1, -1, -1, -1, 'Nnidhip_24', 'Nnidhip_24', 'Nnidhip_24');

--
-- Triggers `mycdc`
--
DROP TRIGGER IF EXISTS `add_user_privilege`;
DELIMITER $$
CREATE TRIGGER `add_user_privilege` BEFORE INSERT ON `mycdc` FOR EACH ROW INSERT INTO user_privilege VALUES(new.uid,"user")
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `offerletter`
--

DROP TABLE IF EXISTS `offerletter`;
CREATE TABLE `offerletter` (
  `docid` varchar(20) NOT NULL,
  `lname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `offerletter`
--

INSERT INTO `offerletter` (`docid`, `lname`) VALUES
('23333EC55B', '23333EC55Bofferletter.pdf'),
('6DEA486E83', '6DEA486E83offerletter.pdf'),
('85BF18C727', '85BF18C727offerletter.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `placementdata`
--

DROP TABLE IF EXISTS `placementdata`;
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
(1, 'LNT', 'Greetings to all, \r\n\r\nCareer Development Cell is here with big opportunity for our Current TY IT CS students.  \r\n\r\nHello friends. Pool campus by L & T Infotech is arranged on 19 and 20 July 2018 at Thanks College for current TYBSC cs and it students. It is only for colleges between Andheri and Bhayandar. We shall not entertain students coming from outside of this range. Please ask your eligible students to enroll at following link by tomorrow before 7 pm. \r\n\r\nStudents are requested to not to pass the link to NON CDC STUDENTS. \r\n\r\nAlso please both the Google forms before tomorrow 7 pm.\r\n\r\n\r\nSSC and HSC 50% \r\n\r\nSem 1-4 60%\r\n\r\n\r\nThank you \r\n\r\n- Mr Ashish Modi.', 'https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application', 'close', '2018-09-25 16:16:13'),
(8, 'dffffdas', 'asdadasd\r\nsasd\r\nasd\r\nass\r\ndas\r\nda\r\ndas\r\ndas', 'https://www.w3schools.com/html/tryit.asp?filename=tryhtml_elem_textarea', 'close', '2018-09-30 10:18:37'),
(10, 'kwhfwlhfbkwf', 'aojfwpkfwfev\nsf9snfosmclwc\nec\nepcmsmfwc', 'https://goo.gl/forms/mdq6KxVlOoBeQEZA3', 'close', '2018-09-30 10:22:30'),
(11, 'nihip', 'chhand\npehchan\nc\nvmsnf\nd\n\n\n\nchdhvjsjjjâ‚¹^&â‚¹&&&â‚¹+\ncjdjjcms\ncsudj', 'http://google.com', 'open', '2019-01-11 14:09:07'),
(12, 'NK', 'Greetings to all, \r\n\r\nCareer Development Cell is here with big opportunity for our Current TY IT CS students.  \r\n\r\nHello friends. Pool campus by L & T Infotech is arranged on 19 and 20 July 2018 at Thanks College for current TYBSC cs and it students. It is only for colleges between Andheri and Bhayandar. We shall not entertain students coming from outside of this range. Please ask your eligible students to enroll at following link by tomorrow before 7 pm. \r\n\r\nStudents are requested to not to pass the link to NON CDC STUDENTS. \r\n\r\nAlso please both the Google forms before tomorrow 7 pm.\r\n\r\n\r\nSSC and HSC 50% \r\n\r\nSem 1-4 60%\r\n\r\n\r\nThank you \r\n\r\n- Mr Ashish Modi.', 'gzhzhzhz', 'open', '2019-02-19 16:17:54'),
(13, 'bababa', 'sasa\nsdadad\na\n\ns\nas\nacscascasc\n\ncdd d das asdas\nda\ndas\ndad\na d a\nd a\n da da\n\n\n\na d', 'aada', 'close', '2019-02-21 05:15:40'),
(14, 'as', 'as', 'sdad', 'close', '2019-02-21 05:20:02');

-- --------------------------------------------------------

--
-- Table structure for table `updateinternship`
--

DROP TABLE IF EXISTS `updateinternship`;
CREATE TABLE `updateinternship` (
  `id` int(11) NOT NULL,
  `iid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `letter` varchar(20) NOT NULL,
  `feedback` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `updateinternship`
--

INSERT INTO `updateinternship` (`id`, `iid`, `uid`, `letter`, `feedback`) VALUES
(1, 1, 16, '6DEA486E83offerlette', 'hxh');

-- --------------------------------------------------------

--
-- Table structure for table `updateplacement`
--

DROP TABLE IF EXISTS `updateplacement`;
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
(9, 8, 16, 'fucking CEO', 999999999);

-- --------------------------------------------------------

--
-- Table structure for table `user_privilege`
--

DROP TABLE IF EXISTS `user_privilege`;
CREATE TABLE `user_privilege` (
  `username` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_privilege`
--

INSERT INTO `user_privilege` (`username`, `type`) VALUES
('aaa', 'user'),
('admin', 'admin'),
('nk', 'admin'),
('nkk', 'user'),
('12edre', 'user'),
('abc', 'user'),
('abc', 'user'),
('aaz', 'user'),
('Knidhip_24', 'user'),
('Nnidhip_24', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`docid`);

--
-- Indexes for table `internship`
--
ALTER TABLE `internship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mycdc`
--
ALTER TABLE `mycdc`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uid` (`uid`),
  ADD KEY `docid` (`docid`);

--
-- Indexes for table `offerletter`
--
ALTER TABLE `offerletter`
  ADD PRIMARY KEY (`docid`);

--
-- Indexes for table `placementdata`
--
ALTER TABLE `placementdata`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `updateinternship`
--
ALTER TABLE `updateinternship`
  ADD PRIMARY KEY (`id`),
  ADD KEY `updateinternship_ibfk_1` (`uid`),
  ADD KEY `updateinternship_ibfk_2` (`iid`);

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
-- AUTO_INCREMENT for table `internship`
--
ALTER TABLE `internship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mycdc`
--
ALTER TABLE `mycdc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `placementdata`
--
ALTER TABLE `placementdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `updateinternship`
--
ALTER TABLE `updateinternship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `updateplacement`
--
ALTER TABLE `updateplacement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mycdc`
--
ALTER TABLE `mycdc`
  ADD CONSTRAINT `mycdc_ibfk_1` FOREIGN KEY (`docid`) REFERENCES `document` (`docid`);

--
-- Constraints for table `updateinternship`
--
ALTER TABLE `updateinternship`
  ADD CONSTRAINT `updateinternship_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `mycdc` (`id`),
  ADD CONSTRAINT `updateinternship_ibfk_2` FOREIGN KEY (`iid`) REFERENCES `internship` (`id`);

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
