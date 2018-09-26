DROP TABLE IF EXISTS `factory_customer`;
CREATE TABLE `factory_customer` (
  `id` bigint(20) NOT NULL,
  `factory_id` bigint(20) NOT NULL,
  `clinic_id` bigint(20) NOT NULL,
  `salesman_id` bigint(20) NOT NULL,
  `is_valid` varchar(5) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
