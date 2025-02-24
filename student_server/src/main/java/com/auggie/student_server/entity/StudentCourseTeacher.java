package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/10 19:57
 * @Description: StudentCourseTeacher
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("StudentCourseTeacher")
public class StudentCourseTeacher {
    private String studentId;      // 学生ID，对应数据库中的 student_id
    private String courseId;       // 课程ID，对应数据库中的 course_id
    private String staffId;        // 教师ID，对应数据库中的 staff_id
    private String semester;       // 学期，对应数据库中的 semester
    private Integer normalScore;   // 平时成绩，对应数据库中的 normal_score
    private Integer testScore;     // 考试成绩，对应数据库中的 test_score
    private Integer totalScore;   // 总成绩，对应数据库中的 total_score
}