/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26 : Database - paper_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`paper_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `paper_manage`;

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
                            `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(500) DEFAULT NULL COMMENT '论文题目',
                            `pid` int(4) unsigned NOT NULL COMMENT '论文题目id',
                            `createdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传日期',
                            `createusername` varchar(50) DEFAULT NULL COMMENT '上传人',
                            `createuserid` varchar(50) NOT NULL COMMENT '上传人id',
                            `filetype` varchar(100) DEFAULT NULL COMMENT '论文分类',
                            `path` varchar(200) DEFAULT NULL COMMENT '文件保存路径',
                            PRIMARY KEY (`id`),
                            KEY `pid` (`pid`),
                            KEY `createuserid` (`createuserid`),
                            CONSTRAINT `document_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `projectinfo` (`id`),
                            CONSTRAINT `document_ibfk_2` FOREIGN KEY (`createuserid`) REFERENCES `userinfo` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `document` */

/*Table structure for table `funcmoduel` */

DROP TABLE IF EXISTS `funcmoduel`;

CREATE TABLE `funcmoduel` (
                              `fmid` int(4) NOT NULL,
                              `fmname` varchar(50) DEFAULT NULL COMMENT '功能名称',
                              `fmurl` varchar(50) DEFAULT NULL COMMENT 'url链接',
                              `picurl` varchar(50) DEFAULT NULL COMMENT '图片url',
                              `parentid` int(4) DEFAULT NULL COMMENT '一级菜单id',
                              `fmbz` char(200) DEFAULT NULL COMMENT '备注',
                              `findex` int(4) DEFAULT NULL COMMENT '排序',
                              `flg` varchar(50) DEFAULT NULL COMMENT '标示',
                              `status` varchar(50) DEFAULT NULL COMMENT '状态',
                              PRIMARY KEY (`fmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `funcmoduel` */

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
                                `id` varchar(50) NOT NULL,
                                `name` varchar(50) DEFAULT NULL COMMENT '部门名',
                                `staff` varchar(50) DEFAULT NULL COMMENT '负责人',
                                `tel` varchar(20) DEFAULT NULL COMMENT '电话',
                                `fax` varchar(20) DEFAULT NULL COMMENT '传真',
                                `parentid` varchar(50) DEFAULT NULL COMMENT '上级部门id',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `organization` */

/*Table structure for table `procurementorder` */

DROP TABLE IF EXISTS `procurementorder`;

CREATE TABLE `procurementorder` (
                                    `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
                                    `pmid` int(4) unsigned NOT NULL COMMENT '论文题目id',
                                    `state` varchar(50) DEFAULT NULL COMMENT '状态',
                                    `finishdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '选题日期',
                                    `appuser` varchar(30) NOT NULL COMMENT '选题人员',
                                    PRIMARY KEY (`id`),
                                    KEY `pmid` (`pmid`),
                                    KEY `appuser` (`appuser`),
                                    CONSTRAINT `procurementorder_ibfk_1` FOREIGN KEY (`pmid`) REFERENCES `projectinfo` (`id`),
                                    CONSTRAINT `procurementorder_ibfk_2` FOREIGN KEY (`appuser`) REFERENCES `userinfo` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `procurementorder` */

/*Table structure for table `projectinfo` */

DROP TABLE IF EXISTS `projectinfo`;

CREATE TABLE `projectinfo` (
                               `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
                               `procjdept` varchar(500) DEFAULT NULL COMMENT '题目来源单位',
                               `projectname` varchar(200) DEFAULT NULL COMMENT '论文题目',
                               `projectstyle` varchar(50) DEFAULT NULL COMMENT '是否校外项目',
                               `clientman` varchar(100) DEFAULT NULL COMMENT '题目来源名称',
                               `proconstractcode` varchar(20) DEFAULT NULL COMMENT '所属专业',
                               `projectdemo` tinytext COMMENT '论文要求',
                               `projectstate` varchar(40) DEFAULT '待审核' COMMENT '状态',
                               `requirecontent` tinytext COMMENT '主要研究内容',
                               `infowriteman` varchar(36) DEFAULT NULL COMMENT '申报人ID',
                               `infowritedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申报日期',
                               `manageman` varchar(50) DEFAULT NULL COMMENT '申报人',
                               `issubmit` varchar(100) DEFAULT NULL COMMENT '届别选择ID',
                               `contractsigndate` varchar(500) DEFAULT NULL COMMENT '审批结论',
                               `projectfr` varchar(50) DEFAULT NULL COMMENT '届别选择',
                               `projectchange` varchar(50) DEFAULT '1' COMMENT '是否自拟题',
                               `projectfujian` varchar(50) DEFAULT NULL COMMENT '重复使用情况',
                               PRIMARY KEY (`id`),
                               KEY `infowriteman` (`infowriteman`),
                               CONSTRAINT `projectinfo_ibfk_1` FOREIGN KEY (`infowriteman`) REFERENCES `userinfo` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `projectinfo` */

/*Table structure for table `roleinfo` */

DROP TABLE IF EXISTS `roleinfo`;

CREATE TABLE `roleinfo` (
                            `roleid` int(4) NOT NULL,
                            `rolename` varchar(50) NOT NULL COMMENT '角色名称',
                            `rbz` varchar(200) DEFAULT NULL COMMENT '备注',
                            `flg` varchar(50) DEFAULT NULL COMMENT '标示',
                            PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roleinfo` */

/*Table structure for table `rolepow` */

DROP TABLE IF EXISTS `rolepow`;

CREATE TABLE `rolepow` (
                           `rpid` int(4) NOT NULL,
                           `roleid` int(4) NOT NULL COMMENT '角色id',
                           `fmid` int(4) NOT NULL COMMENT '功能id',
                           PRIMARY KEY (`rpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rolepow` */

/*Table structure for table `specialcourse` */

DROP TABLE IF EXISTS `specialcourse`;

CREATE TABLE `specialcourse` (
                                 `id` varchar(50) NOT NULL,
                                 `scname` varchar(500) DEFAULT NULL COMMENT '专业名称',
                                 `sccode` varchar(50) DEFAULT NULL COMMENT '专业代码',
                                 `scdiscern` tinytext COMMENT '描述',
                                 `parentid` varchar(50) DEFAULT NULL COMMENT '上级专业id',
                                 `isdisplay` varchar(50) DEFAULT NULL COMMENT '是否显示',
                                 `flg` varchar(50) DEFAULT NULL COMMENT '标示',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `specialcourse` */

/*Table structure for table `staffinfo` */

DROP TABLE IF EXISTS `staffinfo`;

CREATE TABLE `staffinfo` (
                             `userid` int(10) unsigned NOT NULL AUTO_INCREMENT,
                             `name` varchar(50) DEFAULT NULL COMMENT '人员姓名',
                             `username` varchar(30) NOT NULL COMMENT '账号',
                             `sex` varchar(50) DEFAULT NULL COMMENT '性别',
                             `dept` varchar(50) DEFAULT NULL COMMENT '部门',
                             `passwoed` varchar(20) DEFAULT NULL COMMENT '密码（这个字段暂时不用)',
                             `loginname` varchar(40) DEFAULT NULL COMMENT '用户名',
                             `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
                             `homeaddress` varchar(200) DEFAULT NULL COMMENT '居住地址',
                             `postcode` varchar(10) DEFAULT NULL COMMENT '邮编',
                             `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
                             `mobilephone` varchar(20) DEFAULT NULL COMMENT '手机',
                             `status` varchar(50) DEFAULT NULL COMMENT '状态',
                             `sysroleguid` varchar(50) DEFAULT NULL COMMENT '学生/老师',
                             PRIMARY KEY (`userid`,`username`),
                             KEY `username` (`username`),
                             CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `userinfo` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `staffinfo` */

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
                            `userid` int(4) unsigned NOT NULL AUTO_INCREMENT,
                            `roleid` int(4) DEFAULT NULL COMMENT '角色id',
                            `username` varchar(30) NOT NULL COMMENT '用户名',
                            `password` varchar(30) NOT NULL DEFAULT '888888' COMMENT '密码',
                            `userstate` varchar(50) DEFAULT NULL COMMENT '状态',
                            `staffid` varchar(50) DEFAULT NULL COMMENT '基本信息id',
                            `flg` varchar(50) DEFAULT NULL COMMENT '学生/老师',
                            PRIMARY KEY (`userid`,`username`),
                            KEY `roleid` (`roleid`),
                            KEY `username` (`username`),
                            CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `roleinfo` (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
