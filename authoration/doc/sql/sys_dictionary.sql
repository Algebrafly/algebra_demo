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

 Date: 10/10/2020 15:18:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典目录/类型',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典code',
  `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典内容（label）',
  `pattern` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '附加字段',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `app_key` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'default' COMMENT '应用标识',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (1, 'area', 'area_001', '山东省', NULL, NULL, 'default', 0);
INSERT INTO `sys_dictionary` VALUES (2, 'area', 'area_002', '浙江省', NULL, NULL, 'default', 0);
INSERT INTO `sys_dictionary` VALUES (3, 'area', 'area_003', '上海市', NULL, NULL, 'default', 0);
INSERT INTO `sys_dictionary` VALUES (4, 'gender', 'g_001', '男', NULL, NULL, 'default', 0);
INSERT INTO `sys_dictionary` VALUES (5, 'gender', 'g_002', '女', NULL, NULL, 'default', 0);

SET FOREIGN_KEY_CHECKS = 1;
