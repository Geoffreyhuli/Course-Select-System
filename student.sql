/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 26/02/2025 23:10:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `semester` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `staff_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`semester`, `course_id`, `staff_id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  INDEX `staff_id`(`staff_id` ASC) USING BTREE,
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`staff_id`) REFERENCES `teacher` (`staff_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('2012-2013冬季', '08305002', '0101', '星期三1-4');
INSERT INTO `class` VALUES ('2012-2013冬季', '08305002', '0102', '星期三1-4');
INSERT INTO `class` VALUES ('2012-2013冬季', '08305002', '0103', '星期三1-4');
INSERT INTO `class` VALUES ('2012-2013冬季', '08305003', '0102', '星期五5-8');
INSERT INTO `class` VALUES ('2012-2013秋季', '08305001', '0103', '星期三5-8');
INSERT INTO `class` VALUES ('2013-2014冬季', '08302001', '0201', '星期一5-8');
INSERT INTO `class` VALUES ('2013-2014秋季', '08305001', '0102', '星期一5-8');
INSERT INTO `class` VALUES ('2013-2014秋季', '08305004', '0101', '星期二1-4');
INSERT INTO `class` VALUES ('25-春季学期', '08301001', '0101', '未知');
INSERT INTO `class` VALUES ('25-春季学期', '08302001', '0101', '未知');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credit` int NOT NULL DEFAULT 4,
  `credit_hours` int NOT NULL DEFAULT 40,
  `dept_id` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `dept_id`(`dept_id` ASC) USING BTREE,
  INDEX `idx2`(`course_name` ASC) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('08301001', '分子物理学', 4, 40, '03');
INSERT INTO `course` VALUES ('08302001', '通信学', 3, 30, '02');
INSERT INTO `course` VALUES ('08305001', '离散数学', 4, 40, '01');
INSERT INTO `course` VALUES ('08305002', '数据库原理', 4, 50, '01');
INSERT INTO `course` VALUES ('08305003', '数据结构', 4, 50, '01');
INSERT INTO `course` VALUES ('08305004', '系统结构', 6, 60, '01');

-- ----------------------------
-- Table structure for course_selection
-- ----------------------------
DROP TABLE IF EXISTS `course_selection`;
CREATE TABLE `course_selection`  (
  `student_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `semester` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `staff_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `normal_score` int NULL DEFAULT NULL,
  `test_score` int NULL DEFAULT NULL,
  `total_score` int NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`, `semester`, `course_id`, `staff_id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  INDEX `staff_id`(`staff_id` ASC) USING BTREE,
  CONSTRAINT `course_selection_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_selection_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_selection_ibfk_3` FOREIGN KEY (`staff_id`) REFERENCES `teacher` (`staff_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_selection_chk_1` CHECK (`normal_score` between 1 and 100),
  CONSTRAINT `course_selection_chk_2` CHECK (`test_score` between 1 and 100),
  CONSTRAINT `course_selection_chk_3` CHECK (`total_score` between 1 and 100)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_selection
-- ----------------------------
INSERT INTO `course_selection` VALUES ('1101', '2012-2013秋季', '08305001', '0103', 69, 60, 64);
INSERT INTO `course_selection` VALUES ('1101', '25-春季学期', '08305002', '0101', 88, 88, 88);
INSERT INTO `course_selection` VALUES ('1101', '25-春季学期', '08305004', '0101', NULL, NULL, NULL);
INSERT INTO `course_selection` VALUES ('1102', '2012-2013冬季', '08305002', '0101', 89, 82, 85);
INSERT INTO `course_selection` VALUES ('1102', '2012-2013秋季', '08305001', '0103', 87, 87, 87);
INSERT INTO `course_selection` VALUES ('1102', '2013-2014秋季', '08305004', '0101', 24, 24, 24);
INSERT INTO `course_selection` VALUES ('1103', '2012-2013冬季', '08305002', '0102', 75, 75, 75);
INSERT INTO `course_selection` VALUES ('1103', '2012-2013冬季', '08305003', '0102', 84, 84, 84);
INSERT INTO `course_selection` VALUES ('1103', '2012-2013秋季', '08305001', '0103', 56, 56, 56);
INSERT INTO `course_selection` VALUES ('1103', '2013-2014秋季', '08305001', '0102', 44, 33, 37);
INSERT INTO `course_selection` VALUES ('1103', '2013-2014秋季', '08305004', '0101', 44, 66, 57);
INSERT INTO `course_selection` VALUES ('1104', '2012-2013秋季', '08305001', '0103', 74, 74, 74);
INSERT INTO `course_selection` VALUES ('1104', '2013-2014冬季', '08302001', '0201', NULL, NULL, NULL);
INSERT INTO `course_selection` VALUES ('1106', '2012-2013冬季', '08305002', '0103', 77, 90, 85);
INSERT INTO `course_selection` VALUES ('1106', '2012-2013秋季', '08305001', '0103', 85, 85, 85);
INSERT INTO `course_selection` VALUES ('1107', '2012-2013冬季', '08305003', '0102', 79, 79, 79);
INSERT INTO `course_selection` VALUES ('1107', '2012-2013秋季', '08305001', '0103', 90, 90, 90);
INSERT INTO `course_selection` VALUES ('1107', '2013-2014秋季', '08305004', '0101', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dept_id` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_code` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('01', '计算机学院', '上大东校区三号楼', '65347567');
INSERT INTO `department` VALUES ('02', '通讯学院', '上大东校区二号楼', '65341234');
INSERT INTO `department` VALUES ('03', '材料学院', '上大东校区四号楼', '65347890');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_of_birth` date NULL DEFAULT NULL,
  `native_place` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mobile_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dept_id` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `idx1`(`dept_id` ASC, `name` DESC) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1101', '李明', '男', '1993-03-06', '上海', '13613005486', '02', '123');
INSERT INTO `student` VALUES ('1102', '刘晓明', '男', '1992-12-08', '安徽', '18913457890', '01', '123');
INSERT INTO `student` VALUES ('1103', '张颖', '女', '1993-01-05', '江苏', '18826490423', '01', '');
INSERT INTO `student` VALUES ('1104', '刘晶晶', '女', '1994-11-06', '上海', '13331934111', '01', '');
INSERT INTO `student` VALUES ('1105', '刘成刚', '男', '1991-06-07', '上海', '18015872567', '01', '');
INSERT INTO `student` VALUES ('1106', '李二丽', '女', '1993-05-04', '江苏', '18107620945', '01', '');
INSERT INTO `student` VALUES ('1107', '张晓峰', '男', '1992-08-16', '浙江', '13912341078', '01', '');
INSERT INTO `student` VALUES ('1201', '火狐', NULL, NULL, NULL, NULL, NULL, '12345');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `staff_id` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date_of_birth` date NULL DEFAULT NULL,
  `professional_ranks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  `dept_id` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`staff_id`) USING BTREE,
  INDEX `dept_id`(`dept_id` ASC) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('0101', '陈迪茂', '男', '1973-03-06', '副教授', 3567.00, '01', '12');
INSERT INTO `teacher` VALUES ('0102', '马小红', '女', '1972-12-08', '讲师', 2845.00, '01', '123');
INSERT INTO `teacher` VALUES ('0103', '吴宝钢', '男', '1980-11-06', '讲师', 2554.00, '01', '123');
INSERT INTO `teacher` VALUES ('0201', '张心颖', '女', '1960-01-05', '教授', 4200.00, '02', '');
INSERT INTO `teacher` VALUES ('0202', 'admin', '男', '2024-11-01', '管理员', 5000.00, '01', '1234');
INSERT INTO `teacher` VALUES ('2212', '尹俊杰', NULL, NULL, NULL, NULL, NULL, '1212');

-- ----------------------------
-- Triggers structure for table course_selection
-- ----------------------------
DROP TRIGGER IF EXISTS `update_total_score`;
delimiter ;;
CREATE TRIGGER `update_total_score` BEFORE UPDATE ON `course_selection` FOR EACH ROW BEGIN
  -- 检查 normal_score 或 test_score 是否被更新
  IF NEW.normal_score IS NOT NULL OR NEW.test_score IS NOT NULL THEN
    -- 计算 total_score
    SET NEW.total_score = ROUND(IFNULL(NEW.normal_score, 0) * 0.4 + IFNULL(NEW.test_score, 0) * 0.6);
  END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
