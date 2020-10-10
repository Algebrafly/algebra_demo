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

 Date: 10/10/2020 15:23:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_branch
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch`;
CREATE TABLE `sys_branch`  (
  `bran_id` char(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门ID',
  `bran_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门名称',
  `bran_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门编码',
  `org_id` char(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属机构ID',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '部门描述',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`bran_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_branch
-- ----------------------------
INSERT INTO `sys_branch` VALUES ('', '得的', '222333', 'org_4244dc8cabd64c17960c09b333cd26d7', '天天', '2020-10-04 19:09:43', 'user_d6e957c2666d4183a7ea83ab767fc1ba', 'uuu');

SET FOREIGN_KEY_CHECKS = 1;
