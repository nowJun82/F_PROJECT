# 데이터베이스 삭제/생성/선택
DROP DATABASE IF EXISTS my_first_project;
CREATE DATABASE my_first_project;

USE my_first_project;

DROP TABLE IF EXISTS movieArticle;

CREATE TABLE movieArticle (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL,
	price INT UNSIGNED NOT NULL
);

SELECT * FROM movieArticle;

INSERT INTO movieArticle
SET regDate = '2023-05-31',
updateDate = NOW(),
title = '범죄도시3',
`body` = '범죄 잡는 마동석',
price = 15000;

INSERT INTO movieArticle
SET regDate = '2023-06-14',
updateDate = NOW(),
title = '엘리멘탈',
`body` = '4원소들의 열정 넘치는 스토리',
price = 15000;

INSERT INTO movieArticle
SET regDate = '2023-06-28',
updateDate = NOW(),
title = '여름날 우리',
`body` = '너에게 풍덩 빠져버렸던 17살 여름',
price = 15000;

INSERT INTO movieArticle
SET regDate = '2023-07-12',
updateDate = NOW(),
title = '미션 임파서블 : 데드 레코닝',
`body` = '가장 위험한 작전!',
price = 15000;

INSERT INTO movieArticle
SET regDate = '2023-07-19',
updateDate = NOW(),
title = '인시디어스 : 빨간 문',
`body` = '램버트 가족이 다시 겪게 되는 끔찍한 악몽 이야기',
price = 15000;

INSERT INTO movieArticle
SET regDate = '2023-07-20',
updateDate = NOW(),
title = '명탐정 코난 : 흑철의 어영',
`body` = '믿고 보는 코난',
price = 15000;

SELECT * FROM movieArticle;

DROP TABLE IF EXISTS `member`

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	Email CHAR(100) NOT NULL UNIQUE,
	nickName CHAR(100) NOT NULL UNIQUE,
	loginPw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
Email = 'admin',
nickName = '관리자',
`name` = '관리자';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'phj',
loginPw = 'phj',
Email = 'phj',
nickName = '박현재',
`name` = '박현재';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'sdw',
loginPw = 'sdw',
Email = 'sdw',
nickName = '신동우',
`name` = '신동우';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'syj',
loginPw = 'syj',
Email = 'syj',
nickName = '심유정',
`name` = '심유정';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'ljj',
loginPw = 'ljj',
Email = 'ljj',
nickName = '이재준',
`name` = '이재준';

SELECT * FROM `member`;

/* 업데이트 문 사용 예시
update `member`
set loginPw = 'admin123'
where id = 1;
*/

DROP TABLE IF EXISTS review;

CREATE TABLE review (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL,
	grades FLOAT(10, 1) NOT NULL
);

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '범죄도시3',
`body` = '마동석 배우의 파격적인 액션신은 역시 기대 이상이었습니다.',
`name` = '이재준',
grades = 4.5;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '엘리멘탈',
`body` = '여자친구랑 같이 보고 왔는데, 둘 다 만족했습니다.',
`name` = '이재준',
grades = 5.0;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '범죄도시3',
`body` = '개인적으로 노잼',
`name` = '신동우',
grades = 0.5;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '여름날 우리',
`body` = '전 겨울이 더 좋아요.',
`name` = '심유정',
grades = 3.7;

INSERT INTO review
SET regDate = NOW(),
updateDate = NOW(),
title = '명탐정 코난 : 흑철의 어영',
`body` = '재밌어요.',
`name` = '박현재',
grades = 4.1;

SELECT * FROM review;

-- 필요한 경우에 사용
-- DROP TABLE IF EXISTS defaultSeats;

CREATE TABLE movieSeats (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	seat CHAR(100),
	movieTitle CHAR(100),
	nickName CHAR(100) NOT NULL,
	enabledSeat BOOL
);

SELECT * FROM movieSeats;

DROP TABLE IF EXISTS `movieSeats`;

SELECT * FROM movieSeats WHERE movieTitle = '엘리멘탈' AND enabledSeat = 1;