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

 Date: 10/10/2020 15:23:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  `config_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `config_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `app_key` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'AppKey',
  `deleted` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'sms_send', 'register', 'SMS_195862922', '注册发送短信', 'so_cft', 0);
INSERT INTO `sys_config` VALUES (2, 'sms_send', 'login', 'SMS_196145682', '登录发送短信', 'so_cft', 0);
INSERT INTO `sys_config` VALUES (3, 'sms_send', 'modifyPwd', 'SMS_195872862', '修改密码发送短信', 'so_cft', 0);
INSERT INTO `sys_config` VALUES (4, 'aliyun_acct', 'accessKeyId', '/', '阿里云校验ID', 'so_cft', 0);
INSERT INTO `sys_config` VALUES (5, 'aliyun_acct', 'accessKeySecret', '/', '阿里云校验secret', 'so_cft', 0);
INSERT INTO `sys_config` VALUES (6, 'aliyun_acct', 'url', NULL, NULL, 'so_cft', 0);
INSERT INTO `sys_config` VALUES (7, 'sms_send', 'signName', '米糖', '签名', 'so_cft', 0);

SET FOREIGN_KEY_CHECKS = 1;
