package com.auggie.student_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Auther: auggie
 * @Date: 2022/2/9 13:29
 * @Description: Course
 * @Version 1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Course")
public class Course {
    private String courseId;      // 课程ID，对应数据库中的 course_id
    private String courseName;    // 课程名称，对应数据库中的 course_name
    private Integer credit;       // 学分，对应数据库中的 credit
    private Integer creditHours;  // 学时，对应数据库中的 credit_hours
    private String deptId;        // 院系ID，对应数据库中的 dept_id
}