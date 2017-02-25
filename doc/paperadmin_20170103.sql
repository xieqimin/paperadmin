-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema paperadmin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema paperadmin
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paperadmin` DEFAULT CHARACTER SET utf8 ;
USE `paperadmin` ;

-- -----------------------------------------------------
-- Table `paperadmin`.`institute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`institute` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '学院ID',
  `instituteName` VARCHAR(64) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '学院表';


-- -----------------------------------------------------
-- Table `paperadmin`.`userinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`userinfo` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
  `password` CHAR(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `paperadmin`.`managerinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`managerinfo` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
  `password` CHAR(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `paperadmin`.`school_year`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`school_year` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '学年ID',
  `schoolYearName` CHAR(16) NOT NULL COMMENT '学年名称',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '学年表';

-- -----------------------------------------------------
-- Table `paperadmin`.`paperinfo`
-- -----------------------------------------------------
CREATE TABLE `paperinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷ID',
  `course` varchar(64) NOT NULL COMMENT '课程名',
  `schoolYearID` int(11) NOT NULL COMMENT '学年ID',
  `term` char(64) NOT NULL COMMENT '学期',
  `instituteID` int(11) NOT NULL COMMENT '学院ID',
  `profession` varchar(64) NOT NULL COMMENT '专业ID',
  `paperNumber` int(11) NOT NULL COMMENT '试卷份数',
  `answersheetNumber` int(11) NOT NULL COMMENT '答题纸份数',
  `scratchpaperNumber` int(11) NOT NULL COMMENT '草稿纸份数',
  `paperType` char(8) DEFAULT NULL COMMENT '试卷类型',
  `testTime` int(11) DEFAULT NULL,
  `uploadTime` int(11) DEFAULT NULL,
  `paperStatus` int(11) NOT NULL COMMENT '试卷状态, 0: 未打印 1: 打印中 2: 待取',
  `userID` int(11) NOT NULL COMMENT '用户ID',
  `saveaddress` varchar(128) DEFAULT NULL COMMENT '保存路径',
  `receivePerson` varchar(64) DEFAULT NULL COMMENT '取卷人',
  `receiveTime` int(11) DEFAULT NULL COMMENT '取卷时间',
  `saveLocation` varchar(64) DEFAULT NULL COMMENT '试卷存放位置',
  PRIMARY KEY (`ID`),
  KEY `paperinfo_fk_1` (`userID`),
  KEY `paperinfo_fk_2` (`schoolYearID`),
  KEY `paperinfo_fk_3` (`instituteID`),
  CONSTRAINT `paperinfo_fk_1` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`ID`),
  CONSTRAINT `paperinfo_fk_2` FOREIGN KEY (`schoolYearID`) REFERENCES `school_year` (`ID`),
  CONSTRAINT `paperinfo_fk_3` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- -----------------------------------------------------
-- Table `paperadmin`.`paper_aeskey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`paper_aeskey` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '密钥ID',
  `keyContent` CHAR(32) NOT NULL COMMENT '密钥内容',
  `paperID` INT(11) NOT NULL COMMENT '试卷ID',
  PRIMARY KEY (`ID`),
  INDEX `paper_aeskey_fk_1` (`paperID` ASC),
  CONSTRAINT `paper_aeskey_fk_1`
    FOREIGN KEY (`paperID`)
    REFERENCES `paperadmin`.`paperinfo` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '试卷加密密钥表，试卷与密钥一一对应';


-- -----------------------------------------------------
-- Table `paperadmin`.`paper_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`paper_status` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '试卷状态ID',
  `statusName` CHAR(32) NULL DEFAULT NULL COMMENT '试卷状态名称',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '试卷状态表';

INSERT INTO `paper_status` VALUES(1, '未处理'), (2, '送印中'), (3, '待取卷'), (4, '已取卷');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
