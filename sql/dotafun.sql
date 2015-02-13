/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : dotafun

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2015-02-13 18:47:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for prem_send_msg
-- ----------------------------
DROP TABLE IF EXISTS `prem_send_msg`;
CREATE TABLE `prem_send_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sender` (`sender`),
  KEY `receiver` (`receiver`),
  CONSTRAINT `prem_send_msg_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user` (`id`),
  CONSTRAINT `prem_send_msg_ibfk_2` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of prem_send_msg
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `id_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
