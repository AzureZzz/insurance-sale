/*
Navicat MySQL Data Transfer

Source Server         : iNotes
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : sale_db

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-04-10 17:28:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `HEAD_IMG` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '');

-- ----------------------------
-- Table structure for clerk
-- ----------------------------
DROP TABLE IF EXISTS `clerk`;
CREATE TABLE `clerk` (
  `CLERK_ID` int(11) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `HEAD_IMG` varchar(100) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `SEX` enum('男','女','未知') DEFAULT NULL,
  `MOBILE` varchar(11) DEFAULT NULL,
  `NOTE` text,
  `CLERK_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CLERK_ID`),
  KEY `CLERK_ID` (`CLERK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clerk
-- ----------------------------
INSERT INTO `clerk` VALUES ('111364', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '女', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111366', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '女', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111367', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111368', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '女', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111369', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111370', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '男', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111371', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '女', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111372', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', '18589845671', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111373', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '男', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111374', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '男', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111375', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '男', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111376', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '女', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('111377', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('123456', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '男', '18589831214', 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('222222', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('234568', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('654321', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('852369', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', null, 'hello!', 'new clerk');
INSERT INTO `clerk` VALUES ('987654', '4QrcOUm6Wau+VuBX8g+IPg==', '../views/images/head.png', '2018-04-08', '未知', null, 'hello!', 'new clerk');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('533');

-- ----------------------------
-- Table structure for holder_bill
-- ----------------------------
DROP TABLE IF EXISTS `holder_bill`;
CREATE TABLE `holder_bill` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `HOLDER_NAME` varchar(20) NOT NULL,
  `SEX` enum('保密','男','女') NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `MOBILE` varchar(11) NOT NULL,
  `POL_NAME` enum('意外险','健康险','补充医疗险','分红险') NOT NULL,
  `MONEY` decimal(9,2) NOT NULL,
  `BAODAN_NO` varchar(50) NOT NULL,
  `CLERK_ID` int(11) DEFAULT NULL,
  `INFORCE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CLERK_ID` (`CLERK_ID`),
  CONSTRAINT `CLERK_ID` FOREIGN KEY (`CLERK_ID`) REFERENCES `clerk` (`CLERK_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=531 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of holder_bill
-- ----------------------------
INSERT INTO `holder_bill` VALUES ('405', '李一', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264096213', '123456', '2018-04-09 16:54:56');
INSERT INTO `holder_bill` VALUES ('408', '里尔', '女', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264096913', '123456', '2018-04-10 16:54:57');
INSERT INTO `holder_bill` VALUES ('411', '阿斯顿', '男', '1888-12-04', '11111111111', '健康险', '2000.00', 'danhao1523264097652', '123456', '2018-04-11 16:54:58');
INSERT INTO `holder_bill` VALUES ('417', '王交互', '女', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264098854', '123456', '2018-04-13 16:54:59');
INSERT INTO `holder_bill` VALUES ('420', '李赛', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264099487', '123456', '2018-04-14 16:54:59');
INSERT INTO `holder_bill` VALUES ('423', '阿三', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264100110', '123456', '2018-04-11 16:55:00');
INSERT INTO `holder_bill` VALUES ('426', '琪琪', '女', '1888-12-04', '11111111111', '健康险', '2000.00', 'danhao1523264100632', '123456', '2018-04-15 16:55:01');
INSERT INTO `holder_bill` VALUES ('429', '马化腾', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264101197', '123456', '2018-04-15 16:55:01');
INSERT INTO `holder_bill` VALUES ('435', '李建刚', '男', '1888-12-04', '11111111111', '分红险', '2000.00', 'danhao1523264102408', '654321', '2018-04-14 16:55:02');
INSERT INTO `holder_bill` VALUES ('438', '王带', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264102953', '987654', '2018-04-11 16:55:03');
INSERT INTO `holder_bill` VALUES ('441', '赵刚', '女', '1888-12-04', '11111111111', '分红险', '2000.00', 'danhao1523264103503', '654321', '2018-04-10 16:55:04');
INSERT INTO `holder_bill` VALUES ('444', '李玉龙', '男', '1888-12-04', '11111111111', '健康险', '2000.00', 'danhao1523264104058', '654321', '2018-04-13 16:55:04');
INSERT INTO `holder_bill` VALUES ('447', '孙悟空', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264104606', '852369', '2018-04-14 16:55:05');
INSERT INTO `holder_bill` VALUES ('453', '赵新', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264105769', '654321', '2018-04-09 16:55:06');
INSERT INTO `holder_bill` VALUES ('456', '亚索', '男', '1888-12-04', '11111111111', '健康险', '2000.00', 'danhao1523264106277', '987654', '2018-04-15 16:55:06');
INSERT INTO `holder_bill` VALUES ('459', '瑞文', '女', '1888-12-04', '11111111111', '健康险', '2000.00', 'danhao1523264106813', '123456', '2018-04-15 16:55:07');
INSERT INTO `holder_bill` VALUES ('462', '德莱文', '男', '1888-12-04', '11111111111', '健康险', '2000.00', 'danhao1523264107354', '123456', '2018-04-13 16:55:07');
INSERT INTO `holder_bill` VALUES ('465', '诺手', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264107882', '123456', '2018-04-15 16:55:08');
INSERT INTO `holder_bill` VALUES ('468', '阿木木', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264827895', '123456', '2018-04-09 17:07:08');
INSERT INTO `holder_bill` VALUES ('471', '瑞兹', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264829226', '987654', '2018-04-09 17:07:09');
INSERT INTO `holder_bill` VALUES ('474', '阿卡丽', '女', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264829996', '123456', '2018-04-09 17:07:10');
INSERT INTO `holder_bill` VALUES ('477', '劫', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264830728', '123456', '2018-04-09 17:07:11');
INSERT INTO `holder_bill` VALUES ('480', '狮子狗', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264831318', '123456', '2018-04-09 17:07:11');
INSERT INTO `holder_bill` VALUES ('483', '螳螂', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264831998', '123456', '2018-04-09 17:07:12');
INSERT INTO `holder_bill` VALUES ('486', '奥巴马', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264832412', '123456', '2018-04-09 17:07:12');
INSERT INTO `holder_bill` VALUES ('489', '哈士奇', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264832888', '654321', '2018-04-12 17:07:13');
INSERT INTO `holder_bill` VALUES ('492', '杰森', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264833296', '123456', '2018-04-09 17:07:13');
INSERT INTO `holder_bill` VALUES ('495', '阿萨大大', '女', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264834069', '123456', '2018-04-12 17:07:14');
INSERT INTO `holder_bill` VALUES ('498', '若风', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264834783', '123456', '2018-04-11 17:07:15');
INSERT INTO `holder_bill` VALUES ('501', 'faker', '男', '1888-12-04', '11111111111', '补充医疗险', '2000.00', 'danhao1523264835452', '123456', '2018-04-11 17:07:15');
INSERT INTO `holder_bill` VALUES ('504', '卡萨丁', '男', '1888-12-04', '11111111111', '意外险', '2000.00', 'danhao1523264835955', '123456', '2018-04-09 17:07:16');
INSERT INTO `holder_bill` VALUES ('510', 'wanter', '男', '2016-04-12', '18589831214', '意外险', '50000.00', 'danhao1523331394396', '123456', '2018-04-10 11:36:34');
INSERT INTO `holder_bill` VALUES ('513', 'wanter', '男', '2016-04-12', '18589831214', '意外险', '50000.00', 'danhao1523331395351', '123456', '2018-04-10 11:36:35');
INSERT INTO `holder_bill` VALUES ('516', 'wanter', '男', '2016-04-12', '18589831214', '意外险', '50000.00', 'danhao1523331395577', '123456', '2018-04-10 11:36:36');
INSERT INTO `holder_bill` VALUES ('524', '王狗', '男', '2018-04-07', '18376694695', '分红险', '10.00', 'danhao1523348389412', '123456', '2018-04-10 16:19:49');

-- ----------------------------
-- Table structure for insured
-- ----------------------------
DROP TABLE IF EXISTS `insured`;
CREATE TABLE `insured` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `INSURED_NAME` varchar(20) NOT NULL,
  `REL` varchar(20) NOT NULL,
  `BILL_ID` int(11) DEFAULT NULL,
  `LEVEL` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `BILL_ID` (`BILL_ID`),
  CONSTRAINT `BILL_ID` FOREIGN KEY (`BILL_ID`) REFERENCES `holder_bill` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=533 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of insured
-- ----------------------------
INSERT INTO `insured` VALUES ('223', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('224', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('226', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('227', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('229', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('230', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('232', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('233', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('235', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('236', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('238', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('239', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('241', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('242', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('244', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('245', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('247', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('248', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('250', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('251', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('253', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('254', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('256', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('257', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('259', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('260', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('262', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('263', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('265', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('266', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('268', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('269', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('271', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('272', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('274', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('275', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('277', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('278', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('280', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('281', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('283', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('284', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('286', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('287', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('289', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('290', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('292', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('293', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('295', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('296', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('298', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('299', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('301', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('302', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('304', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('305', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('307', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('308', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('310', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('311', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('313', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('314', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('316', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('317', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('319', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('320', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('322', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('323', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('325', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('326', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('328', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('329', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('331', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('332', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('334', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('335', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('337', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('338', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('340', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('341', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('343', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('344', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('388', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('389', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('391', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('392', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('394', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('395', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('397', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('398', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('400', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('401', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('403', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('404', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('406', '李四', '母子', '405', '1');
INSERT INTO `insured` VALUES ('407', '王五', '父子', '405', '2');
INSERT INTO `insured` VALUES ('409', '李四', '母子', '408', '1');
INSERT INTO `insured` VALUES ('410', '王五', '父子', '408', '2');
INSERT INTO `insured` VALUES ('412', '李四', '母子', '411', '1');
INSERT INTO `insured` VALUES ('413', '王五', '父子', '411', '2');
INSERT INTO `insured` VALUES ('415', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('416', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('418', '李四', '母子', '417', '1');
INSERT INTO `insured` VALUES ('419', '王五', '父子', '417', '2');
INSERT INTO `insured` VALUES ('421', '李四', '母子', '420', '1');
INSERT INTO `insured` VALUES ('422', '王五', '父子', '420', '2');
INSERT INTO `insured` VALUES ('424', '王五', '父子', '423', '2');
INSERT INTO `insured` VALUES ('425', '李四', '母子', '423', '1');
INSERT INTO `insured` VALUES ('427', '王五', '父子', '426', '2');
INSERT INTO `insured` VALUES ('428', '李四', '母子', '426', '1');
INSERT INTO `insured` VALUES ('430', '李四', '母子', '429', '1');
INSERT INTO `insured` VALUES ('431', '王五', '父子', '429', '2');
INSERT INTO `insured` VALUES ('433', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('434', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('436', '李四', '母子', '435', '1');
INSERT INTO `insured` VALUES ('437', '王五', '父子', '435', '2');
INSERT INTO `insured` VALUES ('439', '王五', '父子', '438', '2');
INSERT INTO `insured` VALUES ('440', '李四', '母子', '438', '1');
INSERT INTO `insured` VALUES ('442', '王五', '父子', '441', '2');
INSERT INTO `insured` VALUES ('443', '李四', '母子', '441', '1');
INSERT INTO `insured` VALUES ('445', '王五', '父子', '444', '2');
INSERT INTO `insured` VALUES ('446', '李四', '母子', '444', '1');
INSERT INTO `insured` VALUES ('448', '李四', '母子', '447', '1');
INSERT INTO `insured` VALUES ('449', '王五', '父子', '447', '2');
INSERT INTO `insured` VALUES ('451', '王五', '父子', null, '2');
INSERT INTO `insured` VALUES ('452', '李四', '母子', null, '1');
INSERT INTO `insured` VALUES ('454', '王五', '父子', '453', '2');
INSERT INTO `insured` VALUES ('455', '李四', '母子', '453', '1');
INSERT INTO `insured` VALUES ('457', '王五', '父子', '456', '2');
INSERT INTO `insured` VALUES ('458', '李四', '母子', '456', '1');
INSERT INTO `insured` VALUES ('460', '王五', '父子', '459', '2');
INSERT INTO `insured` VALUES ('461', '李四', '母子', '459', '1');
INSERT INTO `insured` VALUES ('463', '李四', '母子', '462', '1');
INSERT INTO `insured` VALUES ('464', '王五', '父子', '462', '2');
INSERT INTO `insured` VALUES ('466', '李四', '母子', '465', '1');
INSERT INTO `insured` VALUES ('467', '王五', '父子', '465', '2');
INSERT INTO `insured` VALUES ('469', '王五', '父子', '468', '2');
INSERT INTO `insured` VALUES ('470', '李四', '母子', '468', '1');
INSERT INTO `insured` VALUES ('472', '王五', '父子', '471', '2');
INSERT INTO `insured` VALUES ('473', '李四', '母子', '471', '1');
INSERT INTO `insured` VALUES ('475', '王五', '父子', '474', '2');
INSERT INTO `insured` VALUES ('476', '李四', '母子', '474', '1');
INSERT INTO `insured` VALUES ('478', '王五', '父子', '477', '2');
INSERT INTO `insured` VALUES ('479', '李四', '母子', '477', '1');
INSERT INTO `insured` VALUES ('481', '王五', '父子', '480', '2');
INSERT INTO `insured` VALUES ('482', '李四', '母子', '480', '1');
INSERT INTO `insured` VALUES ('484', '王五', '父子', '483', '2');
INSERT INTO `insured` VALUES ('485', '李四', '母子', '483', '1');
INSERT INTO `insured` VALUES ('487', '李四', '母子', '486', '1');
INSERT INTO `insured` VALUES ('488', '王五', '父子', '486', '2');
INSERT INTO `insured` VALUES ('490', '王五', '父子', '489', '2');
INSERT INTO `insured` VALUES ('491', '李四', '母子', '489', '1');
INSERT INTO `insured` VALUES ('493', '李四', '母子', '492', '1');
INSERT INTO `insured` VALUES ('494', '王五', '父子', '492', '2');
INSERT INTO `insured` VALUES ('496', '王五', '父子', '495', '2');
INSERT INTO `insured` VALUES ('497', '李四', '母子', '495', '1');
INSERT INTO `insured` VALUES ('499', '李四', '母子', '498', '1');
INSERT INTO `insured` VALUES ('500', '王五', '父子', '498', '2');
INSERT INTO `insured` VALUES ('502', '李四', '母子', '501', '1');
INSERT INTO `insured` VALUES ('503', '王五', '父子', '501', '2');
INSERT INTO `insured` VALUES ('505', '李四', '母子', '504', '1');
INSERT INTO `insured` VALUES ('506', '王五', '父子', '504', '2');
INSERT INTO `insured` VALUES ('511', 'xxx', '母子', '510', '2');
INSERT INTO `insured` VALUES ('512', 'xxx', '父子', '510', '1');
INSERT INTO `insured` VALUES ('514', 'xxx', '母子', '513', '2');
INSERT INTO `insured` VALUES ('515', 'xxx', '父子', '513', '1');
INSERT INTO `insured` VALUES ('517', 'xxx', '母子', '516', '2');
INSERT INTO `insured` VALUES ('518', 'xxx', '父子', '516', '1');
INSERT INTO `insured` VALUES ('520', 'xxx', '母子', null, '2');
INSERT INTO `insured` VALUES ('521', 'xxx', '父子', null, '1');
INSERT INTO `insured` VALUES ('525', '王杰森', '狗子', '524', '2');
INSERT INTO `insured` VALUES ('526', '王狗二', '朋友', '524', '1');
INSERT INTO `insured` VALUES ('528', '王狗二', '朋友', null, '1');
INSERT INTO `insured` VALUES ('529', '王杰森', '狗子', null, '2');
INSERT INTO `insured` VALUES ('531', 'aaa', 'aaa', null, '2');
INSERT INTO `insured` VALUES ('532', 'aaa', 'aaa', null, '1');
