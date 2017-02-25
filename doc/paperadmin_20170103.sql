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
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ѧԺID',
  `instituteName` VARCHAR(64) NOT NULL COMMENT 'ѧԺ����',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'ѧԺ��';


-- -----------------------------------------------------
-- Table `paperadmin`.`userinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`userinfo` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�û�ID',
  `username` VARCHAR(32) NOT NULL UNIQUE COMMENT '�û���',
  `password` CHAR(64) NOT NULL COMMENT '����',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `paperadmin`.`managerinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`managerinfo` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�û�ID',
  `username` VARCHAR(32) NOT NULL UNIQUE COMMENT '�û���',
  `password` CHAR(64) NOT NULL COMMENT '����',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `paperadmin`.`school_year`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`school_year` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ѧ��ID',
  `schoolYearName` CHAR(16) NOT NULL COMMENT 'ѧ������',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'ѧ���';

-- -----------------------------------------------------
-- Table `paperadmin`.`paperinfo`
-- -----------------------------------------------------
CREATE TABLE `paperinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�Ծ�ID',
  `course` varchar(64) NOT NULL COMMENT '�γ���',
  `schoolYearID` int(11) NOT NULL COMMENT 'ѧ��ID',
  `term` char(64) NOT NULL COMMENT 'ѧ��',
  `instituteID` int(11) NOT NULL COMMENT 'ѧԺID',
  `profession` varchar(64) NOT NULL COMMENT 'רҵID',
  `paperNumber` int(11) NOT NULL COMMENT '�Ծ����',
  `answersheetNumber` int(11) NOT NULL COMMENT '����ֽ����',
  `scratchpaperNumber` int(11) NOT NULL COMMENT '�ݸ�ֽ����',
  `paperType` char(8) DEFAULT NULL COMMENT '�Ծ�����',
  `testTime` int(11) DEFAULT NULL,
  `uploadTime` int(11) DEFAULT NULL,
  `paperStatus` int(11) NOT NULL COMMENT '�Ծ�״̬, 0: δ��ӡ 1: ��ӡ�� 2: ��ȡ',
  `userID` int(11) NOT NULL COMMENT '�û�ID',
  `saveaddress` varchar(128) DEFAULT NULL COMMENT '����·��',
  `receivePerson` varchar(64) DEFAULT NULL COMMENT 'ȡ����',
  `receiveTime` int(11) DEFAULT NULL COMMENT 'ȡ��ʱ��',
  `saveLocation` varchar(64) DEFAULT NULL COMMENT '�Ծ���λ��',
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
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '��ԿID',
  `keyContent` CHAR(32) NOT NULL COMMENT '��Կ����',
  `paperID` INT(11) NOT NULL COMMENT '�Ծ�ID',
  PRIMARY KEY (`ID`),
  INDEX `paper_aeskey_fk_1` (`paperID` ASC),
  CONSTRAINT `paper_aeskey_fk_1`
    FOREIGN KEY (`paperID`)
    REFERENCES `paperadmin`.`paperinfo` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '�Ծ������Կ���Ծ�����Կһһ��Ӧ';


-- -----------------------------------------------------
-- Table `paperadmin`.`paper_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `paperadmin`.`paper_status` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '�Ծ�״̬ID',
  `statusName` CHAR(32) NULL DEFAULT NULL COMMENT '�Ծ�״̬����',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '�Ծ�״̬��';

INSERT INTO `paper_status` VALUES(1, 'δ����'), (2, '��ӡ��'), (3, '��ȡ��'), (4, '��ȡ��');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
