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

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` varchar(60) NOT NULL,
  `category_name` varchar(60) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_name`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('6ea864cea181464296e253c89b0ea603','Clothes',1,'2015-12-16','admin','2015-12-22','admin'),('ae96da2da86b4ae680d19ed4e676a392','Food',1,'2015-12-16','admin','2015-12-22','admin'),('dad095775d0b41c993f6df3b3ccd4195','Electronic',1,'2015-12-16','admin','2015-12-22','admin');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` varchar(60) NOT NULL,
  `goods_code` varchar(60) NOT NULL,
  `goods_name` varchar(70) NOT NULL,
  `brand` varchar(30) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`goods_code`,`goods_name`,`brand`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('0e7e2714b01544e0a80cda66c41f4de8','SMPK','Sempak','Tiger',1,'2015-12-20','admin','2015-12-25','admin'),('babba2a84a59424498b8da10000b1018','TS','Test Product','yyyy',1,'2015-12-20','admin','2015-12-20','admin');

/*Table structure for table `goods_category` */

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `goods_id` varchar(60) NOT NULL,
  `category_id` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `fk_categoy_goods_m_to_m` (`category_id`),
  CONSTRAINT `fk_categoy_goods_m_to_m` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_goods_category_1_to_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `goods_category` */

insert  into `goods_category`(`goods_id`,`category_id`) values ('0e7e2714b01544e0a80cda66c41f4de8','ae96da2da86b4ae680d19ed4e676a392'),('babba2a84a59424498b8da10000b1018','dad095775d0b41c993f6df3b3ccd4195');

/*Table structure for table `goods_discount` */

DROP TABLE IF EXISTS `goods_discount`;

CREATE TABLE `goods_discount` (
  `goods_id` varchar(60) NOT NULL,
  `start_discount` date DEFAULT NULL,
  `end_discount` date DEFAULT NULL,
  `discount` decimal(10,2) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `discount` (`discount`),
  CONSTRAINT `fk_goods_discount` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `goods_discount` */

insert  into `goods_discount`(`goods_id`,`start_discount`,`end_discount`,`discount`,`description`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('babba2a84a59424498b8da10000b1018','2012-12-03','2012-12-09','0.31','diskon akhir tahun','2012-12-02','kazuya mishima','2012-12-03','jin kazama');

/*Table structure for table `goods_price` */

DROP TABLE IF EXISTS `goods_price`;

CREATE TABLE `goods_price` (
  `goods_id` varchar(60) NOT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  CONSTRAINT `fk_goods_price` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `goods_price` */

insert  into `goods_price`(`goods_id`,`price`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('0e7e2714b01544e0a80cda66c41f4de8','13000','2015-12-26','admin','2015-12-27','admin'),('babba2a84a59424498b8da10000b1018','200000','2015-12-27','admin','2015-12-27','admin');

/*Table structure for table `goods_supplier` */

DROP TABLE IF EXISTS `goods_supplier`;

CREATE TABLE `goods_supplier` (
  `supplier_id` varchar(60) NOT NULL,
  `goods_id` varchar(60) NOT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `fk_supplier_goods_m_to_m` (`supplier_id`),
  CONSTRAINT `fk_goods_supplier_m_to_m` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_supplier_goods_m_to_m` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `goods_supplier` */

insert  into `goods_supplier`(`supplier_id`,`goods_id`) values ('6675a18f22234b12b54e42bd25c19eb0','0e7e2714b01544e0a80cda66c41f4de8'),('6675a18f22234b12b54e42bd25c19eb0','babba2a84a59424498b8da10000b1018');

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

insert  into `menu`(`menu_id`,`label`,`relative_url`,`parent_id`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('055dbbf122924def8aaa81e82b40cff1','Goods','/master/goods','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL),('1a40147050164b1ea4f40a3c55a072fa','Authorization','/authorization',NULL,1,'2012-12-12','xxx',NULL,NULL),('1e25c49ee5cc4bf48f07662be4e0368b',NULL,NULL,NULL,1,'2012-12-12','xxx',NULL,NULL),('253dd73c9ac64477a3d1818df75d113a','Order','/master/order','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL),('2ad65c08594d4017bbb7b9ea30c5261b','Menu','/master/menu','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL),('38902529a7364d1da47e88e4c8cbec87','Supplier','/master/supplier','f30476fdd840488aaa06cc72870a03b2',1,'2013-12-12','xxx',NULL,NULL),('4a541f71b1194784807e95b0d8f9c2bd',NULL,NULL,NULL,1,'2012-12-12','xxx',NULL,NULL),('6cdc66e2f4d94cd0b1d9a40d58598a8d','Setting Price','/price/setting_price','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL),('6f5486d7767c4fa68c42c3ab089f9f6c',NULL,NULL,NULL,1,'2012-12-12','xxx',NULL,NULL),('951227bbc74e4f9f91c68b8b3959b391','Discount','/transaction/discount','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL),('ac6a239aa35d41509aed-d76b403b2193','Category','/master/category','f30476fdd840488aaa06cc72870a03b2',1,'2013-12-12','xxx',NULL,NULL),('afbd728104ae45f0855f435d6216c690',NULL,NULL,NULL,1,'2012-12-12','xxx',NULL,NULL),('c4689e9d5d6b4c30bc0f824a6e050216',NULL,NULL,NULL,1,'2012-12-12','xxx',NULL,NULL),('f30476fdd840488aaa06cc72870a03b2','Master','/master',NULL,1,'2012-12-12','xxx',NULL,NULL),('f7a57aa0ef9b4968b91f88e1494ac11b','Stock','/master/stock','f30476fdd840488aaa06cc72870a03b2',1,'2012-12-12','xxx',NULL,NULL);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` varchar(60) NOT NULL,
  `transaction_number` varchar(60) NOT NULL,
  `customer_name` varchar(60) NOT NULL,
  `total_payment` decimal(10,0) DEFAULT NULL,
  `transaction_datetime` datetime DEFAULT NULL,
  `user` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order` */

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `order_id` varchar(60) NOT NULL,
  `goods_id` varchar(60) NOT NULL,
  `quantity` double DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_order_detail` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_detail` */

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

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `goods_id` varchar(60) NOT NULL,
  `quantity` int(11) NOT NULL,
  `max_quantity` int(11) DEFAULT NULL,
  `location` varchar(60) NOT NULL,
  `sub_location` varchar(60) NOT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(60) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  CONSTRAINT `fk_goods_stock` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stock` */

insert  into `stock`(`goods_id`,`quantity`,`max_quantity`,`location`,`sub_location`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('babba2a84a59424498b8da10000b1018',145,1200,'x','y',NULL,NULL,'2015-12-25','admin');

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `supplier_id` varchar(60) NOT NULL,
  `supplier_name` varchar(50) NOT NULL,
  `pic` varchar(50) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supplier` */

insert  into `supplier`(`supplier_id`,`supplier_name`,`pic`,`address`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('04a0b495fc8e4e4aa77a7f06d2d43b2b','supplier name 2x','mr.yc','lonely street on the boulevard of broken dream 2-2y',1,'2015-12-18','admin','2015-12-22','admin'),('5c9fca38d19241ee9713b3718c9fa8af','supplier name 9','miss.h','wet slippy road',1,'2015-12-18','admin','2015-12-22','admin'),('6675a18f22234b12b54e42bd25c19eb0','supplier name 5','miss.h','wet slippy road',1,'2015-12-18','admin','2015-12-22','admin'),('7fdf8786258d468ea14acb2b654c9ba2','supplier name 4','miss.v','wet slippy',1,'2015-12-18','admin','2015-12-22','admin');

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
