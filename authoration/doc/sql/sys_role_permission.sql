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

 Date: 10/10/2020 15:23:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `perm_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, '1', '1', '2020-05-19 17:02:59');
INSERT INTO `sys_role_permission` VALUES (2, '1', '2', '2020-05-19 17:02:59');
INSERT INTO `sys_role_permission` VALUES (3, '1', '3', '2020-05-19 17:45:40');
INSERT INTO `sys_role_permission` VALUES (4, '1', '4', '2020-05-19 17:45:43');
INSERT INTO `sys_role_permission` VALUES (5, '1', '5', '2020-05-19 17:45:45');
INSERT INTO `sys_role_permission` VALUES (6, '1', '6', '2020-05-19 17:45:48');
INSERT INTO `sys_role_permission` VALUES (7, '1', '7', '2020-05-19 17:45:50');
INSERT INTO `sys_role_permission` VALUES (8, '1', '8', '2020-05-19 17:45:53');
INSERT INTO `sys_role_permission` VALUES (9, '1', '9', '2020-05-19 17:45:56');
INSERT INTO `sys_role_permission` VALUES (12, 'role_12477d08e34d479d890a431b02442ccb', '1', '2020-10-09 00:16:22');
INSERT INTO `sys_role_permission` VALUES (13, 'role_12477d08e34d479d890a431b02442ccb', '22', '2020-10-09 00:16:22');
INSERT INTO `sys_role_permission` VALUES (14, 'role_12477d08e34d479d890a431b02442ccb', '23', '2020-10-09 00:16:22');
INSERT INTO `sys_role_permission` VALUES (15, 'role_12477d08e34d479d890a431b02442ccb', '27', '2020-10-09 00:16:22');
INSERT INTO `sys_role_permission` VALUES (16, 'role_12477d08e34d479d890a431b02442ccb', '3', '2020-10-09 00:16:22');

SET FOREIGN_KEY_CHECKS = 1;
