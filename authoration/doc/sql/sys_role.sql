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

 Date: 10/10/2020 15:23:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_level` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色等级',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色代码',
  `org_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构ID',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', NULL, NULL, '1000', 'ctfd', NULL, '2020-05-19 17:42:49', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES ('2', '普通用户', NULL, NULL, '1001', 'ctfd', NULL, '2020-07-09 11:40:19', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES ('role_12477d08e34d479d890a431b02442ccb', 'string', '1', 'string', 'string', 'string', 'string', '2020-10-06 11:54:27', '0', '2020-10-06 11:52:55', '0');

SET FOREIGN_KEY_CHECKS = 1;
