/*
 *Create Board Database
 */

CREATE DATABASE boardProject character set 'utf8' default collate utf8_bin;
USE boardProject;
SET NAMES utf8;

CREATE TABLE `member` (
                          `memberId` int auto_increment COMMENT '아이디',
                          `memberName` varchar(50) NOT NULL COMMENT '닉네임',
                          `memberEmail` varchar(50) NOT NULL COMMENT '이메일',
                          `memberPassword` char(100) NOT NULL COMMENT '패스워드',
                          `memberStatus` varchar(5) NOT NULL COMMENT '상태',
                          PRIMARY KEY (`memberId`)
    -- UNIQUE KEY (`memberName`)
);

CREATE TABLE `board` (
                        `postId` int(11) NOT NULL auto_increment COMMENT '아이디',
                        `memberId` varchar(50) COMMENT '작성자 아이디',
                        `memberName` varchar(50) NOT NULL COMMENT '닉네임',
                        `postTitle` varchar(50) NOT NULL COMMENT '게시글 제목',
                        `postContent` MEDIUMBLOB COMMENT '게시글 내용',
                        PRIMARY KEY (`postId`)
);

CREATE TABLE `comment` (
                         `commentId` int(11) NOT NULL auto_increment COMMENT '아이디',
                         `memberName` varchar(50) NOT NULL COMMENT '닉네임',
                         `postId` varchar(50) NOT NULL COMMENT '게시글 아이디',
                         `commentContent` MEDIUMBLOB COMMENT '댓글 내용',
                         PRIMARY KEY (`commentId`)
);

CREATE TABLE `authCookie` (
                           `cookie` varchar(100) NOT NULL COMMENT '쿠키',
                           `memberId` int(11) NOT NULL COMMENT '닉네임',
                           `expires` date NOT NULL COMMENT '만료시간'
);
