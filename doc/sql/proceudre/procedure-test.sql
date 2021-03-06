/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.22 : Database - test_job
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test_job` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `test_job`;

/* Procedure structure for procedure `getSum` */

/*!50003 DROP PROCEDURE IF EXISTS  `getSum` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `getSum`(in a int, in b int, out sum int)
BEGIN
	set sum = a + b;
	SELECT sum ;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
