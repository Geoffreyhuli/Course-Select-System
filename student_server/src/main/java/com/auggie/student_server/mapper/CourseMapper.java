package com.auggie.student_server.mapper;

import com.auggie.student_server.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper {

    // 根据条件查询课程
    public List<Course> findBySearch(@Param("courseId") String courseId,
                                     @Param("courseName") String courseName,
                                     @Param("fuzzy") Integer fuzzy,
                                     @Param("lowBound") Integer lowBound,
                                     @Param("highBound") Integer highBound);

    // 插入课程
    public boolean insertCourse(@Param("course") Course course);

    // 更新课程信息
    public boolean updateById(@Param("course") Course course);

    // 根据课程ID删除课程
    public boolean deleteById(@Param("courseId") String courseId);
}