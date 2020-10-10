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

 Date: 10/10/2020 15:19:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_excel_header
-- ----------------------------
DROP TABLE IF EXISTS `sys_excel_header`;
CREATE TABLE `sys_excel_header`  (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'header名称',
  `alias` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'header别名',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '字段类型（1-默认普通字段；2-下拉选）',
  `sort_order` int(6) NULL DEFAULT NULL COMMENT '排序序号',
  `column_width` int(10) NULL DEFAULT NULL COMMENT '列宽',
  `column_color` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列填充颜色',
  `check_none_value` tinyint(1) NULL DEFAULT 0 COMMENT '是否校验空值',
  `example_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '示例值',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_excel_header
-- ----------------------------
INSERT INTO `sys_excel_header` VALUES (1, 'name', '姓名', '1', 1, 30, NULL, 0, '张三', 0);
INSERT INTO `sys_excel_header` VALUES (2, 'gender', '性别', '2', 2, 20, NULL, 0, '男', 0);
INSERT INTO `sys_excel_header` VALUES (3, 'age', '年龄', '1', 3, 30, NULL, 0, '23', 0);
INSERT INTO `sys_excel_header` VALUES (4, 'area', '地区', '2', 4, 50, NULL, 0, '山东省', 0);

SET FOREIGN_KEY_CHECKS = 1;
