package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/10 18:45
 * @Description: CourseTeacherInfo
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("CourseTeacherInfo")
public class CourseTeacherInfo {
    private String courseId;      // 课程ID，对应数据库中的 course_id
    private String staffId;       // 教师ID，对应数据库中的 staff_id
    private String courseName;    // 课程名称，对应数据库中的 course_name
    private String teacherName;   // 教师姓名，对应数据库中的 name（teacher 表）
    private Integer credit;       // 学分，对应数据库中的 credit
    private Float grade;          // 成绩，对应数据库中的 total_score
}