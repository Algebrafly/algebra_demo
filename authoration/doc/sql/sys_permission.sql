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

 Date: 10/10/2020 15:23:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `perm_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `perm_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `perm_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型 0 - 系统接口; 1 - 菜单; 2 - 内置页面; 3 - 按钮; 4 - 数据权限',
  `perm_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端path',
  `redirect_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转url',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标样式',
  `active_menu` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级id',
  `sort_order` int(11) NULL DEFAULT NULL COMMENT '排序优先级',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`perm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'users', '用户管理', '1', '10000', '/users', '/users/index', 'layout', 'iconfont icon-user', NULL, '-1', 1, '2019-09-17 14:13:00');
INSERT INTO `sys_permission` VALUES ('10', 'choice', '选择题管理', '1', '10009', '/choice', NULL, NULL, 'iconfont icon-choose1', NULL, '2', 21, '2020-10-08 21:44:33');
INSERT INTO `sys_permission` VALUES ('11', 'docker', '容器题管理', '1', '10010', '/docker', NULL, NULL, 'iconfont icon-web', NULL, '2', 22, '2020-10-08 21:50:21');
INSERT INTO `sys_permission` VALUES ('12', 'file', '附件题管理', '1', '10011', '/file', NULL, NULL, 'iconfont icon-fujian2', NULL, '2', 23, '2020-10-08 21:53:39');
INSERT INTO `sys_permission` VALUES ('13', 'addchoice', '新增选择题', '2', '10012', NULL, NULL, NULL, NULL, NULL, '10', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('14', 'deletechoice', '删除选择题', '2', '10013', NULL, NULL, NULL, NULL, NULL, '10', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('15', 'editchoice', '编辑选择题', '2', '10014', NULL, NULL, NULL, NULL, NULL, '10', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('16', 'adddocker', '新增容器题', '2', '10015', NULL, NULL, NULL, NULL, NULL, '11', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('17', 'deletedocker', '删除容器题', '2', '10016', NULL, NULL, NULL, NULL, NULL, '11', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('18', 'editdocker', '编辑容器题', '2', '10017', NULL, NULL, NULL, NULL, NULL, '11', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('19', 'addfile', '新增附件题', '2', '10018', NULL, NULL, NULL, NULL, NULL, '12', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('2', 'question', '题目管理', '1', '10001', '/question', '/question/list', 'layout', 'iconfont icon-question', NULL, '-1', 2, '2019-09-17 14:13:00');
INSERT INTO `sys_permission` VALUES ('20', 'deletefile', '删除附件题', '2', '10019', NULL, NULL, NULL, NULL, NULL, '12', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('21', 'editfile', '编辑附件题', '2', '10020', NULL, NULL, NULL, NULL, NULL, '12', 211, '2020-10-08 22:02:48');
INSERT INTO `sys_permission` VALUES ('22', 'adduser', '添加用户', '2', '10021', NULL, NULL, NULL, NULL, NULL, '1', NULL, '2020-10-08 22:17:18');
INSERT INTO `sys_permission` VALUES ('23', 'deleteuser', '删除用户', '2', '10022', NULL, NULL, NULL, NULL, NULL, '1', NULL, '2020-10-08 22:17:21');
INSERT INTO `sys_permission` VALUES ('24', 'edituser', '编辑用户', '2', '10023', NULL, NULL, NULL, NULL, NULL, '1', NULL, '2020-10-08 22:17:26');
INSERT INTO `sys_permission` VALUES ('25', 'banuser', '屏蔽用户', '2', '10024', NULL, NULL, NULL, NULL, NULL, '1', NULL, '2020-10-08 22:17:30');
INSERT INTO `sys_permission` VALUES ('26', 'awarduser', '奖励用户', '2', '10025', NULL, NULL, NULL, NULL, NULL, '1', NULL, '2020-10-08 22:17:34');
INSERT INTO `sys_permission` VALUES ('27', 'userinfo', '个人信息', '1', '10026', '/userinfo', NULL, NULL, 'iconfont icon-profile', NULL, '1', NULL, '2020-10-08 22:17:13');
INSERT INTO `sys_permission` VALUES ('3', 'challenge', '赛事管理', '1', '10002', '/challenge', '/challenge/list', 'layout', 'iconfont icon-challenge', NULL, '-1', 3, '2019-09-17 14:13:00');
INSERT INTO `sys_permission` VALUES ('30', 'challenge-online', '赛事直播', '2', '10029', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:27:13');
INSERT INTO `sys_permission` VALUES ('31', 'add-challenge', '创建竞赛', '2', '10030', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:30:30');
INSERT INTO `sys_permission` VALUES ('32', 'delete-challenge', '删除竞赛', '2', '10031', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:30:34');
INSERT INTO `sys_permission` VALUES ('33', 'edit-challenge', '编辑竞赛', '2', '10032', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:30:36');
INSERT INTO `sys_permission` VALUES ('34', 'challenge-rank', '竞赛排名', '2', '10033', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:30:39');
INSERT INTO `sys_permission` VALUES ('36', 'open-challenge', '发布竞赛', '2', '10035', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:33:31');
INSERT INTO `sys_permission` VALUES ('37', 'ranking-db', '结算成绩', '2', '10036', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:34:43');
INSERT INTO `sys_permission` VALUES ('38', 'dev-challenge', '赛事运维', '2', '10037', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:37:08');
INSERT INTO `sys_permission` VALUES ('39', 'edit-score', '修改成绩', '2', '10038', NULL, NULL, NULL, NULL, NULL, '38', NULL, '2020-10-08 22:39:38');
INSERT INTO `sys_permission` VALUES ('4', 'news-admin', '公告管理', '1', '10040', NULL, NULL, NULL, 'iconfont icon-gonggao', NULL, '-1', NULL, '2020-10-08 22:43:29');
INSERT INTO `sys_permission` VALUES ('40', 'challenge-detail', '竞赛详情', '2', '10039', NULL, NULL, NULL, NULL, NULL, '3', NULL, '2020-10-08 22:41:36');
INSERT INTO `sys_permission` VALUES ('41', 'delete-news', '删除公告', '2', '10042', NULL, NULL, NULL, NULL, NULL, '4', NULL, '2020-10-08 22:30:34');
INSERT INTO `sys_permission` VALUES ('42', 'add-news', '新增公告', '2', '10043', NULL, NULL, NULL, NULL, NULL, '4', NULL, '2020-10-08 22:30:34');
INSERT INTO `sys_permission` VALUES ('43', 'edit-news', '编辑公告', '2', '10044', NULL, NULL, NULL, NULL, NULL, '4', NULL, '2020-10-08 22:30:34');
INSERT INTO `sys_permission` VALUES ('44', 'add-doc', '新增资料', '2', '10045', NULL, NULL, NULL, NULL, NULL, '5', NULL, '2020-10-08 22:49:18');
INSERT INTO `sys_permission` VALUES ('45', 'delete-doc', '删除资料', '2', '10046', NULL, NULL, NULL, NULL, NULL, '5', NULL, '2020-10-08 22:51:59');
INSERT INTO `sys_permission` VALUES ('46', 'edit-doc', '编辑资料', '2', '10047', NULL, NULL, NULL, NULL, NULL, '5', NULL, '2020-10-08 22:52:01');
INSERT INTO `sys_permission` VALUES ('47', 'add-menu', '添加菜单', '2', '10051', NULL, NULL, NULL, NULL, NULL, '6', NULL, '2020-10-08 22:27:13');
INSERT INTO `sys_permission` VALUES ('48', 'delete-menu', '删除菜单', '2', '10052', NULL, NULL, NULL, NULL, NULL, '6', NULL, '2020-10-08 22:30:34');
INSERT INTO `sys_permission` VALUES ('49', 'edit-menu', '编辑菜单', '2', '10053', NULL, NULL, NULL, NULL, NULL, '6', NULL, '2020-10-08 23:16:21');
INSERT INTO `sys_permission` VALUES ('5', 'doc-admin', '资料管理', '1', '10041', NULL, NULL, NULL, 'iconfont icon-ziliao', NULL, '-1', NULL, '2020-10-08 22:44:42');
INSERT INTO `sys_permission` VALUES ('50', 'role-admin', '角色管理', '1', '10054', NULL, NULL, NULL, 'iconfont icon-jiaose', NULL, '-1', NULL, '2020-10-08 23:17:58');
INSERT INTO `sys_permission` VALUES ('51', 'add-role', '添加角色', '2', '10055', NULL, NULL, NULL, NULL, NULL, '50', NULL, '2020-10-08 22:27:13');
INSERT INTO `sys_permission` VALUES ('52', 'delete-role', '删除角色', '2', '10056', NULL, NULL, NULL, NULL, NULL, '50', NULL, '2020-10-08 23:19:56');
INSERT INTO `sys_permission` VALUES ('53', 'edit-role', '编辑角色', '2', '10057', NULL, NULL, NULL, NULL, NULL, '50', NULL, '2020-10-08 23:21:10');
INSERT INTO `sys_permission` VALUES ('55', 'tool-admin', '武器库管理', '1', '10058', NULL, NULL, NULL, 'iconfont icon-ai-tool', NULL, '-1', NULL, '2020-10-08 23:21:55');
INSERT INTO `sys_permission` VALUES ('56', 'add-tool', '增加工具', '2', '10059', NULL, NULL, NULL, NULL, NULL, '55', NULL, '2020-10-08 23:25:18');
INSERT INTO `sys_permission` VALUES ('57', 'delete-tool', '删除工具', '2', '10060', NULL, NULL, NULL, NULL, NULL, '55', NULL, '2020-10-08 23:25:18');
INSERT INTO `sys_permission` VALUES ('58', 'edit-tool', '编辑工具', '2', '10061', NULL, NULL, NULL, NULL, NULL, '55', NULL, '2020-10-08 23:27:41');
INSERT INTO `sys_permission` VALUES ('59', 'ranking', '排行榜', '1', '10062', NULL, NULL, NULL, NULL, NULL, '-1', NULL, '2020-10-08 23:28:22');
INSERT INTO `sys_permission` VALUES ('6', 'menu-admin', '菜单管理', '1', '10048', NULL, NULL, NULL, 'iconfont icon-caidan', NULL, '-1', NULL, '2020-10-08 23:10:53');
INSERT INTO `sys_permission` VALUES ('7', 'org-admin', '机构管理', '1', '10049', NULL, NULL, NULL, NULL, NULL, '-1', NULL, '2020-10-08 23:12:42');
INSERT INTO `sys_permission` VALUES ('8', 'permission', '权限管理', '1', '10050', NULL, NULL, NULL, 'iconfont icon-quanxian1', NULL, '-1', NULL, '2020-10-08 23:13:27');

SET FOREIGN_KEY_CHECKS = 1;
