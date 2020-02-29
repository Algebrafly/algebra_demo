/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 29/02/2020 14:03:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_web_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_web_log`;
CREATE TABLE `sys_web_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增log主键',
  `log_date` datetime(0) NULL DEFAULT NULL COMMENT '日志记录时间',
  `log_level` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '日志级别',
  `log_thread` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '线程号',
  `trace_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '其他标记：全局ID',
  `log_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类名',
  `log_method` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法名',
  `log_method_line` int(10) NULL DEFAULT NULL COMMENT '影响行',
  `log_msg` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '打印信息',
  `opr_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人',
  `opr_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作人ID',
  `service_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务模块',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
