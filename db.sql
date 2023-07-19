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

SELECT * FROM movieArticle;

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '관리자';

SELECT * FROM `member`;

/*
CREATE TABLE articleReply (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`body` CHAR(100) NOT NULL,
	memberId INT(1) UNSIGNED NOT NULL,
	articleId INT(1) UNSIGNED NOT NULL,
	INDEX articleId (`articleId`)
);

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글1',
memberId = '1',
articleId = '관리자';

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글2',
memberId = '2',
articleId = 'uesr1';

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글3',
memberId = '3',
articleId = 'user2';

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '관리자';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '유저1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '유저2';

CREATE TABLE board (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`code` CHAR(100) NOT NULL UNIQUE, # free/notice
	`name` CHAR(100) NOT NULL # 자유/공지사항
);

select * from article, articleReply, `member`;
/*
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
`code` = 'user2',
`name` = '유저2';
*/