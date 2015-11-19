/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.6.16 : Database - union_app
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`union_app` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `union_app`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menu_id` varchar(60) NOT NULL,
  `label` varchar(60) DEFAULT NULL,
  `relative_url` varchar(60) DEFAULT NULL,
  `parent_id` varchar(60) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `menu` */

insert  into `menu`(`menu_id`,`label`,`relative_url`,`parent_id`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('1a40147050164b1ea4f40a3c55a072fa','Menu Authorization','/authorization','',1,'2012-12-12','xxx',NULL,NULL),('2ad65c08594d4017bbb7b9ea30c5261b','Menu','/master/menu','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL),('f30476fdd840488aaa06cc72870a03b2','Master','/master',NULL,1,'2012-12-12','xxx',NULL,NULL),('f7a57aa0ef9b4968b91f88e1494ac11b','Stock','/master/order','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(40) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_name`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('6dc5acb6c869423fbdd014b979c04cef','admin','2012-12-12','xxx','2012-12-12','xxx');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `role_id` varchar(60) DEFAULT NULL,
  `menu_id` varchar(60) DEFAULT NULL,
  KEY `menu_role_id_pk` (`role_id`),
  KEY `role_menu_id_pk` (`menu_id`),
  CONSTRAINT `menu_role_id_pk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_menu_id_pk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `role_menu` */

insert  into `role_menu`(`role_id`,`menu_id`) values ('6dc5acb6c869423fbdd014b979c04cef','f30476fdd840488aaa06cc72870a03b2');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(60) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varbinary(60) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('2c947916e2de4ae79682de0ff6e91e91','xxxp','$2a$10$nHynrw1G4Im0yjeZm9cXCO6w.UdwtiuvpHHwjjWRGceSXOIXoAhfm',1,'2012-12-12','xxx','2012-12-12','xxx'),('a8b35b77e3a14c718480019afe091fe3','test1','xxxx',1,'2012-12-12','xxx','2012-12-12','xxx'),('xx','xx','xxxx',1,'2012-12-12','xxx','2012-12-12','xxx'),('yyyy','xxxy','xxxx',1,'2012-12-12','xxx','2012-12-12','xxx');

/*Table structure for table `user_detail` */

DROP TABLE IF EXISTS `user_detail`;

CREATE TABLE `user_detail` (
  `user_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `religion` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_detail` */

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` varchar(60) DEFAULT NULL,
  `role_id` varchar(60) DEFAULT NULL,
  KEY `user_role_id_pk` (`user_id`),
  KEY `role_user_id_pk` (`role_id`),
  CONSTRAINT `role_user_id_pk` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_id_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values ('2c947916e2de4ae79682de0ff6e91e91','6dc5acb6c869423fbdd014b979c04cef');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
