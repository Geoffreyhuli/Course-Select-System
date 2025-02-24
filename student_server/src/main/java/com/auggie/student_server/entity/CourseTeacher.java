package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/10 16:55
 * @Description: CourseTeacher
 * @Version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("CourseTeacher")
public class CourseTeacher {
    private String semester;   // 学期，对应数据库中的 semester
    private String courseId;   // 课程ID，对应数据库中的 course_id
    private String staffId;    // 教师ID，对应数据库中的 staff_id
    private String classTime;  // 上课时间，对应数据库中的 class_time
}