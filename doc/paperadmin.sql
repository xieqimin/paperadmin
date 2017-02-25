create database `paperadmin`;
use `paperadmin`;
CREATE TABLE `userinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�û�ID',
  `username` varchar(32) NOT NULL COMMENT '�û���',
  `password` char(64) NOT NULL COMMENT '����',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `school_year` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ѧ��ID',
  `schoolYearName` char(16) NOT NULL COMMENT 'ѧ������',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ѧ���';

CREATE TABLE `institute` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ѧԺID',
  `instituteName` varchar(64) NOT NULL COMMENT 'ѧԺ����',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ѧԺ��';


CREATE TABLE `profession` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'רҵID',
  `professionName` varchar(64) NOT NULL COMMENT 'רҵ����',
  `instituteID` int(11) NOT NULL COMMENT 'ѧԺID',
  PRIMARY KEY (`ID`),
  KEY `profession_fk_1` (`instituteID`),
  CONSTRAINT `profession_fk_1` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='רҵ��';

CREATE TABLE `paper_status` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�Ծ�״̬ID',
  `statusName` char(32) DEFAULT NULL COMMENT '�Ծ�״̬����',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='�Ծ�״̬��';


CREATE TABLE `paperinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�Ծ�ID',
  `course` varchar(64) NOT NULL COMMENT '�γ���',
  `schoolYearID` int(11) NOT NULL COMMENT 'ѧ��ID',
  `term` char(64) NOT NULL COMMENT 'ѧ��',
  `instituteID` int(11) NOT NULL COMMENT 'ѧԺID',
  `professionID` int(11) NOT NULL COMMENT 'רҵID',
  `paperNumber` int(11) NOT NULL COMMENT '�Ծ����',
  `answersheetNumber` int(11) NOT NULL COMMENT '����ֽ����',
  `scratchpaperNumber` int(11) NOT NULL COMMENT '�ݸ�ֽ����',
  `paperType` char(8) DEFAULT NULL COMMENT '�Ծ�����',
  `testTime` datetime NOT NULL COMMENT '����ʱ��',
  `uploadTime` datetime NOT NULL COMMENT '�Ծ��ϴ�������޸�ʱ��',
  `paperStatus` int(11) NOT NULL COMMENT '�Ծ�״̬, 0: δ��ӡ 1: ��ӡ�� 2: ��ȡ',
  `userID` int(11) NOT NULL COMMENT '�û�ID',
  `saveaddress` varchar(128) DEFAULT NULL COMMENT '����·��',
  PRIMARY KEY (`ID`),
  KEY `paperinfo_fk_1` (`userID`),
  KEY `paperinfo_fk_2` (`schoolYearID`),
  KEY `paperinfo_fk_3` (`instituteID`),
  KEY `paperinfo_fk_4` (`professionID`),
  CONSTRAINT `paperinfo_fk_1` FOREIGN KEY (`userID`) REFERENCES `userinfo` (`ID`),
  CONSTRAINT `paperinfo_fk_2` FOREIGN KEY (`schoolYearID`) REFERENCES `school_year` (`ID`),
  CONSTRAINT `paperinfo_fk_3` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`ID`),
  CONSTRAINT `paperinfo_fk_4` FOREIGN KEY (`professionID`) REFERENCES `profession` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `paper_aeskey` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '��ԿID',
  `keyContent` char(32) NOT NULL COMMENT '��Կ����',
  `paperID` int(11) NOT NULL COMMENT '�Ծ�ID',
  PRIMARY KEY (`ID`),
  KEY `paper_aeskey_fk_1` (`paperID`),
  CONSTRAINT `paper_aeskey_fk_1` FOREIGN KEY (`paperID`) REFERENCES `paperinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�Ծ������Կ���Ծ�����Կһһ��Ӧ';