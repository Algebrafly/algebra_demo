/*
 Navicat Premium Data Transfer

 Source Server         : zjj.rusi.ink_3306
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : zjj.rusi.ink:3306
 Source Schema         : so_ctf

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 10/10/2020 15:23:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_authorization
-- ----------------------------
DROP TABLE IF EXISTS `sys_authorization`;
CREATE TABLE `sys_authorization`  (
  `auth_id` char(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限ID',
  `auth_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限名称',
  `auth_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限代码',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限描述',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`auth_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_authorization
-- ----------------------------
INSERT INTO `sys_authorization` VALUES ('1', 'administrator', '1000', '超级管理员权限', '2020-07-09 10:48:14', 'admin', NULL);
INSERT INTO `sys_authorization` VALUES ('2', 'user', '1001', '普通用户权限', '2020-07-09 10:48:48', 'admin', NULL);

SET FOREIGN_KEY_CHECKS = 1;
