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

 Date: 10/10/2020 15:24:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `usr_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一主键',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户密码',
  `real_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别（1-男，2-女）',
  `portrait` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `idcard_typ` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `idcard_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号',
  `phone` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `team_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加入的团队ID',
  `school` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校或者单位',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司单位',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `usr_branch` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属机构ID',
  `usr_organization` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门ID',
  `usr_status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户状态',
  `user_typ` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户类型（1-C端用户，2-后台管理者）',
  `usr_super` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级主管ID',
  `special_role_id` int(10) NULL DEFAULT NULL COMMENT '特殊角色ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `login_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`usr_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', NULL, NULL, NULL, NULL, '17865428099', '961819766@qq.com', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, '2020-05-19 17:01:52', NULL, '', NULL);
INSERT INTO `sys_user` VALUES ('user_046d7efb50e349ceb35fd351c149df20', '12', '4297f44b13955235245b2497399d7a93', 'qqq', NULL, '', NULL, NULL, '13397850500', '', '', 'qqq', NULL, 'qqq', NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '2020-08-05 17:43:26', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('user_0877ad88868647bd9e11f8be720fad16', '测试', 'e10adc3949ba59abbe56e057f20f883e', 'aaaaaaasdsadsad', 1, 'https://soctf.oss-cn-hangzhou.aliyuncs.com/SoCTF/2020-09-14/4dc01d1fc180496cba4533e4a90215bd-user_head.jpg', NULL, NULL, '13397850513', '', '', '444', '试试', '6666666', '一样', '天津市/天津市/河西区', NULL, NULL, '1', '1', NULL, NULL, '2020-08-04 22:15:45', NULL, NULL, '啊啊啊');
INSERT INTO `sys_user` VALUES ('user_7847535b908b47a6b48647f986039394', '霜冷长河', '94e8cde4612b3fd390677d42e7b22002', '霜冷长河', NULL, '', NULL, NULL, '18625782276', '', NULL, '安御大学', NULL, '背水一战', NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '2020-09-20 09:15:31', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('user_8cede1a60a1f45548cf1bf8a02c3ae1e', 'zzzzzzzz', 'e10adc3949ba59abbe56e057f20f883e', 'zzzzzz', NULL, '', NULL, NULL, '17864192636', '', NULL, '学校学校', NULL, '喜欢吃肉', NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '2020-09-06 21:33:35', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('user_b834f8914f154e45af90c959818e21c7', '浩哥', 'ffe2095898d95f8357ec91ac24b4a25f', '', NULL, '', NULL, NULL, '18637167117', '', NULL, '鹏驰', NULL, '。。。', NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '2020-10-10 13:12:43', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('user_d6e957c2666d4183a7ea83ab767fc1ba', 'hello', 'e10adc3949ba59abbe56e057f20f883e', 'hello', 2, 'https://soctf.oss-cn-hangzhou.aliyuncs.com/SoCTF/2020-10-04/5b2a165571ff4b7686477ad2a1970970-cc.png', NULL, NULL, '18363995071', 'asd@qq.com', 'TM_f4d1dbfc94124911b1520264c3fccd0f', NULL, NULL, '哦哦哦', NULL, '天津市/天津市/和平区', NULL, NULL, '1', '2', NULL, NULL, '2020-07-28 10:38:14', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('user_e09282cd27ab4639a98894f2d2f7e375', 'jerry', 'f740c5225f4d93a5004145d52246912b', '李兆杰', NULL, '', NULL, NULL, '17865428032', '', 'TM_dab4d1f9bae84f1d93a336947547dc80', '青岛大学', NULL, '挺好的', NULL, NULL, NULL, NULL, '1', '1', NULL, NULL, '2020-09-19 10:16:38', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('user_e4ea34316c514180a857abda9aa2dd43', 'zeal', 'd5fb36981654727c66664578236def4d', 'czl', NULL, '', NULL, NULL, '17854257895', '', NULL, 'CTF', 'asda', 'aa', 'asdasd', '山东省/济南市/历城区', NULL, NULL, '1', '1', NULL, NULL, '2020-09-19 11:11:53', NULL, NULL, 'asdasd');
INSERT INTO `sys_user` VALUES ('yanquanfei', '闫全飞', NULL, NULL, NULL, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598119023233&di=322fa1dbfe1a1b39aa9d0a86ef83c7e7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201707%2F10%2F20170710210234_y3Kf5.jpeg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('yanquanfei2', '闫全飞2', NULL, NULL, NULL, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598119023233&di=322fa1dbfe1a1b39aa9d0a86ef83c7e7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201707%2F10%2F20170710210234_y3Kf5.jpeg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('yanquanfei3', '闫全飞3', NULL, NULL, NULL, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598119023233&di=322fa1dbfe1a1b39aa9d0a86ef83c7e7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201707%2F10%2F20170710210234_y3Kf5.jpeg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('yanquanfei4', '闫全飞4', NULL, NULL, NULL, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598119023233&di=322fa1dbfe1a1b39aa9d0a86ef83c7e7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201707%2F10%2F20170710210234_y3Kf5.jpeg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
