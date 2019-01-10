/*
 Navicat MySQL Data Transfer

 Source Server         : denture
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 106.14.237.27:3306
 Source Schema         : liyou

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 23/12/2018 14:13:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applied_ingredient
-- ----------------------------
DROP TABLE IF EXISTS `applied_ingredient`;
CREATE TABLE `applied_ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `denture_id` varchar(45) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `applied_number` double DEFAULT NULL,
  `applicant_id` bigint(20) DEFAULT NULL,
  `applied_date` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `ingredient_id` bigint(20) DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applied_ingredient
-- ----------------------------
BEGIN;
INSERT INTO `applied_ingredient` VALUES (1, '000001201810182255161001', NULL, 2, 1, '2018-11-07 19:54:13', 'ads', 3, 1);
INSERT INTO `applied_ingredient` VALUES (2, '000001201810182255161001', NULL, 12, 1, '2018-11-18 20:13:08', 'aaa', 3, NULL);
COMMIT;

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `union_id` varchar(128) DEFAULT NULL COMMENT '微信联合ID，一个诊所只有一个服务微信号；所有诊所下的用户的union_id都会挂到这个诊所union_id下',
  `region` varchar(45) DEFAULT NULL COMMENT '区域',
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clinic
-- ----------------------------
BEGIN;
INSERT INTO `clinic` VALUES (1, '亮白', '深圳', '0975-123456', NULL, '广东/深圳/南山区', NULL);
INSERT INTO `clinic` VALUES (20, '', '', '', NULL, '', NULL);
INSERT INTO `clinic` VALUES (21, '李亮牙科', '于都县', '18179969065', NULL, '江西/赣州/于都县', NULL);
COMMIT;

-- ----------------------------
-- Table structure for clinic_user
-- ----------------------------
DROP TABLE IF EXISTS `clinic_user`;
CREATE TABLE `clinic_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clinic_id` bigint(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contact_UNIQUE` (`contact`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clinic_user
-- ----------------------------
BEGIN;
INSERT INTO `clinic_user` VALUES (2, 2, 'bb', '134', 'Dentist', NULL, NULL);
INSERT INTO `clinic_user` VALUES (3, 1, 'cc', '123', 'Dentist', NULL, NULL);
INSERT INTO `clinic_user` VALUES (4, 3, 'alley', '1112', 'Dentist', NULL, NULL);
INSERT INTO `clinic_user` VALUES (5, 1, '李友', '15280257501', 'Dentist', '7d271897b1303a27b9145a67d92b9e79', NULL);
INSERT INTO `clinic_user` VALUES (10, 9, 'yuzi_1031', '15280257503', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (11, 10, 'yuzi_1031', '15280257502', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (13, 12, 'yuzi_1031', '15280257505', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (15, 14, 'yuzi_1031', '15280257504', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (16, 15, 'yuzi_1031', '15280257507', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (17, 16, 'yuzi_1101', '15280257603', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (19, 18, 'yuzi_1101', '15280257605', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (20, 1, '211', '15280257509', 'Nurse', 'e10adc3949ba59abbe56e057f20f883e', NULL);
INSERT INTO `clinic_user` VALUES (21, 19, 'dddd', 'ddddd', 'Dentist', NULL, NULL);
INSERT INTO `clinic_user` VALUES (22, 20, '', '', 'Dentist', NULL, NULL);
INSERT INTO `clinic_user` VALUES (23, 21, '李亮', '18179969065', 'Dentist', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for delivery_info
-- ----------------------------
DROP TABLE IF EXISTS `delivery_info`;
CREATE TABLE `delivery_info` (
  `id` bigint(20) NOT NULL,
  `denture_id` varchar(25) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL COMMENT '物流公司',
  `delivery_id` varchar(45) DEFAULT NULL,
  `post_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delivery_info
-- ----------------------------
BEGIN;
INSERT INTO `delivery_info` VALUES (1, '123456', 'SF', '123456', '2018-09-18 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for denture
-- ----------------------------
DROP TABLE IF EXISTS `denture`;
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
  `field_typ` varchar(45) DEFAULT NULL COMMENT 'DaMaAn(大马鞍)\nPianCe(偏侧型)\nZiDan(子弹型)\nXiaoMaAn(小马鞍)\nWeiSheng(卫生型)\nShenRu(深入型)',
  `bite_level` varchar(45) DEFAULT NULL COMMENT 'YaoMi(咬密)\nQingYao(轻咬)\nBuYao(不咬)',
  `border_type` varchar(45) DEFAULT NULL COMMENT 'Plan(面接)\nPoint(点接)\nNormal(正常)',
  `neck_type` varchar(45) DEFAULT NULL COMMENT 'AnJianTai(按肩台)\nTop(龈上边缘)\nBelow(龈下边缘)',
  `inner_crown_type` varchar(45) DEFAULT NULL COMMENT 'Normal(正常)\nTight(紧)\nLoose(松)',
  `padding_type` varchar(45) DEFAULT NULL COMMENT 'PiDuiYao(批对咬)\nPiJiYa(批基牙)\nZuoYaoJin(做咬金)\nLouDianJin(露点金)\nUnknown(待问)',
  `outer_crown_type` varchar(45) DEFAULT NULL COMMENT 'SheCe(舌侧)\nJinTop(全金属边)\nThreeQuarter(松)\nSC-TQ(舌侧金属3/4颌侧)\nJinBelow(金属颌侧)\nAllCi(舌侧全瓷边)\n',
  `requirement` varchar(256) DEFAULT NULL,
  `base_price` decimal(12,2) DEFAULT NULL,
  `factory_price` decimal(12,2) DEFAULT NULL,
  `estimated_duration` double DEFAULT NULL COMMENT '预计完成时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of denture
-- ----------------------------
BEGIN;
INSERT INTO `denture` VALUES ('000001201810182255161001', 1, 'Fixed', 'GuGe', 1, 1, 'b2', 'a-2', '2018-10-18 22:55:16', 1, '2018-11-19 19:07:34', '2018-11-19 19:07:34', NULL, 'Accept', '2018-11-19 19:07:34', 'Accept', '2018-11-19 19:07:34', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10.00, 10.00, NULL);
INSERT INTO `denture` VALUES ('000001201810261548181001', 1, 'Fixed', 'GuGe', 1, 1, 'b2', 'a-1', '2018-10-26 15:48:19', 1, '2018-11-19 19:29:02', '2018-11-19 19:29:02', NULL, 'Accept', '2018-11-19 19:29:02', 'Accept', '2018-11-19 19:29:02', 'qq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10.00, 10.00, NULL);
INSERT INTO `denture` VALUES ('000001201811042244191001', 1, 'Fixed', 'GuGe', 1, 1, 'a-1', 'a3,c4', '2018-11-04 22:44:20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dd', 'WeiSheng', NULL, 'Point', NULL, NULL, 'LouDianJin', NULL, NULL, 10.00, 10.00, NULL);
INSERT INTO `denture` VALUES ('123456', 1, 'Fixed', 'GuGe', 1, 1, 'm2', 'a-2', '2018-09-18 00:00:00', 1, '2018-09-21 09:30:33', '2018-09-21 09:30:33', NULL, 'Accept', '2018-09-21 09:30:33', 'Accept', '2018-09-21 09:30:33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10.00, 10.00, NULL);
INSERT INTO `denture` VALUES ('123457', 1, 'Fixed', 'GuGe', 1, 1, 'm2', 'a-2', '2018-09-19 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10.00, 10.00, NULL);
COMMIT;

-- ----------------------------
-- Table structure for denture_order
-- ----------------------------
DROP TABLE IF EXISTS `denture_order`;
CREATE TABLE `denture_order` (
  `id` varchar(25) NOT NULL,
  `clinic_id` bigint(20) DEFAULT NULL,
  `denture_id` varchar(25) DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `pay_amount` decimal(12,2) DEFAULT '0.00',
  `model_delivery_id` bigint(20) DEFAULT NULL,
  `denture_delivery_id` bigint(20) DEFAULT NULL,
  `received_date` datetime DEFAULT NULL,
  `Installed_date` datetime DEFAULT NULL,
  `dentist_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `recorder_id` bigint(20) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of denture_order
-- ----------------------------
BEGIN;
INSERT INTO `denture_order` VALUES ('000001201810182255161002', 1, '000001201810182255161001', 1, '2018-10-18 22:55:17', 'Rejected', 0.00, NULL, NULL, NULL, NULL, 5, NULL, NULL, '');
INSERT INTO `denture_order` VALUES ('000001201810261548181002', 1, '000001201810261548181001', 1, '2018-10-26 15:48:19', 'Created', 0.00, NULL, NULL, NULL, NULL, 5, NULL, NULL, 'qq');
INSERT INTO `denture_order` VALUES ('000001201811042244191002', 1, '000001201811042244191001', 1, '2018-11-04 22:44:20', 'Created', 0.00, NULL, NULL, NULL, NULL, 5, NULL, NULL, 'dd');
INSERT INTO `denture_order` VALUES ('152802575036', 1, '123457', 1, '2018-09-18 00:00:00', 'Created', 0.00, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL);
INSERT INTO `denture_order` VALUES ('1536676377771', 1, '123456', 1, '2018-09-18 00:00:00', 'Accepted', 0.00, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for factory
-- ----------------------------
DROP TABLE IF EXISTS `factory`;
CREATE TABLE `factory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory
-- ----------------------------
BEGIN;
INSERT INTO `factory` VALUES (1, '嗨咯', '123', '贝海');
INSERT INTO `factory` VALUES (2, '沃卡', '123', 'd\'d\'d');
COMMIT;

-- ----------------------------
-- Table structure for factory_customer
-- ----------------------------
DROP TABLE IF EXISTS `factory_customer`;
CREATE TABLE `factory_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factory_id` bigint(20) NOT NULL,
  `clinic_id` bigint(20) NOT NULL,
  `salesman_id` bigint(20) NOT NULL,
  `is_valid` tinyint(4) DEFAULT NULL COMMENT '0:发起请求\\n1:  同意\\n-1：拒绝',
  `created_date` datetime DEFAULT NULL,
  `fixed_guarantee` int(11) DEFAULT '3' COMMENT '固定类保修期/年',
  `mobilizable_guarantee` int(11) DEFAULT '1' COMMENT '活动类保修期/年',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory_customer
-- ----------------------------
BEGIN;
INSERT INTO `factory_customer` VALUES (1, 1, 1, 1, 1, '2018-09-30 14:18:38', 3, 1);
INSERT INTO `factory_customer` VALUES (6, 1, 20, 1, 0, '2018-11-25 21:37:02', NULL, NULL);
INSERT INTO `factory_customer` VALUES (7, 1, 21, 1, 0, '2018-11-25 21:39:08', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for factory_role
-- ----------------------------
DROP TABLE IF EXISTS `factory_role`;
CREATE TABLE `factory_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factory_uid` bigint(20) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory_role
-- ----------------------------
BEGIN;
INSERT INTO `factory_role` VALUES (2, 4, 'CheJin');
INSERT INTO `factory_role` VALUES (3, 10, 'ShiGaoLeader');
INSERT INTO `factory_role` VALUES (80, 1, 'admin');
INSERT INTO `factory_role` VALUES (81, 1, 'ShiGaoLeader');
INSERT INTO `factory_role` VALUES (82, 1, 'ShiGao');
INSERT INTO `factory_role` VALUES (83, 1, 'Comprehensive');
INSERT INTO `factory_role` VALUES (84, 1, 'Market');
INSERT INTO `factory_role` VALUES (85, 1, 'CheCiLeader');
INSERT INTO `factory_role` VALUES (86, 1, 'Management');
INSERT INTO `factory_role` VALUES (87, 1, 'ShangCi');
INSERT INTO `factory_role` VALUES (88, 1, 'ChongjiaoLeader');
INSERT INTO `factory_role` VALUES (89, 1, 'ShangCiLeader');
COMMIT;

-- ----------------------------
-- Table structure for factory_user
-- ----------------------------
DROP TABLE IF EXISTS `factory_user`;
CREATE TABLE `factory_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(45) NOT NULL,
  `group_type` varchar(45) NOT NULL,
  `join_date` datetime DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `marital` tinyint(4) DEFAULT NULL,
  `card_id` varchar(45) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `educational` varchar(45) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contact_UNIQUE` (`contact`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory_user
-- ----------------------------
BEGIN;
INSERT INTO `factory_user` VALUES (1, '李友', '15280257503', 'Comprehensive', NULL, 1, '7d271897b1303a27b9145a67d92b9e79', NULL, NULL, 0, NULL, NULL, NULL, 'CTO');
INSERT INTO `factory_user` VALUES (4, '张建林', '15362982349', 'CheJin', '2018-01-09 00:10:00', 1, '9c1f78320e4a42764a00f40a0e84f422', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `factory_user` VALUES (10, 'dds', '15280257501', 'ShiGao', '2018-01-05 00:11:00', 1, '83f60a74aa6445fcf00c292cb65982fa', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for hj_customer
-- ----------------------------
DROP TABLE IF EXISTS `hj_customer`;
CREATE TABLE `hj_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `contact_position` varchar(45) DEFAULT NULL,
  `contact_phone` varchar(45) DEFAULT NULL,
  `contact_is_shareholder` varchar(45) DEFAULT NULL COMMENT '联系人是否是公司股东',
  `contact_other` varchar(45) DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `address` varchar(125) NOT NULL,
  `source` varchar(45) DEFAULT NULL COMMENT '客户来源',
  `type` varchar(45) DEFAULT NULL COMMENT '客户类型',
  `state_owned` varchar(45) DEFAULT NULL COMMENT '是否国企',
  `scale` varchar(45) DEFAULT NULL COMMENT '电炉规模',
  `furnace_number` varchar(45) DEFAULT NULL COMMENT '炉子数',
  `start_date` varchar(45) DEFAULT NULL COMMENT '合作建立开始时间',
  `customer_manager` varchar(45) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `product_type` varchar(45) DEFAULT NULL,
  `comment` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hj_customer
-- ----------------------------
BEGIN;
INSERT INTO `hj_customer` VALUES (1, '福建冶金', '110', '张三丰', '总工程师', '18900000000', '是', NULL, '东南', '福建三明', NULL, NULL, '否', '100吨', '5台', '2015', 'Y.Lee', '2018-11-21 20:20:29', '2018-11-21 20:20:29', '工业硅', '有钱，壕');
COMMIT;

-- ----------------------------
-- Table structure for ingredient
-- ----------------------------
DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL COMMENT '型号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_name` (`name`,`factory_id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ingredient
-- ----------------------------
BEGIN;
INSERT INTO `ingredient` VALUES (3, '瓷片', 109, 1, 'a1');
INSERT INTO `ingredient` VALUES (4, 'test', 0, 1, 'e1');
INSERT INTO `ingredient` VALUES (6, '胶套', 0, 1, 'e2');
COMMIT;

-- ----------------------------
-- Table structure for ingredient_purchase_record
-- ----------------------------
DROP TABLE IF EXISTS `ingredient_purchase_record`;
CREATE TABLE `ingredient_purchase_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ingredient_id` bigint(20) DEFAULT NULL,
  `number` double DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `bill_no` varchar(64) DEFAULT NULL,
  `price` decimal(12,2) DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ingredient_purchase_record
-- ----------------------------
BEGIN;
INSERT INTO `ingredient_purchase_record` VALUES (1, 3, 123, '2018-11-05 19:26:19', '2018-11-05 19:26:19', 1, '123456', 12.00, NULL);
INSERT INTO `ingredient_purchase_record` VALUES (2, 3, 22, '2018-11-05 19:28:36', '2018-11-05 19:28:36', 1, '12345', 11.00, NULL);
COMMIT;

-- ----------------------------
-- Table structure for inspect_item
-- ----------------------------
DROP TABLE IF EXISTS `inspect_item`;
CREATE TABLE `inspect_item` (
  `id` bigint(20) NOT NULL,
  `report_id` bigint(20) DEFAULT NULL,
  `item` varchar(45) DEFAULT NULL,
  `term` varchar(45) DEFAULT NULL,
  `requirement` varchar(45) DEFAULT NULL,
  `result` varchar(45) DEFAULT NULL,
  `conclusion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for inspect_report
-- ----------------------------
DROP TABLE IF EXISTS `inspect_report`;
CREATE TABLE `inspect_report` (
  `id` bigint(20) NOT NULL,
  `denture_id` varchar(45) DEFAULT NULL,
  `sampled_date` datetime DEFAULT NULL,
  `inspect_date` datetime DEFAULT NULL,
  `inspector_id` bigint(20) DEFAULT NULL COMMENT '检查员id',
  `double_check_id` bigint(20) DEFAULT NULL COMMENT '审核员id',
  `approved_id` bigint(20) DEFAULT NULL COMMENT '批准人员',
  `conclusion` varchar(45) DEFAULT NULL,
  `comment` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operation_item
-- ----------------------------
DROP TABLE IF EXISTS `operation_item`;
CREATE TABLE `operation_item` (
  `id` bigint(20) NOT NULL,
  `pack_id` bigint(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `operation_record` varchar(45) DEFAULT NULL,
  `operation_content` varchar(45) DEFAULT NULL,
  `operation_result` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pack_and_clr
-- ----------------------------
DROP TABLE IF EXISTS `pack_and_clr`;
CREATE TABLE `pack_and_clr` (
  `id` bigint(20) NOT NULL,
  `denture_id` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `inspector_id` bigint(20) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pack_clr_ingredient
-- ----------------------------
DROP TABLE IF EXISTS `pack_clr_ingredient`;
CREATE TABLE `pack_clr_ingredient` (
  `id` bigint(20) NOT NULL,
  `pac_id` bigint(20) DEFAULT NULL,
  `ingredient_id` bigint(20) DEFAULT NULL,
  `used_number` double DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `clinic_id` bigint(20) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for procedure
-- ----------------------------
DROP TABLE IF EXISTS `procedure`;
CREATE TABLE `procedure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pg_id` bigint(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `specification` varchar(45) DEFAULT NULL,
  `operation_record` varchar(45) DEFAULT NULL,
  `comment` varchar(225) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `completed_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of procedure
-- ----------------------------
BEGIN;
INSERT INTO `procedure` VALUES (1, 1, '上瓷', NULL, NULL, NULL, 1, '2018-09-10 00:00:00', '2018-09-10 00:00:00');
INSERT INTO `procedure` VALUES (2, 1, '充胶', NULL, NULL, NULL, 2, '2018-09-10 00:00:00', '2018-09-10 00:00:00');
INSERT INTO `procedure` VALUES (8, 1, '上瓷', NULL, NULL, NULL, 1, '2018-10-14 21:30:01', '2018-10-14 21:30:01');
COMMIT;

-- ----------------------------
-- Table structure for procedure_group
-- ----------------------------
DROP TABLE IF EXISTS `procedure_group`;
CREATE TABLE `procedure_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `denture_id` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `inspection_result` varchar(45) DEFAULT NULL,
  `inspection_content` varchar(225) DEFAULT NULL,
  `inspector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of procedure_group
-- ----------------------------
BEGIN;
INSERT INTO `procedure_group` VALUES (1, 'CheCi', '123456', '2018-09-10 00:00:00', NULL, 2, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (2, 'LaXing', '123456', '2018-09-10 00:00:00', NULL, 3, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (3, 'ShiGao', '000001201810182255161001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (4, 'LaXing', '000001201810182255161001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (5, 'CheJin', '000001201810182255161001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (6, 'ChongJiao', '000001201810182255161001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (7, 'ShangCi', '000001201810182255161001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (8, 'CheCi', '000001201810182255161001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (13, 'ShiGao', '000001201810261531501001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (14, 'LaXing', '000001201810261531501001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (15, 'CheJin', '000001201810261531501001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (16, 'ChongJiao', '000001201810261531501001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (17, 'ShangCi', '000001201810261531501001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (18, 'CheCi', '000001201810261531501001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (23, 'ShiGao', '000001201810261532551001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (24, 'LaXing', '000001201810261532551001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (25, 'CheJin', '000001201810261532551001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (26, 'ChongJiao', '000001201810261532551001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (27, 'ShangCi', '000001201810261532551001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (28, 'CheCi', '000001201810261532551001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (33, 'ShiGao', '000001201810261547001001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (34, 'LaXing', '000001201810261547001001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (35, 'CheJin', '000001201810261547001001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (36, 'ChongJiao', '000001201810261547001001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (37, 'ShangCi', '000001201810261547001001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (38, 'CheCi', '000001201810261547001001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (43, 'ShiGao', '000001201810261548181001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (44, 'LaXing', '000001201810261548181001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (45, 'CheJin', '000001201810261548181001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (46, 'ChongJiao', '000001201810261548181001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (47, 'ShangCi', '000001201810261548181001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (48, 'CheCi', '000001201810261548181001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (53, 'ShiGao', '000001201811042244191001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (54, 'LaXing', '000001201811042244191001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (55, 'CheJin', '000001201811042244191001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (56, 'ChongJiao', '000001201811042244191001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (57, 'ShangCi', '000001201811042244191001', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `procedure_group` VALUES (58, 'CheCi', '000001201811042244191001', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for release_record
-- ----------------------------
DROP TABLE IF EXISTS `release_record`;
CREATE TABLE `release_record` (
  `id` bigint(20) NOT NULL,
  `denture_id` varchar(45) DEFAULT NULL,
  `review` varchar(45) DEFAULT NULL,
  `reviewer_id` bigint(20) DEFAULT NULL,
  `review_date` datetime DEFAULT NULL,
  `double_review` varchar(45) DEFAULT NULL,
  `double_reviewer_id` bigint(20) DEFAULT NULL,
  `double_review_date` datetime DEFAULT NULL,
  `comment` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contacter` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
BEGIN;
INSERT INTO `supplier` VALUES (1, '化机厂', '瑞金市', '110', 1, '15288888888', 'Y.Lee');
COMMIT;

-- ----------------------------
-- Table structure for total_ingredient_statistic
-- ----------------------------
DROP TABLE IF EXISTS `total_ingredient_statistic`;
CREATE TABLE `total_ingredient_statistic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factory_id` bigint(20) NOT NULL,
  `year` decimal(22,2) DEFAULT NULL,
  `month` decimal(22,2) DEFAULT NULL,
  `day` decimal(12,0) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date_UNIQUE` (`date`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of total_ingredient_statistic
-- ----------------------------
BEGIN;
INSERT INTO `total_ingredient_statistic` VALUES (1, 1, 14.90, 14.00, NULL, '2018-11-18');
COMMIT;

-- ----------------------------
-- Table structure for used_ingredient
-- ----------------------------
DROP TABLE IF EXISTS `used_ingredient`;
CREATE TABLE `used_ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '使用记录ID就是领用记录ID',
  `pg_id` bigint(20) DEFAULT NULL COMMENT '工序组ID',
  `ingredient_id` bigint(20) DEFAULT NULL,
  `used_number` double DEFAULT NULL,
  `comment` varchar(225) DEFAULT NULL,
  `factory_id` bigint(20) DEFAULT NULL,
  `equipment` varchar(128) DEFAULT NULL,
  `denture_id` varchar(45) DEFAULT NULL,
  `used_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of used_ingredient
-- ----------------------------
BEGIN;
INSERT INTO `used_ingredient` VALUES (3, 3, 3, 2, 'ss', 1, 'ss', '000001201810182255161001', '2018-12-10 10:33:24');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
