DROP TABLE IF EXISTS `factory_user`;
CREATE TABLE `factory_user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(45) NOT NULL,
  `group_type` varchar(45) NOT NULL,
  `join_date` datetime DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contact_UNIQUE` (`contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;