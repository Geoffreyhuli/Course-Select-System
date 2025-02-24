package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.entity.CourseTeacher;
import com.auggie.student_server.entity.CourseTeacherInfo;
import com.auggie.student_server.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/courseTeacher")
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;

    // 插入课程-教师关联信息
    @GetMapping("/insert/{courseId}/{staffId}/{semester}")
    public boolean insert(@PathVariable String courseId, @PathVariable String staffId, @PathVariable String semester) {
        if (courseTeacherService.findBySearch(courseId, staffId, semester).size() != 0) {
            return false;
        }
        return courseTeacherService.insertCourseTeacher(courseId, staffId, semester);
    }

    // 查询教师教授的课程
    @GetMapping("/findMyCourse/{staffId}/{semester}")
    public List<Course> findMyCourse(@PathVariable String staffId, @PathVariable String semester) {
        System.out.println("查询教师课程：" + staffId + " " + semester);
        return courseTeacherService.findMyCourse(staffId, semester);
    }

    // 查询课程-教师关联信息
    @PostMapping("/findCourseTeacherInfo")
    public List<CourseTeacherInfo> findCourseTeacherInfo(@RequestBody Map<String, String> map) {
        return courseTeacherService.findCourseTeacherInfo(map);
    }

    // 删除课程-教师关联信息
    @PostMapping("/deleteById")
    public boolean deleteById(@RequestBody CourseTeacher courseTeacher) {
        return courseTeacherService.deleteById(courseTeacher);
    }
}