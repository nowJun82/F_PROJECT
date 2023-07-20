# 데이터베이스 삭제/생성/선택
DROP DATABASE IF EXISTS my_first_project;
CREATE DATABASE my_first_project;
USE my_first_project;

CREATE TABLE movieArticle (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL,
	memberId INT(1) UNSIGNED NOT NULL,
	boardId INT(1) UNSIGNED NOT NULL,
	INDEX boardId (`boardId`)
);

SELECT * FROM movieArticle;

INSERT INTO movieArticle
SET regDate = NOW(),
updateDate = NOW(),
title = '미션 임파서블 : 데드 레코닝',
`body` = '',
memberId = 1,
boardId = 1;

INSERT INTO movieArticle
SET regDate = NOW(),
updateDate = NOW(),
title = '엘리멘탈',
`body` = '',
memberId = 2,
boardId = 2;

INSERT INTO movieArticle
SET regDate = NOW(),
updateDate = NOW(),
title = '여름날 우리',
`body` = '',
memberId = 3,
boardId = 3;

INSERT INTO movieArticle
SET regDate = NOW(),
updateDate = NOW(),
title = '명탐정 코난 : 흑철의 어영',
`body` = '',
memberId = 4,
boardId = 4;

INSERT INTO movieArticle
SET regDate = NOW(),
updateDate = NOW(),
title = '인시디어스 : 빨간 문',
`body` = '',
memberId = 5,
boardId = 5;

INSERT INTO movieArticle
SET regDate = NOW(),
updateDate = NOW(),
title = '범죄도시3',
`body` = '',
memberId = 6,
boardId = 6;

SELECT * FROM movieArticle;

/*
DROP DATABASE IF EXISTS `member`;
CREATE DATABASE `member`;
USE `member`;
*/

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(100) NOT NULL,
	nickName CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
nickName = '운영자',
`name` = '관리자';

SELECT * FROM `member`;