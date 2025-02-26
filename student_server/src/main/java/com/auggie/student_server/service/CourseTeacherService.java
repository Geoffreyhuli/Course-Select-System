package com.auggie.student_server.service;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.entity.CourseTeacher;
import com.auggie.student_server.entity.CourseTeacherInfo;
import com.auggie.student_server.mapper.CourseTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseTeacherService {
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    // 插入课程-教师关联信息
    public boolean insertCourseTeacher(String courseId, String staffId, String semester) {
        return courseTeacherMapper.insertCourseTeacher(courseId, staffId, semester);
    }

    // 查询教师教授的课程
    public List<Course> findMyCourse(String staffId, String semester) {
        return courseTeacherMapper.findMyCourse(staffId, semester);
    }

    // 查询课程-教师关联信息
    public List<CourseTeacherInfo> findCourseTeacherInfo(Map<String, String> map) {
        String staffId = null, courseId = null;
        Integer tFuzzy = null, cFuzzy = null;
        String teacherName = null, courseName = null,classTime=null;
        if (map.containsKey("staffId")) {
            staffId = map.get("staffId");
        }
        if (map.containsKey("courseId")) {
            courseId = map.get("courseId");
        }
        if (map.containsKey("teacherName")) {
            teacherName = map.get("teacherName");
        }
        if (map.containsKey("courseName")) {
            courseName = map.get("courseName");
        }
        if (map.containsKey("classTime")) {
            courseName = map.get("classTime");
        }
        if (map.containsKey("tFuzzy")) {
            tFuzzy = map.get("tFuzzy").equals("true") ? 1 : 0;
        }
        if (map.containsKey("cFuzzy")) {
            cFuzzy = map.get("cFuzzy").equals("true") ? 1 : 0;
        }
        System.out.println("ct 模糊查询：" + map);
        System.out.println(courseTeacherMapper.findCourseTeacherInfo(staffId, teacherName, tFuzzy, courseId, courseName,classTime,cFuzzy));
        return courseTeacherMapper.findCourseTeacherInfo(staffId, teacherName, tFuzzy, courseId, courseName, classTime,cFuzzy);
    }

    // 根据条件查询课程-教师关联信息
    public List<CourseTeacher> findBySearch(String courseId, String staffId, String semester) {
        return courseTeacherMapper.findBySearch(courseId, staffId, semester);
    }

    // 根据条件查询课程-教师关联信息（Map 参数）
    public List<CourseTeacher> findBySearch(Map<String, String> map) {
        String courseId = null;
        String staffId = null;
        String semester = null;

        if (map.containsKey("semester")) {
            semester = map.get("semester");
        }
        if (map.containsKey("staffId")) {
            staffId = map.get("staffId");
        }
        if (map.containsKey("courseId")) {
            courseId = map.get("courseId");
        }

        System.out.println("开课表查询：" + map);
        return courseTeacherMapper.findBySearch(courseId, staffId, semester);
    }

    // 删除课程-教师关联信息
    public boolean deleteById(CourseTeacher courseTeacher) {
        return courseTeacherMapper.deleteById(courseTeacher);
    }
}