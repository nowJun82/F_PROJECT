/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - my_first_project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_first_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `my_first_project`;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `loginId` char(100) NOT NULL,
  `Email` char(100) NOT NULL,
  `nickName` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  `grade` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `nickName` (`nickName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`loginId`,`Email`,`nickName`,`loginPw`,`name`,`grade`) values 
(1,'2023-08-02 09:45:16','2023-08-02 09:45:16','admin','admin','관리자','admin','관리자','VIP'),
(2,'2023-08-02 09:45:16','2023-08-02 09:45:16','phj','phj','박현재','phj','박현재','silver'),
(3,'2023-08-02 09:45:16','2023-08-02 09:45:16','sdw','sdw','신동우','sdw','신동우','bronze'),
(4,'2023-08-02 09:45:16','2023-08-02 09:45:16','syj','syj','심유정','syj','심유정','gold'),
(5,'2023-08-02 09:45:16','2023-08-02 09:45:16','ljj','ljj','이재준','ljj','이재준','VIP');

/*Table structure for table `movieArticle` */

DROP TABLE IF EXISTS `movieArticle`;

CREATE TABLE `movieArticle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` char(100) NOT NULL,
  `price` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `movieArticle` */

insert  into `movieArticle`(`id`,`regDate`,`updateDate`,`title`,`body`,`price`) values 
(1,'2023-05-31 00:00:00','2023-08-02 09:35:26','범죄도시3','범죄 잡는 마동석',15000),
(2,'2023-06-14 00:00:00','2023-08-02 09:35:26','엘리멘탈','4원소들의 열정 넘치는 스토리',15000),
(3,'2023-06-28 00:00:00','2023-08-02 09:35:26','여름날 우리','너에게 풍덩 빠져버렸던 17살 여름',15000),
(4,'2023-07-12 00:00:00','2023-08-02 09:35:26','미션 임파서블 : 데드 레코닝','가장 위험한 작전!',15000),
(5,'2023-07-19 00:00:00','2023-08-02 09:35:26','인시디어스 : 빨간 문','램버트 가족이 다시 겪게 되는 끔찍한 악몽 이야기',15000),
(6,'2023-07-20 00:00:00','2023-08-02 09:35:26','명탐정 코난 : 흑철의 어영','믿고 보는 코난',15000),
(7,'2023-07-26 00:00:00','2023-08-02 09:35:26','밀수','열 길 물속은 알아도 한길 사람 속은 모른다!',15000),
(8,'2023-08-02 00:00:00','2023-08-02 09:35:26','더 문','홀로 남은 대원을 살려내기 위한 고군분투',15000);

/*Table structure for table `movieSeats` */

DROP TABLE IF EXISTS `movieSeats`;

CREATE TABLE `movieSeats` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `seat` char(100) DEFAULT NULL,
  `movieTitle` char(100) DEFAULT NULL,
  `nickName` char(100) NOT NULL,
  `price` float(10,2) unsigned NOT NULL,
  `enabledSeat` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `movieSeats` */

insert  into `movieSeats`(`id`,`regDate`,`updateDate`,`seat`,`movieTitle`,`nickName`,`price`,`enabledSeat`) values 
(1,'2023-08-02 09:15:24','2023-08-02 09:15:24','A1','엘리멘탈','심유정',13500.00,1),
(2,'2023-08-02 09:15:24','2023-08-02 09:15:24','A2','엘리멘탈','심유정',13500.00,1),
(3,'2023-08-02 09:15:24','2023-08-02 09:15:24','G6','엘리멘탈','이재준',12000.00,1),
(4,'2023-08-02 09:15:24','2023-08-02 09:15:24','G7','엘리멘탈','이재준',12000.00,1),
(6,'2023-08-02 09:15:24','2023-08-02 09:15:24','H11','엘리멘탈','신동우',15000.00,1),
(7,'2023-08-02 09:15:24','2023-08-02 09:15:24','H12','엘리멘탈','신동우',15000.00,1),
(8,'2023-08-02 09:15:24','2023-08-02 09:15:24','C1','미션 임파서블 : 데드 레코닝','이재준',12000.00,1),
(9,'2023-08-02 09:15:24','2023-08-02 09:15:24','C2','미션 임파서블 : 데드 레코닝','이재준',12000.00,1),
(10,'2023-08-02 09:15:24','2023-08-02 09:15:24','D3','미션 임파서블 : 데드 레코닝','박현재',14250.00,1),
(11,'2023-08-02 09:15:24','2023-08-02 09:15:24','D4','미션 임파서블 : 데드 레코닝','박현재',14250.00,1),
(12,'2023-08-02 09:15:24','2023-08-02 09:15:24','D5','미션 임파서블 : 데드 레코닝','박현재',14250.00,1),
(14,'2023-08-02 10:26:00','2023-08-02 10:26:00','D3','엘리멘탈','이재준',14250.00,1),
(15,'2023-08-02 10:26:00','2023-08-02 10:26:00','D4','엘리멘탈','이재준',14250.00,1),
(16,'2023-08-02 10:26:15','2023-08-02 10:26:15','E1','여름날 우리','이재준',13500.00,1),
(17,'2023-08-02 10:26:15','2023-08-02 10:26:15','E7','여름날 우리','이재준',13500.00,1),
(18,'2023-08-02 10:26:15','2023-08-02 10:26:15','E8','여름날 우리','이재준',13500.00,1),
(19,'2023-08-02 10:27:11','2023-08-02 10:27:11','I5','엘리멘탈','이재준',12000.00,1),
(20,'2023-08-02 10:27:11','2023-08-02 10:27:11','I6','엘리멘탈','이재준',12000.00,1);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  `grades` float(10,1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `review` */

insert  into `review`(`id`,`regDate`,`updateDate`,`title`,`body`,`name`,`grades`) values 
(1,'2023-08-02 09:45:26','2023-08-02 09:45:26','범죄도시3','마동석 배우의 파격적인 액션신은 역시 기대 이상이었습니다.','이재준',4.5),
(2,'2023-08-02 09:45:26','2023-08-02 09:45:26','엘리멘탈','여자친구랑 같이 보고 왔는데, 둘 다 만족했습니다.','이재준',5.0),
(3,'2023-08-02 09:45:26','2023-08-02 09:45:26','범죄도시3','개인적으로 노잼','신동우',0.5),
(4,'2023-08-02 09:45:26','2023-08-02 09:45:26','여름날 우리','전 겨울이 더 좋아요.','심유정',3.7),
(5,'2023-08-02 09:45:26','2023-08-02 09:45:26','명탐정 코난 : 흑철의 어영','재밌어요.','박현재',4.1),
(6,'2023-08-02 10:29:02','2023-08-02 10:29:35','엘리멘탈','재밌었다 !','이재준',4.8);

/*Table structure for table `seatData` */

DROP TABLE IF EXISTS `seatData`;

CREATE TABLE `seatData` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `seatName` char(100) NOT NULL,
  `movieTitle` char(100) NOT NULL,
  `enabledSeat` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `seatData` */

insert  into `seatData`(`id`,`regDate`,`updateDate`,`seatName`,`movieTitle`,`enabledSeat`) values 
(1,'2023-07-31 11:04:48','2023-07-31 11:04:51','A1','',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
