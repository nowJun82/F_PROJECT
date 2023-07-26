# 데이터베이스 삭제/생성/선택
DROP DATABASE IF EXISTS my_first_project;
CREATE DATABASE my_first_project;

USE my_first_project;

DROP TABLE IF EXISTS movieArticle;

CREATE TABLE movieArticle (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	memberId INT(1) UNSIGNED NOT NULL,
	boardId INT(1) UNSIGNED NOT NULL,
	INDEX boardId (`boardId`)
);

SELECT * FROM movieArticle;

INSERT INTO movieArticle
SET regDate = '2023-05-31',
title = '범죄도시3',
memberId = 1,
boardId = 1;

INSERT INTO movieArticle
SET regDate = '2023-06-14',
title = '엘리멘탈',
memberId = 2,
boardId = 2;

INSERT INTO movieArticle
SET regDate = '2023-06-28',
title = '여름날 우리',
memberId = 3,
boardId = 3;

INSERT INTO movieArticle
SET regDate = '2023-07-12',
title = '미션 임파서블 : 데드 레코닝',
memberId = 4,
boardId = 4;

INSERT INTO movieArticle
SET regDate = '2023-07-19',
title = '인시디어스 : 빨간 문',
memberId = 5,
boardId = 5;

INSERT INTO movieArticle
SET regDate = '2023-07-20',
title = '명탐정 코난 : 흑철의 어영',
memberId = 6,
boardId = 6;

select * from movieArticle;

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(100) NOT NULL,
	Email CHAR(100) NOT NULL UNIQUE,
	nickName CHAR(100) NOT NULL UNIQUE,
	`name` CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
Email = 'admin',
nickName = 'admin',
`name` = '관리자';

select * from `member`;

create table review (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL,
	boardId INT(1) UNSIGNED NOT NULL,
	`name` CHAR(100) NOT NULL,
	grades float(10, 1) NOT NULL
);

insert into review
set regDate = now(),
updateDate = now(),
title = '범죄도시3',
`body` = '오늘 범죄도시3 보고 왔는데, 너무 재밌었어요',
boardId = 1,
`name` = '홍길동',
grades = 4.8;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '엘리멘탈',
`body` = '엘리멘탈 진짜 최고ㅠㅠㅠㅠ',
boardId = 2,
`name` = '홍길동',
grades = 5.0;

SELECT * FROM review;

delete * from review;

drop table if exists seats;

CREATE TABLE seats (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	seatEnabled BOOL NOT NULL,
	movieArticle CHAR(100) NOT NULL,
	seatNum CHAR(100) NOT NULL UNIQUE,
	nickName CHAR(100) NOT NULL
);

select * from seats;