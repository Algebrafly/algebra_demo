/*
 Navicat Premium Data Transfer

 Source Server         : 1-local
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 10/10/2020 15:19:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_model_header
-- ----------------------------
DROP TABLE IF EXISTS `sys_model_header`;
CREATE TABLE `sys_model_header`  (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `model_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '模型主键',
  `header_id` int(12) NULL DEFAULT NULL COMMENT 'header列主键',
  `column_index` int(2) NULL DEFAULT NULL COMMENT 'header列下标（相同model下不允许重复）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_model_header
-- ----------------------------
INSERT INTO `sys_model_header` VALUES (1, 'm1', 1, 1);
INSERT INTO `sys_model_header` VALUES (2, 'm1', 2, 2);
INSERT INTO `sys_model_header` VALUES (3, 'm1', 3, 3);
INSERT INTO `sys_model_header` VALUES (4, 'm1', 4, 4);

SET FOREIGN_KEY_CHECKS = 1;
