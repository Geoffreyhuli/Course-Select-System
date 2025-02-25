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
    private String courseId;
    private String staffId;
    private String courseName;
    private String teacherName;
    private Integer credit;
    private Float normalScore;
    private Float testScore;
    private Float totalScore;
}