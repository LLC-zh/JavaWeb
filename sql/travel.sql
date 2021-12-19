/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.34 : Database - travel_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`travel_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `travel_system`;

/*Table structure for table `delicacy_project` */

DROP TABLE IF EXISTS `delicacy_project`;

CREATE TABLE `delicacy_project` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '美食主键id',
  `delicacy_name` varchar(30) NOT NULL COMMENT '美食名称',
  `delicacy_price` decimal(5,2) NOT NULL COMMENT '美食价格',
  `delicacy_introduction` varchar(300) NOT NULL COMMENT '美食介绍',
  `delicacy_image` varchar(100) DEFAULT NULL COMMENT '美食图片地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `play_project` */

DROP TABLE IF EXISTS `play_project`;

CREATE TABLE `play_project` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '游玩攻略主键id',
  `scenic_number` varchar(10) NOT NULL COMMENT '景点编号',
  `play_introduction` varchar(300) NOT NULL COMMENT '攻略内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `scenic_project` */

DROP TABLE IF EXISTS `scenic_project`;

CREATE TABLE `scenic_project` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '景点主键id',
  `scenic_number` varchar(10) NOT NULL COMMENT '景点编号',
  `scenic_name` varchar(30) NOT NULL COMMENT '景点名称',
  `scenic_image` varchar(30) DEFAULT NULL COMMENT '景点图片地址',
  `scenic_location` varchar(50) NOT NULL COMMENT '景点位置',
  `scenic_describe` varchar(200) NOT NULL COMMENT '景点简述',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `scenic_number` (`scenic_number`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `travel_note` */

DROP TABLE IF EXISTS `travel_note`;

CREATE TABLE `travel_note` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '游记主键id',
  `user_uuid` char(32) NOT NULL COMMENT '用户uuid',
  `note_title` varchar(100) DEFAULT NULL COMMENT '游记标题',
  `note_content` varchar(500) NOT NULL COMMENT '游记内容',
  `gmt_create` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(6) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `user_project` */

DROP TABLE IF EXISTS `user_project`;

CREATE TABLE `user_project` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
  `user_uuid` char(32) NOT NULL COMMENT '用户uuid',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `user_password` varchar(16) NOT NULL DEFAULT '123456' COMMENT '用户密码',
  `is_admin` int(1) unsigned DEFAULT NULL COMMENT '是否为管理员(0:否,1:是)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
