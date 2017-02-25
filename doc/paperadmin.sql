create database `paperadmin`;
use `paperadmin`;
CREATE TABLE `userinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` char(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `school_year` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '学年ID',
  `schoolYearName` char(16) NOT NULL COMMENT '学年名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学年表';

CREATE TABLE `institute` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院ID',
  `instituteName` varchar(64) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学院表';


CREATE TABLE `profession` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `professionName` varchar(64) NOT NULL COMMENT '专业名称',
  `instituteID` int(11) NOT NULL COMMENT '学院ID',
  PRIMARY KEY (`ID`),
  KEY `profession_fk_1` (`instituteID`),
  CONSTRAINT `profession_fk_1` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专业表';

CREATE TABLE `paper_status` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷状态ID',
  `statusName` char(32) DEFAULT NULL COMMENT '试卷状态名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='试卷状态表';


CREATE TABLE `paperinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷ID',
  `course` varchar(64) NOT NULL COMMENT '课程名',
  `schoolYearID` int(11) NOT NULL COMMENT '学年ID',
  `term` char(64) NOT NULL COMMENT '学期',
  `instituteID` int(11) NOT NULL COMMENT '学院ID',
  `professionID` int(11) NOT NULL COMMENT '专业ID',
  `paperNumber` int(11) NOT NULL COMMENT '试卷份数',
  `answersheetNumber` int(11) NOT NULL COMMENT '答题纸份数',
  `scratchpaperNumber` int(11) NOT NULL COMMENT '草稿纸份数',
  `paperType` char(8) DEFAULT NULL COMMENT '试卷类型',
  `testTime` datetime NOT NULL COMMENT '考试时间',
  `uploadTime` datetime NOT NULL COMMENT '试卷上传或最后修改时间',
  `paperStatus` int(11) NOT NULL COMMENT '试卷状态, 0: 未打印 1: 打印中 2: 待取',
  `userID` int(11) NOT NULL COMMENT '用户ID',
  `saveaddress` varchar(128) DEFAULT NULL COMMENT '保存路径',
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
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '密钥ID',
  `keyContent` char(32) NOT NULL COMMENT '密钥内容',
  `paperID` int(11) NOT NULL COMMENT '试卷ID',
  PRIMARY KEY (`ID`),
  KEY `paper_aeskey_fk_1` (`paperID`),
  CONSTRAINT `paper_aeskey_fk_1` FOREIGN KEY (`paperID`) REFERENCES `paperinfo` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试卷加密密钥表，试卷与密钥一一对应';