-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- 생성 시간: 19-07-22 09:56
-- 서버 버전: 10.4.6-MariaDB-1:10.4.6+maria~bionic
-- PHP 버전: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `temp`
--
CREATE DATABASE IF NOT EXISTS `temp` DEFAULT CHARACTER SET euckr COLLATE euckr_bin;
USE `temp`;

-- --------------------------------------------------------

--
-- 테이블 구조 `member`
--

CREATE TABLE `member` (
  `name` varchar(20) COLLATE euckr_bin NOT NULL,
  `id` varchar(20) COLLATE euckr_bin NOT NULL,
  `pw` varchar(20) COLLATE euckr_bin NOT NULL,
  `phone` varchar(20) COLLATE euckr_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=euckr COLLATE=euckr_bin;

-- --------------------------------------------------------

--
-- 테이블 구조 `mvc_board`
--

CREATE TABLE `mvc_board` (
  `bId` int(4) NOT NULL,
  `bName` varchar(20) COLLATE euckr_bin NOT NULL,
  `bTitle` varchar(100) COLLATE euckr_bin NOT NULL,
  `bContent` varchar(300) COLLATE euckr_bin NOT NULL,
  `bDate` date NOT NULL DEFAULT current_timestamp(),
  `bHit` int(4) NOT NULL DEFAULT 0,
  `bGroup` int(4) NOT NULL,
  `bStep` int(4) NOT NULL,
  `bIndent` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=euckr COLLATE=euckr_bin;

--
-- 테이블의 덤프 데이터 `mvc_board`
--

INSERT INTO `mvc_board` (`bId`, `bName`, `bTitle`, `bContent`, `bDate`, `bHit`, `bGroup`, `bStep`, `bIndent`) VALUES
(1, '123', '123', '123', '2019-07-16', 0, 123, 1231, 123);

-- --------------------------------------------------------

--
-- 테이블 구조 `mvc_board_seq`
--

CREATE TABLE `mvc_board_seq` (
  `next_not_cached_value` bigint(21) NOT NULL,
  `minimum_value` bigint(21) NOT NULL,
  `maximum_value` bigint(21) NOT NULL,
  `start_value` bigint(21) NOT NULL COMMENT 'start value when sequences is created or value if RESTART is used',
  `increment` bigint(21) NOT NULL COMMENT 'increment value',
  `cache_size` bigint(21) UNSIGNED NOT NULL,
  `cycle_option` tinyint(1) UNSIGNED NOT NULL COMMENT '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
  `cycle_count` bigint(21) NOT NULL COMMENT 'How many cycles have been done'
) ENGINE=InnoDB;

--
-- 테이블의 덤프 데이터 `mvc_board_seq`
--

INSERT INTO `mvc_board_seq` (`next_not_cached_value`, `minimum_value`, `maximum_value`, `start_value`, `increment`, `cache_size`, `cycle_option`, `cycle_count`) VALUES
(1, 1, 9223372036854775806, 1, 1, 1000, 0, 0);

-- --------------------------------------------------------

--
-- 테이블 구조 `tblRegister`
--

CREATE TABLE `tblRegister` (
  `id` varchar(200) COLLATE euckr_bin NOT NULL,
  `pwd` varchar(200) COLLATE euckr_bin NOT NULL,
  `name` varchar(200) COLLATE euckr_bin NOT NULL,
  `num1` varchar(200) COLLATE euckr_bin NOT NULL,
  `num2` varchar(200) COLLATE euckr_bin NOT NULL,
  `email` varchar(200) COLLATE euckr_bin NOT NULL,
  `phone` varchar(200) COLLATE euckr_bin NOT NULL,
  `zipcode` varchar(200) COLLATE euckr_bin NOT NULL,
  `address` varchar(200) COLLATE euckr_bin NOT NULL,
  `job` varchar(200) COLLATE euckr_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=euckr COLLATE=euckr_bin;

--
-- 테이블의 덤프 데이터 `tblRegister`
--

INSERT INTO `tblRegister` (`id`, `pwd`, `name`, `num1`, `num2`, `email`, `phone`, `zipcode`, `address`, `job`) VALUES
('123', '123', 'park', '123', '123', 'ccc@ccc.ccc', '01000000000', '12345', '경남', '학생');

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `mvc_board`
--
ALTER TABLE `mvc_board`
  ADD PRIMARY KEY (`bId`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `mvc_board`
--
ALTER TABLE `mvc_board`
  MODIFY `bId` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
