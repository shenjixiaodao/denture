DROP TABLE IF EXISTS `factory_role`;
CREATE TABLE `factory_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factory_uid` bigint(20) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
