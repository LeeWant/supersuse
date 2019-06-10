/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.16 : Database - super_suse
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`super_suse` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `super_suse`;

/*Table structure for table `t_affair` */

DROP TABLE IF EXISTS `t_affair`;

CREATE TABLE `t_affair` (
  `affId` varchar(50) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `level` int(11) NOT NULL,
  `comment` mediumtext NOT NULL,
  `releaseTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `instCode` varchar(50) NOT NULL,
  `deptCode` varchar(50) NOT NULL,
  `majoCode` varchar(50) NOT NULL,
  `isDelete` int(2) DEFAULT '0',
  `status` varchar(10) DEFAULT '无',
  PRIMARY KEY (`affId`),
  KEY `FK_inst` (`instCode`),
  KEY `FK_majo` (`majoCode`),
  KEY `FK_userId` (`userId`),
  KEY `FK_dept_affair` (`deptCode`),
  CONSTRAINT `FK_dept_affair` FOREIGN KEY (`deptCode`) REFERENCES `t_department` (`deptCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_inst` FOREIGN KEY (`instCode`) REFERENCES `t_institute` (`instCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_majo` FOREIGN KEY (`majoCode`) REFERENCES `t_major` (`majoCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_clazz` */

DROP TABLE IF EXISTS `t_clazz`;

CREATE TABLE `t_clazz` (
  `clazzId` int(11) NOT NULL AUTO_INCREMENT,
  `majoCode` varchar(50) NOT NULL,
  `clazzNumber` varchar(50) NOT NULL,
  `year` varchar(50) NOT NULL,
  `teacherId` int(11) DEFAULT NULL,
  `monitorId` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isActivity` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`clazzId`),
  KEY `FK_clazz_majo` (`majoCode`),
  KEY `FK_user_monitorId` (`monitorId`),
  KEY `FK_user_tea` (`teacherId`),
  CONSTRAINT `FK_clazz_majo` FOREIGN KEY (`majoCode`) REFERENCES `t_major` (`majoCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_monitorId` FOREIGN KEY (`monitorId`) REFERENCES `t_user` (`userId`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_tea` FOREIGN KEY (`teacherId`) REFERENCES `t_user` (`userId`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `deptCode` varchar(50) NOT NULL,
  `instCode` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`deptCode`),
  KEY `FK_inst_dept` (`instCode`),
  CONSTRAINT `FK_inst_dept` FOREIGN KEY (`instCode`) REFERENCES `t_institute` (`instCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_file` */

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `fileId` varchar(50) NOT NULL,
  `affId` varchar(50) NOT NULL,
  `fileName` varchar(100) NOT NULL,
  `filePath` varchar(200) NOT NULL,
  `uploadTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isDelete` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`fileId`),
  KEY `affId_file_pk` (`affId`),
  CONSTRAINT `affId_file_pk` FOREIGN KEY (`affId`) REFERENCES `t_affair` (`affId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_institute` */

DROP TABLE IF EXISTS `t_institute`;

CREATE TABLE `t_institute` (
  `instCode` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`instCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_ip` */

DROP TABLE IF EXISTS `t_ip`;

CREATE TABLE `t_ip` (
  `ipId` varchar(50) NOT NULL,
  `ipAddress` varchar(20) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_major` */

DROP TABLE IF EXISTS `t_major`;

CREATE TABLE `t_major` (
  `majoCode` varchar(50) NOT NULL,
  `deptCode` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`majoCode`),
  KEY `FK_maj_dept` (`deptCode`),
  CONSTRAINT `FK_maj_dept` FOREIGN KEY (`deptCode`) REFERENCES `t_department` (`deptCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(50) NOT NULL,
  `menuCode` varchar(50) NOT NULL,
  `menuLevel` int(11) NOT NULL,
  PRIMARY KEY (`menuId`),
  UNIQUE KEY `menuCode` (`menuCode`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `resId` int(11) NOT NULL AUTO_INCREMENT,
  `resName` varchar(50) NOT NULL,
  `resCode` varchar(50) NOT NULL,
  PRIMARY KEY (`resId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) NOT NULL,
  `roleCode` varchar(50) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleCode` (`roleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `t_role_mannage` */

DROP TABLE IF EXISTS `t_role_mannage`;

CREATE TABLE `t_role_mannage` (
  `roleId` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`roleId`),
  CONSTRAINT `FK_role` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`menuId`,`roleId`),
  KEY `FK_role_rolemenu` (`roleId`),
  CONSTRAINT `FK_menu_rolemenu` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`menuId`),
  CONSTRAINT `FK_role_rolemenu` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_role_resource` */

DROP TABLE IF EXISTS `t_role_resource`;

CREATE TABLE `t_role_resource` (
  `roleId` int(11) NOT NULL,
  `resId` int(11) NOT NULL,
  PRIMARY KEY (`resId`,`roleId`),
  KEY `FK_role_roleresource` (`roleId`),
  CONSTRAINT `FK_role_roleresource` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`),
  CONSTRAINT `FK_roleresource_resource` FOREIGN KEY (`resId`) REFERENCES `t_resource` (`resId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `clazzId` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `number` varchar(50) NOT NULL,
  `sex` smallint(6) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isDelete` int(2) DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `number_uniq` (`number`),
  KEY `FK_clazz_user` (`clazzId`),
  CONSTRAINT `FK_clazz_user` FOREIGN KEY (`clazzId`) REFERENCES `t_clazz` (`clazzId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8;

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK_role_roleuser` (`roleId`),
  CONSTRAINT `FK_role_roleuser` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`),
  CONSTRAINT `FK_user_roleuser` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
