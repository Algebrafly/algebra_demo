/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : test_job

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 18/05/2020 12:10:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_account_money
-- ----------------------------
DROP TABLE IF EXISTS `test_account_money`;
CREATE TABLE `test_account_money`  (
  `biz_pk` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务主键',
  `acct_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `acct_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户姓名',
  `change_amt` decimal(10, 2) NULL DEFAULT NULL COMMENT '最近一次变更金额',
  `acct_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '账户余额',
  `trans_remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易备注',
  PRIMARY KEY (`biz_pk`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
