
DROP TABLE IF EXISTS `procedure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedure` (
  `id` bigint(20) NOT NULL,
  `pg_id` bigint(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `specification` varchar(45) DEFAULT NULL,
  `operation_record` varchar(45) DEFAULT NULL,
  `comment` varchar(225) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;