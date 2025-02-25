package com.auggie.student_server.mapper;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.entity.CourseTeacher;
import com.auggie.student_server.entity.CourseTeacherInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseTeacherMapper {

    // 插入课程-教师关联信息
    @Insert("INSERT INTO class (course_id, staff_id, semester, class_time) " +
            "VALUES (#{courseId}, #{staffId}, #{semester}, '未知')")
    public boolean insertCourseTeacher(@Param("courseId") String courseId,
                                       @Param("staffId") String staffId,
                                       @Param("semester") String semester);

    // 根据条件查询课程-教师关联信息
    public List<CourseTeacher> findBySearch(@Param("courseId") String courseId,
                                            @Param("staffId") String staffId,
                                            @Param("semester") String semester);

    // 查询教师教授的课程
    public List<Course> findMyCourse(@Param("staffId") String staffId,
                                     @Param("semester") String semester);

    // 查询课程-教师关联信息
    public List<CourseTeacherInfo> findCourseTeacherInfo(@Param("staffId") String staffId,
                                                         @Param("teacherName") String teacherName,
                                                         @Param("tFuzzy") Integer tFuzzy,
                                                         @Param("courseId") String courseId,
                                                         @Param("courseName") String courseName,
                                                         @Param("cFuzzy") Integer cFuzzy);

    // 删除课程-教师关联信息
    @Delete("DELETE FROM class WHERE course_id = #{c.courseId} AND staff_id = #{c.staffId}")
    public boolean deleteById(@Param("c") CourseTeacher courseTeacher);
}