-- CREATE DATABASE test;

-- USE cts;

DROP TABLE IF EXISTS `worker`;

DROP TABLE IF EXISTS `announcer`;
DROP TABLE IF EXISTS `rater`;
DROP TABLE IF EXISTS `accept`;
DROP TABLE IF EXISTS `evaluate`;
DROP TABLE IF EXISTS `missionpicture`;
DROP TABLE IF EXISTS `release0`;
DROP TABLE IF EXISTS `workerpicture`;
DROP TABLE IF EXISTS `evaluatepicture`;
DROP TABLE IF EXISTS `weeklylogin`;

CREATE TABLE `worker` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT ,
  `user_name`               VARCHAR(255) NOT NULL,
  `password`                VARCHAR(255) NOT NULL,
  `points`                  INTEGER NOT NULL DEFAULT 0,
  `sex`                     INT(1),
  `area`                    VARCHAR(255),
  `phone`                   VARCHAR(255),
  `email`                   VARCHAR(255),
  `image`                   longBlob,
  PRIMARY KEY (`id`)
);


CREATE TABLE `announcer` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `user_name`               VARCHAR(255) NOT NULL,
  `password`                VARCHAR(255) NOT NULL,
  `points`                  INTEGER NOT NULL DEFAULT 0,
  `sex`                     INT(1),
  `area`                    VARCHAR(255),
  `phone`                   VARCHAR(255),
  `email`                   VARCHAR(255),
  `image`                   longBlob,
  PRIMARY KEY (`id`)
);


CREATE TABLE `rater` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `user_name`               VARCHAR(255) NOT NULL,
  `password`                VARCHAR(255) NOT NULL,
  `points`                  INTEGER NOT NULL DEFAULT 0,
  `sex`                     INT(1),
  `area`                    VARCHAR(255),
  `phone`                   VARCHAR(255),
  `email`                   VARCHAR(255),
  `image`                   longBlob,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `mission`;
CREATE TABLE `mission` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `mission_name`            VARCHAR(255) NOT NULL,
  `start_time`              VARCHAR(255) NOT NULL,
  `end_time`                VARCHAR(255) NOT NULL,
  `points`                  INTEGER NOT NULL DEFAULT 0,
  `needs`                   INTEGER NOT NULL DEFAULT 0,
  `description`             VARCHAR(255),
  `missiontype`                    VARCHAR(255),
  `way`                     VARCHAR(255),
  `difficultyClass`         VARCHAR(255),
  `accepts`                 int (11) default 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `accept` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `wname`                   VARCHAR(255) NOT NULL,
  `mname`                   VARCHAR(255) NOT NULL,
  `start_time`              VARCHAR(255) NOT NULL,
  `end_time`                VARCHAR(255) NOT NULL,
  `isFinished`              int(1) default 0,
  `checkFlag`               int(1) default 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `evaluate` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `mname`                   VARCHAR(255) NOT NULL,
  `rname`                   VARCHAR(255) NOT NULL,
  `start_time`              VARCHAR(255) NOT NULL,
  `end_time`                VARCHAR(255) NOT NULL,
  `checkFlag`               int(1) default 0,
  `state0`               int(1) default 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `release0` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `aname`                   VARCHAR(255) NOT NULL,
  `mname`                   VARCHAR(255) NOT NULL,
  `start_time`              VARCHAR(255) NOT NULL,
  `end_time`                VARCHAR(255) NOT NULL,
  `checkFlag`               int(1) default 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `missionpicture` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `picture_name`            VARCHAR(255) NOT NULL,
  `mname`                   VARCHAR(255) NOT NULL,
  `picture`                 LONGBLOB NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `workerpicture` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `picture_name`            VARCHAR(255) NOT NULL,
  `wname`                   VARCHAR(255) NOT NULL,
  `mname`                   VARCHAR(255) NOT NULL,
  `picture`                 LongBLOB NOT NULL,
  `state0`                   int(1) DEFAULT 0,
  `ptag`                   VARCHAR(255),
  PRIMARY KEY (`id`)
);

CREATE TABLE `evaluatepicture` (
  `id`                      int(11) NOT NULL AUTO_INCREMENT,
  `ename`                    VARCHAR(255) NOT NULL,
  `rname`                   VARCHAR(255) NOT NULL,
  `mname`                   VARCHAR(255) NOT NULL,
  `isRight`                 int(1) default 0,
  `state0`                   int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
);


CREATE TABLE `weeklylogin` (
  `id`                     int(11) NOT NULL AUTO_INCREMENT,
  `mon`                    int(11) DEFAULT 0,
  `tue`                    int(11) DEFAULT 0,
  `wed`                    int(11) DEFAULT 0,
  `thu`                    int(11) DEFAULT 0,
  `fri`                    int(11) DEFAULT 0,
  `sat`                    int(11) DEFAULT 0,
  `sun`                    int(11) DEFAULT 0,
  `lastStr`                VARCHAR(255),
  PRIMARY KEY (`id`)
);

INSERT INTO `weeklylogin` values (1, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (2, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (3, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (4, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (5, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (6, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (7, 0, 0, 0, 0, 0, 0, 0, '20180618');
INSERT INTO `weeklylogin` values (8, 0, 0, 0, 0, 0, 0, 0, '20180618');