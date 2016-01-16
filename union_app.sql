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
  KEY `discount` (`discount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `goods_discount` */

insert  into `goods_discount`(`goods_id`,`start_discount`,`end_discount`,`discount`,`description`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('','2012-12-03','2012-12-09','0.31','diskon akhir tahun','2012-12-02','kazuya mishima','2012-12-03','jin kazama');

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

/*Table structure for table `increment_key` */

DROP TABLE IF EXISTS `increment_key`;

CREATE TABLE `increment_key` (
  `date_key` date NOT NULL,
  `increment_key` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`date_key`,`increment_key`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `increment_key` */

insert  into `increment_key`(`date_key`,`increment_key`) values ('2012-12-14',1),('2012-12-14',2),('2012-12-14',3),('2012-12-14',4),('2012-12-14',5),('2012-12-15',1),('2012-12-15',2),('2015-12-28',1),('2015-12-28',2),('2015-12-28',3),('2015-12-30',1),('2015-12-30',2),('2015-12-30',3),('2015-12-30',4),('2015-12-30',5),('2015-12-30',6),('2015-12-30',7),('2015-12-30',8),('2015-12-30',9),('2015-12-30',10),('2015-12-30',11),('2015-12-30',12),('2015-12-30',13),('2015-12-30',14),('2015-12-30',15),('2015-12-30',16),('2015-12-30',17),('2015-12-30',18),('2015-12-30',19),('2015-12-30',20),('2015-12-30',21),('2015-12-30',22),('2015-12-30',23),('2015-12-30',24),('2015-12-30',25),('2016-01-03',1),('2016-01-03',2),('2016-01-03',3),('2016-01-03',4),('2016-01-03',5),('2016-01-10',1),('2016-01-10',2),('2016-01-10',3),('2016-01-10',4),('2016-01-10',5),('2016-01-10',6),('2016-01-10',7),('2016-01-10',8),('2016-01-10',9),('2016-01-10',10),('2016-01-10',11),('2016-01-10',12),('2016-01-10',13),('2016-01-10',14);

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

insert  into `menu`(`menu_id`,`label`,`relative_url`,`parent_id`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('055dbbf122924def8aaa81e82b40cff1','Goods','/master/goods','f30476fdd840488aaa06cc72870a03b2',0,'2012-12-12','xxx','2016-01-16','yakub'),('1a40147050164b1ea4f40a3c55a072fa','Authorization','/authorization',NULL,1,'2012-12-12','xxx',NULL,NULL),('253dd73c9ac64477a3d1818df75d113a','Order','/master/order','a9213aae28f642dd9bef08e17eeb692b',1,'2012-12-12','xxx','2016-01-11','yakub'),('2ad65c08594d4017bbb7b9ea30c5261b','Menu','/master/menu','491de3c15b6c4a839f81afe7974a2182',1,'2012-12-12','xxx',NULL,NULL),('2b75b95498954f859fd05ec245fb327e','Inventory','/inventory',NULL,1,'2012-12-12','xxx',NULL,NULL),('38902529a7364d1da47e88e4c8cbec87','Supplier','/master/supplier','f30476fdd840488aaa06cc72870a03b2',1,'2013-12-12','xxx','2016-01-11','yakub'),('491de3c15b6c4a839f81afe7974a2182','Setting','/setting',NULL,1,'2012-12-12','xxx',NULL,NULL),('6cdc66e2f4d94cd0b1d9a40d58598a8d','Setting Price','/price/setting_price','ee4f4ee0e7444e1d88cfae3ba5a35ae3',1,'2012-12-12','xxx',NULL,NULL),('951227bbc74e4f9f91c68b8b3959b391','Discount','/transaction/discount','f30476fdd840488aaa06cc72870a03b2',0,'2012-12-12','xxx','2016-01-11','yakub'),('a9213aae28f642dd9bef08e17eeb692b','Sales','/sales',NULL,1,'2012-12-12','xxx','2016-01-11','yakub'),('ac6a239aa35d41509aed-d76b403b2193','Category','/master/category','f30476fdd840488aaa06cc72870a03b2',1,'2013-12-12','xxx','2016-01-11','yakub'),('ee4f4ee0e7444e1d88cfae3ba5a35ae3','Goods Setting','/goodssetting',NULL,1,'2012-12-12','xxx',NULL,NULL),('f30476fdd840488aaa06cc72870a03b2','Master','/master',NULL,1,'2012-12-12','xxx','2016-01-11','yakub'),('f7a57aa0ef9b4968b91f88e1494ac11b','Stock','/master/stock','2b75b95498954f859fd05ec245fb327e',1,'2012-12-12','xxx','2016-01-11','yakub');

/*Table structure for table `order_transaction` */

DROP TABLE IF EXISTS `order_transaction`;

CREATE TABLE `order_transaction` (
  `order_id` varchar(60) NOT NULL,
  `transaction_number` varchar(60) NOT NULL,
  `total_payment` decimal(10,0) DEFAULT NULL,
  `transaction_datetime` datetime DEFAULT NULL,
  `login_user` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_transaction` */

insert  into `order_transaction`(`order_id`,`transaction_number`,`total_payment`,`transaction_datetime`,`login_user`) values ('26bb8e9f39c048d8b2bdfc6e951dec5b','TR20160110-00009','200000','2016-01-10 14:56:14','admin'),('3fb69bb2fa574e38b268059f020ed35a','TR20160110-00008','556000','2016-01-10 02:15:36','admin'),('431fec3a52f444e6abbfe99f0fcdd37d','TR20160110-00014','239000','2016-01-10 15:15:54','admin'),('58206d5a87374f35ae0515e7d43e1acf','TR20160110-00012','800000','2016-01-10 15:09:19','admin'),('6b28533573b14a0da983a57792733d5a','TR20160110-00010','13000','2016-01-10 15:05:44','admin'),('9e46b512e1ae44ed860ab7217a9b9270','TR20160110-00006','600000','2016-01-10 02:13:59','admin'),('bf54383780954619968d867611209f09','TR20160110-00011','400000','2016-01-10 15:07:25','admin'),('db16bbe8ae29452b86e1492a9d102753','TR20160110-00013','13000','2016-01-10 15:12:15','admin'),('fc986b245a4042eea21b0e71c9fee890','TR20160110-00007','1400000','2016-01-10 02:14:17','admin');

/*Table structure for table `order_transaction_detail` */

DROP TABLE IF EXISTS `order_transaction_detail`;

CREATE TABLE `order_transaction_detail` (
  `order_id` varchar(60) NOT NULL,
  `goods_id` varchar(60) NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`order_id`,`goods_id`),
  CONSTRAINT `fk_order_detail` FOREIGN KEY (`order_id`) REFERENCES `order_transaction` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_transaction_detail` */

insert  into `order_transaction_detail`(`order_id`,`goods_id`,`quantity`) values ('26bb8e9f39c048d8b2bdfc6e951dec5b','babba2a84a59424498b8da10000b1018',1),('3fb69bb2fa574e38b268059f020ed35a','0e7e2714b01544e0a80cda66c41f4de8',12),('3fb69bb2fa574e38b268059f020ed35a','babba2a84a59424498b8da10000b1018',2),('431fec3a52f444e6abbfe99f0fcdd37d','0e7e2714b01544e0a80cda66c41f4de8',3),('431fec3a52f444e6abbfe99f0fcdd37d','babba2a84a59424498b8da10000b1018',1),('58206d5a87374f35ae0515e7d43e1acf','babba2a84a59424498b8da10000b1018',4),('6b28533573b14a0da983a57792733d5a','0e7e2714b01544e0a80cda66c41f4de8',1),('9e46b512e1ae44ed860ab7217a9b9270','babba2a84a59424498b8da10000b1018',3),('bf54383780954619968d867611209f09','babba2a84a59424498b8da10000b1018',2),('db16bbe8ae29452b86e1492a9d102753','0e7e2714b01544e0a80cda66c41f4de8',1),('fc986b245a4042eea21b0e71c9fee890','babba2a84a59424498b8da10000b1018',7);

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

insert  into `role_menu`(`role_id`,`menu_id`) values ('6dc5acb6c869423fbdd014b979c04cef','f30476fdd840488aaa06cc72870a03b2'),('6dc5acb6c869423fbdd014b979c04cef','2b75b95498954f859fd05ec245fb327e'),('6dc5acb6c869423fbdd014b979c04cef','491de3c15b6c4a839f81afe7974a2182'),('6dc5acb6c869423fbdd014b979c04cef','a9213aae28f642dd9bef08e17eeb692b'),('6dc5acb6c869423fbdd014b979c04cef','ee4f4ee0e7444e1d88cfae3ba5a35ae3');

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

insert  into `stock`(`goods_id`,`quantity`,`max_quantity`,`location`,`sub_location`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('0e7e2714b01544e0a80cda66c41f4de8',1183,2000,' gudang1','rak12','2016-01-10','admin',NULL,NULL),('babba2a84a59424498b8da10000b1018',68,1200,'x','y',NULL,NULL,'2015-12-25','admin');

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

insert  into `user`(`user_id`,`username`,`password`,`active`,`created_date`,`created_by`,`updated_date`,`updated_by`) values ('2c947916e2de4ae79682de0ff6e91e91','xxxp','$2a$10$nHynrw1G4Im0yjeZm9cXCO6w.UdwtiuvpHHwjjWRGceSXOIXoAhfm',1,'2012-12-12','xxx','2012-12-12','xxx'),('a88384d96f054bbf899362a455070511','yakub','$2a$10$.QxxXWLxDCF1z9Qf4ud4juYtv2L.scVWpO5M28mnM.KMSpGgAKgYy',1,'2016-01-11','whoever',NULL,''),('a8b35b77e3a14c718480019afe091fe3','test1','xxxx',1,'2012-12-12','xxx','2012-12-12','xxx');

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

insert  into `user_role`(`user_id`,`role_id`) values ('2c947916e2de4ae79682de0ff6e91e91','6dc5acb6c869423fbdd014b979c04cef'),('a88384d96f054bbf899362a455070511','6dc5acb6c869423fbdd014b979c04cef');

/* Function  structure for function  `generate_unique_key` */

/*!50003 DROP FUNCTION IF EXISTS `generate_unique_key` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `generate_unique_key`() RETURNS varchar(20) CHARSET latin1
BEGIN
	
	declare result int;
	declare returnVal,xVal,finalVal varchar(20);
	SET result = (SELECT MAX(increment_key) increment_key FROM increment_key WHERE date_key=CURDATE())+1;
	set xVal=CONCAT('TR',DATE_FORMAT(NOW(),'%Y%m%d'));
	
	if isnull(result) then
	    set result=1;
	end if;
	
	IF result < 10 THEN
	  SET returnVal= CONCAT('0000',result);
	ELSEIF result < 100 THEN 
	  SET returnVal=CONCAT('000',result);
	ELSEIF result <1000 THEN 
	  SET returnVal=CONCAT('00',result);
	ELSEIF result <10000 THEN
	  SET returnVal=CONCAT('0',result);
	ELSE
	  SET returnVal=result;
	END IF;
	
	set finalVal=concat(xVal,concat('-',returnVal));
	
	return finalVal;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `ordering_goods` */

/*!50003 DROP PROCEDURE IF EXISTS  `ordering_goods` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `ordering_goods`(
    in order_id varchar(60),
    in customer_name varchar(40),
    in total_payment decimal(10,3),
    in transaction_datetime datetime,
    in login_user varchar(40),
    out message varchar(20)
)
BEGIN
	
	-- defining new variable
	declare val_order_id varchar(60);
	DECLARE val_transaction_id,val_customer_name,val_login_user VARCHAR(40);
	DECLARE val_total_payment DECIMAL(10,3);
	DECLARE val_transaction_datetime DATETIME;
	declare transaction_number varchar(120);
	
	declare exit handler for sqlexception
	begin
	   select 'failed' as info from dual into message;
	   -- rollback;
	end;
	
	DECLARE EXIT HANDLER FOR sqlwarning
	BEGIN
	   SELECT 'failed' AS info FROM DUAL INTO message;
	   -- ROLLBACK;
	END;
 
	-- assign params value to new variable
	set val_order_id = order_id;
	set val_customer_name = customer_name;
	set val_login_user = login_user;
	set val_total_payment = total_payment;
	set val_transaction_datetime = transaction_datetime;
	select generate_unique_key() into transaction_number;
	
	-- start transaction;
	        INSERT INTO increment_key(date_key,increment_key) VALUES(CURDATE(),NULL);
	        insert into order_transaction (order_id,transaction_number,customer_name,total_payment,transaction_datetime,login_user) 
		values(val_order_id,transaction_number,val_customer_name,val_login_user,val_total_payment,val_transaction_datetime);
		
		SELECT transaction_number;
	-- commit;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
