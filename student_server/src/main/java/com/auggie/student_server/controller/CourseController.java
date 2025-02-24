package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Course;
import com.auggie.student_server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    // 根据条件查询课程
    @PostMapping("/findBySearch")
    public List<Course> findBySearch(@RequestBody Map<String, String> map) {
        return courseService.findBySearch(map);
    }

    // 根据课程ID查询课程
    @GetMapping("/findById/{courseId}")
    public List<Course> findById(@PathVariable String courseId) {
        return courseService.findBySearch(courseId);
    }

    // 保存课程
    @PostMapping("/save")
    public boolean save(@RequestBody Course course) {
        System.out.println("正在保存课程: " + course);
        return courseService.insertCourse(course);
    }

    // 根据课程ID删除课程
    @GetMapping("/deleteById/{courseId}")
    public boolean deleteById(@PathVariable String courseId) {
        System.out.println("正在删除课程 courseId: " + courseId);
        return courseService.deleteById(courseId);
    }

    // 更新课程信息
    @PostMapping("/updateCourse")
    public boolean updateCourse(@RequestBody Course course) {
        System.out.println("正在修改课程: " + course);
        return courseService.updateById(course);
    }
}