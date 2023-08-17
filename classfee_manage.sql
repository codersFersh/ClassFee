/*
 Navicat Premium Data Transfer

 Source Server         : mybatis
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : classfee_manage

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 30/06/2023 09:26:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_classfee
-- ----------------------------
DROP TABLE IF EXISTS `tb_classfee`;
CREATE TABLE `tb_classfee`  (
  `f_id` int NOT NULL AUTO_INCREMENT COMMENT '班费编号',
  `f_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '日期',
  `f_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收取班费目的',
  `g_id` int NOT NULL COMMENT '班级编号',
  `f_gross` double(10, 2) NOT NULL COMMENT '需上交班费总额',
  `f_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2039 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_classfee
-- ----------------------------
INSERT INTO `tb_classfee` VALUES (2001, '2023-06-28 01:27:50', '预收费', 1, 100.00, '已完成');
INSERT INTO `tb_classfee` VALUES (2002, '2023-06-22 13:37:24', '书费', 1, 2000.00, '已完成');
INSERT INTO `tb_classfee` VALUES (2028, '2023-06-28 11:41:41', '烧烤', 1, 1000.00, '已完成');
INSERT INTO `tb_classfee` VALUES (2036, '2023-06-28 22:11:43', '烧', 1, 200.00, '已完成');
INSERT INTO `tb_classfee` VALUES (2038, '2023-06-29 10:42:05', '烧烤一', 1, 1000.00, '进行中');

-- ----------------------------
-- Table structure for tb_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_details`;
CREATE TABLE `tb_details`  (
  `d_id` int NOT NULL AUTO_INCREMENT,
  `d_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `f_id` int NOT NULL,
  `s_id` int NOT NULL,
  `g_id` int NOT NULL,
  `d_money` double(10, 2) NOT NULL,
  PRIMARY KEY (`d_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2041 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_details
-- ----------------------------
INSERT INTO `tb_details` VALUES (2005, '2023-06-21 21:00:07', 2001, 201934, 1, 20.00);
INSERT INTO `tb_details` VALUES (2006, '2023-06-21 21:06:12', 2002, 201934, 1, 400.00);
INSERT INTO `tb_details` VALUES (2007, '2023-06-21 21:09:38', 2002, 201933, 1, 400.00);
INSERT INTO `tb_details` VALUES (2008, '2023-06-21 21:15:41', 2001, 201933, 1, 20.00);
INSERT INTO `tb_details` VALUES (2009, '2023-06-21 21:48:13', 2001, 201935, 1, 20.00);
INSERT INTO `tb_details` VALUES (2011, '2023-06-22 11:06:34', 2001, 201937, 1, 20.00);
INSERT INTO `tb_details` VALUES (2012, '2023-06-21 21:58:17', 2002, 201935, 1, 400.00);
INSERT INTO `tb_details` VALUES (2013, '2023-06-21 22:01:07', 2002, 201936, 1, 400.00);
INSERT INTO `tb_details` VALUES (2016, '2023-06-22 11:09:22', 2001, 201936, 1, 20.00);
INSERT INTO `tb_details` VALUES (2018, '2023-06-22 11:15:16', 2002, 201937, 1, 400.00);
INSERT INTO `tb_details` VALUES (2023, '2023-06-28 11:40:52', 2028, 201933, 1, 200.00);
INSERT INTO `tb_details` VALUES (2024, '2023-06-28 11:40:58', 2028, 201934, 1, 200.00);
INSERT INTO `tb_details` VALUES (2025, '2023-06-28 11:41:04', 2028, 201935, 1, 200.00);
INSERT INTO `tb_details` VALUES (2026, '2023-06-28 11:41:11', 2028, 201936, 1, 200.00);
INSERT INTO `tb_details` VALUES (2027, '2023-06-28 11:41:17', 2028, 201937, 1, 200.00);
INSERT INTO `tb_details` VALUES (2030, '2023-06-28 17:41:17', 2036, 201933, 1, 40.00);
INSERT INTO `tb_details` VALUES (2031, '2023-06-28 17:41:23', 2036, 201934, 1, 40.00);
INSERT INTO `tb_details` VALUES (2032, '2023-06-28 17:41:29', 2036, 201935, 1, 40.00);
INSERT INTO `tb_details` VALUES (2033, '2023-06-28 17:41:35', 2036, 201936, 1, 40.00);
INSERT INTO `tb_details` VALUES (2034, '2023-06-28 17:41:40', 2036, 201937, 1, 40.00);
INSERT INTO `tb_details` VALUES (2036, '2023-06-29 10:42:27', 2038, 201933, 1, 200.00);
INSERT INTO `tb_details` VALUES (2037, '2023-06-29 11:39:44', 2038, 201934, 1, 200.00);
INSERT INTO `tb_details` VALUES (2038, '2023-06-29 11:40:43', 2038, 201935, 1, 200.00);
INSERT INTO `tb_details` VALUES (2039, '2023-06-29 15:40:21', 2038, 201936, 1, 200.00);
INSERT INTO `tb_details` VALUES (2040, '2023-06-29 15:40:28', 2038, 201937, 1, 200.00);

-- ----------------------------
-- Table structure for tb_expend
-- ----------------------------
DROP TABLE IF EXISTS `tb_expend`;
CREATE TABLE `tb_expend`  (
  `e_id` int NOT NULL AUTO_INCREMENT,
  `e_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `e_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `e_gross` double(10, 2) NOT NULL,
  PRIMARY KEY (`e_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2023 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_expend
-- ----------------------------
INSERT INTO `tb_expend` VALUES (1001, '2023-06-21 13:55:36', '烧烤', 1000.00);
INSERT INTO `tb_expend` VALUES (1003, '2023-06-21 10:27:54', '观光', 1000.00);
INSERT INTO `tb_expend` VALUES (2022, '2023-06-28 15:44:28', '烧烤', 1000.00);

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade`  (
  `g_id` int NOT NULL AUTO_INCREMENT,
  `g_classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE,
  INDEX `g_classname`(`g_classname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES (1, '软件工程2201');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, '学生列表', NULL);
INSERT INTO `tb_permission` VALUES (2, '学生新增', NULL);
INSERT INTO `tb_permission` VALUES (3, '学生编辑', NULL);
INSERT INTO `tb_permission` VALUES (4, '学生删除', NULL);
INSERT INTO `tb_permission` VALUES (5, '班费列表', NULL);
INSERT INTO `tb_permission` VALUES (6, '班费新增', NULL);
INSERT INTO `tb_permission` VALUES (7, '班费编辑', NULL);
INSERT INTO `tb_permission` VALUES (8, '班费删除', NULL);
INSERT INTO `tb_permission` VALUES (9, '缴费列表', NULL);
INSERT INTO `tb_permission` VALUES (10, '缴费新增', NULL);
INSERT INTO `tb_permission` VALUES (11, '缴费编辑', NULL);
INSERT INTO `tb_permission` VALUES (12, '缴费删除', NULL);
INSERT INTO `tb_permission` VALUES (13, '支出列表', NULL);
INSERT INTO `tb_permission` VALUES (14, '支出新增', NULL);
INSERT INTO `tb_permission` VALUES (15, '支出编辑', NULL);
INSERT INTO `tb_permission` VALUES (16, '支出删除', NULL);
INSERT INTO `tb_permission` VALUES (17, '查看收支', NULL);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `r_id` int NOT NULL AUTO_INCREMENT,
  `r_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `r_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '学生', '只有查询权限');
INSERT INTO `tb_role` VALUES (2, '生活委员', '增删查改权限');

-- ----------------------------
-- Table structure for tb_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_perm`;
CREATE TABLE `tb_role_perm`  (
  `id` int NOT NULL,
  `role_id` int NULL DEFAULT NULL,
  `perm_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_perm
-- ----------------------------

-- ----------------------------
-- Table structure for tb_stu_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_role`;
CREATE TABLE `tb_stu_role`  (
  `id` int NOT NULL,
  `role_id` int NULL DEFAULT NULL,
  `stu_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stu_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `s_id` int NOT NULL AUTO_INCREMENT,
  `s_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_id` int NULL DEFAULT NULL,
  `r_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `s_phone`) USING BTREE,
  UNIQUE INDEX `s_id`(`s_id`) USING BTREE,
  UNIQUE INDEX `s_phone`(`s_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201953 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (201933, '王五', '女', '13645678901', '123456', 1, 1);
INSERT INTO `tb_student` VALUES (201934, '王五啊', '男', '13645678955', '123455', 1, 1);
INSERT INTO `tb_student` VALUES (201935, '张三', '男', '13645678933', '123456', 1, 1);
INSERT INTO `tb_student` VALUES (201936, '李丽', '女', '13545678901', '123456', 1, 1);
INSERT INTO `tb_student` VALUES (201937, '蔡', '女', '13645678001', '123456', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
