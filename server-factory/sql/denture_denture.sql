-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: denture
-- ------------------------------------------------------
-- Server version	5.6.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `denture`
--

DROP TABLE IF EXISTS `denture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denture` (
  `id` varchar(25) NOT NULL,
  `clinic_id` bigint(20) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `specification` varchar(45) DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `color_no` varchar(45) DEFAULT NULL,
  `positions` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `model_inspector_id` bigint(20) DEFAULT NULL,
  `model_inspection_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL COMMENT '生产开始日期',
  `end_date` datetime DEFAULT NULL,
  `pro_review` varchar(45) DEFAULT NULL COMMENT '生产部负责人审核结果',
  `pro_review_date` datetime DEFAULT NULL,
  `qua_review` varchar(45) DEFAULT NULL COMMENT '质量部负责人审核结果',
  `qua_review_date` datetime DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL COMMENT '医生备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denture`
--

LOCK TABLES `denture` WRITE;
/*!40000 ALTER TABLE `denture` DISABLE KEYS */;
/*!40000 ALTER TABLE `denture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-11 23:52:24
