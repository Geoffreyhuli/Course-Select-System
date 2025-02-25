package com.auggie.student_server.controller;

import com.auggie.student_server.entity.Teacher;
import com.auggie.student_server.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    // 添加教师
    @PostMapping("/addTeacher")
    public boolean addTeacher(@RequestBody Teacher teacher) {
        System.out.println("正在保存教师对象：" + teacher);
        return teacherService.save(teacher);
    }

    // 教师登录验证
    @PostMapping("/login")
    public boolean login(@RequestBody Teacher teacher) {
        System.out.println("正在验证教师登录：" + teacher);
        Teacher t = teacherService.findById(teacher.getStaffId());
        System.out.println("数据库教师信息：" + t);
        if (t == null || !t.getPassword().equals(teacher.getPassword())) {
            return false;
        }
        return true;
    }

    // 根据教师ID查询教师信息
    @GetMapping("/findById/{staffId}")
    public Teacher findById(@PathVariable("staffId") String staffId) {
        System.out.println("正在查询教师信息 By id：" + staffId);
        return teacherService.findById(staffId);
    }

    // 根据条件查询教师
    @PostMapping("/findBySearch")
    public List<Teacher> findBySearch(@RequestBody Map<String, String> map) {
        return teacherService.findBySearch(map);
    }

    // 根据教师ID删除教师
    @GetMapping("/deleteById/{staffId}")
    public boolean deleteById(@PathVariable("staffId") String staffId) {
        System.out.println("正在删除教师 staffId：" + staffId);
        return teacherService.deleteById(staffId);
    }

    // 更新教师信息
    @PostMapping("/updateTeacher")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        System.out.println("更新教师信息：" + teacher);
        return teacherService.updateById(teacher);
    }
}