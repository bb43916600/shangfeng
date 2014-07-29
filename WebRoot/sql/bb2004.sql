/*
 Navicat MySQL Data Transfer

 Source Server         : bb2004
 Source Server Version : 50529
 Source Host           : localhost
 Source Database       : bb2004

 Target Server Version : 50529
 File Encoding         : utf-8

 Date: 02/27/2013 08:21:22 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `WAP_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `WAP_MENU`;
CREATE TABLE `WAP_MENU` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEQENCING` int(11) DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MENU_KIND_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49DDC338A718A390` (`MENU_KIND_ID`),
  CONSTRAINT `FK49DDC338A718A390` FOREIGN KEY (`MENU_KIND_ID`) REFERENCES `WAP_MENUKIND` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1155 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `WAP_MENU`
-- ----------------------------
BEGIN;
INSERT INTO `WAP_MENU` VALUES ('400', '添加角色', '0', '/authority/role-input.action', '301'), ('401', '查询角色', '1', '/authority/role-list.action', '301'), ('402', '添加菜单', '0', '/authority/menuinput.action', '302'), ('403', '查询菜单', '1', '/authority/menu-list.action', '302'), ('404', '添加权限', '0', '/authority/operation-input.action', '303'), ('405', '查询权限', '1', '/authority/operation-list.action', '303'), ('406', '添加栏目', '0', '/authority/menukindInput.action', '304'), ('407', '查询栏目', '1', '/authority/menukind-list.action', '304'), ('500', '上传视频', '0', '/video-input.action', '451'), ('501', '查询视频', '1', '/video-list.action', '451'), ('502', '新建分类', '0', '/kind-input.action?kingType=0', '452'), ('503', '分类查询', '1', '/kind-list.action?kingType=0', '452'), ('550', '新建客户', '0', '/crm/client-input.action', '454'), ('551', '查询客户', '1', '/crm/clientList.action', '454'), ('552', '新建产品', '0', '/crm/product-input.action', '455'), ('553', '查询产品', '1', '/crm/productList.action', '455'), ('1153', '新建送货单', '0', '/crm/inputOrder.action', '1101'), ('1154', '送货单查询', '1', '/admin/crm/orderMain-list.action', '1101');
COMMIT;

-- ----------------------------
--  Table structure for `WAP_MENUKIND`
-- ----------------------------
DROP TABLE IF EXISTS `WAP_MENUKIND`;
CREATE TABLE `WAP_MENUKIND` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEQENCING` int(11) DEFAULT NULL,
  `MENU_KIND_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD27C9ACCA718A390` (`MENU_KIND_ID`),
  CONSTRAINT `FKD27C9ACCA718A390` FOREIGN KEY (`MENU_KIND_ID`) REFERENCES `WAP_MENUKIND` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1102 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `WAP_MENUKIND`
-- ----------------------------
BEGIN;
INSERT INTO `WAP_MENUKIND` VALUES ('300', '权限管理', '3', null), ('301', '角色管理', '0', '300'), ('302', '菜单管理', '1', '300'), ('303', '权限管理', '2', '300'), ('304', '栏目管理', '3', '300'), ('450', '视频信息管理', '1', null), ('451', '视频管理', '0', '450'), ('452', '视频分类', '1', '450'), ('453', '华老专用模块', '2', null), ('454', '客户管理', '0', '453'), ('455', '产品管理', '1', '453'), ('1101', '送货单管理', '2', '453');
COMMIT;

-- ----------------------------
--  Table structure for `WAP_OPERATION`
-- ----------------------------
DROP TABLE IF EXISTS `WAP_OPERATION`;
CREATE TABLE `WAP_OPERATION` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `WAP_OPERATION`
-- ----------------------------
BEGIN;
INSERT INTO `WAP_OPERATION` VALUES ('350', '添加', null), ('351', '编辑', null), ('352', '删除', null);
COMMIT;

-- ----------------------------
--  Table structure for `WAP_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `WAP_ROLE`;
CREATE TABLE `WAP_ROLE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `WAP_ROLE`
-- ----------------------------
BEGIN;
INSERT INTO `WAP_ROLE` VALUES ('1', '管理员');
COMMIT;

-- ----------------------------
--  Table structure for `WAP_USER`
-- ----------------------------
DROP TABLE IF EXISTS `WAP_USER`;
CREATE TABLE `WAP_USER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userBlogs` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userEmail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userImg` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userInsertTime` datetime DEFAULT NULL,
  `userLastTime` datetime DEFAULT NULL,
  `userMSN` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userNickname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userPassWord` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userQQ` bigint(20) DEFAULT NULL,
  `userStatus` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `WAP_USER`
-- ----------------------------
BEGIN;
INSERT INTO `WAP_USER` VALUES ('1', 'admin', null, null, null, null, null, null, null, '39c89be1aa05efa36653d3a68bc1b99f', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bb_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bb_createtime` datetime DEFAULT NULL,
  `bb_img` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bb_keywords` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bb_state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bb_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bb_updatetime` datetime DEFAULT NULL,
  `bb_verifystate` int(11) NOT NULL,
  `bb_verifytime` datetime DEFAULT NULL,
  `bb_viewNumber` int(11) NOT NULL,
  `booleanComment` int(11) NOT NULL,
  `booleanOpen` int(11) NOT NULL,
  `articleType_id` bigint(20) DEFAULT NULL,
  `verify_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD458CCF66168CECB` (`verify_user_id`),
  KEY `FKD458CCF6B6682891` (`user_id`),
  KEY `FKD458CCF6BE559011` (`articleType_id`),
  CONSTRAINT `FKD458CCF66168CECB` FOREIGN KEY (`verify_user_id`) REFERENCES `WAP_USER` (`id`),
  CONSTRAINT `FKD458CCF6B6682891` FOREIGN KEY (`user_id`) REFERENCES `WAP_USER` (`id`),
  CONSTRAINT `FKD458CCF6BE559011` FOREIGN KEY (`articleType_id`) REFERENCES `article_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `article_type`
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ty_Name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE88024E3B6682891` (`user_id`),
  CONSTRAINT `FKE88024E3B6682891` FOREIGN KEY (`user_id`) REFERENCES `WAP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientAddress` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientCharacter` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientCity` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientComment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientCreatetime` datetime DEFAULT NULL,
  `clientEmail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientFax` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientFreeze` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientLinkman` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientMobilephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientProvince` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientQQ` bigint(20) DEFAULT NULL,
  `clientTelephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientType` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clientUpdatetime` datetime DEFAULT NULL,
  `clientZipcode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `client`
-- ----------------------------
BEGIN;
INSERT INTO `client` VALUES ('1', null, null, null, '?????????????', null, null, null, null, '???', null, '???', null, null, null, null, null, null), ('2', null, null, null, '?????????????', null, null, null, null, 'fffff', null, 'aaaaa', null, null, null, null, null, null), ('3', null, null, null, '?????????????', null, null, null, null, 'mck', null, 'aaaa', null, null, null, null, null, null), ('4', null, null, null, '?????????????', null, null, null, null, '11111', null, '1', null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cm_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cm_email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cm_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cm_oppose` int(11) NOT NULL,
  `cm_support` int(11) NOT NULL,
  `cm_time` datetime DEFAULT NULL,
  `cm_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bbcomment_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38A5EE5F9046F1B1` (`article_id`),
  KEY `FK38A5EE5FB6682891` (`user_id`),
  KEY `FK38A5EE5F11E4C951` (`bbcomment_id`),
  KEY `FK38A5EE5F20570D11` (`comment_id`),
  CONSTRAINT `FK38A5EE5F11E4C951` FOREIGN KEY (`bbcomment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FK38A5EE5F20570D11` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FK38A5EE5F9046F1B1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `FK38A5EE5FB6682891` FOREIGN KEY (`user_id`) REFERENCES `WAP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_methodname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderAlreadyDelivery` bigint(20) DEFAULT NULL,
  `orderAlwaysmoney` float NOT NULL,
  `orderClientDiscount` float NOT NULL,
  `orderDissatisfy` bigint(20) DEFAULT NULL,
  `orderNotDelivery` int(11) NOT NULL,
  `orderProductDesc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderProductId` bigint(20) DEFAULT NULL,
  `orderProductName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderProductNumber` int(11) NOT NULL,
  `orderProductRetailPrice` float NOT NULL,
  `orderProductTradePrice` float NOT NULL,
  `orderProductUnit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderProductYardage` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderSatisfy` bigint(20) DEFAULT NULL,
  `orderTotal` float NOT NULL,
  `orderMain_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23AE5A62FBAA6BFE` (`orderMain_id`),
  CONSTRAINT `FK23AE5A62FBAA6BFE` FOREIGN KEY (`orderMain_id`) REFERENCES `order_main` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `order_detail`
-- ----------------------------
BEGIN;
INSERT INTO `order_detail` VALUES ('1', null, '100', '0', null, '0', null, null, '????', '20', '0', '5', '?', null, null, '0', null), ('2', null, '300', '0', null, '0', null, null, '????1', '50', '0', '6', '?', null, null, '0', null), ('3', null, '23', '0', null, '0', null, null, '????12', '23', '0', '1', '?', null, null, '0', null), ('4', null, '100', '0', null, '0', null, null, '1111', '20', '0', '5', '?', null, null, '0', null), ('5', null, '300', '0', null, '0', null, null, '22222', '50', '0', '6', '?', null, null, '0', null), ('6', null, '23', '0', null, '0', null, null, '33333', '23', '0', '1', '?', null, null, '0', null), ('7', null, '100', '0', null, '0', null, null, '1111', '20', '0', '5', '?', null, null, '0', null), ('8', null, '23', '0', null, '0', null, null, '33333', '23', '0', '1', '?', null, null, '0', null), ('9', null, '100', '0', null, '0', '111111', null, '1111', '20', '0', '5', '?', null, null, '0', null), ('10', null, '23', '0', null, '0', '33333', null, '33333', '23', '0', '1', '?', null, null, '0', null), ('11', null, '72', '0', null, '0', '222222', null, '22222', '24', '0', '3', '22', null, null, '0', null), ('15', null, '99', '0', null, '0', 'b', null, 'bbbb', '33', '0', '3', 'bb', 'bbb', null, '0', null), ('16', null, '44', '0', null, '0', 'c', null, 'cccc', '22', '0', '2', 'cc', 'ccc', null, '0', null), ('17', null, '11', '0', null, '0', 'd', null, 'dddd', '11', '0', '1', 'dd', 'ddd', null, '0', null), ('18', null, '99', '0', null, '0', 'b', null, 'bbbb', '33', '0', '3', 'bb', 'bbb', null, '0', null), ('19', null, '11', '0', null, '0', 'd', null, 'dddd', '11', '0', '1', 'dd', 'ddd', null, '0', null), ('23', null, '539', '0', null, '0', 'b', null, 'bbbbb', '77', '0', '7', 'bb', 'bbb', null, '0', '3'), ('24', null, '396', '0', null, '0', 'c', null, 'cccc', '66', '0', '6', 'cc', 'ccc', null, '0', '3'), ('25', null, '275', '0', null, '0', 'd', null, 'dddd', '55', '0', '5', 'dd', 'ddd', null, '0', '3'), ('26', null, '176', '0', null, '0', 'e', null, 'eeee', '44', '0', '4', 'ee', 'eee', null, '0', '3'), ('27', null, '99', '0', null, '0', 'f', null, 'ffff', '33', '0', '3', 'ff', 'fff', null, '0', '3'), ('28', null, '539', '0', null, '0', 'b', null, 'bbbbb', '77', '0', '7', 'bb', 'bbb', null, '0', '4'), ('29', null, '396', '0', null, '0', 'c', null, 'cccc', '66', '0', '6', 'cc', 'ccc', null, '0', '4'), ('30', null, '275', '0', null, '0', 'd', null, 'dddd', '55', '0', '5', 'dd', 'ddd', null, '0', '4'), ('31', null, '176', '0', null, '0', 'e', null, 'eeee', '44', '0', '4', 'ee', 'eee', null, '0', '4'), ('32', null, '99', '0', null, '0', 'f', null, 'ffff', '33', '0', '3', 'ff', 'fff', null, '0', '4'), ('33', null, '539', '0', null, '0', 'b', null, 'bbbbb', '77', '0', '7', 'bb', 'bbb', null, '0', '5'), ('34', null, '396', '0', null, '0', 'c', null, 'cccc', '66', '0', '6', 'cc', 'ccc', null, '0', '5'), ('35', null, '275', '0', null, '0', 'd', null, 'dddd', '55', '0', '5', 'dd', 'ddd', null, '0', '5'), ('36', null, '176', '0', null, '0', 'e', null, 'eeee', '44', '0', '4', 'ee', 'eee', null, '0', '5'), ('37', null, '99', '0', null, '0', 'f', null, 'ffff', '33', '0', '3', 'ff', 'fff', null, '0', '5'), ('38', null, '11', '0', null, '0', '', null, '11111', '1', '0', '11', '1', '1111', null, '0', null), ('39', null, '11', '0', null, '0', '', null, '11111', '1', '0', '11', '1', '1111', null, '0', '6');
COMMIT;

-- ----------------------------
--  Table structure for `order_main`
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderAddress` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderAlwaysmoney` float NOT NULL,
  `orderCreateClientId` bigint(20) DEFAULT NULL,
  `orderCreateClientName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderCreatemanId` bigint(20) DEFAULT NULL,
  `orderCreatemanName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderCreatetime` datetime DEFAULT NULL,
  `orderEmail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderLinkman` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderLogisticsname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderMobilephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderPaymentReceived` float NOT NULL,
  `orderPaymentUncollected` float NOT NULL,
  `orderSn` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderStatus` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderTelephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderToClientId` bigint(20) DEFAULT NULL,
  `orderToClientName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderTotalMoney` float NOT NULL,
  `orderType` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderUpdatetime` datetime DEFAULT NULL,
  `orderVerify` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderVerifymanId` bigint(20) DEFAULT NULL,
  `orderVerifymanName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderVerifytime` datetime DEFAULT NULL,
  `orderZipcode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `order_main`
-- ----------------------------
BEGIN;
INSERT INTO `order_main` VALUES ('3', null, '1485', null, 'kfc', null, null, '2013-02-27 08:08:38', null, 'mck', null, null, '0', '0', '?0000000', null, null, null, 'aaaa', '0', null, null, null, null, null, '2013-02-27 00:00:00', null), ('4', null, '1485', null, 'kfc', null, null, '2013-02-27 08:08:42', null, 'mck', null, null, '0', '0', '?0000000', null, null, null, 'aaaa', '0', null, null, null, null, null, '2013-02-27 00:00:00', null), ('5', null, '1485', null, 'kfc', null, null, '2013-02-27 08:11:20', null, 'mck', null, null, '0', '0', '?0000000', null, null, null, 'aaaa', '0', null, null, null, null, null, '2013-02-27 00:00:00', null), ('6', null, '11', null, '1111', null, null, '2013-02-27 08:18:39', null, '11111', null, null, '0', '0', '?0000005', null, null, null, '1', '0', null, null, null, null, null, '2013-02-27 00:00:00', null);
COMMIT;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productCode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productColor` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productCreateman` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productCreatetime` datetime DEFAULT NULL,
  `productDesc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productLevel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productPackageNumber` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productPattern` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productPic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productRetailPrice` float NOT NULL,
  `productStatus` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productUnit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productUpdatetime` datetime DEFAULT NULL,
  `productYardage` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `wap_menu_operation`
-- ----------------------------
DROP TABLE IF EXISTS `wap_menu_operation`;
CREATE TABLE `wap_menu_operation` (
  `menu_id` bigint(20) NOT NULL,
  `operation_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`menu_id`,`operation_id`),
  KEY `FKA2C1B500F7CED011` (`menu_id`),
  KEY `FKA2C1B500CFD57003` (`operation_id`),
  CONSTRAINT `FKA2C1B500CFD57003` FOREIGN KEY (`operation_id`) REFERENCES `WAP_OPERATION` (`id`),
  CONSTRAINT `FKA2C1B500F7CED011` FOREIGN KEY (`menu_id`) REFERENCES `WAP_MENU` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `wap_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wap_role_menu`;
CREATE TABLE `wap_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKA88B056FF7CED011` (`menu_id`),
  KEY `FKA88B056F113D64B1` (`role_id`),
  CONSTRAINT `FKA88B056F113D64B1` FOREIGN KEY (`role_id`) REFERENCES `WAP_ROLE` (`id`),
  CONSTRAINT `FKA88B056FF7CED011` FOREIGN KEY (`menu_id`) REFERENCES `WAP_MENU` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `wap_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `wap_role_menu` VALUES ('1', '400'), ('1', '401'), ('1', '402'), ('1', '403'), ('1', '404'), ('1', '405'), ('1', '406'), ('1', '407'), ('1', '500'), ('1', '501'), ('1', '502'), ('1', '503'), ('1', '550'), ('1', '551'), ('1', '552'), ('1', '553'), ('1', '1153'), ('1', '1154');
COMMIT;

-- ----------------------------
--  Table structure for `wap_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `wap_user_role`;
CREATE TABLE `wap_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKA8C2D251113D64B1` (`role_id`),
  KEY `FKA8C2D251B6682891` (`user_id`),
  CONSTRAINT `FKA8C2D251113D64B1` FOREIGN KEY (`role_id`) REFERENCES `WAP_ROLE` (`id`),
  CONSTRAINT `FKA8C2D251B6682891` FOREIGN KEY (`user_id`) REFERENCES `WAP_USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Records of `wap_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `wap_user_role` VALUES ('1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
