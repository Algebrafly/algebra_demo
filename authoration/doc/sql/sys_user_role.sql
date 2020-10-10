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

 Date: 10/10/2020 15:24:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `usr_id` char(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` char(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 'user_d6e957c2666d4183a7ea83ab767fc1ba', '1', '2020-07-28 11:24:18');
INSERT INTO `sys_user_role` VALUES (2, '1', '2', NULL);
INSERT INTO `sys_user_role` VALUES (3, '1', '1', NULL);
INSERT INTO `sys_user_role` VALUES (4, 'user_d6e957c2666d4183a7ea83ab767fc1ba', '1', NULL);

SET FOREIGN_KEY_CHECKS = 1;
