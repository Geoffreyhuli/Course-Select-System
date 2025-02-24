package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/11 11:14
 * @Description: SCTInfo
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("SCTInfo")
public class SCTInfo {
    private String studentId;      // 学生ID，对应数据库中的 student_id
    private String staffId;        // 教师ID，对应数据库中的 staff_id
    private String courseId;       // 课程ID，对应数据库中的 course_id
    private String studentName;    // 学生姓名，对应数据库中的 name（student 表）
    private String teacherName;    // 教师姓名，对应数据库中的 name（teacher 表）
    private String courseName;     // 课程名称，对应数据库中的 course_name
    private Float grade;           // 成绩，对应数据库中的 total_score
    private String semester;       // 学期，对应数据库中的 semester
}