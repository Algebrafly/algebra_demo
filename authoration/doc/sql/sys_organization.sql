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

 Date: 10/10/2020 15:23:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `org_id` char(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '机构ID',
  `org_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构名称',
  `org_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构代码',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构描述',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` char(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('org_5c8132a05dc44ac2b57185da5e183b59', '嗯嗯嗯', '222', '吞吞吐吐', '2020-10-04 16:41:48', 'user_d6e957c2666d4183a7ea83ab767fc1ba', 'uuu333');

SET FOREIGN_KEY_CHECKS = 1;
